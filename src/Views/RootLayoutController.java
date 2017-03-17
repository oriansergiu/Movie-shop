package Views;

import StartApp.Main;
import javafx.fxml.FXML;

/**
 * Created by Sergiu on 12/14/2016.
 */
public class RootLayoutController {

    Main mainApp;


    public RootLayoutController() {}

    public void setMain(Main mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleFilm()
    {
        mainApp.initMovieViewLayout();
    }

    @FXML
    private void handleClient()
    {
        mainApp.initClientViewLayout();
    }

    @FXML
    private void handleInchirieri()
    {
        mainApp.initRentViewLayout();
    }
}
