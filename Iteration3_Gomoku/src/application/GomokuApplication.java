/**
 * @Title: GomokuApplication
 * @Package application
 * @Description: Will create and play a human vs human game of Gomoku with gui
 * @author Yuan Liu, Kenneth Liu, Angus Milne, Kaden Shi
 * @date August 10, 2020
 * @version V3
 */
package application;

import java.io.FileInputStream;
import java.io.IOException;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @ClassName: GomokuApplication
 * @Description: This class will play the GUI cersion of Gomoku game.
 * @author Yuan Liu, Kenneth Liu, Angus Milne, Kaden Shi
 * @date August 10, 2020
 */
public class GomokuApplication extends Application {
	//Initialize the Stage of the GUI and different views of scence.
	private Stage window;
	private Scene startScene;
	private Scene HvsHboardScene; 
	private Scene AIvsHboardScene;
	
	/**
	 * @ClassName: Start
	 * @Description: This method create the start menu of the gomoku game and switch different page view using button click.
	 * @author Yuan Liu, Kenneth Liu, Angus Milne, Kaden Shi
	 * @date August 10, 2020
	 */
	@Override
	public void start(Stage primaryStage) throws IOException{
		//Get the Scence from the HumanvsHumanBoard and HumanvsAIBoard
		HvsHboardScene = (new HumanvsHumanBoard()).getboardScene();
		AIvsHboardScene = (new HumanvsAIBoard()).getboardScene();
		window = primaryStage;
		
		//Set the view of the Start Menu.
		Label startMenu = new Label();
		startMenu.setFont(new Font(30));
		startMenu.setText("Welcome to GOMOKU");
		startMenu.relocate(250, -15);
		startMenu.setPrefWidth(400);
		startMenu.setPrefHeight(120);
		
		Button HvsH = new Button("HumanPlayer vs HumanPlayer");
		HvsH.relocate(100, 400);
		HvsH.resize(300,150);
		HvsH.setOnAction(e -> window.setScene(HvsHboardScene));
		
		Button HvsAI = new Button("HumanPlayer vs AI");
		HvsAI.relocate(500, 400);
		HvsAI.resize(300,150);
		HvsAI.setOnAction(e -> window.setScene(AIvsHboardScene));
		Button Quit = new Button("Quit");
		Quit.relocate(350, 400);
		Quit.resize(300,150);
		Quit.setOnAction(e -> window.close());
		
		//Add Image in the first page
	    Image image = new Image(new FileInputStream("src/gomoku.png"));  
	    ImageView imageView = new ImageView(image); 
	    imageView.setX(250); 
	    imageView.setY(120); 
	    imageView.setFitHeight(250); 
	    imageView.setFitWidth(250); 
	    imageView.setPreserveRatio(true);
		
	    Label Rules = new Label();
		Rules.setFont(new Font(13));
		Rules.setText("Rules: Players alternate turns placing a stone of their color on an empty intersection. \n" 
				+ "The winner is the first player to form an unbroken chain of five stones horizontally, vertically, or diagonally.");
		Rules.relocate(50, 480);
		Rules.setPrefWidth(650);
		Rules.setPrefHeight(50);
	    
		Pane layout1 = new Pane();
		layout1.getChildren().addAll(startMenu, HvsH, HvsAI,Quit,imageView, Rules);
		startScene = new Scene(layout1, 800,600);
		window.setScene(startScene);
		window.setTitle("Gomoku");
		window.show();
	}

	/**
	 * @ClassName: main
	 * @Description:implement everything.
	 * @author Yuan Liu, Kenneth Liu, Angus Milne, Kaden Shi
	 * @date August 10, 2020
	 */
	public static void main(String[] args) {
        launch(args);
    }
}
