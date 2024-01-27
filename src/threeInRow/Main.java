package threeInRow;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {		
		Game game;
		int x, y;
		Directions direction;
		
		// Start the game and initialize variables
		game = start_game();
		
		// Generate board which has no matched cells at the start
		while(game.set_matching_cells()) {
			game.update_board();
		}
		game.reset_score();
		
		// Main game loop
		while(true) {
			// Display the game board
			game.view_board();
			
			// Input loop for getting user coordinates and direction
			while(true) {
				// Get Y coordinate
				y = ask_coordinate("Y", game.get_board_size());
				// Get X coordinate
				x = ask_coordinate("X", game.get_board_size());
				// Get movement direction
				direction = ask_direction();
				
				// Check for impossible movements
				if(x == 0 && direction == Directions.LEFT) {
					System.out.println("Impossible movement!");
				}
				else if(x == game.get_board_size() - 1 && direction == Directions.RIGHT) {
					System.out.println("Impossible movement!");
				}
				else if(y == 0 && direction == Directions.UP) {
					System.out.println("Impossible movement!");
				}
				else if(y == game.get_board_size() - 1 && direction == Directions.DOWN) {
					System.out.println("Impossible movement!");
				}
				else {
					break; // Exit the input loop if valid input is received
				}
			}
			
			 // Execute the move and update the game board
			game.swap_cells(y, x, direction);
			move_console();
			game.update_board();
			
			// Check for cell matching
			while(game.set_matching_cells()) {
				System.out.println("MATCH!!!");
				game.view_board();
				game.update_board();
				move_console();
				Thread.sleep(1000);
			}
		}
	}
	
	public static Directions ask_direction() {
		System.out.println("Enter direction of your move:\nUP - 1,\nRIGHT - 2,\nLEFT - 3,\nDOWN - 4");
		
		int direction;
			
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			try{
				direction = scanner.nextInt();
				
				if(direction < 1 || direction > 4) {
					System.out.println("Invalid value!");
				}
				else {
					break;
				}
			} catch(Exception e) {
				System.out.println("Invalid value!");
				scanner.nextLine();
			}
		}
		switch (direction){
			case (1):{
				return Directions.UP;
			}
			case (2):{
				return Directions.RIGHT;
			}
			case (3):{
				return Directions.LEFT;
			}
			case (4):{
				return Directions.DOWN;
			}
			default: {
	            // Handle the case where the direction is not in the valid range
	            throw new IllegalArgumentException("Invalid direction value: " + direction);
	            
	        }
		}
	}
	
	public static int ask_coordinate(String coordinate_title, int board_size) {
		System.out.println("Enter " + coordinate_title + " coordinate(0 <= coordinate <= " + (board_size - 1) + "):");
		
		int coordinate;
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			try{
				coordinate = scanner.nextInt();
				
				if(coordinate < 0 || coordinate > board_size - 1) {
					System.out.println("The value is out of range!");
				}
				else {
					break;
				}
			} catch(Exception e) {
				System.out.println("Invalid value!");
				scanner.nextLine();
			}
		}
		return coordinate;
		
	}
	
	public static Game start_game() {
		System.out.println("Let's start the game!!!\nEnter size of your board (4 <= size <= 6):");
		
		int size;
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			try{
				size = scanner.nextInt();
				
				if(size < 4 || size > 6) {
					System.out.println("The value is out of range!");
				}
				else {
					break;
				}
			} catch(Exception e) {
				System.out.println("Invalid value!");
				scanner.nextLine();
			}
		}
				
		System.out.println();
		
		return new Game(size);
	}
	
	public static void move_console() {
        for(int i = 0; i < 12; i++) {
        	System.out.println();
        }
    }
}
