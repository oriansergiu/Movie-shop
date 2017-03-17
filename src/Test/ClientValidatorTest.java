package Test;

import Domain.Validators.ClientValidator;
import Domain.Client;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergiu on 11/6/2016.
 */
public class ClientValidatorTest {
    @Test
    public void validate() throws Exception {
        ClientValidator val = new ClientValidator();
        Client c1 = new Client(1,"","1234567890123","a");
        Client c2 = new Client(2,"a","123456789012","a");
        Client c3 = new Client(3,"a","1234567890123","");
        try {
            val.validate(c1);
        } catch (Exception e){
            assertEquals(e.getMessage(), "Invalid name.\n");
        }
        try {
            val.validate(c2);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "The CNP must be composed of 13 digits.\n");
        }
        try {
            val.validate(c3);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid address.\n");
        }
    }

}