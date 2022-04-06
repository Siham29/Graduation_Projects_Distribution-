package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AI extends Application{
	
	public static final int NUMB_OF_ELITE_DISTRIBUTIONS = 1;
	public static final int POPULATION_SIZE = 5;
	public static final double CROSSOVER_RATE = 0.9;
	public static final double MUTATION_RATE = 0.1;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;
	public static int DIST_NUM = 0;
	public static double min_conflicts = 100;

	//public static Data data = new Data();
	//public static Population populationSol;

	//public static ArrayList<Distribution> solutions = new ArrayList<>();
	
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,1090,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:../AI-Project-1-1182319-1180548/src/samples/graduation-hat.png"));
			primaryStage.setTitle("Graduation Projects Distribution Form");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
