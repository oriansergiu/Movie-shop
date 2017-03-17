package Test;

import Domain.Validators.MovieValidator;
import Domain.Movie;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergiu on 11/6/2016.
 */
public class MovieValidatorTest {
    @Test
    public void validate() throws Exception {
        MovieValidator val = new MovieValidator();
        Movie f1 = new Movie(1,"","a",100);
        Movie f2 = new Movie(2,"b","",1001);
        Movie f3 = new Movie(3,"c","c",-1);
        try {
            val.validate(f1);
        } catch (Exception e){
            assertEquals(e.getMessage(), "The type it's not good.\n");
        }
        try {
            val.validate(f2);
        } catch (Exception e){
            assertEquals(e.getMessage(), "The type it's not good.\n");
        }
        try {
            val.validate(f3);
        } catch (Exception e){
            assertEquals(e.getMessage(), "The Year must be a natural number greater than zero!\n");
        }
    }

}