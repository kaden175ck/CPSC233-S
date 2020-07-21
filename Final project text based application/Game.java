import java.util.Scanner;

public class Game {
	private HumanPlayer human;
	private  GameConfiguration config;
	private AI computer; //AI class
	private Scanner input = new Scanner(System.in);
	private int gameMode;

	public void setup() {
		
	}

	public void game() {
		System.out.println("Welcome to Gomoku");
		System.out.println("Please choose between following options:");
		System.out.println("1: Player vs AI, 2: Player vs Player");
		gameMode = input.nextInt();
		//if gamemde =human vs human  
		//private humanplayer human2
		
		System.out.println("Which colour would you like to be (player1):")
		System.out.println("1: Black, 2: White");
		int colour = input.nextInt();
		//human.setcolor(colour);
		
		while (config.hasWon() == false && gameMode == 1) {
			if (human.getColor() == black) {
				human.play();
				human2.play();
			}
			else {
				human2.play();
				human.play();
			}
		}
		while (config.hasWon() == false && gameMode == 2) {
			if (human.getColor == black) {
				human.play();
				computer.play();
			}
			else {
				computer.play();
				human.play();
			}
		}
		
	}
	
	public void main(String[] args) {
		game();
	}
	
	
	
}
