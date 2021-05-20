public class Chessboard {

	int size;
	int board[][];
	int assign[];
	int x, d, past, depth = 0;
	boolean consistent;
	int temp;

	Chessboard(int s) {
		size = s;
		board = new int[4][4];
		assign = new int[4];
		assign[0]=-1;
		assign[1]=-1;
		assign[2]=-1;
		assign[3]=-1;
		
	}

	void backtrack(int x,int d) {
		
			if(test() && d==4){
				showSolution();
				Chessboard cb= new Chessboard(4);
				cb.backtrack(assign[0]+1,0);
			}
			
				
			else
				assign(x,d);
			
			if(!test() && x==3){
				board[d][x]=0;
				assign[d]=-1;
				temp=assign[d-1];
				board[d-1][temp]=0;
				assign[d-1]=-1;
				if(temp!=3)
					backtrack(temp+1,d-1);
				else {
					board[d-2][3]=0;
					temp=assign[d-2];
					assign[d-2]=-1;
					board[d-2][temp]=0;
					backtrack(temp+1,d-2);
					
				}
					
			}
			else if(test()){
				backtrack(0,d+1);
			}
			else if(!test()){
				assign[d]=-1;
				board[d][x] = 0;
				backtrack(x+1,d);
			}
			
		}

	

	void showSolution() {
		for (int i = 0; i < size; i++) {
			System.out.println();
			for (int j = 0; j < size; j++)
				System.out.print(board[i][j]);
		}
		System.out.println();
		System.out.print("----");
	}

	void assign(int x, int d) {

		if (board[d][x] == 0){
			board[d][x] = 1;
			assign[d]=x;
		}
		return;
	}

	boolean test() {
		int column = 0;
		int row = 0;
		int diag = 0;
		int d=4;
		
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < size; j++) {
				row += board[i][j];
			}
			if (row > 1)
				return false;
			row = 0;
		}

		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				column += board[i][j];
			}
			if (column > 1)
				return false;
			column = 0;
		}
		/***********************************************************************************************/
		diag=board[1][0]+board[0][1];
		if(diag>1)
			return false;
		else
			diag=0;
		
		diag=board[2][0]+board[1][1]+board[0][2];
		if(diag>1)
			return false;
		else
			diag=0;
		
		diag=board[3][0]+board[2][1]+board[1][2]+board[0][3];
		if(diag>1)
			return false;
		else
			diag=0;
			
		diag=board[3][1]+board[2][2]+board[1][3];
		if(diag>1)
			return false;
		else
			diag=0;
		
		diag=board[3][2]+board[2][3];
		if(diag>1)
			return false;
		else
			diag=0;
		/***********************************************************************************************/
		/***********************************************************************************************/
		diag=board[2][0]+board[3][1];
		if(diag>1)
			return false;
		else
			diag=0;
		
		diag=board[1][0]+board[2][1]+board[3][2];
		if(diag>1)
			return false;
		else
			diag=0;
		
		diag=board[0][0]+board[1][1]+board[2][2]+board[3][3];
		if(diag>1)
			return false;
		else
			diag=0;
			
		diag=board[0][1]+board[1][2]+board[2][3];
		if(diag>1)
			return false;
		else
			diag=0;
		
		diag=board[0][2]+board[1][3];
		if(diag>1)
			return false;
		else
			diag=0;
		/***********************************************************************************************/

		return true;
	}

}
