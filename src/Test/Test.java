package Test;//package Test;

/**
 * Created by Sergiu on 10/8/2016.
 */
public class Test {

    public void tests()
    {
        ClientTest CT = new ClientTest();
        try {
            CT.getId();
            CT.setId();
            CT.getName();
            CT.setName();
            CT.getCNP();
            CT.setCNP();
            CT.getAdress();
            CT.setAdress();
            CT.equals();
            //CT.toString1();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        MovieTest FlT = new MovieTest();
        try {
            FlT.getId();
            FlT.setId();
            FlT.getNume();
            FlT.getNume();
            FlT.getGen();
            FlT.setGen();
            FlT.getAn();
            FlT.setAnul();
            FlT.equals();
            //FlT.toString1();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        RentTest RT = new RentTest();

        try {
            RT.getId();
            RT.setId();
            RT.getClient();
            RT.setClient();
            RT.getFilm();
            RT.setFilm();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ClientValidatorTest CV = new ClientValidatorTest();

        try {
            CV.validate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        MovieValidatorTest FV = new MovieValidatorTest();

        try {
            FV.validate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        RentValidatorTest RV = new RentValidatorTest();

        try{
            RV.validate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        RepositoryInMemoryTest RMT = new RepositoryInMemoryTest();

        try {
            RMT.save();
            RMT.delete();
            RMT.update();
            RMT.find();
            RMT.getSize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Testele au fost trecute cu succes.");
    }
}
