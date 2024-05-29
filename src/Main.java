import Entities.Cancion;
import Entities.LectorCSV;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyChainedHashImpl;

import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        MyChainedHashImpl<String,Cancion> Datos;
        try {
            String filePath = "C:/Users/Nacho/IdeaProjects/universal_top_spotify_songs.csv";
            Datos = LectorCSV.hashDeDatos(filePath);
        }catch (FileNotFoundException e){
            String filePath = "Lautaro/poné/tu/dirección/del/csv/acá";
            Datos = LectorCSV.hashDeDatos(filePath);
        }


    }
}