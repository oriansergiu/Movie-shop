package Domain.Validators;

import Domain.Rent;

/**
 * Created by Sergiu on 11/2/2016.
 */
public class RentValidator implements Validator<Rent> {
    @Override
    public void validate(Rent entity) throws Exception {
        Validator filmValidator = new MovieValidator();
        Validator clientValidator = new ClientValidator();
        filmValidator.validate(entity.getMovie());
        clientValidator.validate(entity.getClient());
    }
}
