package students.repository;

import students.model.Language;

import java.util.List;

public interface LanguageRepository {

	/**
	 * alle Sprachen selektieren
	 * @return
	 * @throws StudentRepositoryException
	 */
	List<Language> selectAll() throws StudentRepositoryException;

	/**
	 * eine Sprache per ID laden
	 * 
	 * @param id die ID der Sprache
	 * @return
	 */
	Language selectById(int id) throws StudentRepositoryException;

//	int insertLanguage(Language entity) throws StudentRepositoryException;
//
//	void updateLanguage(Language entity) throws StudentRepositoryException;
//
//	void deleteLanguage(int id) throws StudentRepositoryException;

}