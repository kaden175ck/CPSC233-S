package application;

import game.Game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import game.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;


public class GomokuBoard extends Canvas {
	 private Button newGameButton;
	    
	 private Button quitButton; 

	//instance variables that are constant, that will occupy board 
    private static final int  
             EMPTY = 0,       
             BLACK = 1,      
             WHITE = 2;       
    
    int[][] board;

    boolean gameInProgress; 


    int currentPlayer;     

    GomokuBoard() {
        super(500,500);  
        doNewGame();
    }

    
    void doNewGame() {
        if (gameInProgress == true) {
            
            return;
        }
        board = new int[15][15];  
                                   
        currentPlayer = BLACK;   
        
        gameInProgress = true;
        drawBoard();
    }

   
    void doQuit() {
    	Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

  


    
    void doClickSquare(int row, int col) {


      /*  if ( board[row][col] != EMPTY ) {
            if (currentPlayer == BLACK)
                message.setText("Player 1's turn");
            else
                message.setText("Player 2's turn");
            return;
        }*/

        

        board[row][col] = currentPlayer;  
        drawBoard();

        if (winner(row,col)) {  
            if (currentPlayer == WHITE)
            	gameInProgress = false;
            else
            	gameInProgress = false;
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
    
    private boolean winner(int row, int col) {
    	return Game.checkWon(row, col, board);
    }  




    
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


   
    public void mousePressed(MouseEvent evt) {
       /* if (gameInProgress == false)
           // message.setText("Click \"New Game\" to start a new game.");
        else { */
    	if(gameInProgress == true) {
            int col = (int)((evt.getX() - 2) / 24);
            int row = (int)((evt.getY() - 2) / 24);
            if (col >= 0 && col < 15 && row >= 0 && row < 15)
                doClickSquare(row,col);
        }
    }

} 


