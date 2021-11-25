package mitarbeiterverwaltungSerialisierung.model;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface<T> {

    List<T> getAll(boolean doNotLoadList) throws IOException, ClassNotFoundException;
    T getById(int id) ;
    T updateById(int id, T ma) throws IOException;
    void add(T ma) throws IOException;
    T removeById(int id) throws IOException;
}
