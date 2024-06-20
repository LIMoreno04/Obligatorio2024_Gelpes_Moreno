import Entities.Cancion;
import Entities.Functions;
import Entities.GestionDeCanciones;
import Entities.LectorCSV;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.hash.MyClosedHashImpl;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class Menu extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JButton probarButton;
    private JTextField textField3;
    private JButton probarButton1;
    private JTextField textField4;
    private JTextField textField5;
    private JButton probarButton2;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JButton probarButton4;
    private JButton cerrarButton;
    private JScrollBar scrollBar1;
    private JButton probarButton3;
    private JPanel MenuPrincipal;

    String filePath;
    MyClosedHashImpl<LocalDate, MyClosedHashImpl<String, MyLinkedListImpl<Cancion>[]>> Datos;
    Functions funciones;


    public Menu(String filePath) {
        this.filePath = filePath;
        this.Datos = LectorCSV.hashDeDatos(filePath);
        this.funciones = new Functions(Datos);


        setContentPane(MenuPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        probarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\n");
                String fecha = textField1.getText();
                LocalDate fechaMod = LocalDate.parse(fecha);
                String pais = textField2.getText();
                try {
                    funciones.topTen(fechaMod,pais);
                    System.out.println("\n -------------------------------Finished----------------------------------- \n");
                } catch (InvalidValue ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        probarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\n");
                String fechaX = textField3.getText();
                LocalDate fechaMod = LocalDate.parse(fechaX);
                try {
                    funciones.globalTop5(fechaMod);
                    System.out.println("\n -------------------------------Finished----------------------------------- \n");
                } catch (InvalidValue ex) {

                }
            }
        });
        probarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\n");
                String fecha1 = textField4.getText();
                String fecha2 = textField5.getText();
                LocalDate fecha1Mod = LocalDate.parse(fecha1);
                LocalDate fecha2Mod = LocalDate.parse(fecha2);
                try {
                    funciones.top7Artistas(fecha1Mod,fecha2Mod);
                    System.out.println("\n -------------------------------Finished----------------------------------- \n");
                } catch (InvalidValue ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        probarButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\n");
                String artista = textField6.getText();
                String fecha = textField7.getText();
                LocalDate fechaMod = LocalDate.parse(fecha);
                String pais = textField8.getText();
                try {
                    funciones.artistaEnTop50(artista,fechaMod,pais);
                    System.out.println("\n -------------------------------Finished----------------------------------- \n");
                } catch (InvalidValue ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        probarButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\n");
                Float tempo1 = Float.parseFloat(textField9.getText());
                Float tempo2 = Float.parseFloat(textField10.getText());
                LocalDate fecha1 = LocalDate.parse(textField11.getText());
                LocalDate fecha2 = LocalDate.parse(textField12.getText());
                try {
                    funciones.cancionesConTempo(fecha1,fecha2,tempo1,tempo2);
                    System.out.println("\n -------------------------------Finished----------------------------------- \n");
                } catch (InvalidValue ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args){
        Menu menu = new Menu("src\\universal_top_spotify_songs.csv");
        menu.setLocationRelativeTo(null);
        menu.pack();
        menu.setVisible(true);
    }
}
