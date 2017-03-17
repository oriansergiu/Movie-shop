package Test;

import Domain.Client;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergiu on 11/6/2016.
 */
public class ClientTest {
    @Test
    public void getName() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(2,"","1234567890123","b");
        assertEquals("a",c1.getName());
        assertEquals("",c2.getName());
    }

    @Test
    public void setName() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(2,"","1234567890123","b");
        c1.setName("B");
        c2.setName("X");
        assertEquals("B", c1.getName());
        assertEquals("X", c2.getName());
    }

    @Test
    public void getCNP() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(2,"","1234567890145","b");
        assertEquals("1234567890123", c1.getCNP());
        assertEquals("1234567890145", c2.getCNP());
    }

    @Test
    public void setCNP() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(2,"","1234567890123","b");
        c1.setCNP("345");
        c2.setCNP("678");
        assertEquals("345", c1.getCNP());
        assertEquals("678", c2.getCNP());
    }

    @Test
    public void getAdress() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(2,"","1234567890123","b");
        assertEquals("a", c1.getAdress());
        assertEquals("b", c2.getAdress());
    }

    @Test
    public void setAdress() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(2,"","1234567890123","b");
        c1.setAdress("A1");
        c2.setAdress("B1");
        assertEquals("A1", c1.getAdress());
        assertEquals("B1", c2.getAdress());
    }

    @Test
    public void getId() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(2,"","1234567890123","b");
        assert(1 == c1.getId());
        assert(2 == c2.getId());
    }

    @Test
    public void setId() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(2,"","1234567890123","b");
        c1.setId(5);
        c2.setId(6);
        assert(5 == c1.getId());
        assert(6 == c2.getId());
    }

    @Test
    public void equals() throws Exception {
        Client c1 = new Client(1,"a","1234567890123","a");
        Client c2 = new Client(1,"a","1234567890123","a");
        Client c3 = new Client(1,"b","1234567890123","a");
        Client c4 = new Client(1,"a","123456789012","a");
        Client c5 = new Client(1,"a","1234567890123","b");
        assert (c1.equals(c2));
        assert (!c1.equals(c3));
        assert (!c1.equals(c4));
        assert (!c1.equals(c5));
    }

//    @Test
//    public void toString1() throws Exception {
//        Client c1 = new Client(1,"a","1234567890123","a");
//        Client c2 = new Client(2,"","1234567890123","b");
//        assertEquals(c1.toString(), "Client{" + "idClient=" + 1 + ", name='" + "a" + '\'' + ", CNP=" + "1234567890123" + ", adress='" + "a" + '\'' + "}\n");
//        assertEquals(c2.toString(), "Client{" + "idClient=" + 2 + ", name='" + "" + '\'' + ", CNP=" + "1234567890123" + ", adress='" + "b" + '\'' + "}\n");
//    }

}