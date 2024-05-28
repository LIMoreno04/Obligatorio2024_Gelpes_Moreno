package uy.edu.um.adt.doublelinkedlist;
import uy.edu.um.adt.Exceptions.EmptyListException;
import uy.edu.um.adt.Exceptions.InvalidValue;

public class MyCircularLinkedListImpl<T> extends MyDoubleLinkedListImpl<T>{


    @Override
    public int size() {
        int size = 0;
        if (this.getPrimero() == null){

        }
        else {
            size++;
            DNode<T> aux = this.getPrimero();
            while (aux.getNext() != this.getPrimero()){
                aux=aux.getNext();
                size++;
            }
        }
        return size;
    }


    @Override
    public void add(T value) {
        if(getPrimero() == null){
            this.setPrimero(new DNode<T>(value));
            this.getPrimero().setPrev(this.getPrimero());;
            this.getPrimero().setNext(this.getPrimero());
            this.setUltimo(this.getPrimero());
        }
        else{
            this.getUltimo().setNext(new DNode<T>(value,this.getUltimo(),this.getPrimero()));
            this.setUltimo(this.getUltimo().getNext());
            this.getPrimero().setPrev(this.getUltimo());
        }
    }

    public void addFirst(T value){
        if(getPrimero() == null){
            this.setPrimero(new DNode<T>(value));
            this.getPrimero().setPrev(this.getPrimero());
            this.getPrimero().setNext(this.getPrimero());
            this.setUltimo(this.getPrimero());
        }
        else{
            this.getPrimero().setPrev(new DNode<T>(value,this.getUltimo(),this.getPrimero()));
            this.setPrimero(this.getPrimero().getPrev());
            this.getUltimo().setNext(this.getPrimero());
        }

    }

    @Override
    public void remove(T value) throws InvalidValue {
        DNode<T> searchValue = this.getPrimero();


        if (contains(value)) {
        //solo si el valor está en la lista

            // Busco el elemento a eliminar
            while (!searchValue.getValue().equals(value)) {

                searchValue = searchValue.getNext();

            }

                // Verifico si es el primer valor (caso borde) y no es el ultimo
                if (searchValue == this.getPrimero() && searchValue != this.getUltimo()) {

                    DNode<T> temp = this.getPrimero();
                    this.setPrimero(this.getPrimero().getNext());  // salteo el primero
                    this.getPrimero().setPrev(this.getUltimo());
                    temp.setPrev(null);
                    temp.setNext(null); // quito referencias del elemento eliminado.

                    // Verifico si es el ultimo valor (caso borde) y no el primero
                } else if (searchValue == this.getUltimo() && searchValue != this.getPrimero()) {
                    DNode<T> aux = this.getUltimo().getPrev();
                    aux.setNext(this.getPrimero());
                    this.getPrimero().setPrev(aux);         //encadeno el penultimo con el primero
                    this.getUltimo().setPrev(null);
                    this.getUltimo().setNext(null);         //quito referencias del elemento eliminado
                    this.setUltimo(aux);

                    // Si es el primer valor y el ultimo (lista de un solo valor)
                } else if (searchValue == this.getUltimo() && searchValue == this.getPrimero()) {

                    this.setPrimero(null);
                    this.setUltimo(null);

                } else { // resto de los casos
                    DNode<T> previo = searchValue.getPrev();
                    DNode<T> siguiente = searchValue.getNext();
                    previo.setNext(siguiente);              //lo salteo en la referencia hacia adelante
                    siguiente.setPrev(previo);              //lo salteo en la referencia hacia atrás
                    searchValue.setPrev(null);
                    searchValue.setNext(null);              //quito referencias del elemento eliminado
                }

        }
        else {

            throw new InvalidValue();

        }

    }



}
