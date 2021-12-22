package students.repository.db;

import students.model.Language;
import students.repository.LanguageRepository;
import students.repository.StudentRepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageDbRepository extends DbRepositoryBase implements LanguageRepository {
    private static final String SELECT_LANGUAGE_SQL =
            "select id, name, code from languages";

    public LanguageDbRepository(String dbURL, String user, String password) {
        super(dbURL, user, password);
    }

    @Override
    public List<Language> selectAll() throws StudentRepositoryException {
        // alle Languages-Datensätze laden
        List<Language> languages = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            // Statement-Objekt holen
            Statement stmt = conn.createStatement();
            // Daten abrufen
            ResultSet result = stmt.executeQuery(SELECT_LANGUAGE_SQL);
            // alle Datensätze verarbeiten
            while (result.next()) {
                Language language = readLanguage(result);
                // in der Liste hinzufügen
                languages.add(language);
            }


        } catch (SQLException e) {
            // SQLException hier fangen und Fehler-Info anzeigen
            e.printStackTrace();
            // Fehler unbedingt weiterwerfen
            throw new StudentRepositoryException("Fehler beim Laden der Sprachen", e);
        }

        return languages;
    }

    @Override
    public Language selectById(int id) throws StudentRepositoryException {
        //  den Languages-Datensatz mit der Id laden und daraus ein Language-Objekt erzeugen
        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            // Statement-Objekt holen
            PreparedStatement stmt = conn.prepareStatement(SELECT_LANGUAGE_SQL + " where id = ?");
            stmt.setInt(1, id);
            // Daten abrufen
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                // ein Language-Objekt erzeugen
                Language language = readLanguage(result);
                return language;
            } else {
                throw new StudentRepositoryException("Sprache mit Id " + id + " wurde nicht gefunden");
            }
        } catch (SQLException e) {
            // SQLException hier fangen und Fehler-Info anzeigen
            e.printStackTrace();
            // Fehler unbedingt weiterwerfen
            throw new StudentRepositoryException("Fehler beim Laden der Sprachen", e);
        }
    }

    private Language readLanguage(ResultSet result) throws SQLException {
        // ein Language-Objekt erzeugen
        Language language = new Language();
        // aus dem aktuellen Datensatz befüllen
        language.setId(result.getInt("id"));
        language.setName(result.getString("name"));
        language.setCode(result.getString("code"));
        return language;
    }


}
