package path.to;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.teamwill.tnrfx.model.UiTestDetails;

public class MyController implements Initializable {

    @FXML private TableView<UiTestDetails> tableView;
    @FXML private TableColumn<UiTestDetails, String> UserId;
    @FXML private TableColumn<UiTestDetails, String> UserName;
    @FXML private TableColumn<UiTestDetails, String> Active;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserId.setCellValueFactory(new PropertyValueFactory<UiTestDetails, String>("id"));
        UserName.setCellValueFactory(new PropertyValueFactory<UiTestDetails, String>("name"));
        Active.setCellValueFactory(new PropertyValueFactory<UiTestDetails, String>("active"));

        tableView.getItems().setAll(parseUserList());
    }
    private List<UiTestDetails> parseUserList(){
    	List<UiTestDetails> list=new ArrayList<>();
    	list.add(new  UiTestDetails(true,"local"));
    	list.add(new  UiTestDetails(false,"local"));
    	
    	
    	
    	return list;
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list   
    }
}