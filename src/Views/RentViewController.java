package Views;

import Domain.Client;
import Domain.Exceptions.RentExceptions;
import Domain.Movie;
import Domain.Rent;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.RentService;
import Utils.Observable;
import Utils.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Lori on 12/7/2016.
 */
public class RentViewController implements Observer<Rent> {

    @FXML
    TableView<Rent> tableViewRents;
    @FXML
    TableColumn<Rent, String> tableColumnRentId;
    @FXML
    TableColumn<Rent, String> tableColumnRentMovieTitle;
    @FXML
    TableColumn<Rent, String> tableColumnRentClientName;

    @FXML
    TableView<Movie> tableViewMovies;
    @FXML
    TableColumn<Movie, String> tableColumnMovieId;
    @FXML
    TableColumn<Movie, String> tableColumnMovieTitle;

    @FXML
    TableView<Client> tableViewClients;
    @FXML
    TableColumn<Client, String> tableColumnClientId;
    @FXML
    TableColumn<Client, String> tableColumnClientName;

    @FXML
    Button buttonAdd;
    @FXML
    Button buttonRemove;

    @FXML
    private TextField filterFieldFilmGen;

    @FXML
    private TextField filterFieldClientNume;

    private RentService service;
    private ObservableList modelRents;
    private ObservableList modelMovies;
    private ObservableList modelClients;

    public RentViewController()
    {}

    public void setController(RentService service) {
        this.service = service;
        modelRents = FXCollections.observableArrayList(service.getRents());
        tableViewRents.setItems(modelRents);
        modelMovies = FXCollections.observableArrayList(service.getMovies());
        tableViewMovies.setItems(modelMovies);
        modelClients = FXCollections.observableArrayList(service.getClients());
        tableViewClients.setItems(modelClients);
    }

    @Override
    public void update(Observable<Rent> observable) {
        RentService rentService = (RentService) observable;
        modelRents.setAll(rentService.getRents());
    }


    @FXML
    private void initialize() {
        filterFieldFilmGen.textProperty().addListener((observable, oldValue, newValue) -> {
            handleFilterFilmByGen();
        });

        filterFieldClientNume.textProperty().addListener((observable, oldValue, newValue) -> {
            handleFilterClientByName();
        });
        
        
        tableColumnRentId.setCellValueFactory(new PropertyValueFactory<Rent, String>("id"));
        tableColumnRentMovieTitle.setCellValueFactory(new PropertyValueFactory<Rent, String>("movie"));
        tableColumnRentClientName.setCellValueFactory(new PropertyValueFactory<Rent, String>("client"));

        tableColumnMovieId.setCellValueFactory(new PropertyValueFactory<Movie, String>("id"));
        tableColumnMovieTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));

        tableColumnClientId.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
        tableColumnClientName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));

        //tableViewRents.getSelectionModel().selectedItemProperty().addListener();
    }

    private void handleFilterClientByName() {
        ObservableList<Client> lista;
        lista = FXCollections.observableArrayList((ArrayList<Client>)filterClientByName(filterFieldClientNume.getText()));
        tableViewClients.setItems(lista);
    }

    private List<Client> filterClientByName(String text) {

        Predicate<Client> p = x-> x.getName().startsWith(text);


        return service.filterGenericClient(service.getClients(), p);
    }

    private void handleFilterFilmByGen() {
        ObservableList<Movie> lista;
        lista = FXCollections.observableArrayList((ArrayList<Movie>)filterFilmByGen(filterFieldFilmGen.getText()));
        tableViewMovies.setItems(lista);
    }

    private List<Movie> filterFilmByGen(String text) {
        Predicate<Movie> p = x-> x.getType().startsWith(text);


        return service.filterGenericFilm(service.getMovies(), p);
    }


//    private ChangeListener<Rent> changedTableItemListener() {
//        ChangeListener<Rent> changeListener = (observable, oldValue, newValue) -> {
//            if (null != newValue)
//                textFieldId.setText(newValue.getId().toString());
//            else
//                textFieldId.setText("");
//        };
//        return changeListener;
//
//    }


    static void showErrorMessage(String text) {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Error");
        message.setContentText(text);
        message.showAndWait();
    }


    @FXML
    private void handleAdd() {
        Movie movie = tableViewMovies.getSelectionModel().getSelectedItem();
        Client client = tableViewClients.getSelectionModel().getSelectedItem();

        try {
            if (tableViewClients.getSelectionModel().getSelectedItem() == null) throw new Exception("Invalid client");
            if (tableViewMovies.getSelectionModel().getSelectedItem() == null) throw new Exception("Invalid movie");
            service.addRent(movie, client);
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }


    @FXML
    private void handleRemove() {
        try {
            Rent r = tableViewRents.getSelectionModel().getSelectedItem();
            service.removeRent(r.getId());
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }


    @FXML
    private void handleUpdate() {
        try {
            if (tableViewRents.getSelectionModel().getSelectedItem() == null) throw new RentExceptions("Invalid rent.");
            if (tableViewMovies.getSelectionModel().getSelectedItem() == null) throw new RentExceptions("Invalid movie.");
            Integer id = tableViewRents.getSelectionModel().getSelectedItem().getId();
            Movie movie = tableViewMovies.getSelectionModel().getSelectedItem();
            service.updateRent(id, movie);

        } catch (Exception er)
        {
            if(er != null)
                showErrorMessage(er.getMessage());
            else
            showErrorMessage("The client and the movie don't match.");

        }
    }
}
