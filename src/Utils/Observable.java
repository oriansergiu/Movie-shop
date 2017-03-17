package Utils;

/**
 * Created by Sergiu on 12/7/2016.
 */
public interface Observable<E> {

    void addObserver(Observer<E> o);

    void removeObserver(Observer<E> o);

    void notifyObservers();
}
