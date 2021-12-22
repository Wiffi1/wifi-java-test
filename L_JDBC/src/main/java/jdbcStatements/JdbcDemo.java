package jdbcStatements;

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo {
    public static void main(String[] args) {
        String connUrl = "jdbc:mysql://localhost/CourseDB";
        String user = "root";

        // eine Verbindung zur DB holen. Je nach subprotocol (bei uns mysql)
        // wird ein passendes Objekt geliefert. Wenn zu dem subprotocol kein Treiber
        // registriert ist, gibt es eine Exception ("no suitable driver...")
        try (Connection conn = DriverManager.getConnection(connUrl, user, "")) {
            // zu Infozwecken den Klassennamen anzeigen
            System.out.println("DB-Connection erhalten, classname=" + conn.getClass().getName());
            // ein Objekt zum Ausführen von SQL-Befehlen holen
            Statement stmt = conn.createStatement();
            // und eine Query (Abfrage) ausführen
            ResultSet result = stmt.executeQuery("Select id, name, code from languages");
            // Ergebnis der Abfrage Datensatz für Datensatz verarbeiten
            // solange es einen nächsten Datensatz zu lesen gibt
            while (result.next()) {
                // das ResultSet ist auf einen Datensatz positioniert,
                // dei getXXX Methoden liefern Werte aus diesem Datensatz
                // Spalten können mit dem Ordinalwert angegeben werden
                int id = result.getInt(1);
                String name = result.getString(2);

                // Spalten können auch mit dem Spaltennamen angegeben werden
                String code = result.getString("code");

                System.out.printf("%s (Id=%d, Code=%s)\n", name, id, code);
            }
            // alles schließen
            result.close();
            stmt.close();


            // Studenten mit einer bestimmten Language-ID laden
            Scanner input = new Scanner(System.in);
            System.out.println("Studenten mit welcher Sprache laden?");
            int langId = input.nextInt();

            // vorbereitetes Statement, mit Platzhalter-Syntax
            PreparedStatement pstmt = conn.prepareStatement(
                    "select id, name, city, birthDate from students where languageId=?");
            // Wert für Platzhalter angeben
            pstmt.setInt(1, langId);
            // das vorbereitete Statement ausführen
            ResultSet result2 = pstmt.executeQuery();

            while(result2.next()){
                int id = result2.getInt("id");
                String name = result2.getString("name");
                String city = result2.getString("city");
                Date birthdate = result2.getDate("birthDate");
                System.out.printf("%s - %d - %s - %s\n", name, id, city, birthdate);
            }

            // kann über try-with-resources gemacht werden
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
