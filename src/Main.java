import Entities.Cancion;
import Entities.GestionDeCanciones;
import Entities.LectorCSV;
import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, InvalidValue, EmptyQueueException {

        GestionDeCanciones gestor;

        try{String filePath = "Ahora\\si\\poné\\tu\\direccion\\acá\\lo\\arreglé\\xd";
            gestor = new GestionDeCanciones(filePath);}
        catch (Exception e){
            String filePath = "C:\\Users\\Nacho\\IdeaProjects\\universal_top_spotify_songs.csv";
            gestor = new GestionDeCanciones(filePath);
        }

        //Print de TODO:
        //System.out.println(gestor.getDatos().toStringDetail(0));

        //Print solo del hash externo:
        //System.out.println(gestor.getDatos().toStringSimple());

        //Print de un día específico para chequear empates (ese dia tiene muchos):
        System.out.println(gestor.getDatos().find((LocalDate.parse("2024-03-18"))).toString());

        //Entities.Entities.Functions Lector = new Entities.Entities.Functions();
        //LocalDate fecha = LocalDate.parse("2024-03-18");  //Hacer el parse cuando hagamos el menú
        //Lector.topTen(fecha,"ZA"); //Prueba Funcion UNO.







    }

}