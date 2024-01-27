package threeInRow;

public class Cell {
	private static final char[] FIGURES = {'◆', '▼', '●', '■'}; // Array of possible cell values
	private char value; // Current value of the cell
	private boolean vertChecked; // Flag indicating vertical matching
	private boolean horChecked; // Flag indicating horizontal matching
	
	// Default constructor to create a cell with a random value
	Cell() {
		reset();
	}
	
	// Constructor to create a cell with a specified value
	Cell(char value){
		this.value = value;
	}
	
	// Reset the cell by setting it to unchecked and assigning a random value from FIGURES
	public void reset() {
		set_unchecked();
		value = FIGURES[(int) (Math.random() * 4)];
	}
	
	// Set the cell as unchecked (both vertically and horizontally)
	public void set_unchecked() {
		vertChecked = false;
		horChecked = false;
	}
	
	// Set the vertical matching flag of the cell
	public void set_vertChecked(boolean value) {
		vertChecked = value;
	}
	
	// Set the horizontal matching flag of the cell
	public void set_horChecked(boolean value) {
		horChecked = value;
	}
	
	// Get the current value of the cell
	public char get_value() {
		return value;
	}
	
	// Set the value of the cell to a specified character
	public void set_value(char value) {
		this.value = value;
	}
	
	// Check if the cell is horizontally matched
	public boolean is_horChecked() {
		return horChecked;
	}
	
	// Check if the cell is vertically matched
	public boolean is_vertChecked() {
		return vertChecked;
	}
}
