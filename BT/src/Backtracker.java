import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backtracker {

	static int[] x;
	static boolean consistent;
	static List<Integer>[] domain;
	static Iterator<Integer> itr;
	static List<Decision> decisions;

	Backtracker() {
		x = new int[4];
		domain = new ArrayList[4];
		decisions = new ArrayList<Decision>(0);

		for (int i = 0; i < 4; i++) {
			x[i] = -1;
			domain[i] = new ArrayList<Integer>(4);
			for (int j = 0; j < 4; j++)
				domain[i].add(j, new Integer(j));
		}
		backtrack(0);

	}

	public static void backtrack(int depth) {

		int value = 0;
		for (itr = domain[depth].iterator(); itr.hasNext();) {
			value = itr.next();
			assign(depth, value);
			consistent = test(depth);
			if (consistent) {
				decisions.add(new Decision(depth, value));
				if (depth == 3) {
					showSolution();
				} else
					backtrack(depth + 1);
			} else if (domain[depth].isEmpty()) {
				wipeout(depth);
			}

		}

	}

	public static void wipeout(int depth) {
		if (domain[depth].size() == 0 && depth == 0) {
			System.exit(0);
		}
		itr = domain[depth].iterator();
		if (domain[depth].size() != 0) {
			domain[depth + 1].clear();
			for (int j = 0; j < 4; j++)
				domain[depth + 1].add(j);

		} else if (domain[depth].size() == 0) {
			if (!decisions.isEmpty()) {
				Decision last = decisions.get(decisions.size() - 1);
				decisions.remove(last);
			}

			x[depth] = -1;
			domain[depth].clear();
			for (int j = 0; j < 4; j++) {
				domain[depth].add(j);
			}
			wipeout(depth - 1);
		}
		backtrack(depth);

	}

	public static void assign(int depth, int value) {
		x[depth] = value;
		itr.remove();

	}

	public static boolean test(int depth) {
		for(int i=0;i<depth;i++){
			if(x[i]==x[depth])
				return false;
			if((x[i]- x[depth])== (depth - i))
				return false;
			if((x[depth]- x[i]) == (depth- i))
				return false;
			
		}
		return true;
	}
	
	

	public static void showSolution() {
		System.out.println("Solution with BT");
		System.out.println(x[0]);
		System.out.println(x[1]);
		System.out.println(x[2]);
		System.out.println(x[3]);
		return;
	}
}
