package Service;

import Domain.Exceptions.RentExceptions;
import Domain.Movie;
import Domain.Validators.Validator;
import Domain.Client;
import Domain.Rent;
import Repository.Repository;
import Utils.Observable;
import Utils.Observer;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
* Created by Sergiu on 12/15/2016.
*/
public class RentService implements Observable<Rent> {

    private Repository<Rent, Integer> rentRepository;
    private Repository<Movie, Integer> filmRepository;
    private Repository<Client, Integer> clientRepository;
    private Validator<Rent> rentValidator;
    private ArrayList<Observer<Rent>> observers = new ArrayList<>();

    public RentService(Repository<Rent, Integer> rentRepository, Repository<Movie, Integer> filmRepository, Repository<Client, Integer> clientRepository, Validator<Rent> rentValidator) {
        this.rentRepository = rentRepository;
        this.filmRepository = filmRepository;
        this.clientRepository = clientRepository;
        this.rentValidator = rentValidator;
    }


    @Override
    public void addObserver(Observer<Rent> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<Rent> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers)
            o.update(this);
    }


    public void addRent( Movie movie, Client client) throws Exception {

        if( getRents().size() > 0) {
            Integer newId = getRents().get(getRents().size() - 1).getId() + 1;
            Rent rent = new Rent(newId, movie, client);
            rentValidator.validate(rent);
            rentRepository.save(rent);
            notifyObservers();
        } else
        {
            Rent newClient = new Rent(1, movie, client);
            rentValidator.validate(newClient);
            rentRepository.save(newClient);
            notifyObservers();
        }
    }


    public void removeRent(Integer id) throws Exception {
        rentRepository.delete(id);
        notifyObservers();
    }


    public void updateRent(Integer id, Movie movie) throws Exception {
        Rent newRent = rentRepository.find(id);

        for (Rent r: getRents()) {
            if(r.getMovie().getTitle().equals(movie.getTitle()))
                throw new RentExceptions("That movie is already rented by this client");
            else
            {
                newRent.setMovie(movie);
                rentValidator.validate(newRent);
                rentRepository.update(id, newRent);
                notifyObservers();
            }
        }
    }


    public List<Rent> getRents() {

        List<Rent> newList = new ArrayList<Rent>();
        for (Rent x: rentRepository.getAll()) {
            newList.add(x);
        }
        return newList;
    }

    public List<Movie> getMovies() {
        List<Movie> newList = new ArrayList<Movie>();
        for (Movie x: filmRepository.getAll()) {
            newList.add(x);
        }
        return newList;
    }

    public List<Client> getClients() {
        List<Client> newList = new ArrayList<Client>();
        for (Client x: clientRepository.getAll()) {
            newList.add(x);
        }
        return newList;
    }

    public List<Client> filterGenericClient(List<Client> list, Predicate<Client> pr)
    {
        List<Client> rez = new ArrayList<Client>();

        list.forEach(x->{
            if(pr.test(x))
            {
                rez.add(x);
            }
        });

        return rez;
    }

    public List<Movie> filterGenericFilm(List<Movie> list, Predicate<Movie> pr)
    {
        List<Movie> rez = new ArrayList<Movie>();

        list.forEach(x->{
            if(pr.test(x))
            {
                rez.add(x);
            }
        });

        return rez;
    }
}