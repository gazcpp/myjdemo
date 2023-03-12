package io.demo.stack;

public interface Stack<T> {
	  T pop();
	  void push(T o);
	  T peek();
	}