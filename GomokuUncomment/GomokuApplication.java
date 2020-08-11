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

public class GomokuApplication extends Application {
	private Stage window;
	private Scene startScene;
	private Scene HvsHboardScene; 
	private Scene AIvsHboardScene;
	
	
	@Override
	public void start(Stage primaryStage) throws IOException{
		HvsHboardScene = (new HumanvsHumanBoard()).getboardScene();
		AIvsHboardScene = (new HumanvsAIBoard()).getboardScene();
		window = primaryStage;
		
		//
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

	public static void main(String[] args) {
        launch(args);
    }
}
