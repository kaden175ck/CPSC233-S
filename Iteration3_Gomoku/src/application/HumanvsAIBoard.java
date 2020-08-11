/**
 * @Title: HumanvsAIBoard
 * @Package application
 * @Description: Will create and play a human vs human game of Gomoku with gui
 * @author Yuan Liu, Kenneth Liu, Angus Milne, Kaden Shi
 * @date August 10, 2020
 * @version V3
 */
package application;

import game.Ai;
import game.Game;
import game.HumanPlayer;
import game.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;


/**
 * @ClassName: HumanvsAIBoard
 * @Description: This class will play a game with visuals between a human player and an AI
 * @author Yuan Liu, Kenneth Liu, Angus Milne, Kaden Shi
 * @date August 10, 2020
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

    /*
	  * @Title: getboardScene
	  * @Description: This method runs the application and will set the scene 
	  * @param none
	  * @return Scene
	  * @throws
	  */
    public Scene getboardScene() {
    	//on launch create buttons, draw the board by calling drawBoard method
    	 //create board, draw the GUI view, set the labels and buttons and circles
    	board = new GoMokuBoard(); 
        board.drawBoard();  
        board.setOnMousePressed( e -> {
			try {
				board.AIMousePressed(e);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} );
        board.relocate(200,40);
        
        quitButton = new Button("Quit");
        quitButton.setOnAction( e -> board.doQuit() );
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
				e1.printStackTrace();
			}
		});
        
        PromptMessage = new Label("Black goes first. Please leave your chess");
        PromptMessage.setFont(new Font(15));
        PromptMessage.relocate(300, 380);
        PromptMessage.setPrefWidth(300);
        PromptMessage.setPrefHeight(80);
        
        Circle circle = new Circle();    
        circle.setCenterX(120.0f); 
        circle.setCenterY(395.0f); 
        circle.setRadius(9.0f); 
        circle.setFill(Color.BLACK); 
        
        You = new Label("You");
        You.setFont(new Font(15));
        You.relocate(130, 353);
        You.setPrefWidth(300);
        You.setPrefHeight(80);
        
        Circle circle2 = new Circle();
        circle2.setCenterX(590.0f); 
        circle2.setCenterY(395.0f); 
        circle2.setRadius(9.0f); 
        circle2.setStroke(Color.BLACK);
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
        return AIvsHboardScene;

    } 
    
    /**
     * @ClassName: GoMokuBoard
     * @Description: This class builds the board, builds the pieces, and places the pieces
     * @author Yuan Liu, Kenneth Liu, Angus Milne, Kaden Shi
     * @date August 10, 2020
     */
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
      
     /*
   	  * @Title: doNewGame
   	  * @Description: will start a new game if pressed
   	  * @param none
   	  * @return void
   	  * @throws
   	  */
        void doNewGame() {
            if (gameInProgress == true) {
                return;
            }
            board = new int[15][15];  
                                       
            currentPlayer = BLACK;   
            
            gameInProgress = true;
            drawBoard();
        }

   	 /*
   	  * @Title: doQuit
   	  * @Description: will quit game if the button is clicked
   	  * @param none
   	  * @return void
   	  * @throws
   	  */
        void doQuit() {
        	Stage stage = (Stage) quitButton.getScene().getWindow();
            stage.close();
        }
        
     /*
   	  * @Title: doClickSquare
   	  * @Description: Will occupy a spot on the board and will populate the corresponding index of the board 2D array when mouse is clicked on it
   	  * @param none
   	  * @return void
   	  * @throws
   	  */ 
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
        
     /*
	  * @Title: checkWinner
	  * @Description: Using the checkWon method from Game will check if a player has 5 pieces in a row.
	  * @param none
	  * @return true or false corresponding to if a player has won.
	  * @throws
	  */
        private boolean checkWinner(int row, int col) {
        	return Game.checkWon(row, col, board);
        }  

     /*
	  * @Title: drawBoard
	  * @Description: builds the board
	  * @param none
	  * @return void
	  * @throws
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
	  * @Title: drawPiece
	  * @Description: Will draw a piece, either black or white, depending on the current player
	  * @param g, current player, row, and col
	  * @return void
	  * @throws
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
	  * @Title: AIMousePressed
	  * @Description: Will draw a piece to occupy a tile of the board when the mouse is clicked
	  * @param evt
	  * @return void
	  * @throws
	  */
        public void AIMousePressed(MouseEvent evt) throws Exception {
        	if(gameInProgress == true) {
        		Player Human = new HumanPlayer(board, BLACK);
                int col = (int)((evt.getX() - 2) / 24);
                int row = (int)((evt.getY() - 2) / 24);
                if(checkWinner(row, col) && col >= 0 && col < 15 && row >= 0 && row < 15 && board[row][col] == EMPTY){
                    doClickSquare(row,col);
                    if(gameInProgress == true) {
                    	Player AI = new Ai (board,WHITE);                   	
                    	int AIrow = AI.playChess()[0];
                    	int AIcol = AI.playChess()[1];
                    	if(checkWinner(AIrow, AIcol) && board[AIrow][AIcol] == EMPTY) {                 
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
