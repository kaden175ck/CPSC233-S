package application;

import java.io.IOException;

import game.Ai;
import game.Game;
import game.HumanPlayer;
import game.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GomokuApplication extends Application {
	private Stage window;
	//private Board board;
	private Scene startScene;
	private Scene HvsHboardScene; 
	private Scene AIvsHboardScene;
	//private Scene victory;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		HvsHboardScene = (new HumanvsHumanBoard()).getboardScene();
		AIvsHboardScene = (new HumanvsAIBoard()).getboardScene();
		//boardScene = board.getboardScene();
		window = primaryStage;
		
		//
		Label startMenu = new Label();
		startMenu.setFont(new Font(30));
		startMenu.setText("Welcome to GOMOKU");
		startMenu.relocate(280, -15);
		startMenu.setPrefWidth(300);
		startMenu.setPrefHeight(80);
		Button HvsH = new Button("HumanPlayer vs HumanPlayer");
		HvsH.relocate(300, 450);
		HvsH.resize(150,50);
		HvsH.setOnAction(e -> window.setScene(HvsHboardScene));
		Button HvsAI = new Button("HumanPlayer vs AI");
		HvsAI.relocate(300, 550);
		HvsAI.resize(150,50);
		HvsAI.setOnAction(e -> window.setScene(AIvsHboardScene));
		
		Pane layout1 = new Pane();
		layout1.getChildren().addAll(startMenu, HvsH, HvsAI);
		startScene = new Scene(layout1, 800,600);
		
		
		
        //Button restartButton = new Button("Restart");
        //restartButton.relocate(450, 450);
        //restartButton.setManaged(false);
        //restartButton.resize(100,30);
        
        
      
        
		
		window.setScene(startScene);
		window.setTitle("Gomoku");
		window.show();
	}
	
	public Scene getStartScene() {
		Label startMenu = new Label();
		startMenu.setFont(new Font(30));
		startMenu.setText("Welcome to GOMOKU");
		startMenu.relocate(280, -15);
		startMenu.setPrefWidth(300);
		startMenu.setPrefHeight(80);
		Button HvsH = new Button("HumanPlayer vs HumanPlayer");
		HvsH.relocate(300, 450);
		HvsH.resize(150,50);
		HvsH.setOnAction(e -> window.setScene(HvsHboardScene));
		Button HvsAI = new Button("HumanPlayer vs AI");
		HvsAI.relocate(300, 550);
		HvsAI.resize(150,50);
		HvsAI.setOnAction(e -> window.setScene(AIvsHboardScene));
		
		Pane layout1 = new Pane();
		layout1.getChildren().addAll(startMenu, HvsH, HvsAI);
		startScene = new Scene(layout1, 800,600);
		return startScene;
	}
	public Stage getwindow() {
		return window;
	}

	public static void main(String[] args) {
        launch(args);
    }
}
