package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SampleController implements Initializable{
	
	String projectsFile;
	String groupsFile;
	public static Data data = new Data();
	public static Population populationSol;
	public static ArrayList<Distribution> solutions = new ArrayList<>();
	public static double min_conflicts = 100;
	
	@FXML
    private JFXTextField proFile;

    @FXML
    private JFXTextField groFile;

    @FXML
    private Button moreB;

    @FXML
    private JFXButton getDB;
    @FXML
    private JFXButton clearB; 
    @FXML
    private Label time;
    @FXML
    private Label day;
       
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		Date thisDate = new Date();                  // to print the current time on the window
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		time.setText(sdf.format(thisDate));     
		
		sdf = new SimpleDateFormat("EEEE, MMMM d, Y");   //to print the current date on the window
		day.setText(sdf.format(thisDate));	
	}

    @FXML
    void clear(ActionEvent event) {
    	proFile.clear();
    	groFile.clear();
    }

    @FXML
    void getD(ActionEvent event) throws FileNotFoundException, IOException {
    	data.Pfile =  projectsFile;
    	data.Gfile = groupsFile ;
    	data.initializeProjects();
		data.initializeSuper();
		data.initializeGroups();
		data.initTpoicsRelated();
		
		Distribution distr = new Distribution(data);

		distr.intialize1();
		int generationNumber = 0;
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(data);
		Population population = new Population(AI.POPULATION_SIZE, data).sortByFitness();

		while (++generationNumber != 2000) {
			population = geneticAlgorithm.evolve(population).sortByFitness();

			AI.DIST_NUM = 0;
			population.getDistributions().forEach(dist -> {
				/*System.out
						.println(AI.DIST_NUM++ + " fitness:" + dist.getFitness1() + " conflicts:" + dist.getNumberOfConflicts());
				*/AI.DIST_NUM = 1;
			});
			solutions.add(population.getDistributions().get(0));
		}

		int first=0;
		int second=0;
		int third=0;
		populationSol = new Population(solutions);
		populationSol = populationSol.sortByFitness();
		System.out.println("******************* FINISH *******************");
		System.out.println("The least conflicts that got: " + min_conflicts);
		System.out.println("The best fitness function value that got: " + 100 / (1 + min_conflicts));
		for (int i = 0; i < distr.randomp.size(); i++) {
			if(distr.randomp.get(i).getProj().getProjectId().equals(distr.randomp.get(i).getGroup().getPrefrances()[0]))
				first++;
			if(distr.randomp.get(i).getProj().getProjectId().equals(distr.randomp.get(i).getGroup().getPrefrances()[1]))
				second++;
			if(distr.randomp.get(i).getProj().getProjectId().equals(distr.randomp.get(i).getGroup().getPrefrances()[2]))
				third++;
			System.out.println("====================================================================================================================================");
			System.out.println("Group Number: " + distr.randomp.get(i).getGroup().getGroupNumber());
			System.out.println("Students Names: " + distr.randomp.get(i).getGroup().getStudentsNames());
			System.out.println("Project Number: " + distr.randomp.get(i).getProj().getProjectId());
			System.out.println("Project Name: " + distr.randomp.get(i).getProj().getProjectName());
			System.out.println("Project Supervisor: " + distr.randomp.get(i).getProj().getSupervisorName().getSuperName());
			System.out.println("====================================================================================================================================");
		}
		
		System.out.println("First: "+first +"\t Second: "+second +"\t Third: "+third);
		System.out.println("Total groups got preferences: "+(first+second+third));
		
		try {
 	     	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dist.fxml"));
 	    	Stage windowCheck = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        	Scene s = new Scene(loader.load());
        	windowCheck.setScene(s);
        	windowCheck.getIcons().add(new Image("file:../AI-Project-1-1182319-1180548/src/samples/graduation-hat.png"));
        	windowCheck.setTitle("Distribution Results");
        	DistController controller = loader.getController();
 	    	controller.getValues(distr, first,second,third);
        	windowCheck.show();
 	       	}catch(Exception e){
	    		System.out.println("Problem here" + e.fillInStackTrace());	
			}

	}


    @FXML
    void groF(ActionEvent event) {
    	groupsFile = groFile.getText();
    }

    @FXML
    void more(ActionEvent event) {
    	try {
			Parent checkParent = FXMLLoader.load(getClass().getResource("/application/more.fxml"));
			Scene checkScene = new Scene(checkParent);
			
			//This line gets the stage information
			Stage windowCheck = (Stage)((Node)event.getSource()).getScene().getWindow() ;
			windowCheck.getIcons().add(new Image("file:../AI-Project-1-1182319-1180548/src/samples/graduation-hat.png"));
			windowCheck.setTitle("Graduation Projects Distribution Form");
			windowCheck.setScene(checkScene);
			windowCheck.show();
			} catch(Exception e){
				System.out.println(e.toString());	
		}
    }

    @FXML
    void proF(ActionEvent event) {
    	projectsFile = proFile.getText();
    }
	
}
