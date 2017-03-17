package Test;

import Domain.Client;
import Domain.Movie;
import Domain.Rent;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergiu on 11/6/2016.
 */
public class RentTest {
    @Test
    public void getId() throws Exception {
        Movie f1 = new Movie(1,"a","a",10);
        Client c1 = new Client(1,"a1","1234","a1");
        Movie f2 = new Movie(2,"b","b",11);
        Client c2 = new Client(2,"b1","1234567","b1");
        Rent r1 = new Rent(1,f1,c1);
        Rent r2 = new Rent(2,f2,c2);

        assert (1 == r1.getId());
        assert (2 == r2.getId());
    }

    @Test
    public void setId() throws Exception {
        Movie f1 = new Movie(1,"a","a",10);
        Client c1 = new Client(1,"a1","1234","a1");
        Movie f2 = new Movie(2,"b","b",11);
        Client c2 = new Client(2,"b1","1234567","b1");
        Rent r1 = new Rent(1,f1,c1);
        Rent r2 = new Rent(2,f2,c2);

        r1.setId(4);
        r2.setId(5);
        assert (4 == r1.getId());
        assert (5 == r2.getId());
    }

    @Test
    public void getFilm() throws Exception {
        Movie f1 = new Movie(1,"a","a",10);
        Client c1 = new Client(1,"a1","1234","a1");
        Movie f2 = new Movie(2,"b","b",11);
        Client c2 = new Client(2,"b1","1234567","b1");

        Movie f3 = new Movie(1,"a","a",10);
        Movie f4 = new Movie(2,"b","b",11);

        Rent r1 = new Rent(1,f1,c1);
        Rent r2 = new Rent(2,f2,c2);

        assertEquals(r1.getMovie(), f3);
        assertEquals(r2.getMovie(), f4);
    }

    @Test
    public void setFilm() throws Exception {
        Movie f1 = new Movie(1,"a","a",10);
        Client c1 = new Client(1,"a1","1234","a1");
        Movie f2 = new Movie(2,"b","b",11);
        Client c2 = new Client(2,"b1","1234567","b1");

        Movie f3 = new Movie(1,"a","a",10);
        Movie f4 = new Movie(2,"b","b",11);

        Rent r1 = new Rent(1,f1,c1);
        Rent r2 = new Rent(2,f2,c2);
        r1.setMovie(f4);
        r2.setMovie(f3);

        assertEquals(r1.getMovie(), f4);
        assertEquals(r2.getMovie(), f3);
    }

    @Test
    public void getClient() throws Exception {
        Movie f1 = new Movie(1,"a","a",10);
        Client c1 = new Client(1,"a1","1234","a1");
        Movie f2 = new Movie(2,"b","b",11);
        Client c2 = new Client(2,"b1","1234567","b1");

        Client c3 = new Client(1,"a1","1234","a1");
        Client c4 = new Client(2,"b1","1234567","b1");

        Rent r1 = new Rent(1,f1,c1);
        Rent r2 = new Rent(2,f2,c2);

        assertEquals(r1.getClient(), c3);
        assertEquals(r2.getClient(), c4);
    }

    @Test
    public void setClient() throws Exception {
        Movie f1 = new Movie(1,"a","a",10);
        Client c1 = new Client(1,"a1","1234","a1");
        Movie f2 = new Movie(2,"b","b",11);
        Client c2 = new Client(2,"b1","1234567","b1");

        Client c3 = new Client(1,"a1","1234","a1");
        Client c4 = new Client(2,"b1","1234567","b1");

        Rent r1 = new Rent(1,f1,c1);
        Rent r2 = new Rent(2,f2,c2);
        r1.setClient(c4);
        r2.setClient(c3);

        assertEquals(r1.getClient(), c4);
        assertEquals(r2.getClient(), c3);
    }

}