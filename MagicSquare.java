package magicSquare;
/**
 * 
 * @author rantao
 * 魔方阵，只限规模为奇数
 */
public class MagicSquare {

	private int scale; //规模
	private static final int DEFALUT_SCALE = 3;
	
	public MagicSquare() {
		this(DEFALUT_SCALE);
	}
	
	public MagicSquare(int scale) {
		setScale(scale);
	}
	
	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		if (scale <= 0) {
			throw new IllegalArgumentException("参数不能为负数和0");
		}
		if (scale % 2 == 0) {
			throw new IllegalArgumentException("参数不能为偶数");
		}
		this.scale = scale;
	}

	/**
	 * 求解魔方阵
	 * @return 
	 * 返回二维数组
	 */
	public int[][] solve() {
		int[][] square = new int[scale][scale];
		int curRowIndex = 0;
		int curColIndex = scale / 2;
		// 1、将1放在第一行的中间位置
		square[curRowIndex][curColIndex] = 1;
		
		for (int i = 2; i <= scale * scale; i++) {
			int lastRowIndex = curRowIndex;
			int lastColIndex = curColIndex;
			curRowIndex--;
			curColIndex++;
			
			//2、如果行的位置小于最小行，则行为最后一行
			if (curRowIndex < 0) { 
				curRowIndex = scale - 1;
			}
			//3、如果列的位置大于最大列，则列为第一列
			if (curColIndex >= scale) {  
				curColIndex = 0;
			}
			//4、如果按上面规则确定的位置上已有数，或上一个数是第一行第n列时，则把下一个数放在上一个数的下面
			if (square[curRowIndex][curColIndex] > 0 || (lastRowIndex == 0 && lastColIndex == scale - 1)) { 
				curRowIndex = lastRowIndex + 1;
				curColIndex = lastColIndex;
			}
			
			square[curRowIndex][curColIndex] = i;
		}
		
		return square;
	}
	
	public static void main(String[] args) {
		MagicSquare magicSquare = new MagicSquare();
		int[][] square = magicSquare.solve();
		printArray(square);
	}
	
	public static void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.printf("%-5d", array[i][j]);
			}
			System.out.println();
		}
	}
}
