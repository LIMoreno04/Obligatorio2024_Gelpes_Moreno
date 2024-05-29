package Entities;

import Entities.Cancion;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyChainedHashImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectorCSV {
    public static MyChainedHashImpl<String,Cancion> hashDeDatos(String csvFilePath) throws FileNotFoundException {

        String fila;
        String centinela = "\",\"";
        int counter = 0;
        MyChainedHashImpl<String,Cancion> hash = new MyChainedHashImpl<>();
        try {hash.resize(299993);} catch (InvalidValue e){System.out.println("Error fatal.");}

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            while ((fila = br.readLine()) != null ) {
                Cancion nuevaCancion = new Cancion()  // crea la cancion vacia
                        ;               // Usa el delimitador para dividir la línea en un array de strings
                String[] data = fila.split(centinela);



                // Arregla el formato en el que vienen los datos
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replaceAll("\"", ""); // Elimina las comillas que vienen por defecto del csv
                }
                if(!data[0].equals("spotify_id,name")) {
                    String[] aux = data[0].split(",", 2); //el limite de divisiones es dos porque hay una cancion que tiene una coma en el nombre y de esta forma solo busca la primer comilla
                    //Ademas, separa los valores del "spotify_id del" de "nombre" porque por como es la division quedan juntos

                    data[23] = data[23].replaceAll(";",""); //le saca los ";" al ultimo valor
                    //

                    nuevaCancion.setSpotify_id(aux[0]);
                    nuevaCancion.setName(aux[1]);
                    nuevaCancion.setArtist(data[1]);
                    nuevaCancion.setDaily_rank(Integer.parseInt(data[2]));
                    nuevaCancion.setDaily_movement(Integer.parseInt(data[3]));
                    nuevaCancion.setWeekly_movement(Integer.parseInt(data[4]));
                    nuevaCancion.setCountry(data[5]);
                    nuevaCancion.setSnapshot_date(data[6]);
                    nuevaCancion.setPupulariry(Integer.parseInt(data[7]));
                    nuevaCancion.setIs_explicit(Boolean.parseBoolean(data[8]));
                    nuevaCancion.setDuration_ms(Integer.parseInt(data[9]));
                    nuevaCancion.setAlbum_name(data[10]);
                    nuevaCancion.setAlbum_release_date(data[11]);
                    nuevaCancion.setDanceability(Float.parseFloat(data[12]));
                    nuevaCancion.setEnergy(Float.parseFloat(data[13]));
                    nuevaCancion.setKey(Integer.parseInt(data[14]));
                    nuevaCancion.setLoudness(Float.parseFloat(data[15]));
                    nuevaCancion.setMode(Float.parseFloat(data[16]));
                    nuevaCancion.setSpeechiness(Float.parseFloat(data[17]));
                    nuevaCancion.setAcousticness(Float.parseFloat(data[18]));
                    nuevaCancion.setInstrumentalness(Float.parseFloat(data[19]));
                    nuevaCancion.setLiveness(Float.parseFloat(data[20]));
                    nuevaCancion.setValence(Float.parseFloat(data[21]));
                    nuevaCancion.setTempo(Float.parseFloat(data[22]));
                    nuevaCancion.setTime_signature(Integer.parseInt(data[23]));
                }
                counter++;



                //System.out.println(counter);
                //System.out.println(nuevaCancion + "\n");
                if (nuevaCancion.getSpotify_id() != null) {
                    hash.put(nuevaCancion.getSpotify_id(), nuevaCancion);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return hash;

    }
}
