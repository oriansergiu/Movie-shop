package Test;

import Domain.Movie;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergiu on 11/6/2016.
 */
public class MovieTest {
    @Test
    public void equals() throws Exception {
        Movie f1 = new Movie(1,"a","a",10);
        Movie f2 = new Movie(1,"a","a",10);
        Movie f3 = new Movie(2,"a2","a2",100);
        assert (f1.equals(f2));
        assert (!f1.equals(f3));
    }

    @Test
    public void getGen() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        assertEquals("a",f1.getType());
        assertEquals("b",f2.getType());
    }

    @Test
    public void getNume() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        assertEquals("a1",f1.getTitle());
        assertEquals("b1",f2.getTitle());
    }

    @Test
    public void getAn() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        assert(10 == f1.getYear());
        assert (1000 == f2.getYear());
    }

    @Test
    public void setGen() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        f1.setType("A1");
        f2.setType("B1");
        assertEquals("A1",f1.getType());
        assertEquals("B1",f2.getType());
    }

    @Test
    public void setNume() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        f1.setTitle("X1");
        f2.setTitle("Y1");
        assertEquals("X1",f1.getTitle());
        assertEquals("Y1",f2.getTitle());
    }

    @Test
    public void setAnul() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        f1.setYear(300);
        f2.setYear(400);
        assert(300 == f1.getYear());
        assert (400 == f2.getYear());
    }

    @Test
    public void getId() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        assert (1 == f1.getId());
        assert (12 == f2.getId());
    }

    @Test
    public void setId() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        f1.setId(30);
        f2.setId(25);
        assert (30 == f1.getId());
        assert (25 == f2.getId());
    }

//    @Test
//    public void toString1() throws Exception {
//        Movie f1 = new Movie(1,"a","a1",10);
//        Movie f2 = new Movie(12,"b","b1",1000);
//        assertEquals(f1.toString(), "Movie{" + "idFilm=" + 1 + ", gen='" + "a" + '\'' + ", nume='" + "a1" + '\'' + ", anul=" + 10 + "}\n");
//        assertEquals(f2.toString(), "Movie{" + "idFilm=" + 12 + ", gen='" + "b" + '\'' + ", nume='" + "b1" + '\'' + ", anul=" + 1000 + "}\n");
//    }

    @Test
    public void filmFileLine() throws Exception {
        Movie f1 = new Movie(1,"a","a1",10);
        Movie f2 = new Movie(12,"b","b1",1000);
        assertEquals(f1.filmFileLine(), f1.getId()+"|"+f1.getType()+"|"+f1.getTitle()+"|"+f1.getYear()+"\n");
        assertEquals(f2.filmFileLine(), f2.getId()+"|"+f2.getType()+"|"+f2.getTitle()+"|"+f2.getYear()+"\n");
    }

}