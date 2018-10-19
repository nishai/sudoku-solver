import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.Scanner;

public class Program {
	static Grid myGrid = new Grid();
	static int backtrack_count = 0;

	public static Grid backtrack(){
		backtrack_count = 0;
		Stack<Pair> stack = new Stack<Pair>();
		Pair parent = new Pair(0,0);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (myGrid.isEmpty(i, j)){
					for (int k = 1; k < 10; k++) {
						if(myGrid.checkValue(i, j, k)){
							stack.push(new Pair(i,j));
							backtrack_count++;
							break;
						}

						while(k == 9){
							myGrid.setValue(i, j, 0);
							parent = stack.pop();

							i = parent.x;
							j = parent.y;
							k = myGrid.getValue(i,j);
						}
					}
				}
			}
		}
		return myGrid;
	}

	public static void readInput(String input) {
		int[] lineValues = new int[9];
		String[] lines = input.split("\n");
		String[] chars = new String[9];

		for (int i = 0; i < lines.length; i++) {
			chars = lines[i].split(" ");

			for (int j = 0; j < chars.length; j++) {
				myGrid.setValue(i, j,Integer.parseInt(chars[j]));
			}
		}
	}

	public static void readTestInput() throws FileNotFoundException {

		File file = new File("test.txt");
		Scanner input = new Scanner(file);

		String[] row = new String[9];

		for(int i = 0; i < row.length; i++){

			String line = input.nextLine();
			row = line.split(" ");

			for (int j = 0; j < row.length; j++) {
				myGrid.setValue(i, j,Integer.parseInt(row[j]));
			}
		}

	}

	public static void main(String args[]) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("data.csv"));
		StringBuilder sb = new StringBuilder();
		SudokuGenerator sg = new SudokuGenerator();
		
		sb.append("no.of clues,average_time,average_backtracks,best_time,best_backtracks,worst_time,worst_backtracks\n");
		
		for (int i = 17; i < 80; i++) {
			long sum_time = 0;
			long sum_backtracks = 0;
			
			long best_time = 500000;
			long worst_time = -1;
			long best_backtracks = Long.MAX_VALUE;
			long worst_backtracks = -1;
			
			for (int j = 0; j < 25; j++) {
				sg.nextBoard(i);
				myGrid = new Grid(sg.board);
				
				long startTime = System.nanoTime();
				backtrack();
				long endTime = System.nanoTime();
				long runtime = endTime - startTime;
				
				sum_time += runtime;
				sum_backtracks += backtrack_count;
				if (runtime > worst_time) {worst_time = runtime;}
				if (runtime < best_time) {best_time = runtime;}
				if (backtrack_count > worst_backtracks) {worst_backtracks = backtrack_count;}
				if (backtrack_count < best_backtracks) {best_backtracks = backtrack_count;}
				
				System.out.print("|");
			}
			
			long avg_time = sum_time / 25; 
			long avg_backtracks = sum_backtracks / 25; 
			sb.append(i + "," + avg_time + "," + avg_backtracks + "," + best_time + "," + best_backtracks + "," + worst_time + "," + worst_backtracks +"\n");
			
			System.out.println(i + " clues complete ");
		}
		
		pw.write(sb.toString());
		pw.close();
		System.out.println("Done writing csv.");
	}
}