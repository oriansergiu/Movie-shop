package Domain;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Sergiu on 10/5/2016.
 */

/*
    Clasa Movie reprezinta obiectul film care este compus dintr-un ID, un type, un regizor si year lansarii.
 */
public class Movie implements HasId<Integer>,Serializable, Comparable<Movie> {

    private Integer id;
    private String title;
    private String type;
    private Integer year;

    public Movie(Integer id_, String type, String title, Integer year)
    {
        this.id = id_;
        this.type = type;
        this.title = title;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != null ? !id.equals(movie.id) : movie.id != null) return false;
        if (type != null ? !type.equals(movie.type) : movie.type != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        return year != null ? year.equals(movie.year) : movie.year == null;

    }

    // returneaza genul filmului
    public String getType()
    {
        return type;
    }

    // returneaza numele regizorului filmului
    public String getTitle()
    {
        return title;
    }

    // returneaza year in care a aparut filmul
    public Integer getYear()
    {
        return year;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
//        return "Movie{" +
//                "id=" + id +
//                ", type='" + type + '\'' +
//                ", title='" + title + '\'' +
//                ", year=" + year +
//                "}\n";
        return title;
    }


    public String filmFileLine() {return getId()+"|"+ getType()+"|"+ getTitle()+"|"+ getYear()+"\n";}

    ///////////////////////////////////////////////////// - COMPARATORI

    public static Comparator<Movie> cmpByName = (Movie f1, Movie f2)->f1.getTitle().compareTo(f2.getTitle());
    public static Comparator<Movie> cmpByGen = (Movie f1, Movie f2)->f1.getType().compareTo(f2.getType());
    public static Comparator<Movie> cmpByAn = (Movie f1, Movie f2)->f1.getYear().compareTo(f2.getYear());

    @Override
    public int compareTo(Movie o) {
        //if(this.getTitle().compareTo(o.getTitle()) == -1)
        return 0;
    }
}
