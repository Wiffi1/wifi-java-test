package mitarbeiterverwaltungSerialisierung.model;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface {

    public List<Mitarbeiter> getAll() throws IOException, ClassNotFoundException;
    public Mitarbeiter get(int index) ;
    public Mitarbeiter update(int index, Mitarbeiter ma) throws IOException;
    public void add(Mitarbeiter ma) throws IOException;
    public void remove(int index) throws IOException;
}
