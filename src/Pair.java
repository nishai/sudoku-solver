
public class Pair {
	public int x, y;
	
	public Pair(int a, int b){
		x = a;
		y = b;
	}
        public String toString(){
            return "("+x+","+y+")";
        }
        public void set(int x,int y){
            this.x = x;
            this.y = y;
        }
}
