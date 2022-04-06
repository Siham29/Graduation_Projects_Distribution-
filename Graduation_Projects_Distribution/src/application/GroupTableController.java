package application;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GroupTableController {
	
	@FXML
    private TableView<RandomSolutionProject> table;

    @FXML
    private TableColumn<RandomSolutionProject, String> GroupID;

    @FXML
    private TableColumn<RandomSolutionProject, String> GroupStudents;

    @FXML
    private TableColumn<RandomSolutionProject, String> ProjectID;

    @FXML
    private TableColumn<RandomSolutionProject, String> SupervisorName;

    @FXML
    private TableColumn<RandomSolutionProject, String> ProjectName;

    @FXML
    private JFXButton backB;
    
    Distribution dist;
    int firstN, secondN, thirdN;
    ObservableList<RandomSolutionProject> list = FXCollections.observableArrayList();

    void getValues(Distribution d, int first, int second, int third) {
    	firstN = first;
		secondN = second;
		thirdN = third;
    	dist = d;
    	for(int i=0; i<dist.randomp.size(); i++) {
    		list.add(dist.randomp.get(i));
    	}
    	
    	Collections.sort(list, RandomSolutionProject.StuNameComparator);
    
    	GroupID.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getGroup().getGroupNumber()));
		GroupStudents.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGroup().getStudentsNames().get(0)+", "+cellData.getValue().getGroup().getStudentsNames().get(2)
				+", "+cellData.getValue().getGroup().getStudentsNames().get(1)));
		ProjectID.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getProj().getProjectId()));
		SupervisorName.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getProj().getSupervisorName().getSuperName()));
		ProjectName.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getProj().getProjectName()));
    	table.setItems(list);
    }
    
    @FXML
    void back(ActionEvent event) {
    	try {
 	     	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dist.fxml"));
 	    	Stage windowCheck = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        	Scene s = new Scene(loader.load());
        	windowCheck.setScene(s);
        	windowCheck.getIcons().add(new Image("file:../AI-Project-1-1182319-1180548/src/samples/graduation-hat.png"));
        	windowCheck.setTitle("Distribution Table");
        	DistController controller = loader.getController();
 	    	controller.getValues(dist, firstN, secondN, thirdN);
        	windowCheck.show();
 	       	}catch(Exception e){
	    		System.out.println("Problem here" + e.fillInStackTrace());	
			}
    }

	/*@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}*/

}
