package Entities;
import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class GestionDeCanciones {
    String filePath;
    MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> Datos;
    Functions funciones;
    public GestionDeCanciones(String filePath) {
        this.filePath = filePath;
        this.Datos = LectorCSV.hashDeDatos(filePath);
        this.funciones = new Functions(Datos);
    }

    public MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> getDatos() {
        return Datos;
    }

    public Functions getFunciones() {
        return funciones;
    }

    public void Gestionar() throws InvalidValue {
        Scanner scanner = new Scanner(System.in);
        int userInput = -1;
        while (userInput != 0){
            System.out.println("Seleccione una función (0-5): \n" +
                    "1.Top 10 para país y día dados\n" +
                    "2.Top 5 canciones en más Tops 50 para un día dado\n" +
                    "3.Top 7 artistas más populares para un rango de fechas dado\n" +
                    "4.Cantidad de apariciones de un artista específico en una fecha dada\n" +
                    "5.Cantidad de canciones con un tempo en un rango específico para un rango de fechas dado\n\n" +
                    "0.Cerrar\n");
            userInput = scanner.nextInt();

            if (userInput == 1){
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD\n");
                LocalDate fecha = LocalDate.parse(scanner.next());
                System.out.println("Ingrese el código de país. Si desea el top mundial ingrese 'World' \n");
                String pais = scanner.next();

                funciones.topTen(fecha,pais);
            }

            else if (userInput == 2) {
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD\n");
                LocalDate fecha = LocalDate.parse(scanner.next());

                funciones.globalTop5(fecha);
            }

            else if (userInput == 3) {

            }

            else if (userInput == 4) {

            }

            else if (userInput == 5) {

            }

            System.out.println("\n=========================================================================\n");

        }



    }



}