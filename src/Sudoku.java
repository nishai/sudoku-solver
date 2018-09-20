import java.util.Stack;

public class Sudoku {
	static int[][] grid = {{0,0,8,7,0,6,2,0,0},
					{0,0,7,0,1,0,3,0,0},
					{0,0,0,4,0,0,0,0,0},
					{3,0,0,0,0,0,6,0,9},
					{0,6,0,0,7,0,0,5,0},
					{1,0,5,0,0,0,0,0,2},
					{0,0,0,0,0,7,0,0,0},
					{0,0,1,0,6,0,8,0,0},
					{0,0,4,2,0,5,7,0,0}};
	
	static Grid myGrid = new Grid(grid); 
	
	public static Grid backtrack(){
		Stack<Pair> stack = new Stack<Pair>();
		Pair parent = new Pair(0,0);
		stack.push(parent);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(myGrid.getValue(i, j) == 0){
					for (int k = 1; k < 10; k++) {
						myGrid.setValue(i, j, k);
						if(myGrid.check(i, j)){
							stack.push(parent);
							break;
						}
						if(k == 9){
							myGrid.setValue(i, j, 0);
							parent = stack.pop();
							i = parent.x;
							j = parent.y;
						}
					}
				}
			}
		}
		return myGrid;
	}
	
	public static void main(String args[]){
		myGrid.printGrid();
	}
}
