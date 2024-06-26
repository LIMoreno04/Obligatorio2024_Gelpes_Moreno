package uy.edu.um.adt.hash;

import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.Math.abs;

public class MyClosedHashImpl<K,V> implements MyHashTable<K,V> {

    protected ValueStash<K,V>[] stashes;
    protected int size;
    protected int count;

    public MyClosedHashImpl(){
        this.size = 11;
        this.stashes = new ValueStash[size];
        this.count = 0;
    }

    public int hashFunction(K key){
        int hashedKey = 1;
        String stringKey = key.toString();

        for (int i = 0; i < stringKey.length(); i++) {
            char aux = stringKey.charAt(i);
            hashedKey = hashedKey * (int)aux -1;
        }

        return abs(hashedKey % size);
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
                throw new InvalidValue("");
            }else{
                stashes[index] = null;
                count--;
            }
        }
    }



    @Override
    public V find(K clave) throws InvalidValue{
        Integer index = hashFunction(clave);
        if(stashes[index] != null && clave.equals(stashes[index].getKey())){
            return stashes[index].getValue();
        }
        else{
            for (int i = 0; i < stashes.length; i++) {
                if(stashes[(index+i)%size] != null && clave.equals(stashes[(index+i)%size].getKey())){
                    index = (index+i)%size;
                }
            }
            if(stashes[index] == null || !clave.equals(stashes[index].getKey())){
                throw new InvalidValue("");
            }else{
                return stashes[index].getValue();
            }
        }
    }

    void resize(int newSize){
        ValueStash[] oldStashValues = stashes;
        stashes = new ValueStash[newSize];
        size = newSize;
        count = 0;

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

    public int getCount() {
        return count;
    }

    public String toStringDetail(int quantity) {
        if (quantity == 0){quantity = size;}
        String s = "===========================================================================================================\n";
        s += "           HASH\nCapacity: ";
        s += count + "/" + size;
        s += "\n===========================================================================================================";
        for (int i = 0; i < quantity; i++) {
            s += "\n";
            s += i + ". k=";
            try {
                s += stashes[i].getKey();
                s+= "\n       ";
                s += stashes[i].getValue().toString();
                s += "\n";

            }catch (NullPointerException e){
                s += "null\n";
            }
        }
        return s;
    }

    public String toStringSimple(){
        String s = "===========================================================================================================\n";
        s += "           HASH\nCapacity: ";
        s += count + "/" + size;
        s += "\n===========================================================================================================";
        for (int i = 0; i < size; i++) {
            s += "\n";
            s += i + ". k=";
            try {
                s += stashes[i].getKey();
                s+= "\n       Capacity: ";
                s += ((MyClosedHashImpl) stashes[i].getValue()).getCount() + "/" + ((MyClosedHashImpl) stashes[i].getValue()).getSize();
                s += "\n";

            }catch (NullPointerException e){
                s += "null\n";
            }
        }
        return s;
    }

    @Override
    public String toString() {
        String s = "       Capacity: ";
        s += count + "/" + size;
        s += "\n       ---------------------------------------------------------------------------------";
        for (int i = 0; i < size; i++) {
            try {
                K key = stashes[i].getKey();
                s += "\n       ";
                s += i + ". k=";
                s += key.toString();
                s += "\n              ";
                if (stashes[i].getValue().getClass().equals(Array.class)) {
                    s += Arrays.toString((Object[]) stashes[i].getValue());
                }else {s+=stashes[i].getValue().toString();}
            } catch (NullPointerException e) {
                s+="\n       null";
            }
        }
        return s;
    }

    public MyList<V> toList(){
        MyList<V> lista = new MyLinkedListImpl<>();
        for (int i = 0; i < size; i++) {
            if(stashes[i] != null){
                lista.add(stashes[i].getValue());
            }
        }
        return lista;
    }
}
