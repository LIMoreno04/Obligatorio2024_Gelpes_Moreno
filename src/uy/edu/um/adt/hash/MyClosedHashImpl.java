package uy.edu.um.adt.hash;

import uy.edu.um.adt.Exceptions.InvalidValue;

public class MyClosedHashImpl<K,V> implements MyHashTable<K,V> {

    private ValueStash<K,V>[] stashes;   //Arreglar
    private int size;
    private int count;

    public MyClosedHashImpl(){
        this.size = 11;
        this.stashes = new ValueStash[size];
        this.count = 0;
    }

    private int hashFunction(K key){
        int hashedKey = 0;
        String stringKey = key.toString();

        for (int i = 0; i < stringKey.length(); i++) {
            char aux = stringKey.charAt(i);
            hashedKey = hashedKey + (int)aux;
        }

        return hashedKey % size;
    }


    @Override
    public void put(K key, V value) {
        if(count == size){
            resize(size*2);
        }

        int index = hashFunction(key);
        ValueStash<K,V> nuevoObjeto = new ValueStash<>();
        nuevoObjeto.setKey(key);
        nuevoObjeto.setValue(value);

        if(stashes[index] != null ){
            while (stashes[index] != null){
                index = (index + 1) % size;
            }
        }

        stashes[index] = nuevoObjeto;
        count++;
    }

    @Override
    public boolean contains(K key) {
        boolean existe = false;
        for (int i = 0; i < stashes.length; i++) {
            if(stashes[i] != null && key.equals(stashes[i].getKey())){
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public void remove(K clave) throws InvalidValue {
        Integer index = hashFunction(clave);
        if(clave.equals(stashes[index].getKey())){
           stashes[index] = null;
           count--;
        }else{
            index = null;
            for (int i = 0; i < stashes.length; i++) {
                if(stashes[i] != null && clave.equals(stashes[i].getKey())){
                    index = i;
                }
            }
            if (index==null) {
                throw new InvalidValue();
            }else{
                stashes[index] = null;
                count--;
            }
        }
    }



    @Override
    public V find(K clave) throws InvalidValue{
        Integer index = hashFunction(clave);
        if(clave.equals(stashes[index].getKey())){
            return stashes[index].getValue();
        }
        else{
            index = null;
            for (int i = 0; i < stashes.length; i++) {
                if(stashes[i] != null && clave.equals(stashes[i].getKey())){
                    index = i;
                }
            }
            if(index == null){
                throw new InvalidValue();
            }else{
                return stashes[index].getValue();
            }
        }
    }

    private void resize(int newSize){
        ValueStash[] oldStashValues = stashes;
        stashes = new ValueStash[newSize];
        size = newSize;

        for (ValueStash<K,V> oldstash : oldStashValues) {
            if(oldstash != null){
                put(oldstash.getKey(), oldstash.getValue());
            }

        }
    }


    public ValueStash[] getStashes() {
        return stashes;
    }

    public void setStashes(ValueStash[] stashes) {
        this.stashes = stashes;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
