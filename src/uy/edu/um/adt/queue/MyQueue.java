package uy.edu.um.adt.queue;

import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;

public interface MyQueue<T> {

	void enqueue(T value);

	T dequeue() throws EmptyQueueException;
	
	boolean contains(T value);

	T get(int i) throws InvalidValue;
	
	int size();

	boolean isEmpty();
}
