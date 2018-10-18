import java.util.Stack;
import java.util.Scanner;

public class Program {
	static Grid myGrid = new Grid();
	
	public static Grid backtrack(){
		Stack<Pair> stack = new Stack<Pair>();
		Pair parent = new Pair(0,0);
	
        for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            if (myGrid.isEmpty(i, j)){
	                for (int k = 1; k < 10; k++) {
	                    if(myGrid.checkValue(i, j, k)){
	                        stack.push(new Pair(i,j));
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
	   
	public static void main(String args[]){
		readInput(args[0]);
        backtrack();
		myGrid.printGrid();
	}
}
