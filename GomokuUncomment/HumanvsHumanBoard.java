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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;


/*
 * implement AI
 * link start -> board-> victory
 *
 */
public class HumanvsHumanBoard {
	private Scene Boardscene;

	//launch application
    
    //instance variables that are constant, that will occupy board 
    private static final int  
             EMPTY = 0,       
             BLACK = 1,      
             WHITE = 2;       
    
    
    private GoMokuBoard board; 


    
    private Button quitButton; 

    private Label message;  
    
   
    private Label victory; 
    
    private Button restartButton;
   
    private GomokuApplication gomoku;
    private Label PromptMessage;
    private Label Human1;
    private Label Human2;

 
    //public void start(Stage stage) {
    public Scene getboardScene() throws ArrayIndexOutOfBoundsException {
    	//on launch create buttons, draw the board by calling drawBoard method
        quitButton = new Button("Quit");

        board = new GoMokuBoard(); 
        board.drawBoard();  
       

        quitButton.setOnAction( e -> board.doQuit() );
        //Play with human
        board.setOnMousePressed( e -> {
			try {
				board.humanMousePressed(e);
			} catch (Exception e1) {
				e1.getStackTrace();
			}
		} );
        //Play with AI
        //board.setOnMousePressed( e -> board.AIMousePressed(e) );
       
        board.relocate(200,40);
        quitButton.relocate(250, 450);
        quitButton.setManaged(false);
        quitButton.resize(100,30);
        
        message = new Label();
        message.setFont(new Font(30));
        message.setText("Human vs Human");
        message.relocate(280, -15);
        message.setPrefWidth(300);
        message.setPrefHeight(80);
        
        victory = new Label();
        victory.setFont(new Font(30));
        victory.relocate(310, 500);
        victory.setPrefWidth(300);
        victory.setPrefHeight(80);
        
        
        restartButton = new Button("Main Menu");
        restartButton.relocate(450, 450);
        restartButton.setManaged(false);
        restartButton.resize(100,30);
        Stage window = new Stage();
        restartButton.setOnAction(e -> {
			try {
				board.doQuit();
				try {
					new GomokuApplication().start(window);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
        
        PromptMessage = new Label("Black goes first. Please leave your chess");
        PromptMessage.setFont(new Font(15));
        PromptMessage.relocate(300, 380);
        PromptMessage.setPrefWidth(300);
        PromptMessage.setPrefHeight(80);
        
        Circle circle = new Circle();    
        
        //Setting the properties of the circle 
        circle.setCenterX(90.0f); 
        circle.setCenterY(395.0f); 
        circle.setRadius(9.0f); 
         
        //Setting color to the circle 
        circle.setFill(Color.BLACK); 
        
        Human1 = new Label("HumanPlayer1");
        Human1.setFont(new Font(15));
        Human1.relocate(100, 350);
        Human1.setPrefWidth(300);
        Human1.setPrefHeight(80);
        
        Circle circle2 = new Circle();
        
        //Setting the properties of the circle 
        circle2.setCenterX(590.0f); 
        circle2.setCenterY(395.0f); 
        circle2.setRadius(9.0f); 
        circle2.setStroke(Color.BLACK);
        //Setting color to the circle 
        circle2.setFill(Color.WHITE); 
        
        Human2 = new Label("HumanPlayer1");
        Human2.setFont(new Font(15));
        Human2.relocate(600, 350);
        Human2.setPrefWidth(300);
        Human2.setPrefHeight(80);
        
        Pane root = new Pane();
        root.setPrefWidth(800);
        root.setPrefHeight(600);
        
 

        root.getChildren().addAll(board, quitButton, message, victory, restartButton,PromptMessage, Human1,circle,Human2,circle2);
        root.setStyle("-fx-background-color: white; "
                           + "-fx-border-color: black; -fx-border-width:3");
        Boardscene = new Scene(root);
        return Boardscene;

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
            g.setFill( Color.TAN );  // fill canvas with light gray
            g.fillRect(0,0,363,363);



            g.setStroke(Color.BLACK);
            g.setLineWidth(1.5);
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
        public void humanMousePressed(MouseEvent evt) throws Exception{
        	try {
        	if(gameInProgress == true) {
                int col = (int)((evt.getX() - 2) / 24);
                int row = (int)((evt.getY() - 2) / 24);
                if (col >= 0 && col < 15 && row >= 0 && row < 15 && board[row][col] == EMPTY)
                	if(checkWinner(row, col))
                    doClickSquare(row,col);
                	if(currentPlayer == 1)
                	PromptMessage.setText("Black turn");
                	if(currentPlayer == 2)
                	PromptMessage.setText("While turn");
            }if(gameInProgress == false) {
            	if(currentPlayer == 1)
            	victory.setText("Black Win!");
            	if(currentPlayer == 2)
            	victory.setText("While Win!");
            }
        	}catch(ArrayIndexOutOfBoundsException e) {
        		System.out.println("Nice Promption: You can not click out of the bound");
        	}
        }
    }

} 