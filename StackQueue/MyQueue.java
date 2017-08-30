package Tecent2018;

import java.util.Stack;

/**
 * 用两个栈实现队列的操作
 * 实现思路：一个栈做插入栈，另一个做输出栈
 * 具体实现：
 * 往队列添加元素时，直接将元素入栈到S1,
 * 从队列取出元素时，首先判断栈S2是否为空，如果不为空直接弹出S2栈顶元素，
 * 如果为空，将S1的元素依次弹出入栈到S2,最后一个直接弹出不用入栈
 * @author rantao
 *
 * @param <E>
 */
public class MyQueue<E> {
	
	private Stack<E> inStack = new Stack<E>();
	private Stack<E> outStack = new Stack<E>();
	
	/**
	 * 往队列中添加元素
	 * @param e
	 * @return
	 */
	public boolean offer(E e) {
		return inStack.push(e) == e;
	}
	
	/**
	 *  获取队列头的元素
	 * @return
	 */
	public E peek() {
		if (!outStack.isEmpty()) return outStack.peek();
		if (inStack.isEmpty()) return null;
		while (inStack.size() > 1) {
			outStack.push(inStack.pop());
		}
		E e = inStack.pop();
		outStack.push(e);
		return e;
	}
	
	/**
	 * 获取队列头的元素并移除
	 * @return
	 */
	public E poll() {
		if (!outStack.isEmpty()) return outStack.pop();
		if (inStack.isEmpty()) return null;
		while (inStack.size() > 1) {
			outStack.push(inStack.pop());
		}
		return inStack.pop();
	}
	
	/**
	 * 队列是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * 队列长度
	 * @return
	 */
	public int size() {
		return inStack.size() + outStack.size();
	}
}
