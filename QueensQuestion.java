package queens;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rantao 
 *  八皇后问题 
 *  使用一维数组解决
 */
public class QueensQuestion {
	private int queens; // 皇后数
	private static final int defaultQueens = 8; // 默认皇后数
	private List<int[]> programmes; // 所有方案

	public QueensQuestion() {
		this(defaultQueens);
	}

	public QueensQuestion(int queens) {
		this.queens = queens;
	}

	/**
	 * 找出满足条件的所有方案
	 */
	public List<int[]> solve() {
		programmes = new ArrayList<int[]>();
		int[] queensArray = new int[queens];
		for (int i = 0; i < queensArray.length; i++) {
			queensArray[i] = -1;
		}
		recursionSolve(queensArray, 0);
		return programmes;
	}

	/**
	 * 递归求解八皇后问题
	 * 
	 * @param queensArray
	 * @param row
	 */
	private void recursionSolve(int[] queensArray, int row) {
		if (row == queens)
			return;
		for (int i = 0; i < queens; i++) {
			queensArray[row] = i;
			if (!validate(queensArray, row))
				continue;
			if (row == queens - 1) {
				int[] queensArrayCopy = new int[queens];
				System.arraycopy(queensArray, 0, queensArrayCopy, 0, queens);
				programmes.add(queensArrayCopy);
			}
			recursionSolve(queensArray, row + 1);
		}
	}

	/**
	 * 验证合法性
	 * 
	 * @param queensArray
	 * @param row
	 * @return
	 */
	private boolean validate(int[] queensArray, int row) {
		if (row == 0)
			return true;
		for (int i = 0; i < row; i++) {
			if (queensArray[i] == queensArray[row]
					|| i - queensArray[i] == row - queensArray[row]
					|| i + queensArray[i] == row + queensArray[row]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		QueensQuestion queensQuestion = new QueensQuestion();
		List<int[]> list = queensQuestion.solve();
		System.out.println("八皇后问题一共有" + list.size() + "种解决方案");
		System.out.println("所有方案如下：");
		for (int i = 0; i < list.size(); i++) {
			int[] array = list.get(i);
			printQueens(array);
			System.out.println();
		}
	}

	/**
	 *  打印某种方案
	 * @param queensArray
	 */
	private static void printQueens(int[] queensArray) {
		for (int i = 0; i < queensArray.length; i++) {
			System.out.print(queensArray[i] + " ");
		}
	}
}
