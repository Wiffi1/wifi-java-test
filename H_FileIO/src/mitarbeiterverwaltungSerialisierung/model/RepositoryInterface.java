package mitarbeiterverwaltungSerialisierung.model;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface {

    public List<Mitarbeiter> getAll() throws IOException, ClassNotFoundException;
    public Mitarbeiter getById(int id) ;
    public Mitarbeiter updateById(int id, Mitarbeiter ma) throws IOException;
    public void add(Mitarbeiter ma) throws IOException;
    public Mitarbeiter removeById(int id) throws IOException;
//    public void remove(int mitarbeiterID);
}
