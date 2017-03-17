package Repository;

import Domain.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergiu on 11/4/2016.
 */
public class ClientFileRepository extends RepositoryInMemory<Client, Integer> {

    private String fileName;

    public ClientFileRepository () {}

    public ClientFileRepository(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }

    private void readFromFile()
    {
        try
        {
                ObjectInputStream loadFile = new ObjectInputStream(new FileInputStream(fileName));
                    List<Client> listObj = (ArrayList<Client>) loadFile.readObject();
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
            ObjectOutputStream writeToFile = new ObjectOutputStream(new FileOutputStream(fileName));
            writeToFile.writeObject(super.entities);

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Client newClient)
    {
        super.save(newClient);
        writeToFile();
    }

    @Override
    public void delete(Integer id_)
    {
        super.delete(id_);
        writeToFile();
    }

    @Override
    public void update(Integer id_, Client newClient)
    {
        super.update(id_, newClient);
        writeToFile();
    }

    @Override
    public Client find(Integer id_)
    {
        return super.find(id_);
    }

    @Override
    public Iterable<Client> getAll() {return super.entities;}
}
