package Entities;

import Entities.Cancion;
import Entities.LectorCSV;
import org.w3c.dom.ls.LSOutput;
import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyChainedHashImpl;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.hash.ValueStash;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.Node;
import uy.edu.um.adt.queue.MyQueue;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Functions {

    MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> hashDatos;

    public Functions(MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> hashDatos) {
        this.hashDatos = hashDatos;
    }


    //Funcion UNO:
    public void topTen(LocalDate fecha,String pais) throws InvalidValue {

        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]> hashPais = hashDatos.find(fecha);
        MyLinkedListImpl<Cancion>[] topPais = hashPais.find(pais);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < topPais[i].size(); j++) {     //Se fija si en esa posicion del top hay mas de una cancion y la muestra
                if(topPais[i] != null) {
                    System.out.println(topPais[i].get(j).toString());
                }
            }
        }
    }

    //Funcion DOS:
    public void globalTop5(LocalDate fecha) throws InvalidValue {
        ValueStash<Integer,MyLinkedListImpl<Cancion>>[] topFive = new ValueStash[5];

        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]> hashPais = hashDatos.find(fecha);
        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>> cancionesDelDia = new MyClosedHashImpl<>();

        //Me creo un hash (con key=spotify_id) para almacenar todas las canciones del día pedido
        //Si una cancion se repite la agrego al mismo lugar en forma de linked list
        for (int i = 0; i < hashPais.getSize(); i++) {
            if (hashPais.getStashes()[i] != null) {
                MyLinkedListImpl<Cancion>[] top50 = (MyLinkedListImpl<Cancion>[]) hashPais.getStashes()[i].getValue();
                for (int j = 0; j < 50; j++) {
                    if (top50[j] != null) {
                        Node<Cancion> cancionAux = top50[j].getFirstNode();
                        String cancionID = cancionAux.getValue().getSpotify_id();
                        int index = cancionesDelDia.hashFunction(cancionID);
                        while (cancionAux != null) {
                            MyLinkedListImpl<Cancion> listaCancionX;
                            try {
                                listaCancionX = cancionesDelDia.find(cancionID);
                            }catch (InvalidValue e){
                                listaCancionX = new MyLinkedListImpl<>();
                                cancionesDelDia.put(cancionID,listaCancionX);
                            }
                            listaCancionX.add(cancionAux.getValue());
                            cancionAux = cancionAux.getNext();
                        }
                    }
                }
            }
        }
        //Reviso todas las canciones del día, y chequeo el tamaño de su linked list
        //(si se repite n veces, su linked list tiene n entradas)
        //Me quedo con las 5 que más se repiten en un array,
        //almacenando las repeticiones de las 5 mejores hasta el momento y comparando
        for (int i = 0; i < cancionesDelDia.getSize(); i++) {
            if (cancionesDelDia.getStashes()[i] != null) {
                int count = ((MyLinkedListImpl<Cancion>) cancionesDelDia.getStashes()[i].getValue()).size();
                for (int j = 0; j < 5; j++) {
                    if (topFive[j] == null || topFive[j].getKey() < count){
                        topFive[j] = new ValueStash<>(count,new MyLinkedListImpl<>(cancionesDelDia.find((String) cancionesDelDia.getStashes()[i].getKey()).getFirst()));
                        break;
                    }
                    else if (topFive[j].getKey() == count) { //Si n canciones empatan, las guardo todas
                        topFive[j].getValue().add(cancionesDelDia.find((String) cancionesDelDia.getStashes()[i].getKey()).getFirst());
                        break;
                    }

                }

            }
        }

        for (int i = 0; i < 5; i += topFive[i].getValue().size()) {
            String string = "";
            if (topFive[i].getValue().size() != 1){
                string += "(Empate) ";
            }
            string += topFive[i].getKey();
            string += " apariciones: ";
            string += topFive[i].getValue();
            System.out.println(string);

        }


    }

}

