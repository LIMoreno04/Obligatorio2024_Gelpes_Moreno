package Entities;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import java.time.LocalDate;

public class GestionDeCanciones {
    String filePath;
    MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> Datos;
    Functions Funciones = new Functions(Datos);
    public GestionDeCanciones(String filePath) {
        this.filePath = filePath;
        this.Datos = LectorCSV.hashDeDatos(filePath);

    }

    public MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> getDatos() {
        return Datos;
    }




}
