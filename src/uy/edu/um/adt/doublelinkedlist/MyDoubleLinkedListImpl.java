package uy.edu.um.adt.doublelinkedlist;

import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.linkedlist.MyList;

public class MyDoubleLinkedListImpl<T> implements MyList<T> {
    private DNode<T> primero = null;
    private DNode<T> ultimo = null;
    public int size(){
        int size = 0;
        if (primero == null){

        }
        else {
            size++;
            DNode<T> aux = this.primero;
            while (aux.getNext() != null){
                aux=aux.getNext();
                size++;
            }
        }
        return size;
    }


    protected DNode<T> getPrimero(){
        return primero;
    }

    protected void setPrimero(DNode<T> primero) {
        this.primero = primero;
    }
    protected DNode<T> getUltimo(){
        return ultimo;
    }

    protected void setUltimo(DNode<T> ultimo) {
        this.ultimo = ultimo;
    }

    @Override
    public T getFirst() {
        return primero.getValue();
    }

    public T getLast() {
        return ultimo.getValue();
    }

    //funciones auxiliares para crear un DNode dado solo su siguiente nodo, o solo su nodo previo
    private DNode<T> DNodeN(T nodo, DNode<T> next){
        return new DNode<T>(nodo,null,next);
    }
    private DNode<T> DNodeP(T nodo, DNode<T> prev){
        return new DNode<T>(nodo,prev,null);
    }
    protected DNode<T> getNodo(int index) throws InvalidValue {
        if (index >= size() || index < 0) {
            throw new InvalidValue();
        }
        DNode<T> aux = this.primero;
        for (int i = 0; i < index; i++) {
            aux = aux.getNext();
        }
        return aux;
    }

    public boolean isEmpty() {
        return primero == null;
    }

    public boolean contains(T value){
        for (int i = 0; i < size(); i++) {
            try {
                if(this.get(i).equals(value)){
                    return true;
                }
            } catch (InvalidValue e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void add(T value) {
        if(primero == null){
            this.primero = new DNode<T>(value);
            this.ultimo=this.primero;
        }
        else{
            this.ultimo.setNext(DNodeP(value,this.ultimo));
            this.ultimo=this.ultimo.getNext();
        }
    }
    public void addFirst(T value){
        if(primero == null){
            this.primero = new DNode<T>(value);
            this.ultimo=this.primero;
        }
        else{
            this.primero.setPrev(DNodeN(value,this.primero));
            this.primero=this.primero.getPrev();
        }
    }

    @Override
    public T get(int index) throws InvalidValue {
        return this.getNodo(index).getValue();
    }

    @Override
    public void remove(T value) throws InvalidValue{
        DNode<T> searchValue = this.primero;

        // Busco el elemento a eliminar
        while (searchValue != null && !searchValue.getValue().equals(value)) {

            searchValue = searchValue.getNext();

        }

        if (searchValue != null) { // si encontre el elemento a eliminar

            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.primero && searchValue != this.ultimo) {

                DNode<T> temp = this.primero;
                this.primero = this.primero.getNext();  // salteo el primero

                temp.setNext(null); // quito referencia del elemento eliminado al siguiente.

                // Verifico si es el ultimo valor (caso borde) y no el primero
            } else if (searchValue == this.ultimo && searchValue != this.primero) {
                DNode<T> aux = this.ultimo.getPrev();
                aux.setNext(null);
                this.ultimo = aux;

                // Si es el primer valor y el ultimo (lista de un solo valor)
            } else if (searchValue == this.ultimo && searchValue == this.primero) {

                this.primero = null;
                this.ultimo = null;

            } else { // resto de los casos
                DNode<T> previo = searchValue.getPrev();
                DNode<T> siguiente = searchValue.getNext();
                previo.setNext(siguiente);              //lo salteo en la referencia hacia adelante
                siguiente.setPrev(previo);              //lo salteo en la referencia hacia atrás
                searchValue.setPrev(null);
                searchValue.setNext(null);              //quito referencias del elemento eliminado
            }

        } else {

            throw new InvalidValue();

        }

    }

    public String toString() {
        String s = "";
        try{
            for (int i = 0; i < size(); i++) {
                s += i+". "+getNodo(i).getValue().toString()+"\n";
            }
        }
        catch (InvalidValue e) {
            if (isEmpty()){
                s = "Lista vacía";
            }
        }
        return s;
    }
}