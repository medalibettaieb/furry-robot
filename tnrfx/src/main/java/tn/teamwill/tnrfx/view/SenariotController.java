package tn.teamwill.tnrfx.view;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tn.teamwill.tnrfx.model.Senario;
import tn.teamwill.tnrfx.model.UiTestDetails;
import tn.teamwill.tnrfx.util.Utilities;

public class SenariotController extends Application {
	Image imageOk = new Image(getClass().getResourceAsStream("/images/l3.jpeg"));
	Button button = new Button("Accept", new ImageView(imageOk));
	private TableView<Senario> tableSenariot = new TableView<Senario>();
	private TableView<UiTestDetails> tableUiTestDetails = new TableView<UiTestDetails>();
	private final ObservableList<Senario> dataSenariot = FXCollections.observableArrayList();
	private final ObservableList<UiTestDetails> dataUiTestDetails = FXCollections.observableArrayList();
	Senario senario = tableSenariot.getSelectionModel().getSelectedItem();

	public static void main(String[] args) {
		launch(args);
	}


	public void createTableUiTestDetailsView() throws FileNotFoundException, IOException {
		TableColumn startdateCol = new TableColumn("start date");
		startdateCol.setMinWidth(100);
		startdateCol.setCellValueFactory(new PropertyValueFactory<UiTestDetails, String>("startDate"));
		TableColumn enddateCol = new TableColumn("endDateOf");
		enddateCol.setMinWidth(100);
		enddateCol.setCellValueFactory(new PropertyValueFactory<UiTestDetails, String>("endDate"));
		TableColumn resultCol = new TableColumn("resultOf");
		resultCol.setMinWidth(200);
		resultCol.setCellValueFactory(new PropertyValueFactory<UiTestDetails, String>("result"));
		tableUiTestDetails.setItems(dataUiTestDetails);
		tableUiTestDetails.getColumns().addAll(startdateCol, enddateCol, resultCol);

	}

	public void prepareTableSenariot() throws FileNotFoundException, IOException {
		TableColumn nameSenariotColumn = new TableColumn("name");
		nameSenariotColumn.setMinWidth(100);
		nameSenariotColumn.setCellValueFactory(new PropertyValueFactory<Senario, String>("name"));
		tableSenariot.getColumns().addAll(nameSenariotColumn);
		List<Senario> senarios = Utilities.fromJSONtoSenario(Utilities.findIp());
		for (Senario s : senarios) {
			dataSenariot.add(s);
		}
		dataSenariot.add(new Senario("ddd", "dddd"));
		tableSenariot.setItems(dataSenariot);
	}

	public void updateUiTestDetailsData() throws FileNotFoundException, IOException {
		if (senario != null) {
			List<UiTestDetails> uiTestDetails = Utilities.fromJSONtoUiTestDetails(Utilities.findIp(),
					senario.getUuid());
			System.out.println(uiTestDetails.size());
			for (UiTestDetails uu : uiTestDetails) {
				dataUiTestDetails.add(uu);
			}
		} else {
		}

	}

	@Override
	public void start(Stage stage) throws FileNotFoundException, IOException {
		prepareTableSenariot();
		createTableUiTestDetailsView();
		Button lanch = new Button();
		lanch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					System.out.println("ok");
					Utilities.testApplicationUi(senario);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		Button show = new Button("Show Test Details");
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				senario = tableSenariot.getSelectionModel().getSelectedItem();
				if (senario!=null) {
					try {
						updateUiTestDetailsData();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Stage stage = new Stage();
					Scene scene = new Scene(new Group());
					((Group) scene.getRoot()).getChildren().addAll(tableUiTestDetails);
					stage.setTitle("Table View Sample");
					stage.setWidth(450);
					stage.setHeight(500);
					stage.setTitle("UiTestDetails");
					stage.setScene(scene);
					stage.setX(10);
					stage.show();
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					//alert.initOwner(mainApp.getPrimaryStage());
					alert.setTitle("No Selection");
					alert.setHeaderText("No Senario Selected");
					alert.setContentText("Please select a Senario in the table.");
					alert.showAndWait();
				}
			}
		});
		//button.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		button.setMaxWidth(10);
		Scene scene = new Scene(new Group());
		stage.setTitle("Senariot view");
		stage.setWidth(600);
		stage.setHeight(800);
		final Label label = new Label("Senarit");
		label.setFont(new Font("Arial", 20));
		tableSenariot.setEditable(true);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, tableSenariot);
		vbox.getChildren().add(show);
		vbox.getChildren().add(button);
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setScene(scene);
		stage.show();
	}
}