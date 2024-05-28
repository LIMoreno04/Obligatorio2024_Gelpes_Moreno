package uy.edu.um.adt.stack;


import uy.edu.um.adt.Exceptions.EmptyStackException;

public interface MyStack<T> {

	void push(T value);
	
	T pop() throws EmptyStackException;
	
	T peek();
	
	int size();

	boolean isEmpty();
}
