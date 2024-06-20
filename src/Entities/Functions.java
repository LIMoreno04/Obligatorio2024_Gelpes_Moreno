package Entities;

import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.hash.ValueStash;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;
import uy.edu.um.adt.linkedlist.Node;

import java.time.LocalDate;
import java.util.Arrays;

public class Functions {

    MyClosedHashImpl<LocalDate,MyLinkedListImpl<String>> artistasPorFecha = LectorCSV.getArtistasPorFecha();
    MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> hashDatos;

    public Functions(MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> hashDatos) {
        this.hashDatos = hashDatos;
    }


    //Funcion UNO:
    public void topTen(LocalDate fecha,String pais) throws InvalidValue {
        System.gc();
        long startTime = System.nanoTime();
        float startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();


        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]> hashPais = hashDatos.find(fecha);
        MyLinkedListImpl<Cancion>[] topPais = hashPais.find(pais);

        for (int i = 0; i < 10; i++) {
            if(topPais[i] != null) {
                for (int j = 0; j < topPais[i].size(); j++) {     //Se fija si en esa posicion del top hay mas de una cancion y la muestra
                        System.out.println(topPais[i].get(j).getDaily_rank() + ". " + topPais[i].get(j).toString());
                }
            }
        }

        long endTime = System.nanoTime();
        float endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Calcular tiempo de ejecución en segundos
        double tiempoEjecucionSegundos = (endTime - startTime) / 1_000_000_000.0;

        // Calcular memoria utilizada en bytes
        float memoriaUsada = endMemory - startMemory;

        System.out.println("\n");
        // Mostrar resultados
        System.out.printf("Tiempo de ejecución: %.3f segundos%n", tiempoEjecucionSegundos);
        System.out.println("Memoria usada: " + memoriaUsada/(1024*1024*1024) + " Gb");
    }

    //Funcion DOS:
    public void globalTop5(LocalDate fecha) throws InvalidValue {
        System.gc();
        long startTime = System.nanoTime();
        float startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        ValueStash<Integer,MyLinkedListImpl<Cancion>>[] topFive = new ValueStash[5];

        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>> cancionesDelDia = hashDeCancionesPuro(fecha,fecha);

        //Reviso todas las canciones del día, y chequeo el tamaño de su linked list
        //(si se repite n veces, su linked list tiene n entradas)
        //Me quedo con las 5 que más se repiten en un array,
        //almacenando las repeticiones de las 5 mejores hasta el momento y comparando
        for (int i = 0; i < cancionesDelDia.getSize(); i++) {
            if (cancionesDelDia.getStashes()[i] != null) {
                MyLinkedListImpl<Cancion> canciones = ((MyLinkedListImpl<Cancion>) cancionesDelDia.getStashes()[i].getValue());
                Cancion cancion = canciones.getFirst();
                int count = canciones.size();
                for (int j = 0; j < 5; j++) {
                    if (topFive[j] == null || topFive[j].getKey() <= count){
                        if (topFive[j] != null && topFive[j].getKey() == count) { //Si n canciones empatan, las guardo todas
                            topFive[j].getValue().add(cancion);
                            break;

                        }else {
                            MyLinkedListImpl<Cancion> listaPosicionJ = new MyLinkedListImpl<>();
                            listaPosicionJ.add(cancion);
                            topFive[j] = new ValueStash<>(count, listaPosicionJ);
                            break;

                        }
                    }

                }

            }
        }

        //El print más complejo que hice en mi vida, pero lo vale
        int techo = 5;
        int pos = 1;
        for (int i = 0; i < techo; i++) {
            String string = "";

            for (int j = 0; j < topFive[i].getValue().size(); j++) {
                string += (pos+j);
                string += "º";
                if (j!=topFive[i].getValue().size()-1) {
                    string += ", ";
                }
            }
            if (topFive[i].getValue().size() != 1){
                string += " (Empate) ";
            }
            string+=" con ";
            string += topFive[i].getKey();
            string += " apariciones --- ";
            for (int j = 0; j < topFive[i].getValue().size(); j++) {
                string += topFive[i].getValue().get(j);
                if (j != topFive[i].getValue().size()-1) {
                    string += "  ---  ";
                }
            }
            pos += topFive[i].getValue().size();
            techo -= (topFive[i].getValue().size()-1);
            System.out.println(string);
        }
        long endTime = System.nanoTime();
        float endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Calcular tiempo de ejecución en segundos
        double tiempoEjecucionSegundos = (endTime - startTime) / 1_000_000_000.0;

        // Calcular memoria utilizada en bytes
        float memoriaUsada = endMemory - startMemory;
        System.out.println("\n");
        // Mostrar resultados
        System.out.printf("Tiempo de ejecución: %.3f segundos%n", tiempoEjecucionSegundos);
        System.out.println("Memoria usada: " + memoriaUsada/(1024*1024*1024) + " Gb");



    }

    //Funcion TRES:
    public void top7Artistas(LocalDate fecha1, LocalDate fecha2) throws InvalidValue {
        System.gc();
        long startTime = System.nanoTime();
        float startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();


        MyClosedHashImpl<String,Integer> conteo = new MyClosedHashImpl<>();
        MyList<MyLinkedListImpl<String>> fechasConArtistas = artistasPorFecha.toList();  //Artistas del hashartistasPorFecha (se repiten los artistas)
        if(!hashDatos.contains(fecha1) || !hashDatos.contains(fecha2)){
            throw new InvalidValue("Error en alguna de las fechas.");
        }

        int ini = 0;        //posicion de la fecha inicial
        int fin = 0;        //posicion de la fecha final

        for (int i = 0; i < Arrays.stream(artistasPorFecha.getStashes()).count(); i++) {
            if(artistasPorFecha.getStashes()[i] != null) {
                if (artistasPorFecha.getStashes()[i].getKey().toString().equals(fecha1.toString())) {
                    ini = i;
                }
                if (artistasPorFecha.getStashes()[i].getKey().toString().equals(fecha2.toString())) {
                    fin = i;
                }
            }
        }

        //Recorre fechasConArtistas desde la fecha inicial hasta la final
        while (ini <= fin) {
            MyLinkedListImpl<String> artistasDelDia = fechasConArtistas.get(ini);
            for (int i = 0; i < artistasDelDia.size(); i++) {
                String artista = artistasDelDia.get(i).trim();
                if (conteo.contains(artista)) {
                    Integer count = conteo.find(artista) + 1;
                    conteo.remove(artista);         //Elimina el artista
                    conteo.put(artista, count);     //Lo agrega otra vez con el nuevo count
                } else {
                    conteo.put(artista, 1);
                }
            }
            ini++;
        }
        //Ordenamiento del top 7
        ValueStash<String,Integer>[] top7 = new ValueStash[7];
        //Metodo de ordenamiento por insercion
        for (int i = 0; i < conteo.getStashes().length; i++) {
            if (conteo.getStashes()[i] != null) {
                ValueStash<String, Integer> actual = conteo.getStashes()[i];
                for (int j = 0; j < top7.length; j++) {
                    if (top7[j] == null || actual.getValue() > top7[j].getValue()) {
                        // Desplazar elementos hacia la derecha
                        for (int k = top7.length - 1; k > j; k--) {
                            top7[k] = top7[k - 1];
                        }
                        top7[j] = actual;
                        break; // Salir del bucle una vez insertado
                    }
                }
            }
        }
        System.out.println("\nTop 7 artistas entre " + fecha1 + " y " + fecha2 + ":\n");
        for (int i = 0; i < top7.length; i++) {
            if (top7[i] != null) {
                System.out.println((i + 1) + ". " + top7[i].getKey() + "\n    Apariciones:  " + top7[i].getValue() + "\n");
            }
        }

        // Medir tiempo y memoria después de ejecutar la función
        long endTime = System.nanoTime();
        float endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Calcular tiempo de ejecución en segundos
        double tiempoEjecucionSegundos = (endTime - startTime) / 1_000_000_000.0;

        // Calcular memoria utilizada en bytes
        float memoriaUsada = endMemory - startMemory;
        System.out.println("\n");
        // Mostrar resultados
        System.out.printf("Tiempo de ejecución: %.3f segundos%n", tiempoEjecucionSegundos);
        System.out.println("Memoria usada: " + memoriaUsada/(1024*1024*1024) + " Gb");

    }

    //Funcion CUATRO:
    public void artistaEnTop50(String artista, LocalDate fecha, String pais) throws InvalidValue {
        System.gc();
        long startTime = System.nanoTime();
        float startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();


        ValueStash<String,Integer> apariciones = new ValueStash<>(artista.trim(),0);

        if(!hashDatos.contains(fecha)){
            throw new InvalidValue("Error en la fecha.");
        }
        if(!hashDatos.find(fecha).contains(pais)){
            throw new InvalidValue("Error en el pais.");
        }

        MyLinkedListImpl<Cancion>[] cancionesDelTop = hashDatos.find(fecha).find(pais);
        //Busca el artista, si lo encuentra le agrega apariciones
        for (int i = 0; i < cancionesDelTop.length; i++) {
            if(cancionesDelTop[i] != null) {
                for (int j = 0; j < cancionesDelTop[i].size(); j++) {   //Como en proporcion, no hay muchas repeticiones el loop anidado no cambia mucho el tiempo de ejecucion
                    if (cancionesDelTop[i].get(j).getArtist().contains(artista)) {
                        apariciones.setValue(apariciones.getValue() + 1);
                    }
                }
            }
        }

        if(apariciones.getValue() == 0){
            System.out.println("El artista: " + apariciones.getKey() + " No aparece en el pais: " + pais + " En la fecha: " + fecha);
        }
        else{
            System.out.println("\nArtista: " + apariciones.getKey() + "\nApariciones: " + apariciones.getValue());
        }

        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Calcular tiempo de ejecución en segundos
        double tiempoEjecucionSegundos = (endTime - startTime) / 1_000_000_000.0;

        // Calcular memoria utilizada en bytes
        float memoriaUsada = endMemory - startMemory;
        System.out.println("\n");
        // Mostrar resultados
        System.out.printf("Tiempo de ejecución: %.3f segundos%n", tiempoEjecucionSegundos);
        System.out.println("Memoria usada: " + memoriaUsada/(1024*1024*1024) + " Gb");
    }

    //Funcion CINCO
    public void cancionesConTempo(LocalDate fecha1, LocalDate fecha2, float tempo1, float tempo2) throws InvalidValue {
        System.gc();

        // Medir tiempo y memoria antes de ejecutar la función
        long startTime = System.nanoTime();
        float startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        int counterTotal = 0;
        int counterSinRepetidas = 0;
        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>> canciones = hashDeCancionesPuro(fecha1,fecha2);

        for (int i = 0; i < canciones.getSize(); i++) {
            if (canciones.getStashes()[i] != null){
                float tempo = ((MyLinkedListImpl<Cancion>) (canciones.getStashes()[i].getValue())).getFirst().getTempo();
                int reps = ((MyLinkedListImpl<Cancion>) (canciones.getStashes()[i].getValue())).size();
                if (tempo > tempo1 && tempo < tempo2){
                    counterSinRepetidas += 1;
                    counterTotal += reps;
                }

            }
        }
        System.out.println("Canciones con tempo entre {" + tempo1 + " } y {" + tempo2 + " } para el rango {" + fecha1 + " } a {" +fecha2 + "}:\nEn total: {" + counterTotal +"}\nSin contar repeticiones: {" + counterSinRepetidas +"}.");

        long endTime = System.nanoTime();
        float endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Calcular tiempo de ejecución en segundos
        double tiempoEjecucionSegundos = (endTime - startTime) / 1_000_000_000.0;

        // Calcular memoria utilizada en bytes
        float memoriaUsada = endMemory - startMemory;
        System.out.println("\n");
        // Mostrar resultados
        System.out.printf("Tiempo de ejecución: %.3f segundos%n", tiempoEjecucionSegundos);
        System.out.println("Memoria usada: " + memoriaUsada/(1024*1024*1024) + " Gb");
    }

    //funcion auxiliar que devuelve un hash por spotify_id de todas las canciones entre 2 fechas,
    //contemplando repeticiones con una linkedlist
    private MyClosedHashImpl<String, MyLinkedListImpl<Cancion>> hashDeCancionesPuro(LocalDate fechaInicio, LocalDate fechaFinal) throws InvalidValue {
        LocalDate fecha = fechaInicio;
        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]> hashPais;
        MyClosedHashImpl<String, MyLinkedListImpl<Cancion>> cancionesDelDia = new MyClosedHashImpl<>(); //(o varios dias)


        while (fecha.isBefore(fechaFinal.plusDays(1))) {

            //Unicas dos fechas que no existen en el csv
            if (!fecha.isEqual(LocalDate.parse("2024-03-15")) && !fecha.isEqual(LocalDate.parse("2024-03-17"))) {
                hashPais = hashDatos.find(fecha);

                //Me creo un hash (con key=spotify_id) para almacenar todas las canciones del día pedido
                //Si una cancion se repite la agrego al mismo lugar en forma de linked list
                for (int i = 0; i < hashPais.getSize(); i++) {
                    if (hashPais.getStashes()[i] != null) {
                        MyLinkedListImpl<Cancion>[] top50 = (MyLinkedListImpl<Cancion>[]) hashPais.getStashes()[i].getValue();
                        for (int j = 0; j < 50; j++) {
                            if (top50[j] != null) {
                                Node<Cancion> cancionAux = top50[j].getFirstNode();
                                String cancionID = cancionAux.getValue().getSpotify_id();
                                int index = cancionesDelDia.hashFunction(cancionID);
                                while (cancionAux != null) {
                                    MyLinkedListImpl<Cancion> listaCancionX;
                                    try {
                                        listaCancionX = cancionesDelDia.find(cancionID);
                                    } catch (InvalidValue e) {
                                        listaCancionX = new MyLinkedListImpl<>();
                                        cancionesDelDia.put(cancionID, listaCancionX);
                                    }
                                    listaCancionX.add(cancionAux.getValue());
                                    cancionAux = cancionAux.getNext();
                                }
                            }
                        }
                    }
                }
            }

            fecha = fecha.plusDays(1);
        }

        return cancionesDelDia;
    }


}

