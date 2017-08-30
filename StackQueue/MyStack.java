package Tecent2018;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ����������ʵ�ֶѵĲ���
 * ����˼·�����е�ͷ����ջ�Ķ�����Ӧ�������仯�����˼�룬ÿ�β�������ݷ��ڶ��е�ͷ��
 * 1���������У�����һ������Ϊ�գ���һ�����д�ż���Ķ���
 * 2���������ݣ�ѡ��ն����в��룬�ٽ�����һ�����е��������γ����в��뱾������
 * @author rantao
 *
 */
public class MyStack<E> {
	
	private Queue<E> queue1 = new LinkedList<E>();
	private Queue<E> queue2 = new LinkedList<E>();
	
	/**
	 * ��ջ����Ԫ��
	 * ʱ�临�Ӷ�O(n)
	 * @param e
	 */
	public E push(E e) {
		Queue<E> emptyQueue = getEmptyQueue();
		Queue<E> notEmptyQueue = getNotEmptyQueue();
		
		if(emptyQueue != null) 
			emptyQueue.offer(e);
		else 
			return null;
		
		// �ǿն����е�����Ԫ�ؼ���ն�����
		while (notEmptyQueue != null && !notEmptyQueue.isEmpty()) {
			emptyQueue.offer(notEmptyQueue.poll());
		}
		return e;
	}
	
	/**
	 * ����ջ��Ԫ�أ�������ɾ��
	 * @return
	 */
	public E peek() {
		if (isEmpty()) throw new EmptyStackException();
		Queue<E> queue = getNotEmptyQueue();
		return queue.peek();
	}
	
	/**
	 * ����ջ��Ԫ�ز�ɾ��
	 * @return
	 */
	public E pop() {
		if (isEmpty()) throw new EmptyStackException();
		Queue<E> queue = getNotEmptyQueue();
		return queue.poll();
	}
	
	/**
	 * �Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty() {
		if (getNotEmptyQueue() == null) return true;
		return false;
	}
	
	/**
	 * ��ջԪ�ظ���
	 * @return
	 */
	public int size() {
		Queue<E> queue = getNotEmptyQueue();
		if (queue == null) return 0;
		return queue.size(); 
	}
	
	/**
	 * ���������еĿն���
	 * @return
	 */
	private Queue<E> getEmptyQueue() {
		if (queue1.isEmpty()) return queue1;
		if (queue2.isEmpty()) return queue2;
		return null;
	}
	
	/**
	 * ���������еķǿն���
	 * @return
	 */
	private Queue<E> getNotEmptyQueue() {
		if (!queue1.isEmpty()) return queue1;
		if (!queue2.isEmpty()) return queue2;
		return null;
	}
}
