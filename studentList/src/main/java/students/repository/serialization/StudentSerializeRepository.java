package students.repository.serialization;

import students.model.Student;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentSerializeRepository implements StudentRepository {

    private String repoPath;

    private List<Student> studentColl;
    private int nextStudentId = 1;

    public StudentSerializeRepository(String path) {
        repoPath = path;

    }

    @Override
    public List<Student> selectAll() throws StudentRepositoryException {
        // Daten laden
        loadData(repoPath);
        // Studenten liste zurückliefern
        return studentColl;

    }

    @Override
    public Student selectById(int id) throws StudentRepositoryException {
        if (studentColl == null) {
            throw new StudentRepositoryException("Das Repository wurde noch nicht geladen");
        }
        // Student-Objekt mit der gesuchten ID aus der Liste holen
        Optional<Student> student = studentColl.stream().filter(s -> s.getId() == id).findFirst();
        // ... und zurückliefern bzw. Fehler werfen falls es nicht existiert
        return student.orElseThrow(
                () -> new StudentRepositoryException("Das Objekt mit Id " + id + " wurde nicht gefunden"));

    }

    @Override
    public int insertStudent(Student student) throws StudentRepositoryException {
        // nächste ID für das Student-Objekt verwenden
        student.setId(nextStudentId++);
        // Objekt im Repository hinzufügen
        studentColl.add(student);
        // und alles speichern
        saveData();
        // ID des neuen Objekts zurückliefern
        return student.getId();

    }

    @Override
    public void updateStudent(Student student) throws StudentRepositoryException {
        // Student-Objekt mit der gesuchten ID aus der Liste holen
        Student orig = selectById(student.getId());
        // wenn nicht (mehr) vorhanden -> Exception
        if (orig == null) {
            new StudentRepositoryException("Das Objekt mit Id " + student.getId() + " wurde nicht gefunden");
        }
        // den Index des Objekts in der Liste suchen
        int index = studentColl.indexOf(orig);
        // und das geänderte Objekt an diesem Index setzen
        studentColl.set(index, student);
        // wieder alles speichern
        saveData();

    }

    @Override
    public void deleteStudent(int id) throws StudentRepositoryException {
        // Student-Objekt mit der gesuchten ID aus der Liste holen
        Student orig = selectById(id);
        // wenn nicht (mehr) vorhanden -> Exception
        if (orig == null) {
            new StudentRepositoryException("Das Objekt mit Id " + id + " wurde nicht gefunden");
        }
        // das Objekt aus der Liste entfernen
        studentColl.remove(orig);
        // und alles speichern
        saveData();

    }

    // StudentCollection aus dem binären File laden oder neue StudentCollection erzeugen
    private void loadData(String repoPath) throws StudentRepositoryException {
        File repoFile = new File(repoPath);
        System.out.println("repoPath: " + repoFile.getAbsolutePath());
        // wenn das File noch nicht existiert
        if (!repoFile.exists()) {
            System.out.println("File " + repoPath + " existiert nicht, erzeuge leere Liste");
            // neue Liste erzeugen
            studentColl = new ArrayList<>();
            nextStudentId = 1;
        } else {
            // sonst: wenn das File existiert Repository aus Binär-File einlesen
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repoFile))) {

                @SuppressWarnings("unchecked")
                List<Student> students = (List<Student>) ois.readObject();
                studentColl = students;
                nextStudentId = ois.readInt();
                System.out.println("nextStudentId: " + nextStudentId);
            }
            catch (IOException | ClassNotFoundException e) {
                throw new StudentRepositoryException("Fehler beim Deserialisieren der Daten aus dem File " + repoPath, e);
            }
        }

    }

    // Save the inventory to the binary file
    private void saveData() throws StudentRepositoryException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repoPath))) {
            System.out.printf("saveData: Anzahl=%d, nextId=%d\n", studentColl.size(), nextStudentId);
            oos.writeObject(studentColl);
            oos.writeInt(nextStudentId);
        } catch (IOException e) {
            throw new StudentRepositoryException("Fehler beim Serialisieren der Daten ins File " + repoPath, e);
        }
    }

}
