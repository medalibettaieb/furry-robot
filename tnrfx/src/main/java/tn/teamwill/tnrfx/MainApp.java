package tn.teamwill.tnrfx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tn.teamwill.tnrfx.model.Senario;
import tn.teamwill.tnrfx.model.UiTestDetails;
import tn.teamwill.tnrfx.util.Utilities;
import tn.teamwill.tnrfx.view.SenarioOverviewController;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Senario> senarioData = FXCollections.observableArrayList();
	private ObservableList<UiTestDetails> uiTestDetailsData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public MainApp() throws FileNotFoundException, IOException {
		
		List<Senario> list = Utilities.fromJSONtoSenario(Utilities.findIp());
		for (Senario s : list) {
			s.setNameFX(new SimpleStringProperty(s.getName()));
			senarioData.add(s);
		}
		senarioData.add(new Senario("add facture", ""));
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Senario list");
		initRootLayout();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			String fxmlFile = "/fxml/SenarioOverview.fxml";
			FXMLLoader loader = new FXMLLoader();
			Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
			Scene scene = new Scene(rootNode, 2000, 1300);
			
			scene.getStylesheets().add("/styles/styles.css");
			SenarioOverviewController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public ObservableList<Senario> getSenarioData() {
		return senarioData;
	}

	public void setSenarioData(ObservableList<Senario> senarioData) {
		this.senarioData = senarioData;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public ObservableList<UiTestDetails> getUiTestDetailsData() {
		return uiTestDetailsData;
	}

	public void setUiTestDetailsData(ObservableList<UiTestDetails> uiTestDetailsData) {
		this.uiTestDetailsData = uiTestDetailsData;
	}
}