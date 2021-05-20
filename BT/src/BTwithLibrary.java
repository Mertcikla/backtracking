import solver.Solver;
import solver.constraints.Constraint;
import solver.constraints.IntConstraintFactory;
import solver.search.strategy.IntStrategyFactory;
import solver.variables.IntVar;
import solver.variables.VariableFactory;

public class BTwithLibrary {
	static Solver fourqueens;
	static IntVar[] row;
	static boolean consistent;

	public static void main(String[] args) {
		fourqueens = new Solver("Four Queens Problem");
		row = VariableFactory.boundedArray("vars", 4, 0, 3, fourqueens);

		Constraint verticalAttack = IntConstraintFactory.alldifferent(row);
		fourqueens.post(verticalAttack);

		Constraint diagonalAttack01 = IntConstraintFactory.distance(row[0], row[1], "!=", 1);
		fourqueens.post(diagonalAttack01);
		Constraint diagonalAttack02 = IntConstraintFactory.distance(row[0], row[2], "!=", 2);		
		fourqueens.post(diagonalAttack02);
		Constraint diagonalAttack03 = IntConstraintFactory.distance(row[0], row[3], "!=", 3);
		fourqueens.post(diagonalAttack03);


		Constraint diagonalAttack12 = IntConstraintFactory.distance(row[1], row[2], "!=", 1);
		fourqueens.post(diagonalAttack12);
		Constraint diagonalAttack13 = IntConstraintFactory.distance(row[1], row[3], "!=", 2);
		fourqueens.post(diagonalAttack13);

		Constraint diagonalAttack23 = IntConstraintFactory.distance(row[2], row[3], "!=", 1);
		fourqueens.post(diagonalAttack23);

		solveUsingLibrary();
		Backtracker bt = new Backtracker();

	}

	public static void showSolution() {
		System.out.println("Solution with choco");
		System.out.println(row[0].getValue());
		System.out.println(row[1].getValue());
		System.out.println(row[2].getValue());
		System.out.println(row[3].getValue());
		return;
	}

	public static void solveUsingLibrary() {
		fourqueens.set(IntStrategyFactory.generateAndTest(fourqueens));

		if (fourqueens.findSolution()) {
			do {
				showSolution();

			} while (fourqueens.nextSolution());
		}

		System.out.println("Done");
	}
}
