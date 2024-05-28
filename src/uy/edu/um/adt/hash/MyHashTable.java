package uy.edu.um.adt.hash;

import uy.edu.um.adt.Exceptions.InvalidValue;

public interface MyHashTable<K,V> {
    public void put(K key, V value);
    public boolean contains(K key);
    public void remove(K clave) throws InvalidValue;
    public V find(K key) throws InvalidValue;
}
