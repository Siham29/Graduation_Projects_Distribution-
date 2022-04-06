package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class DistController implements Initializable{
	@FXML
    private JFXButton groupB;
    @FXML
    private JFXButton projectB;
    @FXML
    private Label firstNum;
    @FXML
    private Label secondNum;
    @FXML
    private Label thirsdNum;
    @FXML
    private JFXButton gamegroupB;
    @FXML
    private JFXButton tableGB;
    @FXML
    private JFXButton gameprojectB;
    @FXML
    private JFXButton tablePB;
    @FXML
    private Label totalNum;

	int firstN, secondN, thirdN;
	Distribution d;
	String str1,str2,str3,str4;
	
	public void getValues(Distribution distr, int first, int second, int third){
		firstN = first;
		secondN = second;
		thirdN = third;
		d = distr;
		str1 = Integer.toString(first);
		firstNum.setText(str1);
		str2 = Integer.toString(second);
		secondNum.setText(str2);
		str3 = Integer.toString(third);
		thirsdNum.setText(str3);
		str4 = Integer.toString(first+second+third);
		totalNum.setText(str4);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gamegroupB.setVisible(false);
		tableGB.setVisible(false);
		gameprojectB.setVisible(false);
	    tablePB.setVisible(false); 
	}
	
	@FXML
	void gameGroup(ActionEvent event) {

	}

	@FXML
	void gameProject(ActionEvent event) {	

	}

	@FXML
	void groupAction(ActionEvent event) {
		gameprojectB.setVisible(false);
	    tablePB.setVisible(false); 
		gamegroupB.setVisible(true);
		tableGB.setVisible(true);
	}
	@FXML
	void projectAction(ActionEvent event) {
		gamegroupB.setVisible(false);
		tableGB.setVisible(false);
		gameprojectB.setVisible(true);
	    tablePB.setVisible(true); 
	}

	@FXML
	void tableGroup(ActionEvent event) {
		try {
 	     	FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupTable.fxml"));
 	    	Stage windowCheck = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        	Scene s = new Scene(loader.load());
        	windowCheck.setScene(s);
        	windowCheck.getIcons().add(new Image("file:../AI-Project-1-1182319-1180548/src/samples/graduation-hat.png"));
        	windowCheck.setTitle("Distribution Table");
        	GroupTableController controller = loader.getController();
 	    	controller.getValues(d, firstN, secondN, thirdN);
        	windowCheck.show();
 	       	}catch(Exception e){
	    		System.out.println("Problem here" + e.fillInStackTrace());	
			}
	}

	@FXML
	void tableProject(ActionEvent event) {
		try {
 	     	FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectTable.fxml"));
 	    	Stage windowCheck = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        	Scene s = new Scene(loader.load());
        	windowCheck.setScene(s);
        	windowCheck.getIcons().add(new Image("file:../AI-Project-1-1182319-1180548/src/samples/graduation-hat.png"));
        	windowCheck.setTitle("Distribution Table");
        	ProjectTableController controller = loader.getController();
 	    	controller.getValues(d, firstN, secondN, thirdN);
        	windowCheck.show();
 	       	}catch(Exception e){
	    		System.out.println("Problem here" + e.fillInStackTrace());	
			}
	}
		
	
}
