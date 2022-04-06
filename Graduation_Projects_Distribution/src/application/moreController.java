package application;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class moreController {
	
	@FXML
    private JFXButton backB;

    @FXML
    void back(ActionEvent event) {
    	try {
			Parent checkParent = FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));
			Scene checkScene = new Scene(checkParent);
			
			//This line gets the stage information
			Stage windowCheck = (Stage)((Node)event.getSource()).getScene().getWindow() ;
			windowCheck.setScene(checkScene);
			windowCheck.show();
			} catch(Exception e){
				System.out.println(e.toString());	
		}
    }
}
