package uy.edu.um.adt.hash;

import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

public class MyChainedHashImpl<K,V> implements MyHashTable<K,V>{
    private MyList<ValueStash<K,V>>[] hashTable;
    private int size;

    public int getSize() {
        return size;
    }

    public MyChainedHashImpl() {
        this.size = 11;
        this.hashTable = new MyList[size];
    }

    public MyChainedHashImpl(int size) {
        this.size = size;
        this.hashTable = new MyList[size];
    }

    //metodo auxiliar para encontrar el stash directo en vez del valor
    private ValueStash<K, V> findStash(K key) throws InvalidValue {
        int hash = hashFunction(key);
        MyList<ValueStash<K, V>> listaColisiones = hashTable[hash];

        if (listaColisiones != null) {
            for (int i = 0; i < listaColisiones.size(); i++) {
                ValueStash<K, V> stash = listaColisiones.get(i);
                if (stash.getKey().equals(key)) {
                    return stash;
                }
            }
        }
        throw new InvalidValue();
    }
    public int hashFunction(K key){
        String sKey = key.toString();
        int hashedKey = 0;
        for (int i = 0; i < sKey.length(); i++) {
            char aux = sKey.charAt(i);
            hashedKey += (int) aux;
        }
        return hashedKey % size;
    }

    @Override
    public void put(K key, V value) {
        ValueStash<K,V> stash = new ValueStash<>();
        stash.setKey(key);
        stash.setValue(value);
        if (hashTable[hashFunction(key)] == null) {
            MyList<ValueStash<K, V>> lista = new MyLinkedListImpl<>();
            hashTable[hashFunction(key)] = lista;
        }
        hashTable[hashFunction(key)].add(stash);
    }

    @Override
    public V find(K key) throws InvalidValue {
        return findStash(key).getValue();
    }

    @Override
    public boolean contains(K key) {
        if (hashTable[hashFunction(key)] != null) { //si hay elementos en la posición correspondiente
            ValueStash<K,V> aux = new ValueStash<>();
            aux.setKey(key); //creo un ValueStash para comparar, (el .equals() solo ve la key)

            return hashTable[hashFunction(key)].contains(aux); //uso el método de las LinkedList
        }
        //no hay elementos en la posición
        return false;
    }

    @Override
    public void remove(K clave) throws InvalidValue{
        if (hashTable[hashFunction(clave)] != null){ //si hay elementos en la posición correspondiente
            ValueStash<K,V> aux = new ValueStash<>();
            aux.setKey(clave); //creo un ValueStash para comparar, (el .equals() solo ve la key)
            hashTable[hashFunction(clave)].remove(aux); //uso el método de las LinkedList
        }
        else {    //no hay elementos en la posición
            throw new InvalidValue();
        }
    }

    public void resize(int newSize) throws InvalidValue {
        if (newSize<1){
            throw new InvalidValue();
        }

        MyList<ValueStash<K,V>>[] newHashTable = new MyList[newSize];
        int oldsize = this.size;
        this.size = newSize;

        for (int i = 0; i < oldsize; i++) {
            if (this.hashTable[i] != null) {
                for (int j = 0; j < this.hashTable[i].size(); j++) {
                    ValueStash<K, V> aux = this.hashTable[i].get(j);
                    if (newHashTable[hashFunction(aux.getKey())] == null){
                        MyList<ValueStash<K, V>> lista = new MyLinkedListImpl<>();
                        newHashTable[hashFunction(aux.getKey())] = lista;
                    }
                    newHashTable[hashFunction(aux.getKey())].add(aux);
                }
            }
        }
        this.hashTable = newHashTable;
    }

}
