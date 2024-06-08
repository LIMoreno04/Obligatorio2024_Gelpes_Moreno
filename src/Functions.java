import Entities.Cancion;
import Entities.LectorCSV;
import org.w3c.dom.ls.LSOutput;
import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.queue.MyQueue;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Functions {
    String csvFilePath = "C:\\Users\\lauta\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv";
    LectorCSV lectorCSV = new LectorCSV();
    MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> hashDatos = lectorCSV.hashDeDatos(csvFilePath);


    //Funcion UNO:
    public void topTen(LocalDate fecha,String pais) throws InvalidValue, EmptyQueueException {

        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]> hashPais = hashDatos.find(fecha);
        MyLinkedListImpl<Cancion>[] topPais = hashPais.find(pais);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < topPais[i].size(); j++) {     //Se fija si en esa posicion del top hay mas de una cancion y la muestra
                System.out.println(topPais[i].get(j).toString());
            }
        }
    }








}

