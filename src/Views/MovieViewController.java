package Views;

import Domain.Exceptions.MovieExceptions;
import Domain.Movie;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import Service.MovieService;
import Utils.Observable;
import Utils.Observer;

public class MovieViewController implements Observer<Movie> {

    private ObservableList<Movie> model;
    private MovieView view;
    MovieService service;

    public MovieViewController(MovieService service, MovieView view)
    {
        this.view = view;
        this.model = FXCollections.observableArrayList(service.get_all());
        view.movieTable.setItems(model);
        this.service = service;
    }

    public ChangeListener<Movie> changedTableItemListener() {
        ChangeListener<Movie> changeListener = (observable, oldValue, newValue) -> {
            showMovieDetails(newValue);
            //view.textFieldID.setEditable(false);
        };

        return changeListener;
    }

    public void showMovieDetails(Movie value)
    {
        if (value == null)
        {
            view.textFieldTitle.setText("");
            view.textFieldType.setText("");
            view.textFieldYear.setText("");
        } else {
            view.textFieldTitle.setText(value.getTitle());
            view.textFieldType.setText(value.getType());
            view.textFieldYear.setText(value.getYear().toString());
        }
    }


    @Override
    public void update(Observable<Movie> observable) {
        MovieService f = (MovieService)observable;
        model.setAll(f.get_all());
    }

    public Movie extractMovie() throws Exception{

        boolean ok = false;

        Integer id = 1;

        if(view.movieTable.getSelectionModel().getSelectedItem()!= null) {
            id = view.movieTable.getSelectionModel().getSelectedItem().getId();
        }
        if(view.textFieldTitle.getText().equals("") || view.textFieldType.getText().equals("") ||view.textFieldYear.getText().equals(""))
            throw new MovieExceptions("The textfields should not be empty.");
        String title = view.textFieldTitle.getText();
        String type = view.textFieldType.getText();
        String year = view.textFieldYear.getText();

            try {
                Integer.parseInt(year);
                ok = true;

            } catch (Exception l) {
                showErrorMessage(l.getMessage());
            }
            if (ok == true) {
                Integer year_ = Integer.parseInt(year);
                Movie f = new Movie(id, title, type, year_);
                return f;
            }

            throw new Exception("Year must be a natural number greater than 0");
        }

    ////////////////////////////////////////////////// ERROR MESSAGE

    static void showMessage(Alert.AlertType type, String header, String text)
    {
        Alert message = new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);
        message.showAndWait();
    }

    static void showErrorMessage(String text)
    {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Error message");
        message.setContentText(text);
        message.showAndWait();
    }

    ////////////////////////////////////////////////// ADD / DEL / UPDATE / CLEAR ALL

    public void handleAddMovie(ActionEvent e)
    {
        Boolean ok = true;
        try{
            Movie f = extractMovie();
            if (f != null)

                System.out.println("1");
                service.save(f.getTitle(), f.getType(), f.getYear());
        }catch (Exception l)
        {
            showErrorMessage(l.getMessage());
            ok = false;
        }
        if (ok == true)
            showMessage(Alert.AlertType.INFORMATION,"Added successfully.","The movie was successfully added.");
        showMovieDetails(null);
    }

    public void handleUpdateMovie(ActionEvent e)
    {
        Boolean ok = true;

        try {
            Movie f = extractMovie();
            if (f != null)
                service.update(f.getId(), f.getTitle(), f.getType(), f.getYear());
        } catch (Exception l){
            showErrorMessage(l.getMessage());
            ok = false;
        }
        if (ok == true)
            showMessage(Alert.AlertType.INFORMATION,"Updated successfully.","The movie was updated.");
        showMovieDetails(null);
    }

    public void handleClearFields(ActionEvent e)
    {
        showMovieDetails(null);
    }

    public void handleDeleteMovie(ActionEvent e)
    {
        try{
            Movie f = extractMovie();
            service.delete(f.getId());
        }catch (Exception l)
        {
            showErrorMessage(l.getMessage());
        }
    }




}

















