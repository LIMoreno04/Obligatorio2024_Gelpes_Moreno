import Entities.Cancion;
import Entities.LectorCSV;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyHashMapImpl;

import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException,InvalidValue {


        MyHashMapImpl<String,String,Cancion> Datos;
        try {
            String filePath = "C:/Users/Nacho/IdeaProjects/universal_top_spotify_songs.csv";
            Datos = LectorCSV.hashDeDatos(filePath);
        }catch (FileNotFoundException e){
            String filePath = "Lautaro/poné/tu/dirección/del/csv/acá";
            Datos = LectorCSV.hashDeDatos(filePath);
        }
        System.out.println(Datos.toString());


    }
}