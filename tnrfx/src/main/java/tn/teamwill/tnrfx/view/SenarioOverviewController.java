package tn.teamwill.tnrfx.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.teamwill.tnrfx.MainApp;
import tn.teamwill.tnrfx.model.Senario;
import tn.teamwill.tnrfx.model.UiTestDetails;
import tn.teamwill.tnrfx.util.Utilities;

public class SenarioOverviewController {
	private ObservableList<UiTestDetails> uiTestDetailsData = FXCollections.observableArrayList();
	@FXML
	private TableView<Senario> personTable;
	@FXML
	private TableColumn<Senario, String> firstNameColumn;
	@FXML
	private TableColumn<Senario, Boolean> lastNameColumn;

	@FXML
	private TableView<UiTestDetails> uiTestDetailsTable;
	@FXML
	private TableColumn<UiTestDetails, String> startdateColumn;
	@FXML
	private TableColumn<UiTestDetails, String> enddateColumn;
	@FXML
	private TableColumn<UiTestDetails, String> typeColumn;
	@FXML
	private TableColumn<UiTestDetails, Boolean> resultColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private TextField ipLabel;
	@FXML
	private TextField tittel;
	private Senario senario;
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public SenarioOverviewController() {
	}

	@FXML
	private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException {
		UiTestDetails uiTestDetails = new UiTestDetails(true, "remote");
		uiTestDetails.setResultOf(new SimpleBooleanProperty(true));

		uiTestDetailsData.add(uiTestDetails);
		uiTestDetailsData.add(new UiTestDetails(false, "local"));
		
		
		
		senario = personTable.getSelectionModel().getSelectedItem();
		if (senario != null) {
			List<UiTestDetails> uiTestDetails2 = Utilities.fromJSONtoUiTestDetails(Utilities.findIp(),
					senario.getUuid());
			startdateColumn.setCellValueFactory(cellData -> cellData.getValue().getStartDateOf());
			typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeOf());
			resultColumn.setCellValueFactory(cellData -> cellData.getValue().getResultOf());
			
			for (UiTestDetails uu : uiTestDetails2) {
				uu.setStartDateOf(new SimpleStringProperty(uu.getStartDate().toString()));
				System.out.println(uu.getStartDateOf());
			}
			initialize();
		} else {
			System.out.println("nothing to display");
		}



		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(uiTestDetailsTable);
		Scene dialogScene = new Scene(dialogVbox, 600, 700);
		dialog.setScene(dialogScene);
		dialog.show();
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@FXML
	private void initialize() throws FileNotFoundException, IOException {
		tittel.setText(Utilities.findIp());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameFX());

		// lastNameColumn.setCellFactory(column -> {
		// return new TableCell<Senario, Boolean>() {
		// protected void updateItem(Boolean item, boolean empty) {
		// super.updateItem(item, empty);
		// if (item == null || empty) {
		// setText(null);
		// setStyle("");
		// } else {
		// if (item) {
		// setTextFill(Color.CHOCOLATE);
		// setStyle("-fx-background-color: green");
		// } else {
		// setTextFill(Color.BLACK);
		// setStyle("-fx-background-color: red");
		// }
		// }
		// };
		// };
		// });
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		personTable.setItems(mainApp.getSenarioData());
	}

	/**
	 * Called when the user clicks on the delete button.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@FXML
	private void handleLanchTest() throws FileNotFoundException, IOException, InterruptedException {
		senario = personTable.getSelectionModel().getSelectedItem();
		if (!tittel.getText().isEmpty()) {
			String regex = makePartialIPRegex();
			final UnaryOperator<Change> ipAddressFilter = c -> {
				String text = c.getControlNewText();
				if (text.matches(regex)) {
					return c;
				} else {
					return null;
				}
			};
			tittel.setTextFormatter(new TextFormatter<>(ipAddressFilter));
			int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
			if (selectedIndex >= 0) {
				initialize();

				Utilities.testApplicationUi(senario);
				initialize();

			} else {
				// Nothing selected senario.
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setTitle("No Selection");
				alert.setHeaderText("No Senario Selected");
				alert.setContentText("Please select a Senario in the table.");
				alert.showAndWait();
			}
		} else {
			// Nothing selected ip.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No @IP");
			alert.setHeaderText("No @IP Selected");
			alert.setContentText("Please add an @IP ");
			alert.showAndWait();
		}
	}

	@FXML
	private void showTestUiDetails() throws FileNotFoundException, IOException, InterruptedException {
		senario = personTable.getSelectionModel().getSelectedItem();
		if (senario != null) {
			System.out.println(senario.getUuid());
		}

	}

	private String makePartialIPRegex() {
		String partialBlock = "(([01]?[0-9]{0,2})|(2[0-4][0-9])|(25[0-5]))";
		String subsequentPartialBlock = "(\\." + partialBlock + ")";
		String ipAddress = partialBlock + "?" + subsequentPartialBlock + "{0,3}";
		return "^" + ipAddress;
	}

	private List<UiTestDetails> parseUserList() {
		List<UiTestDetails> list = new ArrayList<>();
		list.add(new UiTestDetails(true, "local"));
		list.add(new UiTestDetails(false, "local"));

		return list;
		// parse and construct User datamodel list by looping your ResultSet rs
		// and return the list
	}

	public ObservableList<UiTestDetails> getUiTestDetailsData() {
		return uiTestDetailsData;
	}

	public void setUiTestDetailsData(ObservableList<UiTestDetails> uiTestDetailsData) {
		this.uiTestDetailsData = uiTestDetailsData;
	}

}