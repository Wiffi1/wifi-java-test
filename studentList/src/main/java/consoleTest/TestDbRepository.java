package consoleTest;

import students.model.Language;
import students.model.Student;
import students.repository.LanguageRepository;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;
import students.repository.db.LanguageDbRepository;
import students.repository.db.StudentDbRepository;

import java.util.List;

public class TestDbRepository {
    public static void main(String[] args) {
        StudentRepository studentRepository =  new StudentDbRepository("jdbc:mysql://localhost/CourseDB", "root", "");
        LanguageRepository languageRepository = new LanguageDbRepository("jdbc:mysql://localhost/CourseDB", "root", "");

        try {
            Student s = studentRepository.selectById(1);
            System.out.println("Student mit ID 1: " + s);
        } catch (StudentRepositoryException e) {
            e.printStackTrace();
        }

        try {
            Language l = languageRepository.selectById(2);
            System.out.println("Language mit ID 2: " + l);
        }catch (StudentRepositoryException e) {
            e.printStackTrace();
        }

        try {
            Student s = studentRepository.selectById(100);
            System.out.println("Student mit ID 100: " + s);
        } catch (StudentRepositoryException e) {
            e.printStackTrace();
        }

        try {
            Language l = languageRepository.selectById(10);
            System.out.println("Language mit ID 10: " + l);
        }catch (StudentRepositoryException e) {
            e.printStackTrace();
        }


        try {
            List<Language> languages = languageRepository.selectAll();
            System.out.println("Alle Sprachen: ");
            languages.forEach(System.out::println);
        }catch (StudentRepositoryException e) {
            e.printStackTrace();
        }
    }
}
