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

    public static void main(String[] args) throws InvalidValue {

        GestionDeCanciones gestor;

        try{String filePath = "Ahora\\si\\poné\\tu\\direccion\\acá\\lo\\arreglé\\xd";
            gestor = new GestionDeCanciones(filePath);}
        catch (Exception e){
            String filePath = "C:\\Users\\Nacho\\IdeaProjects\\universal_top_spotify_songs.csv";
            gestor = new GestionDeCanciones(filePath);
        }
        gestor.Gestionar();

        //PRUEBAS

        //Print de TODO:
        //System.out.println(gestor.getDatos().toStringDetail(0));

        //Print solo del hash externo:
        //System.out.println(gestor.getDatos().toStringSimple());

        //Print de un día específico para chequear empates (este dia tiene muchos):
        //System.out.println(gestor.getDatos().find((LocalDate.parse("2024-03-18"))).toString());

        //Para probar funcion UNO: 2024-03-18 ; ZA
        //Para probar funcion DOS: 2024-03-18






    }

}