package Domain.Validators;


/**
 * Created by Sergiu on 10/5/2016.
 *
 * Valideaza datele de intrare;
 */

public interface Validator<E>
{
    void validate(E entity) throws Exception;
}