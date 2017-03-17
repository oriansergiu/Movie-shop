package Views;

import Domain.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Service.ClientService;
import Utils.Observable;
import Utils.Observer;

import java.io.IOException;

/**
 * Created by Sergiu on 12/8/2016.
 */
public class ClientViewController implements Observer<Client> {

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> adressColumn;

    @FXML
    private TableColumn<Client, String> CNPColumn;

    ClientService service;
    ObservableList<Client> model;

    public ClientViewController() {

    }

    public void setService(ClientService service){
        this.service = service;
        this.model = FXCollections.observableArrayList(service.get_all());
        clientTable.setItems(model);
    }

    @FXML
    private void initialize()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        CNPColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("CNP"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("adress"));
    }

    @Override
    public void update(Observable<Client> observable) {
        ClientService service = (ClientService) observable;
        model.setAll(service.get_all());

    }

    public void showClientEditDialog(Integer id_, Client client, String button) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClientViewController.class.getResource("EditClientView.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Client");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EditClientViewController controller = loader.getController();
            controller.setService(id_, service, dialogStage,client, button);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdate()
    {
        Client c = clientTable.getSelectionModel().getSelectedItem();
        if(c != null)
            showClientEditDialog(c.getId(), c, "Update");
        else
            MessageAlert.showErrorMessage(null, "Select a client.");
    }

    @FXML
    private void handleAdd()
    {
        showClientEditDialog(0,null, "Add");
    }

    @FXML
    private void handleDelete() {
        Client c = clientTable.getSelectionModel().getSelectedItem();
        if(c != null)
            service.delete(c.getId());
    }
}
