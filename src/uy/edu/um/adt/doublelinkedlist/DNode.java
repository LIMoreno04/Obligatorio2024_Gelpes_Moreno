package uy.edu.um.adt.doublelinkedlist;

public class DNode<T> {
    private final T value;
    private DNode<T> next;
    private DNode<T> prev;

    public T getValue() {
        return value;
    }


    public DNode<T> getNext() {
        return next;
    }

    public void setNext(DNode<T> next) {
        this.next = next;
    }

    public DNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DNode<T> prev) {
        this.prev = prev;
    }

    public DNode(T nodo, DNode<T> prev, DNode<T> next){
        this.prev=prev;
        this.next=next;
        this.value=nodo;
    }

    public DNode(T nodo){
        this.value=nodo;
        this.next=null;
        this.prev=null;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
