package Domain;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Sergiu on 10/15/2016.
 */

//Client(idClient, nume, adresa etc)

public class Client implements HasId<Integer>, Serializable, Comparable<Client> {

    private Integer idClient;
    private String name;
    private String CNP;
    private String adress;

    public Client(Integer idClient, String name, String CNP, String adress) {
        this.idClient = idClient;
        this.name = name;
        this.CNP = CNP;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public Integer getId()
    {
        return idClient;
    }

    @Override
    public void setId(Integer id)  {this.idClient = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (idClient != client.idClient) return false;
        if (CNP != client.CNP) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        return adress != null ? adress.equals(client.adress) : client.adress == null;

    }


    @Override
    public String toString() {
//        return "Client{" +
//                "idClient=" + idClient +
//                ", name='" + name + '\'' +
//                ", CNP=" + CNP +
//                ", adress='" + adress + '\'' +
//                "}\n";
        return name;
    }

    public static Comparator<Client> cmpByName = (Client c1, Client c2)->c1.getName().compareTo(c2.getName());
    public static Comparator<Client> cmpByCNP = (Client c1, Client c2)->c1.getCNP().compareTo(c2.getCNP());
    public static Comparator<Client> cmpByAdress = (Client c1, Client c2)->c1.getAdress().compareTo(c2.getAdress());

    @Override
    public int compareTo(Client o) {
        return 0;
    }
}
