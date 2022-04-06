package application;

import java.util.Collections;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ProjectTableController {
	
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
    	
    	Collections.sort(list, RandomSolutionProject.ProNumComparator);
    
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
        	windowCheck.getIcons().add(new Image("file:///C:/Users/USER/Desktop/4th-Year-1st-Sem/AI/Ai-Projects-1182319-1180548/Project-1/graduation-hat.png"));
        	windowCheck.setTitle("Distribution Table");
        	DistController controller = loader.getController();
 	    	controller.getValues(dist, firstN, secondN, thirdN);
        	windowCheck.show();
 	       	}catch(Exception e){
	    		System.out.println("Problem here" + e.fillInStackTrace());	
			}
    }

}
