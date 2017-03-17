package Views;


import Domain.Movie;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import Service.MovieService;

public class MovieView
{
    BorderPane borderPane;

    TableView<Movie> movieTable = new TableView<>();
    TableColumn<Movie, String> idColumn = new TableColumn<>("Id");
    TableColumn<Movie, String> titleColumn = new TableColumn<>("Title");
    TableColumn<Movie, String> typeColumn = new TableColumn<>("Type");

    TextField textFieldTitle = new TextField();
    TextField textFieldType = new TextField();
    TextField textFieldYear = new TextField();

    Button buttonAdd = new Button("Add");
    Button buttonUpdate = new Button("Update");
    Button buttonDelete = new Button("Delete");
    Button buttonClear = new Button("Clear fields");

    MovieViewController ctrl;

    public MovieView(MovieService service){
        this.ctrl = new MovieViewController(service, this);
        service.addObserver(ctrl);
        initBorderPane();

    }

    public BorderPane getView() {return borderPane;}

    private void initBorderPane(){
        borderPane = new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setLeft(initLeft());
    }

    private Node initTop() {
        AnchorPane anchorPane = new AnchorPane();

        Label l = new Label("Movies");
        l.setFont(new Font(20));
        l.setStyle("-fx-font-weight: bolder");
        AnchorPane.setTopAnchor(l,30d);
        AnchorPane.setLeftAnchor(l,20d);
        anchorPane.getChildren().add(l);
        return anchorPane;
    }

    private Label createLabel(String s, int fontSize, Color c){
        Label l = new Label();
        l.setText(s);
        l.setFont(new Font(15));
        l.setTextFill(c);
        return l;
    }

    private Node initCenter() {
        AnchorPane anchorPane = new AnchorPane();

        GridPane gridFilmDetails = new GridPane();
        gridFilmDetails.setHgap(5);
        gridFilmDetails.setVgap(5);
        anchorPane.setRightAnchor(gridFilmDetails, 40d);
        anchorPane.setTopAnchor(gridFilmDetails,20d);
        ColumnConstraints c = new ColumnConstraints();
        c.setPrefWidth(50);
        gridFilmDetails.getColumnConstraints().add(c);

        Label labelTitle = createLabel("Title:",1,Color.BLACK);
        labelTitle.setStyle("-fx-font-weight: normal");
        Label labelType = createLabel("Type:",1,Color.BLACK);
        labelType.setStyle("-fx-font-weight: normal");
        Label labelYear = createLabel("Year:",1,Color.BLACK);
        labelYear.setStyle("-fx-font-weight: normal");

        gridFilmDetails.add(textFieldTitle,1,1);
        gridFilmDetails.add(textFieldType,1,2);
        gridFilmDetails.add(textFieldYear,1,3);
        gridFilmDetails.add(labelTitle,0,1);
        gridFilmDetails.add(labelType,0,2);
        gridFilmDetails.add(labelYear,0,3);


        anchorPane.getChildren().add(gridFilmDetails);

        VBox hb = new VBox(5, buttonAdd, buttonUpdate, buttonDelete, buttonClear);
        buttonAdd.setOnAction(ctrl::handleAddMovie);
        buttonAdd.setPrefSize(100, 25);
        buttonUpdate.setOnAction(ctrl::handleUpdateMovie);
        buttonUpdate.setPrefSize(100, 25);
        buttonDelete.setOnAction(ctrl::handleDeleteMovie);
        buttonDelete.setPrefSize(100, 25);
        buttonClear.setOnAction(ctrl::handleClearFields);
        buttonClear.setPrefSize(100, 25);

        anchorPane.setBottomAnchor(hb,90d);
        anchorPane.setLeftAnchor(hb,70d);
        anchorPane.getChildren().add(hb);

        return anchorPane;
    }

    private Node initLeft() {
        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setLeftAnchor(movieTable, 20d);
        AnchorPane.setTopAnchor(movieTable,20d);

        movieTable.setMinHeight(50d);
        movieTable.setPrefHeight(300d);
        initTableView();
        anchorPane.getChildren().add(movieTable);
        return anchorPane;
    }

    public void initTableView()
    {
        movieTable.getColumns().add(idColumn);
        movieTable.getColumns().add(titleColumn);
        movieTable.getColumns().add(typeColumn);

        idColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        titleColumn.setPrefWidth(200.0);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("type"));
        typeColumn.setPrefWidth(100.0);

        movieTable.getSelectionModel().selectedItemProperty().addListener(ctrl.changedTableItemListener());

        movieTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ctrl.showMovieDetails(null);


    }
}