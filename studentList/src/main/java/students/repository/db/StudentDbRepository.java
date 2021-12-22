package students.repository.db;

import students.model.Gender;
import students.model.Student;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDbRepository extends DbRepositoryBase implements StudentRepository {

    private static final String SELECT_STUDENT_SQL =
            "select id, name, areaCode, city, birthDate , gender, xml, html, fxml, comment, languageId from students";
    // TODO: Konstante für Insert, update, delete
    private static final String INSERT_STUDENT_SQL =
            "insert into Students (name, areaCode, city, birthDate , gender, xml, html, fxml, comment, languageId) " +
                    // jedes ? ist ein Platzhalter für einen Parameter
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public StudentDbRepository(String dbURL, String user, String password) {
        super(dbURL, user, password);
    }

    @Override
    public List<Student> selectAll() throws StudentRepositoryException {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            // Statement-Objekt holen
            Statement stmt = conn.createStatement();
            // Daten abrufen
            ResultSet result = stmt.executeQuery(SELECT_STUDENT_SQL);
            // alle Datensätze verarbeiten
            while (result.next()) {
                // ein Student-Objekt erzeugen
                Student student = readStudent(result);

                // in der Liste hinzufügen
                students.add(student);
            }


        } catch (SQLException e) {
            // SQLException hier fangen und Fehler-Info anzeigen
            e.printStackTrace();
            // Fehler unbedingt weiterwerfen
            throw new StudentRepositoryException("Fehler beim Laden der Student*innen", e);
        }

        return students;
    }

    @Override
    public Student selectById(int id) throws StudentRepositoryException {
        // den Students-Datensatz mit der Id laden und daraus ein Student-Objekt erzeugen
        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            // Statement-Objekt holen
            PreparedStatement stmt = conn.prepareStatement(SELECT_STUDENT_SQL + " where id = ?");
            stmt.setInt(1, id);
            // Daten abrufen
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                Student student = readStudent(result);
                return student;
            }else{
                throw new StudentRepositoryException("Student*in mit Id " + id + " wurde nicht gefunden");
            }
        } catch (SQLException e) {
            // SQLException hier fangen und Fehler-Info anzeigen
            e.printStackTrace();
            // Fehler unbedingt weiterwerfen
            throw new StudentRepositoryException("Fehler beim Laden der Student*in", e);
        }

    }

    private Student readStudent(ResultSet result) throws SQLException {
        // ein Student-Objekt erzeugen
        Student student = new Student();
        // aus dem aktuellen Datensatz befüllen
        student.setId(result.getInt("id"));
        student.setName(result.getString("name"));
        student.setAreaCode(result.getInt("areaCode"));
        student.setCity(result.getString("city"));
        // LocalDate: java.sql.Date holen und in LocalDate umwandeln
        student.setBirthDate(result.getDate("birthDate").toLocalDate());
        // Enum: aus dem String in der Spalte gender ermitteln
        student.setGender(Enum.valueOf(Gender.class, result.getString("gender")));
        // bit-werte aus DB: als boolean holen
        student.setHtml(result.getBoolean("html"));
        student.setXml(result.getBoolean("xml"));
        student.setFxml(result.getBoolean("fxml"));

        student.setComment(result.getString("comment"));
        student.setLanguageId(result.getInt("languageId"));
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + student);
        return student;
    }

    @Override
    public int insertStudent(Student student) throws StudentRepositoryException {
        return 0;
    }

    @Override
    public void updateStudent(Student student) throws StudentRepositoryException {

    }

    @Override
    public void deleteStudent(int id) throws StudentRepositoryException {

    }
}
