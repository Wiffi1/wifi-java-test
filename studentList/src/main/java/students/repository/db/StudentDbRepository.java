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
    // Konstante für Insert, update, delete
    private static final String INSERT_STUDENT_SQL =
            "insert into Students (name, areaCode, city, birthDate , gender, xml, html, fxml, comment, languageId) " +
                    // jedes ? ist ein Platzhalter für einen Parameter
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // für Update die Spalten in der gleichen Reihenfolge angeben wie im Insert (damit setCommonParams funktioniert):
    private static final String UPDATE_STUDENT_SQL =
            "update Students set name = ?, areaCode = ?, city = ?, birthDate  = ?, gender  = ?, " +
                    "xml = ?, html = ?, fxml = ?, comment = ?, languageId = ? " +
                    " where id = ?"; // nur den Datensatz mit der angegebenen Id

    private static final String DELETE_STUDENT_SQL =
            "delete from Students where id = ?";

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
            if (result.next()) {
                Student student = readStudent(result);
                return student;
            } else {
                throw new StudentRepositoryException("Student*in mit Id " + id + " wurde nicht gefunden");
            }
        } catch (SQLException e) {
            // SQLException hier fangen und Fehler-Info anzeigen
            e.printStackTrace();
            // Fehler unbedingt weiterwerfen
            throw new StudentRepositoryException("Fehler beim Laden der Student*in", e);
        }

    }


    @Override
    public int insertStudent(Student student) throws StudentRepositoryException {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            // bei diesem Statement wollen wir nach der Ausführung den Key des neuen Datensatzes abholen
            PreparedStatement stmt = conn.prepareStatement(INSERT_STUDENT_SQL, Statement.RETURN_GENERATED_KEYS);
            // Werte für die Parameter setzen
            setCommonParams(student, stmt);
            int rowsAff = stmt.executeUpdate();
            // das kann eigentlich nicht vorkommen - wenn das Einfügen nicht funktioniert, wird
            // eine SQLException ausgelöst
            if (rowsAff == 0) {
                throw new StudentRepositoryException("Vom Insert sind keine Datensätze betroffen.");
            }
            // den Key holen
            ResultSet keyResult = stmt.getGeneratedKeys();
            if (keyResult.next()) {
                int id = keyResult.getInt(1);
                System.out.println("Student eingefügt, Id=" + id);
                return id;
            } else {
                System.out.println("Provider unterstützt RETURN_GENERATED_KEYS nicht");
                return -1;
            }
        } catch (SQLException e) {
            // SQLException hier fangen und Fehler-Info anzeigen
            e.printStackTrace();
            // Fehler unbedingt weiterwerfen
            throw new StudentRepositoryException("Fehler beim Einfügen der Student*in", e);
        }


    }

    @Override
    public void updateStudent(Student student) throws StudentRepositoryException {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_STUDENT_SQL);
            // die gemeinsamen Parameter setzen
            int maxIndex = setCommonParams(student, stmt);
            // die Id setzen
            stmt.setInt(++maxIndex, student.getId());

            int rowsAff = stmt.executeUpdate();
            // wenn kein Datensatz betroffen ist, hat jemand anderer den Datensatz gelöscht
            if (rowsAff == 0) {
                throw new StudentRepositoryException("Student*in mit Id " + student.getId() + " wurde nicht gefunden");
            }
            // sonst: alles OK, nichts weiter zu tun

        } catch (SQLException e) {
            // SQLException hier fangen und Fehler-Info anzeigen
            e.printStackTrace();
            // Fehler unbedingt weiterwerfen
            throw new StudentRepositoryException("Fehler beim Aktualisieren der Student*in", e);
        }

    }

    @Override
    public void deleteStudent(int id) throws StudentRepositoryException {

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            // wir führen nur 1 Statement aus, daher wäre manuelle Transaktionsteuerung nicht nötig
            // hier für Demozwecke trotzdem verwenden
            // manuelle Transaktionssteuerung aktivieren
            conn.setAutoCommit(false);

            try {
                PreparedStatement stmt = conn.prepareStatement(DELETE_STUDENT_SQL);
                stmt.setInt(1, id);

                int rowsAff = stmt.executeUpdate();
                // wenn kein Datensatz betroffen ist, hat jemand anderer den Datensatz gelöscht
                if (rowsAff == 0) {
                    throw new StudentRepositoryException("Student*in mit Id " + id + " wurde nicht gefunden");
                }

                // hier könnten wir weitere Statements ausführen, die Daten ändern,
                // wenn alle ohne Fehler ausgeführt werden, rufen wir commit auf
                // wenn in einem Statements ein Fehler passiert, wird alles zurückgesetzt

                // sonst: alles OK -> bei manueller Steuerung selbst das commit ausführen
                conn.commit();
            } catch (SQLException e1) {
                // im Fehlerfall die Transaktion zurücksetzen
                conn.rollback();
                throw new StudentRepositoryException("Fehler beim Löschen der Student*in", e1);
            }
        } catch (SQLException e) {
            // SQLException hier fangen und Fehler-Info anzeigen
            e.printStackTrace();
            // Fehler unbedingt weiterwerfen
            throw new StudentRepositoryException("Fehler beim Löschen der Student*in", e);
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
        return student;
    }

    private int setCommonParams(Student student, PreparedStatement stmt) throws SQLException {
        // die Eigenschaften aus dem Student-Objekt im Statement eintragen
        stmt.setString(1, student.getName());
        stmt.setInt(2, student.getAreaCode());
        stmt.setString(3, student.getCity());
        // Umwandlung von LocalDate nach Date muss über Date erfolgen
        //stmt.setDate(4, student.getBirthDate().toDate());
        stmt.setDate(4, Date.valueOf(student.getBirthDate()));
        stmt.setString(5, student.getGender().toString());
        stmt.setBoolean(6, student.isXml());
        stmt.setBoolean(7, student.isHtml());
        stmt.setBoolean(8, student.isFxml());
        stmt.setString(9, student.getComment());
        stmt.setInt(10, student.getLanguageId());
        // den letzten verwendeten Parameter-Index zurückliefern
        return 10;
    }


}
