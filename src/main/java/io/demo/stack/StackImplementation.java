package io.demo.stack;

public class StackImplementation<T> implements Stack<T> {

	Node<T> top;
	
	@Override
	public T pop() {
//		if (top == null)
//			throw new StackEmptyException();
		
		Node<T> newNode = top;
		top = top.next;
		return newNode.getData();
	}

	@Override
	public void push(T o) {
		Node<T> newNode = new Node<T>();
		newNode.setData(o);
		
//		if(top == null) {
//			top = newNode;
//		} else {
			newNode.next = top;
			top = newNode;
//		}
	}

	@Override
	public T peek() {
		return top.getData();
	}

	public static void main(String[] args) {
		
		StackImplementation<String> mySI = new StackImplementation<String>();
		
		String o = "TEST";
		
		mySI.push(o);
		System.out.println(mySI.top.getData());
		
		System.out.println("peek: " + mySI.peek());
		
		String o1 = "Second TEST";
		mySI.push(o1);
		System.out.println(mySI.top.getData());

		mySI.pop();
		System.out.println(mySI.top.getData());

	}



}

class Node<T> {
	private T data;
	Node<T> next;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}