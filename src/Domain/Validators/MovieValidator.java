package Domain.Validators;

import Domain.Exceptions.MovieExceptions;
import Domain.Movie;

/**
 * Created by Sergiu on 11/2/2016.
 */
public class MovieValidator implements Validator<Movie> {

    @Override
    public void validate(Movie entity) throws MovieExceptions
    {
        String errs = "";
        if((entity.getType() == null) || entity.getType().isEmpty())
        {
           errs+="The type it's not good.\n";
        }

        if((entity.getTitle() == null) || entity.getTitle().isEmpty())
        {
            errs+="The type it's not good.\n";
        }

        if(entity.getYear() == null || entity.getYear() <= 0)
        {
            errs+="The Year must be a natural number greater than zero!\n";
        }
        if(errs != "")
            throw new MovieExceptions(errs);
    }
}
