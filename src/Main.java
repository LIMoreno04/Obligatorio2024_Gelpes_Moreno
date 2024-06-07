import Entities.Cancion;
import Entities.LectorCSV;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import java.io.FileNotFoundException;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws FileNotFoundException,InvalidValue {

        MyLinkedListImpl<String> lista = new MyLinkedListImpl<String>();


        MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> Datos;
        try {
            String filePath = "C:/Users/Nacho/IdeaProjects/universal_top_spotify_songs.csv";
            Datos = LectorCSV.hashDeDatos(filePath);
        }catch (FileNotFoundException e){
            String filePath = "Lautaro/poné/tu/dirección/del/csv/acá";
            Datos = LectorCSV.hashDeDatos(filePath);
        }

        //Print completo (todas las fechas)
        //System.out.println(Datos.toStringDetail(0));

        //Print parcial (k fechas)
        //k=3
        //System.out.println(Datos.toStringDetail(k));

        //Print para chequear empates (ese dia tiene muchos, por ejemplo el top 2 en World, el primer "empate" de todos)
        //System.out.println(Datos.find((LocalDate.parse("2024-03-18"))).toString());

    }

}