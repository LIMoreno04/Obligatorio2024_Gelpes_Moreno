package uy.edu.um.adt.linkedlist;

import uy.edu.um.adt.Exceptions.EmptyListException;
import uy.edu.um.adt.Exceptions.InvalidValue;

public interface MyList<T> {

    void add(T value);

    T get(int position) throws InvalidValue;

    boolean contains(T value);

    void remove(T value) throws InvalidValue;

    int size();

    T getFirst();

    boolean isEmpty();
}
