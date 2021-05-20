
public class backtracker {


	public static void main(String[] args){
		Chessboard fourqueens = new Chessboard(4);
		fourqueens.backtrack(0,0);
		fourqueens.showSolution();
	}


	
}
