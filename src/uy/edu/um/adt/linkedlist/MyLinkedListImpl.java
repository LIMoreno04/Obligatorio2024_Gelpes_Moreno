package uy.edu.um.adt.linkedlist;


import uy.edu.um.adt.Exceptions.EmptyListException;
import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.queue.MyQueue;
import uy.edu.um.adt.Exceptions.EmptyStackException;
import uy.edu.um.adt.stack.MyStack;

import java.util.Comparator;

public class MyLinkedListImpl<T> implements MyList<T>, MyQueue<T>, MyStack<T> {


    private Node<T> first;

    private Node<T> last;

    public MyLinkedListImpl() {
        this.first = null;
        this.last = null;
    }


    @Override
    public void add(T value) {
        addToTheEnd(value);
    }

    private void addToBeginning(T value) {
        if (value != null) {

            Node<T> elementToAdd = new Node<>(value);

            if (this.first == null) { // si la lista es vacia

                this.first = elementToAdd;
                this.last = elementToAdd;

            } else { // en caso de no ser vacia se agrega al comienzo

                elementToAdd.setNext(this.first);
                this.first = elementToAdd;
            }

        } else {
            // si el elemento es vacio se ignora
        }
    }

    private void addToTheEnd(T value) {
        if (value != null) {

            Node<T> elementToAdd = new Node<>(value);

            if (this.first == null) { // si la lista es vacia

                this.first = elementToAdd;
                this.last = elementToAdd;

            } else { // en caso de no ser vacia se agrega al final

                this.last.setNext(elementToAdd);
                this.last = elementToAdd;
            }

        } else {
            // si el elemento es vacio se ignora
        }
    }

    public T get(int position) throws InvalidValue {
        if (position < 0 || position >= size()) {
            throw new InvalidValue();
        }

        T valueToReturn = null;
        int tempPosition = 0;
        Node<T> temp = this.first;

        // Se busca el nodo que corresponde con la posicion
        while (temp != null && tempPosition != position) {

            temp = temp.getNext();
            tempPosition++;

        }

        // si se encontro la posicion se retorna el valor
        // en caso que se haya llegado al final y no se llego a la posicion se retorna null
        if (tempPosition == position) {


            valueToReturn = temp.getValue();

        }

        return valueToReturn;
    }

    public boolean contains(T value) {
        boolean contains = false;
        Node<T> temp = this.first;

        while (temp != null && !temp.getValue().equals(value)) {

            temp = temp.getNext();

        }

        if (temp != null) { // Si no se llego al final de la lista, se encontro el valor

            contains = true;

        }

        return contains;
    }

    public void remove(T value) throws InvalidValue {
        Node<T> beforeSearchValue = null;
        Node<T> searchValue = this.first;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value)) {

            beforeSearchValue = searchValue;
            searchValue = searchValue.getNext();

        }

        if (searchValue != null) { // si encontre el elemento a eliminar

            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.first && searchValue != this.last) {

                Node<T> temp = this.first;
                this.first = this.first.getNext(); // salteo el primero

                temp.setNext(null); // quito referencia del elemento eliminado al siguiente.

                // Verifico si es el primer valor (caso borde) y no el primero
            } else if (searchValue == this.last && searchValue != this.first) {

                beforeSearchValue.setNext(null);
                this.last = beforeSearchValue;

                // Si es el primer valor y el ultimo (lista de un solo valor)
            } else if (searchValue == this.last && searchValue == this.first) {

                this.first = null;
                this.last = null;

            } else { // resto de los casos

                beforeSearchValue.setNext(searchValue.getNext());
                searchValue.setNext(null);

            }

        } else {

            throw new InvalidValue();

        }

    }

    private T removeLast() { // esta operación remueve el último elemento y retorna el elemento eliminado
        T valueToRemove = null;

        if (this.last != null) {
            valueToRemove = this.last.getValue();
            try {
                remove(valueToRemove);
            } catch (InvalidValue e) {
                System.out.println("Este mensaje nunca debería saltar."); //InvalidValue solo salta si no se encuentra el elemento, y en este caso se usa el this.last, que seguro está en la lista.
            }

        }

        return valueToRemove;
    }

    public int size() {
        int size = 0;

        Node<T> temp = this.first;

        while (temp != null) {

            temp = temp.getNext();
            size++;

        }

        return size;
    }

    public Node<T> getFirstNode() {return this.first;}

    @Override
    public T getFirst() {
        return this.first.getValue();
    }

    // Operaciones particulares a Queue

    public void enqueue(T value) {
        addToBeginning(value);
    }

    public T dequeue() throws EmptyQueueException {
        if (this.last == null) { // si la queue esta vacia

            throw new EmptyQueueException();
        }

        return removeLast();
    }

    // Operaciones particulares a Stack

    public void push(T value) {
        addToTheEnd(value);
    }

    public T pop() throws EmptyStackException {
        if (this.last == null) { // si la pila esta vacia

            throw new EmptyStackException();
        }

        return removeLast();
    }

    public T peek() {
        T valueToReturn = null;

        if (this.last != null) {
            valueToReturn = this.last.getValue();
        }

        return valueToReturn;
    }

    public boolean isEmpty() {
        return (this.first == null && this.last==null);
    }

    @Override
    public String toString() {
        String s = "";
        try{
            s+=get(0).toString();
            for (int i = 1; i < size(); i++) {
                s += " ----> " + get(i).toString();
            }
        }catch (InvalidValue e) {
            throw new RuntimeException(e);
        }
        s += "\n              ";
        return s;
    }

    public void sort(Comparator<T> comparator) {
        if (size() <= 1) {
            return; // La lista está vacía o tiene un solo elemento, ya está ordenada
        }

        Node<T> current = first;
        while (current != null) {
            Node<T> minNode = current;
            Node<T> innerCurrent = current.getNext();
            while (innerCurrent != null) {
                if (comparator.compare(innerCurrent.getValue(), minNode.getValue()) < 0) {
                    minNode = innerCurrent;
                }
                innerCurrent = innerCurrent.getNext();
            }

            // Swap values between current and minNode
            T temp = current.getValue();
            current.setValue(minNode.getValue());
            minNode.setValue(temp);

            current = current.getNext();
        }
    }


}
