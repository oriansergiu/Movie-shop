package Service;

import Domain.Movie;
import Domain.Validators.Validator;
import Repository.Repository;
import Utils.Observable;
import Utils.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergiu on 12/5/2016.
 */
public class MovieService implements Observable<Movie> {

    private Repository<Movie, Integer> filmRepo;
    private Validator<Movie> filmVal;
    private List <Observer<Movie>> listObservers = new ArrayList<Observer<Movie>>();

    public MovieService(Repository<Movie, Integer> repo, Validator<Movie> val) {

        this.filmVal = val;
        this.filmRepo = repo;
    }

    public void save(String title,String type,Integer year) throws Exception
    {
        if( get_all().size() > 0) {
            Integer newId = get_all().get(get_all().size() - 1).getId() + 1;
            Movie newMovie = new Movie(newId, title, type, year);
            filmVal.validate(newMovie);
            filmRepo.save(newMovie);
            notifyObservers();

        }
        else
        {
            Movie newMovie = new Movie(1, title, type, year);
            filmVal.validate(newMovie);
            filmRepo.save(newMovie);
            notifyObservers();
        }

    };

    public void delete(Integer id_) {

        filmRepo.delete(id_);
        notifyObservers();

    };

    public void update(Integer id, String title,String type,Integer year) throws Exception {
        Movie newMovie = new Movie(id, title, type, year);
        filmVal.validate(newMovie);
        filmRepo.update(id, newMovie);
        notifyObservers();
    };

    public List<Movie> get_all() {
        List<Movie> newList = new ArrayList<Movie>();
        Iterable<Movie> it_ = filmRepo.getAll();
        for (Movie f: it_) {
            newList.add(f);
        }

        return newList;
//        List<Movie> filme = new ArrayList<Movie>();
//
//        filmRepo.getAll().forEach(x->filme.add((Movie) x));
//
//        return filme;
    };


    @Override
    public void addObserver(Observer<Movie> o) { listObservers.add(o);}

    @Override
    public void removeObserver(Observer<Movie> o) { listObservers.remove(o);}

    @Override
    public void notifyObservers() {
        for (Observer<Movie> o: listObservers) {
            o.update(this);
        }
    }
}
