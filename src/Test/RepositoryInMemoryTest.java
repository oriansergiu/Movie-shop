package Test;

import Domain.Client;
import Domain.Movie;
import org.junit.Test;
import Repository.ClientFileRepository;
import Repository.MovieXMLRepository;

/**
 * Created by Sergiu on 11/7/2016.
 */
public class RepositoryInMemoryTest {

    MovieXMLRepository f1r = new MovieXMLRepository("./src/Test/movies.xml");

    ClientFileRepository c1r = new ClientFileRepository("./src/Test/Clients.txt");
    @Test
    public void save() throws Exception {
        Movie f1 = new Movie(1,"a","a",100);
        Client c1 = new Client(1,"a","1234567890123","a");
        f1r.save(f1);
        c1r.save(c1);
        assert (f1r.getSize() == 1);
        assert (c1r.getSize() == 1);
        f1r.delete(1);
        c1r.delete(1);
    }

    @Test
    public void delete() throws Exception {
        Movie f1 = new Movie(1,"b","b",101);
        Client c1 = new Client(1,"a","1234567890123","a");
        f1r.save(f1);
        c1r.save(c1);

        f1r.delete(1);
        c1r.delete(1);
        assert (f1r.getSize() == 0);
        assert (c1r.getSize() == 0);
    }

    @Test
    public void update() throws Exception {
        Movie f1 = new Movie(1,"c","c",102);
        Client c1 = new Client(1,"a","1234567890123","a");

        Movie f2 = new Movie(2, "d", "d", 201);
        Client c2 = new Client(3,"b","1234567890123", "b");

        f1r.save(f1);
        f1r.update(1, f2);

        c1r.save(c1);
        c1r.update(1, c2);

        assert ("d" == f1r.find(2).getType());
        assert ("b" == c1r.find(3).getName());

        f1r.delete(2);
        c1r.delete(3);
    }

    @Test
    public void find() throws Exception {

        Movie f1 = new Movie(1,"c","c",102);
        Client c1 = new Client(1,"a","1234567890123","a");

        f1r.save(f1);
        c1r.save(c1);

        assert ("c" == f1r.find(1).getType());
        assert ("a" == c1r.find(1).getName());

        f1r.delete(1);
        c1r.delete(1);
    }

    @Test
    public void getSize() throws Exception {

        Movie f1 = new Movie(1,"c","c",102);
        Client c1 = new Client(1,"a","1234567890123","a");

        f1r.save(f1);
        c1r.save(c1);

        assert (f1r.getSize() == 1);
        assert (c1r.getSize() == 1);

        f1r.delete(1);
        c1r.delete(1);

    }

}