package StartApp;

import Domain.Movie;
import Views.ClientViewController;
import Views.MovieView;
import Views.RentViewController;
import Views.RootLayoutController;
import Domain.Validators.ClientValidator;
import Domain.Validators.MovieValidator;
import Domain.Validators.RentValidator;
import Domain.Validators.Validator;
import Domain.Client;
import Domain.Rent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Repository.*;
import Service.ClientService;
import Service.MovieService;
import Service.RentService;
import Test.Test;

import java.io.IOException;

/**
 * Created by Sergiu on 10/5/2016.
 */

public class Main extends Application {

    BorderPane border;
    AnchorPane centerLayout;
    Stage primaryStage;

    ClientService clService;
    MovieService flmService;
    RentService rntService;



    @Override
    public void start(Stage primaryStage) throws Exception {
//
//        Movie f= new Movie(1,"gen","nume",2015);

        Validator<Client> clVal  = new ClientValidator();
        Repository<Client, Integer> clRepo = new ClientFileRepository("./src/Clients.txt");
        Validator<Movie> flVal = new MovieValidator();
        Repository<Movie, Integer> flRepo = new MovieXMLRepository("./src/movies.xml");
        Validator<Rent> rVal = new RentValidator();
        Repository<Rent,Integer> rRepo = new RentRepository("./src/Rents.txt");


        this.flmService = new MovieService(flRepo, flVal);
        this.clService = new ClientService(clRepo, clVal);
        this.rntService = new RentService(rRepo, flRepo,clRepo, rVal);

        this.primaryStage = primaryStage;

        initRootLayout();
        initMovieViewLayout();

    }

    public void initRootLayout() {
        try {
            //Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Views/RootLayout.fxml"));
            border = (BorderPane) loader.load();
            RootLayoutController rootLayoutController = loader.getController();
            rootLayoutController.setMain(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(border);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initMovieViewLayout()
    {
        AnchorPane anchorPane = new AnchorPane();
        MovieView movieView = new MovieView(flmService);
        anchorPane.getChildren().add(movieView.getView());
        border.setCenter(anchorPane);
    }

    public void initClientViewLayout()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Views/ClientView.fxml"));
            centerLayout = (AnchorPane) loader.load();
            border.setCenter(centerLayout);
            ClientViewController viewCtrl = loader.getController();
            viewCtrl.setService(clService);
            clService.addObserver(viewCtrl);
        } catch (IOException e){
            e.printStackTrace();
        }



    }

    public void initRentViewLayout()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/Views/RentView.fxml"));
        try {
            centerLayout = (AnchorPane)loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        border.setCenter(centerLayout);

        RentViewController viewController = loader.getController();
        viewController.setController(rntService);
        rntService.addObserver(viewController);

    }


    public static void main(String[] args)
    {
        Test t = new Test();
        t.tests();
        launch(args);
    }
}
