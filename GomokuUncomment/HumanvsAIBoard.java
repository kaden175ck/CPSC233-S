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
public class HumanvsAIBoard {
	private Scene AIvsHboardScene;

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
    private Label PromptMessage;
    private Label You;
    private Label AI;

    
   

 
    //public void start(Stage stage) {
    public Scene getboardScene() {
    	//on launch create buttons, draw the board by calling drawBoard method
        quitButton = new Button("Quit");

        board = new GoMokuBoard(); 
        board.drawBoard();  
       

        quitButton.setOnAction( e -> board.doQuit() );
        //Play with human
        //board.setOnMousePressed( e -> board.humanMousePressed(e) );
        //Play with AI
        board.setOnMousePressed( e -> {
			try {
				board.AIMousePressed(e);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} );
       
        board.relocate(200,40);
        quitButton.relocate(250, 450);
        quitButton.setManaged(false);
        quitButton.resize(100,30);
        
        message = new Label();
        message.setFont(new Font(30));
        message.setText("Human vs AI");
        message.relocate(310, -15);
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
				new GomokuApplication().start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
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
        circle.setCenterX(120.0f); 
        circle.setCenterY(395.0f); 
        circle.setRadius(9.0f); 
         
        //Setting color to the circle 
        circle.setFill(Color.BLACK); 
        
        You = new Label("You");
        You.setFont(new Font(15));
        You.relocate(130, 353);
        You.setPrefWidth(300);
        You.setPrefHeight(80);
        
        Circle circle2 = new Circle();
        
        //Setting the properties of the circle 
        circle2.setCenterX(590.0f); 
        circle2.setCenterY(395.0f); 
        circle2.setRadius(9.0f); 
        circle2.setStroke(Color.BLACK);
        //Setting color to the circle 
        circle2.setFill(Color.WHITE); 
        
        AI = new Label("HumanPlayer1");
        AI.setFont(new Font(15));
        AI.relocate(600, 353);
        AI.setPrefWidth(300);
        AI.setPrefHeight(80);
        
        Pane root = new Pane();
        root.setPrefWidth(800);
        root.setPrefHeight(600);
        
 

        root.getChildren().addAll(board, quitButton, message, victory, restartButton, PromptMessage, circle, You, circle2, AI);
        root.setStyle("-fx-background-color: white; "
                           + "-fx-border-color: black; -fx-border-width:3");
        AIvsHboardScene = new Scene(root);
        /**stage.setScene(Boardscene);
        stage.setResizable(false);
        stage.show();*/
        return AIvsHboardScene;

    } 
    
    
    
     private Object restartButtonClicked() {
		// TODO Auto-generated method stub
		return null;
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
          
            board[row][col] = currentPlayer;  
            drawBoard();

            if (checkWinner(row,col)) {  
                if (currentPlayer == WHITE) {
                	gameInProgress = false;
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
        private void drawPiece(GraphicsContext g, int currentPlayer, int row, int col) {
            if (currentPlayer == WHITE) {
                g.setFill(Color.WHITE);
                g.fillRect(8 + 24 * col, 8 + 24 * row, 12, 12);
            }
            if(currentPlayer == BLACK) {
                g.setFill(Color.BLACK);
                g.fillRect(8 + 24*col, 8 + 24*row, 12, 12);
            }
        }


       /*
        * title: mouse pressed
        * description: when mouse is clicked over square, the drawn piece will occupy the square 
        * returns nothing
        */
       
        public void AIMousePressed(MouseEvent evt) throws Exception {
        	if(gameInProgress == true) {
        		Player Human = new HumanPlayer(board, BLACK);
        		//currentPlayer = BLACK;
                int col = (int)((evt.getX() - 2) / 24);
                int row = (int)((evt.getY() - 2) / 24);
                if(checkWinner(row, col) && col >= 0 && col < 15 && row >= 0 && row < 15 && board[row][col] == EMPTY){
                	//board[row][col] = BLACK;
                	//drawBoard();
                    doClickSquare(row,col);
                    if(gameInProgress == true) {
                    	Player AI = new Ai (board,WHITE);
                    	//currentPlayer = WHITE;
                    	int AIrow = AI.playChess()[0];
                    	int AIcol = AI.playChess()[1];
                    	if(checkWinner(AIrow, AIcol) && board[AIrow][AIcol] == EMPTY) {
                    		//board[row][col] = WHITE;
                    		//drawBoard();
                    		doClickSquare( AIrow, AIcol);
                    	}
                    	}else{
                        	victory.setText("You Win!");
                        }
                	}
                if(currentPlayer == 1)
                	PromptMessage.setText("Your turn");
                if(currentPlayer == 2)
                	PromptMessage.setText("AI turn");
            }else{
        		victory.setText("AI Win!");
        	}
        }
       
    }

} 
