package application;

import java.io.IOException;
import java.util.Scanner;

import game.Ai;
import game.Game;
import game.HumanPlayer;
import game.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;


/*
 * implement AI
 * link start -> board-> victory
 *
 */
public class gomokuMain extends Application {

	//launch application
    public static void main(String[] args) {
        launch(args);
    }
    
    //instance variables that are constant, that will occupy board 
    private static final int  
             EMPTY = 0,       
             BLACK = 1,      
             WHITE = 2;       
    
    
    private GoMokuBoard board; 


    
    private Button quitButton; 

    private Label message;  
    
    private int choice;
    
    

 
    public void start(Stage stage) {
    	//on launch create buttons, draw the board by calling drawBoard method
        quitButton = new Button("Quit");

        board = new GoMokuBoard(); 
        board.drawBoard();  
       

        quitButton.setOnAction( e -> board.doQuit() );
        //Play with human
        //board.setOnMousePressed( e -> board.humanMousePressed(e) );
        //Play with AI
        board.setOnMousePressed( e -> board.AIMousePressed(e) );
       
        board.relocate(200,40);
        quitButton.relocate(335, 450);
        
     
        
        quitButton.setManaged(false);
        quitButton.resize(100,30);
        
        message = new Label();
        message.setFont(new Font(30));
        message.setText("GOMOKU");
        message.relocate(310, -15);
        message.setPrefWidth(300);
        message.setPrefHeight(80);
        
        Pane root = new Pane();
        
        root.setPrefWidth(800);
        root.setPrefHeight(600);
        
 

        root.getChildren().addAll(board, quitButton, message);
        root.setStyle("-fx-background-color: white; "
                           + "-fx-border-color: black; -fx-border-width:3");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    } 
    
    
     // how do we link startmenu -> board UI -> victoryScreen
     
   /* public void showVictory(String file, Stage primaryStage) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource(file));
    	primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }*/
    
    private class GoMokuBoard extends Canvas {
        
    	//create instance variables
       private int[][] board;		//board[][] is the 2d array that checks if won

       private boolean gameInProgress; 

       private int currentPlayer;     //black or white
       
       private Game game;
  
        GoMokuBoard() {
            super(500,500);  
            doNewGame();
        }
      

        //if new game button is clicked, start a new game
        void doNewGame() {
            if (gameInProgress == true) {
                
                return;
            }
            board = new int[15][15];  
                                       
            currentPlayer = BLACK;   
            
            gameInProgress = true;
            drawBoard();
        }

       //clicking the quit button will close the application
        void doQuit() {
        	Stage stage = (Stage) quitButton.getScene().getWindow();
            stage.close();
        }

        //clicking on a square will occupy board and board 2d array 
        void doClickSquare(int row, int col)  {


          /*  if ( board[row][col] != EMPTY ) {
                if (currentPlayer == BLACK)
                    message.setText("Player 1's turn");
                else
                    message.setText("Player 2's turn");
                return;
            }*/

            

            board[row][col] = currentPlayer;  
            drawBoard();

            if (checkWinner(row,col)) {  
                if (currentPlayer == WHITE) {
                	gameInProgress = false;
                	//showVictory("src/victoryScreen/VictoryScreen.fxml");
                }
                else {
                	gameInProgress = false;
                }
                return;
            }

            boolean emptySpace = false;     
            for (int i = 0; i < 15; i++)
                for (int j = 0; j < 15; j++)
                    if (board[i][j] == EMPTY)
                        emptySpace = true;
            if (emptySpace == false) {
            	gameInProgress = false;
                return;
            }


            if (currentPlayer == BLACK) {
                currentPlayer = WHITE;
               
            }
            else {  
                currentPlayer = BLACK;
                
            }

        }  
        
        /*Title: checkWinner
         * Description: Using the checkwon class from Game, check if pieces are 5 in a row
         * returns true or false depending on if player won
         */
        private boolean checkWinner(int row, int col) {
        	return Game.checkWon(row, col, board);
        }  




        /*
         * Title: drawBoard()
         * Description: draws the board
         * returns nothing
         */
        public void drawBoard() {

            GraphicsContext g = getGraphicsContext2D();
            g.setFill( Color.BROWN );  // fill canvas with light gray
            g.fillRect(0,0,360,360);



            g.setStroke(Color.BLACK);
            g.setLineWidth(4);
            for (int i = 0; i <= 15; i++) {
                g.strokeLine(2 + 24*i, 0, 2 + 24*i, 360);
                g.strokeLine(0, 2 + 24*i, 360, 2 + 24*i);
            }


            for (int row = 0; row < 15; row++)
                for (int col = 0; col < 15; col++)
                    if (board[row][col] != EMPTY)
                        drawPiece(g, board[row][col], row, col);

        }  


        /*
         * Title: draw piece
         * description: draws a token depending on if its white or black
         */
        private void drawPiece(GraphicsContext g, int piece, int row, int col) {
            if (piece == WHITE) {
                g.setFill(Color.WHITE);
                g.fillRect(8 + 24 * col, 8 + 24 * row, 12, 12);
            }
            else {
                g.setFill(Color.BLACK);
                g.fillRect(8 + 24*col, 8 + 24*row, 12, 12);
            }
        }


       /*
        * title: mouse pressed
        * description: when mouse is clicked over square, the drawn piece will occupy the square 
        * returns nothing
        */
        public void humanMousePressed(MouseEvent evt) {
        	if(gameInProgress == true) {
                int col = (int)((evt.getX() - 2) / 24);
                int row = (int)((evt.getY() - 2) / 24);
                if (col >= 0 && col < 15 && row >= 0 && row < 15 && board[row][col] == EMPTY)
                    doClickSquare(row,col);
            }
        }
        public void AIMousePressed(MouseEvent evt) {
        	if(gameInProgress == true) {
                int col = (int)((evt.getX() - 2) / 24);
                int row = (int)((evt.getY() - 2) / 24);
                if (col >= 0 && col < 15 && row >= 0 && row < 15 && board[row][col] == EMPTY)
                	
                    doClickSquare(row,col);
            }
        	Player AI = new Ai (board,WHITE);
        	int row = AI.playChess()[0];
        	int col = AI.playChess()[1];
        	if(board[row][col] == EMPTY)
        	doClickSquare( AI.playChess()[0], AI.playChess()[0]);
        	
        }
    } 


} 