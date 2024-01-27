package threeInRow;

public class Game {
	private int size;           // Size of the game board
	private double score = 0.0; // Player's score
	private Cell[][] board;     // 2D array to represent the game board
	
	// Constructor to initialize the game with a specified size
	public Game(int size){
		board = new Cell[size][size];
		// Initialize each cell in the board
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            board[i][j] = new Cell(); 
	        }
	    }
		this.size = size;
	}
	
	// Reset the game by resetting the board and score
	public void reset() {
		reset_board();
		reset_score();
	}
	
	// Get the player's current score
	public double get_score() {
		return score;
	}
	
	// Reset the player's score to zero
	public void reset_score() {
		score = 0.0;
	}
	
	// Swap cells based on the specified direction
	public void swap_cells(int y, int x, Directions direction) {
		switch(direction) {
			case UP:{
				Cell temp = new Cell(board[y][x].get_value());
				board[y][x].set_value(board[y - 1][x].get_value());
				board[y - 1][x].set_value(temp.get_value());
				break;
			}
			case RIGHT:{
				Cell temp = new Cell(board[y][x].get_value());
				board[y][x].set_value(board[y][x + 1].get_value());
				board[y][x + 1].set_value(temp.get_value());
				break;
			}
			case LEFT:{
				Cell temp = new Cell(board[y][x].get_value());
				board[y][x].set_value(board[y][x - 1].get_value());
				board[y][x - 1].set_value(temp.get_value());
				break;
			}
			case DOWN:{
				Cell temp = new Cell(board[y][x].get_value());
				board[y][x].set_value(board[y + 1][x].get_value());
				board[y + 1][x].set_value(temp.get_value());
				break;
			}
		}
	}
	
	// Update the game board by setting matching cells, 
	// removing them, and updating unchecked cells
	public void update_board() {
		set_matching_cells();
		remove_matching_cells();
		set_unchecked_board();
	}
	
	// Set all cells on the board to unchecked
	private void set_unchecked_board() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i][j].set_unchecked();
			}
		}
	}
	
	// Remove cells that have been marked as matching
	private void remove_matching_cells() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j].is_horChecked() && board[i][j].is_vertChecked()) {
					move_down(i, j);
					score += 450.0;
				}
				else if(board[i][j].is_horChecked() || board[i][j].is_vertChecked()) {
					move_down(i, j);
					score += 100.0;
				}
			}
		}
	}
	
	// Move cells down when cells are removed
	private void move_down(int row, int col) {
		for(int i = row; i > 0; i--) {
			board[i][col].set_value(board[i - 1][col].get_value());
		}
		board[0][col].reset();
	}
	
	// Recognize matching cells on the board
	public boolean set_matching_cells() {
		boolean is_match = false;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(i <= size - 3 && !board[i][j].is_vertChecked()) {
					is_match = set_vert_matching(i, j) || is_match;
				}
				if(j <= size - 3 && !board[i][j].is_horChecked()) {
					is_match = set_hor_matching(i, j) || is_match;
				}
			}
		}
		return is_match;
	}
	
	// Recognize horizontal matching cells on the board
	private boolean set_hor_matching(int row, int col) {
		int length = 0;
		for(int j = col; j < size; j++) {
			if(board[row][j].get_value() != board[row][col].get_value()) {
				break;
			}
			length++;
		}
		
		if(length >= 3) {
			for(int j = 0; j < length; j++) {
				board[row][col + j].set_horChecked(true);
			}
			return true;
		}
		return false;
	}
	
	// Recognize vertical matching cells on the board
	private boolean set_vert_matching(int row, int col) {
		int length = 0;
		for(int i = row; i < size; i++) {
			if(board[i][col].get_value() != board[row][col].get_value()) {
				break;
			}
			length++;
		}
		
		if(length >= 3) {
			for(int i = 0; i < length; i++) {
				board[row + i][col].set_vertChecked(true);
			}
			return true;
		}
		return false;
	}
	
	// Reset all cells on the board
	private void reset_board() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i][j].reset();
			}
		}
	}
	
	// Display the current state of the game board
	public void view_board() {
		System.out.println("Score: " + score);
		System.out.print("  ");
		for(int i = 0; i < size; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		for(int i = 0; i < size; i++) {
			System.out.print(i + " ");
			for(int j = 0; j < size; j++) {
				System.out.print(board[i][j].get_value() + " ");
			}
			System.out.println();
		}
	}
	
	// Get the size of the game board
	public int get_board_size() {
		return size;
	}
}
