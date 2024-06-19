package Entities;

import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.hash.MyHashTable;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class LectorCSV {

    public static MyClosedHashImpl<LocalDate,MyLinkedListImpl<String>> artistasPorFecha = new MyClosedHashImpl<>();

    public static MyClosedHashImpl<LocalDate,MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> hashDeDatos(String csvFilePath)  {

        String fila;
        String centinela = "\",\"";
        int counter = 0;

        MyClosedHashImpl<LocalDate,MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> hashMap = new MyClosedHashImpl<>();
        //Hash por fecha que en cada lugar tiene un hash por pais. Cada hash por pais en cada lugar tiene un array con su top 50 de ese día.
        //Si hay dos canciones con la misma posicion el mismo dia y en el mismo pais (empate) se agregan a una linked list.

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            System.out.println("\nLoading Values...");
            while ((fila = br.readLine()) != null ) {
                Cancion nuevaCancion = new Cancion();    //crea la cancion vacia
                String[] data = fila.split(centinela);   //Usa el delimitador para dividir la línea en un array de strings


                // Arregla el formato en el que vienen los datos

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replaceAll("\"", ""); // Elimina las comillas que vienen por defecto del csv
                }
                if(!data[0].equals("spotify_id,name")) {
                    String[] aux = data[0].split(",", 2); //el limite de divisiones es dos porque hay una cancion que tiene una coma en el nombre y de esta forma solo busca la primer comilla
                    //Ademas, separa los valores del "spotify_id del" de "nombre" porque por como es la division quedan juntos

                    data[23] = data[23].replaceAll(";",""); //le saca los ";" al ultimo valor

                    if (Objects.equals(data[5], "")){
                        data[5]="World";
                    }
                    //Separacion de artistas
                    // Normalización de nombres de artistas al procesar el CSV
                    String[] artistas = data[1].split(",");
                    for (int x = 0; x < artistas.length; x++) {
                        String nombreNormalizado = artistas[x].trim();  //Elimina espacios en blanco antes y despues del nombre
                        nuevaCancion.getArtist().add(nombreNormalizado);
                    }

                    nuevaCancion.setSpotify_id(aux[0]);
                    nuevaCancion.setName(aux[1]);
                    nuevaCancion.setDaily_rank(Integer.parseInt(data[2]));
                    nuevaCancion.setDaily_movement(Integer.parseInt(data[3]));
                    nuevaCancion.setWeekly_movement(Integer.parseInt(data[4]));
                    nuevaCancion.setCountry(data[5]);
                    nuevaCancion.setSnapshot_date(LocalDate.parse(data[6]));
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

                    // Agregar artistas al hash artistasPorFecha
                    LocalDate fecha = nuevaCancion.getSnapshot_date();
                    if(artistasPorFecha.contains(fecha)) {
                        MyLinkedListImpl<String> artistasDeFecha = artistasPorFecha.find(fecha);
                        for (String artista : artistas) {
                            artistasPorFecha.find(fecha).add(artista); // Permitir que los artistas se repitan
                        }
                    }else{
                        artistasPorFecha.put(fecha,new MyLinkedListImpl<String>());
                        for (String artista : artistas) {
                            artistasPorFecha.find(fecha).add(artista); // Permitir que los artistas se repitan
                        }
                    }
                }
                counter++;
                if(counter == 650000){
                    System.out.println("Finished.\n");
                }


                if (nuevaCancion.getSpotify_id() != null) {

                    MyLinkedListImpl<Cancion> posicionEnElTop = new MyLinkedListImpl<>();
                    MyLinkedListImpl<Cancion>[] top50 = new  MyLinkedListImpl[50];
                    MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]> hashDelDia = new MyClosedHashImpl<>();

                    hashDelDia.put(nuevaCancion.getCountry(),top50);
                    top50[nuevaCancion.getDaily_rank() - 1] = posicionEnElTop;

                    try {
                        //busco el hash de paises correspondiente al día de la cancion.
                        hashDelDia = hashMap.find(nuevaCancion.getSnapshot_date());

                        try {
                            //busco el pais de la cancion
                            top50 = hashDelDia.find(nuevaCancion.getCountry());

                            if (top50[nuevaCancion.getDaily_rank() - 1] == null){
                                //no hay empates
                                top50[nuevaCancion.getDaily_rank() - 1] = posicionEnElTop;
                            }
                            else {
                                //hubo un empate
                                posicionEnElTop = top50[nuevaCancion.getDaily_rank() - 1];
                            }
                        }
                        catch (InvalidValue countryNotFound){
                            //todavia no hay canciones de ese pais en esa fecha
                            hashDelDia.put(nuevaCancion.getCountry(),top50);
                        }
                    }
                    catch (InvalidValue dateNotFound){
                        //todavia no hay canciones con esa fecha
                        hashMap.put(nuevaCancion.getSnapshot_date(),hashDelDia);
                    }
                    posicionEnElTop.add(nuevaCancion);
                }

                MyLinkedListImpl<String> artistas = new MyLinkedListImpl<>();


            }

        } catch (IOException e) {
            throw new RuntimeException();
        } catch (InvalidValue e) {
            throw new RuntimeException(e);
        }
        return hashMap;

    }

    public static MyClosedHashImpl<LocalDate, MyLinkedListImpl<String>> getArtistasPorFecha() {
        return artistasPorFecha;
    }

    public static void setArtistasPorFecha(MyClosedHashImpl<LocalDate, MyLinkedListImpl<String>> artistasPorFecha) {
        LectorCSV.artistasPorFecha = artistasPorFecha;
    }
}
