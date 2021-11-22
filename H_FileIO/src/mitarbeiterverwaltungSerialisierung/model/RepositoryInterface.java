package mitarbeiterverwaltungSerialisierung.model;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface<T> {

    public List<T> getAll() throws IOException, ClassNotFoundException;
    public T getById(int id) ;
    public T updateById(int id, T ma) throws IOException;
    public void add(T ma) throws IOException;
    public T removeById(int id) throws IOException;
//    public void remove(int mitarbeiterID);
}
