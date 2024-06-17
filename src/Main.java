import Entities.Cancion;
import Entities.LectorCSV;
import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, InvalidValue, EmptyQueueException {

        MyLinkedListImpl<String> lista = new MyLinkedListImpl<String>();

        MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> Datos;
        String filePath = "C:\\Users\\lauta\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv";

        Functions Lector = new Functions();
        LocalDate fecha = LocalDate.parse("2024-03-18");  //Hacer el parse cuando hagamos el menú
        Lector.topTen(fecha,"ZA"); //Prueba Funcion UNO.





        //Print para chequear empates (ese dia tiene muchos, por ejemplo el top 2 en World, el primer "empate" de todos)
        //System.out.println(Datos.find((LocalDate.parse("2024-03-18"))).toString());

    }

}