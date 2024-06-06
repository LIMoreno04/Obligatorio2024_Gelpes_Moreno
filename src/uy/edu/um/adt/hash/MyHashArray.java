package uy.edu.um.adt.hash;

import uy.edu.um.adt.Exceptions.AlreadyExistingValue;
import uy.edu.um.adt.Exceptions.InvalidValue;

import java.util.Arrays;

public class MyHashArray<K,V> extends MyClosedHashImpl<K,V[]> {


    public void putValue(K key, V value, int rank, int cotaTop) throws InvalidValue, AlreadyExistingValue { //key = pais

        if (count == size-1){
            this.resize(size*2);
        }

        V[] top50 = (V[]) new Object[cotaTop];
        int index = this.hashFunction(key);

        //si no hay pais con ese lugar
        if (stashes[index] == null){
            top50[rank-1] = value;
            stashes[index] = new ValueStash<>(key,top50);
            count++;
        }

        //si el pais ya está en ese lugar
        else if (key.equals(stashes[index].getKey())) {
            top50 = stashes[index].getValue();
            if (top50[rank-1] != null) {
                //ya hay algo en ese lugar para ese dia y pais, puede pasar porque el csv está mal hecho xd
                System.out.println("Error en el ranking diario, posición repetida para dos canciones en el mismo país y el mismo día.");
                throw new AlreadyExistingValue();
            }
            top50[rank-1] = value;
            stashes[index].setValue(top50);
        }

        //si en ese lugar hay un pais pero no es el correcto
        else {
            while (stashes[index] != null && !key.equals(stashes[index].getKey())) {
                index = (index+1)%size;
            }

            //si al final no estaba el pais
            if (stashes[index] == null){
                top50[rank-1] = value;
                stashes[index] = new ValueStash<>(key,top50);
                count++;
            }

            //si estaba pero mas abajo
            else if (key.equals(stashes[index].getKey())) {
                top50 = stashes[index].getValue();
                if (top50[rank-1] != null) {
                    //ya hay algo en ese lugar para ese dia y pais, puede pasar porque el csv está mal hecho xd
                    System.out.println("Error en el ranking diario, posición repetida para dos canciones en el mismo país y el mismo día.");
                    throw new AlreadyExistingValue();
                }
                top50[rank-1] = value;
                stashes[index].setValue(top50);
            }
        }
    }

    @Override
    public String toString() {
        return "MyHashArray{" +
                "stashes=" + Arrays.toString(stashes) +
                ",\n size=" + size +
                ",\n count=" + count +
                '}';
    }
}
