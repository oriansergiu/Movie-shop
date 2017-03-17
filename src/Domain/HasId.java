package Domain;

/**
 * Created by Sergiu on 11/2/2016.
 */
public interface HasId<T> {
    T getId();
    void setId(T id);
}
