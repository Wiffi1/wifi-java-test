package students.repository.db;

public class DbRepositoryBase {
    // Informationen für den DB-Zugriff
    protected  final String dbUrl, user, password;
    protected DbRepositoryBase(String dbURL, String user, String password){
        this.dbUrl = dbURL;
        this.user = user;
        this.password = password;
    }

}
