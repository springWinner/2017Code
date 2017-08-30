package Tecent2018;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现堆的操作
 * 解题思路：队列的头部与栈的顶部对应起来，变化插入的思想，每次插入的数据放在队列的头部
 * 1、两个队列，其中一个队列为空，另一个队列存放加入的对象
 * 2、插入数据，选择空队列中插入，再将另外一个队列的数据依次出队列插入本队列中
 * @author rantao
 *
 */
public class MyStack<E> {
	
	private Queue<E> queue1 = new LinkedList<E>();
	private Queue<E> queue2 = new LinkedList<E>();
	
	/**
	 * 往栈顶加元素
	 * 时间复杂度O(n)
	 * @param e
	 */
	public E push(E e) {
		Queue<E> emptyQueue = getEmptyQueue();
		Queue<E> notEmptyQueue = getNotEmptyQueue();
		
		if(emptyQueue != null) 
			emptyQueue.offer(e);
		else 
			return null;
		
		// 非空队列中的所有元素加入空队列中
		while (notEmptyQueue != null && !notEmptyQueue.isEmpty()) {
			emptyQueue.offer(notEmptyQueue.poll());
		}
		return e;
	}
	
	/**
	 * 弹出栈顶元素，但并不删除
	 * @return
	 */
	public E peek() {
		if (isEmpty()) throw new EmptyStackException();
		Queue<E> queue = getNotEmptyQueue();
		return queue.peek();
	}
	
	/**
	 * 弹出栈顶元素并删除
	 * @return
	 */
	public E pop() {
		if (isEmpty()) throw new EmptyStackException();
		Queue<E> queue = getNotEmptyQueue();
		return queue.poll();
	}
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
		if (getNotEmptyQueue() == null) return true;
		return false;
	}
	
	/**
	 * 堆栈元素个数
	 * @return
	 */
	public int size() {
		Queue<E> queue = getNotEmptyQueue();
		if (queue == null) return 0;
		return queue.size(); 
	}
	
	/**
	 * 两个队列中的空队列
	 * @return
	 */
	private Queue<E> getEmptyQueue() {
		if (queue1.isEmpty()) return queue1;
		if (queue2.isEmpty()) return queue2;
		return null;
	}
	
	/**
	 * 两个队列中的非空队列
	 * @return
	 */
	private Queue<E> getNotEmptyQueue() {
		if (!queue1.isEmpty()) return queue1;
		if (!queue2.isEmpty()) return queue2;
		return null;
	}
}
