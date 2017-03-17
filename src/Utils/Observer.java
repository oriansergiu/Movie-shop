package Utils;

/**
 * Created by Sergiu on 12/7/2016.
 */
public interface Observer<E> {
    void update(Observable<E> observable);
}
