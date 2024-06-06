package uy.edu.um.adt.hash;

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

    @Override
    public String toString() {
        String s = "HashMap:\n";
        for (int i = 0; i < stashes.length; i++) {
            if (stashes[i]==null){
                s += "null\n";
            }
            else {
                MyHashArray<Q, V> hashInterno = stashes[i].getValue();
                s += i + ". [key = " + stashes[i].getKey() + " ], [hash interno: " +
                        hashInterno.getCount() + "/" + hashInterno.getSize() + " ]\n";
            }
        }

        return s;
    }
}
