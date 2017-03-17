package Test;

import Domain.Movie;
import Domain.Validators.RentValidator;
import Domain.Client;
import Domain.Rent;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergiu on 11/7/2016.
 */
public class RentValidatorTest {
    @Test
    public void validate() throws Exception {
        RentValidator rv = new RentValidator();

        Movie f1 = new Movie(1,"a","a",10);
        Client c1 = new Client(2,"b","12345","b");
        Rent r1 = new Rent(1, f1, c1);

        try {
            rv.validate(r1);
        } catch (Exception e)
        {
            assertEquals(e.getMessage(), "The CNP must be composed of 13 digits.\n");
        }
    }

}