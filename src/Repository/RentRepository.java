package Repository;

import Domain.Rent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergiu on 11/2/2016.
 */
public class RentRepository extends RepositoryInMemory<Rent, Integer> {
    private String fileName;

    public RentRepository () {}

    public RentRepository(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }

    private void readFromFile()
    {
        try
        {
            ObjectInputStream loadFile = new ObjectInputStream(new FileInputStream(fileName));
            List<Rent> listObj = (ArrayList<Rent>) loadFile.readObject();
            listObj.forEach(x -> {
                try {
                    super.save(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile()
    {
        try
        {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream(fileName));
            writeFile.writeObject(super.entities);

        } catch (FileNotFoundException l){
            System.out.println(l.getMessage());
        } catch (IOException l) {
            l.printStackTrace();
        }
    }

    @Override
    public void save(Rent newRent)
    {
        super.save(newRent);
        writeToFile();
    }

    @Override
    public void delete(Integer id_)
    {
        super.delete(id_);
        writeToFile();
    }

    @Override
    public void update(Integer id_, Rent newRent)
    {
        super.update(id_, newRent);
        writeToFile();
    }

    @Override
    public Rent find(Integer id_)
    {
        return super.find(id_);
    }

    @Override
    public Iterable<Rent> getAll() {return super.entities;}
}
