import java.util.Arrays;

public class Grid {
	private int[][] grid = new int[9][9];
	
	public Grid(int[][] arr){
		grid = arr;
	}
	
	public int getValue(int x, int y){
		return grid[x][y];
	}
	
	public void setValue(int x, int y, int value){
		grid[x][y] = value;
	}
	
	public void printGrid(){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(Integer.toString(grid[i][j]) + "  ");
			}
			System.out.println();
		}
	}
	
	public boolean squareCheck(int x, int y){
		int squareX = x / 3;
		int squareY = y / 3;
		int[] nums = {0,0,0,0,0,0,0,0,0};
		
		for (int i = squareX ; i < squareX + 3; i++) { 
			for (int j = squareY; j < squareY + 3; j++) {
				if (grid[i][j] != 0) {
					if(nums[grid[i][j] - 1] == 1) return false;	//Already found in square
					nums[grid[i][j] - 1] = 1; //Found num in square
				}
			}
		}
		return true;
	}
	
	public boolean lineCheck(int x, int y){
		int[] nums = {0,0,0,0,0,0,0,0,0};
		
		for (int i = 0 ; i < 9; i++) {
			if (grid[i][y] != 0){
				if(nums[grid[i][y] - 1] == 1) return false;	//Already found in horizontal line
				nums[grid[i][y] - 1] = 1; //Found num in line
			}
		}
	
		for (int i = 0 ; i < 9; i++) {
			nums[i] = 0;
		}
		
		for (int i = 0 ; i < 9; i++) {
			if (grid[x][i] != 0){
				if(nums[grid[x][i] -1] == 1) return false;	//Already found in vertical line
				nums[grid[x][i] - 1] = 1; //Found num in line
			}
		}
		
		return true;
	}
	
	public boolean check(int x, int y){
		return squareCheck(x, y) && lineCheck(x, y); 
	}
}
