package Repository;


/**
 * Created by Sergiu on 10/25/2016.
 */
public interface Repository<T, ID> {

    void save(T entity);
    void delete(ID id);
    void update(ID id, T entity);
    T find(ID id);
    long getSize();
    Iterable<T> getAll();

}