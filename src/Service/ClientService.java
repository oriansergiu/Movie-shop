package Service;


import Domain.Validators.Validator;
import Domain.Client;
import Repository.Repository;
import Utils.Observable;
import Utils.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergiu on 12/8/2016.
 */
public class ClientService implements Observable<Client> {

    private Repository<Client, Integer> clientRepo;
    private Validator<Client> clientVal;
    private List<Observer<Client>> listObservers = new ArrayList<Observer<Client>>();

    public ClientService(Repository<Client, Integer> repo, Validator<Client> val)
    {
        this.clientRepo = repo;
        this.clientVal = val;
    }

    public void save(String name,String CNP,String adress) throws Exception
    {
        if( get_all().size() > 0) {
            Integer newId = get_all().get(get_all().size() - 1).getId() + 1;
            Client newClient = new Client(newId, name, CNP, adress);
            clientVal.validate(newClient);
            clientRepo.save(newClient);
            notifyObservers();
        }
        else
        {
            Client newClient = new Client(1, name, CNP, adress);
            clientVal.validate(newClient);
            clientRepo.save(newClient);
            notifyObservers();
        }
    };

    public void delete(Integer id_) {

        clientRepo.delete(id_);
        notifyObservers();

    };

    public void update(Integer id, String name,String CNP,String adress) throws Exception {
        Client newClient = new Client(id,name,CNP,adress);
        clientVal.validate(newClient);
        clientRepo.update(id, newClient);
        notifyObservers();
    };

    public List<Client> get_all() {
        List<Client> newList = new ArrayList<Client>();
        Iterable<Client> it_ = clientRepo.getAll();
        for (Client c: it_) {
            newList.add(c);
        }

        return newList;
//        List<Client> clienti = new ArrayList<Client>();
//
//        clientRepo.getAll().forEach(x->clienti.add((Client) x));
//
//        return clienti;
    };


    @Override
    public void addObserver(Observer<Client> o) { listObservers.add(o);}

    @Override
    public void removeObserver(Observer<Client> o) { listObservers.remove(o);}

    @Override
    public void notifyObservers() {
        for (Observer<Client> o: listObservers) {
            o.update(this);
        }
    }
}
