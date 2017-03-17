package Domain;

import java.io.Serializable;

/**
 * Created by Sergiu on 11/2/2016.
 */
public class Rent implements HasId<Integer>, Serializable{

    private Integer id;
    private Movie movie;
    private Client client;

    public Rent(Integer id, Movie movie, Client client) {
        this.id = id;
        this.movie = movie;
        this.client = client;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
