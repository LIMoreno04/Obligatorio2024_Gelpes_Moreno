import Entities.Cancion;
import Entities.GestionDeCanciones;
import Entities.LectorCSV;
import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.hash.MyHashTable;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {

    public static void main(String[] args) throws InvalidValue {

        GestionDeCanciones gestor;
        MyClosedHashImpl<LocalDate,MyLinkedListImpl<String>> artistasPorFecha = LectorCSV.getArtistasPorFecha();

        try{String filePath = "C:\\Users\\lauta\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv";  //Ver errores en fechas y fechas inexistentes
            gestor = new GestionDeCanciones(filePath);
            System.out.println(artistasPorFecha.getCount()); //Veo si funciona el hash
            }
        catch (Exception e){
            String filePath = "C:\\Users\\Nacho\\IdeaProjects\\universal_top_spotify_songs.csv";
            gestor = new GestionDeCanciones(filePath);
        }

        gestor.Gestionar();

        //Hacer hash que guarde los artistas con sus canciones





        //PRUEBAS

        //Para probar funcion UNO: 2024-03-18 ; ZA
        //Para probar funcion DOS: 2024-05-04 con empates, 2024-05-03 sin empates

        //Print de TODO:
        //System.out.println(gestor.getDatos().toStringDetail(0));

        //Print solo del hash externo:
        //System.out.println(gestor.getDatos().toStringSimple());

        //Print de un día específico para chequear empates (este dia tiene muchos):
        //System.out.println(gestor.getDatos().find((LocalDate.parse("2024-03-18"))).toString());








    }

}