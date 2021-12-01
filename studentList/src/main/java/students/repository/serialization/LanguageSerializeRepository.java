package students.repository.serialization;

import students.model.Language;
import students.repository.LanguageRepository;
import students.repository.StudentRepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LanguageSerializeRepository implements LanguageRepository {
    private List<Language> languageColl;

    public LanguageSerializeRepository() {
        languageColl = new ArrayList<>();
        int id = 1;

        languageColl.add(createLanguage(id++, "Deutsch", "de"));
        languageColl.add(createLanguage(id++, "Englisch", "en"));
        languageColl.add(createLanguage(id++, "Italienisch", "it"));
        System.out.printf("LanguageRepository mit %d Sprachen erzeugt\n", languageColl.size() );

    }

    @Override
    public List<Language> selectAll() throws StudentRepositoryException {
        return languageColl;
    }

    @Override
    public Language selectById(int id) throws StudentRepositoryException {

        // Student-Objekt mit der gesuchten ID aus der Liste holen
        Optional<Language> language = languageColl.stream().filter(l -> l.getId() == id).findFirst();
        // ... und zurückliefern bzw. Fehler werfen, falls es nicht existiert
        return language.orElseThrow(
                () -> new StudentRepositoryException("Das Objekt mit Id " + id + " wurde nicht gefunden"));

    }

    private Language createLanguage(int id, String name, String code) {
        Language l = new Language();
        l.setId(id);
        l.setName(name);
        l.setCode(code);
        return l;
    }
}
