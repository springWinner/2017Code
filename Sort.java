package sort;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author 冉涛
 * 排序算法（基数排序除外）
 */
public class Sort {
	/**
	 * 冒泡排序
	 * 时间复杂度O（n * n）
	 * 稳定性：稳定（稳定性解释：数组中相同的元素在排序前和排序后前后顺序不变）
	 * @param arr
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			boolean flag = true;  //标识符，是否排好序
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					swrp(arr, j, j + 1);
					flag = false;
				}
			}
			// 如果上次没有发生交换则认为已经排好序
			if (flag) break;
		}
	}
	
	/**
	 * 选择排序
	 * 时间复杂度O（n * n）
	 * 稳定性：不稳定
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void  selectSort(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[minIndex].compareTo(arr[j]) > 0)
					minIndex = j;
			}
			if (i != minIndex) {
				swrp(arr, i, minIndex);
			}
		}
	}
	
	/**
	 * 插入排序
	 * 时间复杂度O（n * n）
	 * 稳定性：稳定
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void inserSort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			T curElement = arr[i];
			int j = i - 1;
			for (; j >= 0 && arr[j].compareTo(curElement) > 0; j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = curElement;
		}
	}
	
	/**
	 * 快速排序
	 * 时间复杂度O（n * logn）
	 * 稳定性：稳定（我的是稳定的）双向是不稳定的
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}
	
	/**
	 * 归并排序
	 * 时间复杂度：O(n * logn)
	 * 稳定性：不稳定
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
		if (arr.length > 1) {
			int half = (arr.length % 2 == 0 ?  arr.length / 2 :  arr.length / 2 + 1);
			T[] left = (T[])new Comparable[half];
			System.arraycopy(arr, 0, left, 0, half);
			mergeSort(left);
			T[] right = (T[])new Comparable[arr.length - half];
			System.arraycopy(arr, half, right, 0, arr.length - half);
			mergeSort(right);
			T[] temp = merge(left, right);
			System.arraycopy(temp, 0, arr, 0, temp.length);
		}
	}
	
	/**
	 * 堆排序
	 * 时间复杂度：O(n * logn)
	 * 稳定性：不稳定
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void heapSort(T[] arr) {
		Heap heap = new Heap(arr);
		int index = 0;
		while (!heap.isEmpty()) {
			arr[index++] = (T) heap.removeElement();
		}
	}
	
	/**
	 * 希尔排序，最重要的是如何确认希尔增量
	 * 插入排序的改进版
	 * 时间复杂度：O（n*n）
	 * 稳定性：不稳定
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void shellSort(T[] arr) {
		int[] shellIncrements = new int[] {5, 3, 2, 1};  //默认希尔增量
		for (int increment : shellIncrements) {
			for (int i = 0; i < increment; i++) {
				for (int j = increment + i; j < arr.length; j += increment) {
					T curElement = arr[j];
					int k =  j - increment;
					for (; k >= i && arr[k].compareTo(curElement) > 0; k -= increment) {
						arr[k + increment] = arr[k];
					}
					arr[k + increment] = curElement;
				}
			}
		}
	}
	
	/**
	 * 快速排序递归结构
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static <T extends Comparable<? super T>> void quickSort(T[] arr, int low, int high) {
		if (low < high) {
			int index = partion(arr, low, high);
			quickSort(arr, low, index - 1);
			quickSort(arr, index + 1, high);
		}
	}
	/**
	 * 快速排序关键算法
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	private static <T extends Comparable<? super T>> int partion(T[] arr, int low, int high) {
		T povot = arr[low];
		int index = low + 1;
		for (int i = index; i <= high; i++) {
			if (povot.compareTo(arr[i]) > 0) {
				if (i != index) 
					swrp(arr, i, index);
				index++;
			}
		}
		swrp(arr, --index, low);
		return index;
	}
	
	/**
	 * 按升序合并两个数组排好序的数组
	 * @param left
	 * @param right
	 * @return
	 */
	private static <T extends Comparable<? super T>> T[] merge(T[] left, T[] right) {
		T[] arr = (T[])new Comparable[left.length + right.length];
		int curIndexLeft = 0;
		int curIndexRight = 0;
		int curIndex = 0;
		while (curIndexLeft < left.length && curIndexRight < right.length) {
			if (left[curIndexLeft].compareTo(right[curIndexRight]) >= 0) {
				arr[curIndex++] = right[curIndexRight++];
			} else {
				arr[curIndex++] = left[curIndexLeft++];
			}
		}
		while (curIndexLeft < left.length) {
			arr[curIndex++] = left[curIndexLeft++];
		}
		while (curIndexRight < right.length) {
			arr[curIndex++] = right[curIndexRight++];
		}
		return arr;
	}
	
	/**
	 * 交换数组下标两个数
	 * @param arr
	 * @param index1
	 * @param index2
	 */
	private static void swrp(Object[] arr, int index1, int index2) {
		Object temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/**
	 * 打印数组
	 * @param arr
	 */
	public static void printArr(Object[] arr) {
		for (Object obj : arr) {
			System.out.print(obj);
			System.out.print("  ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Integer[] arr1 = new Integer[]{ 1, 2 , 4, 3};
		Sort.bubbleSort(arr1);
		Sort.printArr(arr1);
		Integer[] arr2 = new Integer[]{ 8, 4, 5, 3, 2, 4, 9};
		Sort.selectSort(arr2);
		Sort.printArr(arr2);
		Integer[] arr3 = new Integer[]{ 8, 4, 5, 3, 2, 7, 6};
		Sort.inserSort(arr3);
		Sort.printArr(arr3);
		Integer[] arr4 = new Integer[]{ 8, 4, 5, 3, 2, 7, 6};
		Sort.quickSort(arr4);
		Sort.printArr(arr4);
		Node[] arr5 = new Node[]{ new Node<Integer>(5, 0),  new Node<Integer>(2, 1), new Node<Integer>(5, 2), new Node<Integer>(4, 3), new Node<Integer>(8, 4)};
		Sort.quickSort(arr5);
		for (Node node : arr5) {
			System.out.println(node.t + ":" + node.index);
		}
		Integer[] arr6 = new Integer[]{ 8, 4, 5, 3, 2, 7, 6};
		Sort.mergeSort(arr6);
		Sort.printArr(arr6);
		Integer[] arr7 = new Integer[]{ 8, 4, 5, 3, 2, 10, 20, 45, 0, 6};
		Sort.heapSort(arr7);
		Sort.printArr(arr7);
		Integer[] arr8 = new Integer[]{ 8, 4, 10, 25, 30, 60, -5, 5, 3, 2, 10, 20, 45, 0, 6};
		Sort.shellSort(arr8);
		Sort.printArr(arr8);
	}
	
	/**
	 * 
	 * @author rantao
	 * 最小化堆
	 * 定义：堆就是一个二叉树，子节点大于或等于父节点的值
	 * 使用数组实现，下标从0开始，所以一个节点的左子节点的下标为 2 * parentIndex + 1, 右子节点的下标为 2 * parentIndex + 1
	 * @param <T>
	 */
	static class Heap<T extends Comparable<T>> {
		private List<T> list = new ArrayList<T>();
		
		public Heap() {}
		
		public Heap(T[] elments) {
			for (T t : elments) {
				addElement(t);
			}
		}
		
		/**
		 * 往堆中添加一个元素
		 * 首先添加到数组的最后一位，再根据堆的特性调整堆的结构
		 * @param t
		 */
		public void addElement(T t) {
			list.add(t);
			int curIndex = list.size() - 1;
			while (curIndex > 0) {
				int parentIndex = curIndex % 2 == 0 ? curIndex / 2 - 1 : curIndex / 2;
				if (list.get(curIndex).compareTo(list.get(parentIndex)) < 0) {
					T temp = list.get(curIndex);
					list.set(curIndex, list.get(parentIndex));
					list.set(parentIndex, temp);
					curIndex = parentIndex;
				} else {
					break;
				}
			}
		}
		
		/**
		 * 移除一个元素，及移除根节点，及下标为0的元素
		 * 移除后将最后一个元素放在根节点的位置，再根据堆的特性调整堆的结构
		 * @return
		 */
		public T removeElement() {
			T root = list.get(0);
			list.set(0, list.get(list.size() - 1));
			list.remove(list.size() - 1);
			int curIndex = 0;
			while (curIndex < list.size()) {
				int leftIndex = curIndex * 2 + 1;
				int rightIndex = curIndex * 2 + 2;
				int selectIndex = curIndex;
				if (leftIndex >= list.size()) {
					break;
				} else if (rightIndex >= list.size()) {
					selectIndex = leftIndex;
				} else {
					selectIndex = list.get(leftIndex).compareTo(list.get(rightIndex)) < 0 ? leftIndex : rightIndex;
				}
				if (list.get(curIndex).compareTo(list.get(selectIndex)) > 0) {
					T temp = list.get(curIndex);
					list.set(curIndex, list.get(selectIndex));
					list.set(selectIndex, temp);
					curIndex = selectIndex;
				} else {
					break;
				}
			}
			return root;
		}
		
		public boolean isEmpty() {
			return list.size() == 0;
		}
	
	}
	/**
	 * 
	 * @author 冉涛
	 * 测试稳定性
	 * @param <T>
	 */
	static class Node<T extends Comparable<T>> implements Comparable<Node> {
		T t;
		int index;
		
		public Node(T t, int index) {
			this.t = t;
			this.index = index;
		}

		@Override
		public int compareTo(Node o) {
			return this.t.compareTo((T) o.t);
		}
	}
}
