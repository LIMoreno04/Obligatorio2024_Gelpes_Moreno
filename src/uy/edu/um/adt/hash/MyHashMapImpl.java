package uy.edu.um.adt.hash;

import uy.edu.um.adt.Exceptions.AlreadyExistingValue;
import uy.edu.um.adt.Exceptions.InvalidValue;

public class MyHashMapImpl<K,Q,V> extends MyClosedHashImpl<K, MyHashArray<Q,V>> {

    public MyHashMapImpl() {
        this.size = 11;
        this.count = 0;
        this.stashes = new ValueStash[size];
    }

    public void put(K key, Q secondaryKey, V value, int rank) { //key = fecha
        System.out.println(key+"   rank: "+rank+"    pais:"+secondaryKey);
        System.out.println(value.toString()+"\n\n");


        if (count == size-1){
            this.resize(size*2);
        }
        int index = this.hashFunction(key);

        //no hay canciones con esa fecha
        if (stashes[index] == null) {

           MyHashArray<Q,V> nuevoHash = new MyHashArray<>();
           nuevoHash.putValue(secondaryKey,value,rank);
           stashes[index] = new ValueStash<K,MyHashArray<Q,V>>(key,nuevoHash);
           count++;

        //hay un hash en esa fecha y coincide
        } else if (stashes[index].getKey().equals(key)) {
            stashes[index].getValue().putValue(secondaryKey, value, rank);

        //hay un hash en esa fecha pero la fecha no es la misma
        }  else {
            while (stashes[index] != null && !key.equals(stashes[index].getKey())) {
                index = (index+1)%size;
            }

            //la fecha no está (la agrego)
            if (stashes[index] == null){
                MyHashArray<Q,V> nuevoHash = new MyHashArray<>();
                nuevoHash.putValue(secondaryKey,value,rank);
                stashes[index] = new ValueStash<K,MyHashArray<Q,V>>(key,nuevoHash);
                count++;

            //la fecha estaba más adelante
            } else if (key.equals(stashes[index].getKey())) {
                stashes[index].getValue().putValue(secondaryKey,value,rank);
            }
        }

    }
}
