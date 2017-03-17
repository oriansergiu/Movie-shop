package Domain.Validators;

import Domain.Client;
import Domain.Exceptions.ClientExceptions;

/**
 * Created by Sergiu on 11/2/2016.
 */
public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ClientExceptions {

        String errs = "";
        if (entity.getName() == null || entity.getName().isEmpty())
            errs+="Invalid name.\n";

        if (entity.getCNP() == null || entity.getCNP().length() < 13 || entity.getCNP().length() >13)
            errs+="The CNP must be composed of 13 digits.\n";

        if (entity.getAdress() == null || entity.getAdress().isEmpty())
            errs+="Invalid address.\n";


        if(errs != "")
            throw new ClientExceptions(errs);
    }
}
