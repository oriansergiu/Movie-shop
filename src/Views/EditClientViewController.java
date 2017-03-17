package Views;

import Domain.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.ClientService;

/**
 * Created by Sergiu on 12/14/2016.
 */
public class EditClientViewController {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldCNP;
    @FXML
    private TextField textFieldAdress;

    private Integer id;
    private ClientService service;
    Stage dialogStage;
    Client client;
    String button;

    @FXML
    private void initialize() {
    }


    public void setService(Integer id_, ClientService service,  Stage stage, Client c, String button) {
        this.id = id_;
        this.service = service;
        this.dialogStage=stage;
        this.client=c;
        this.button = button;
        if (null != c) {
            setFields(c);
        }
    }

    @FXML
    public void handleSave(){

        try{
            Integer id = this.id;
            String name= textFieldName.getText();
            String CNP=textFieldCNP.getText();
            String adress=textFieldAdress.getText();

            saveClient(id, name, CNP, adress, button);
        }catch (Exception l)
        {
            showErrorMessage("Id must be a natural number greater than 0.");
        }

    }

    private void updateClient(Integer id, String name,String CNP,String adress)
    {
        try {
            this.service.update(id, name, CNP, adress);
        } catch (Exception e) {
            showMessage(Alert.AlertType.INFORMATION,"Updated successfully.","The client was updated.");
        }
        dialogStage.close();
    }


    private void saveClient(Integer id, String name,String CNP,String adress, String button)
    {
        if (button == "Update")
            updateClient(id, name, CNP, adress);
        else
        try {
            service.save(name, CNP, adress);
            clearFields();
            showMessage(Alert.AlertType.INFORMATION,"Added successfully.","The client was successfully added.");
        } catch (Exception e1) {
            MessageAlert.showErrorMessage(dialogStage, e1.getMessage());

        }


    }

    private void clearFields() {
        textFieldName.setText("");
        textFieldCNP.setText("");
        textFieldAdress.setText("");
    }
    private void setFields(Client c)
    {
        textFieldName.setText(c.getName());
        textFieldCNP.setText(c.getCNP());
        textFieldAdress.setText(c.getAdress());
    }

    @FXML
    public void handleCancel(){
        dialogStage.close();
    }

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
        message.setTitle("Error message.");
        message.setContentText(text);
        message.showAndWait();
    }
}
