package Tecent2018;

import java.util.Stack;

/**
 * ������ջʵ�ֶ��еĲ���
 * ʵ��˼·��һ��ջ������ջ����һ�������ջ
 * ����ʵ�֣�
 * ���������Ԫ��ʱ��ֱ�ӽ�Ԫ����ջ��S1,
 * �Ӷ���ȡ��Ԫ��ʱ�������ж�ջS2�Ƿ�Ϊ�գ������Ϊ��ֱ�ӵ���S2ջ��Ԫ�أ�
 * ���Ϊ�գ���S1��Ԫ�����ε�����ջ��S2,���һ��ֱ�ӵ���������ջ
 * @author rantao
 *
 * @param <E>
 */
public class MyQueue<E> {
	
	private Stack<E> inStack = new Stack<E>();
	private Stack<E> outStack = new Stack<E>();
	
	/**
	 * �����������Ԫ��
	 * @param e
	 * @return
	 */
	public boolean offer(E e) {
		return inStack.push(e) == e;
	}
	
	/**
	 *  ��ȡ����ͷ��Ԫ��
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
	 * ��ȡ����ͷ��Ԫ�ز��Ƴ�
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
	 * �����Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * ���г���
	 * @return
	 */
	public int size() {
		return inStack.size() + outStack.size();
	}
}
