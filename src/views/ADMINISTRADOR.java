package views;

/**
 *
 * @author Ricardious
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import javax.swing.*;

public class ADMINISTRADOR extends JFrame implements ActionListener, FocusListener{

    public ADMINISTRADOR() {
        // ----------------------TAMAÑO DEL FRAME O VENTANA------------------------
        int frameWidth = 1000;
        int frameHeight = 600;
        
        //--------------------------------------------------------
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        
        //Crear las pestañas
        JPanel pest1 = new JPanel(new FlowLayout());
        JPanel pest2 = new JPanel(new FlowLayout());
        JPanel pest3 = new JPanel(new FlowLayout());
          
       // Ajustar el tamaño preferido de los paneles
        pest1.setPreferredSize(new Dimension(800, 500));
        pest2.setPreferredSize(new Dimension(800, 500));
        pest3.setPreferredSize(new Dimension(800, 500));
        
        getContentPane().add(tabbedPane);
        
        tabbedPane.addTab("Doctores", pest1);
        tabbedPane.addTab("Pacientes", pest2);
        tabbedPane.addTab("Productos", pest3);
        
        //---------------------------Pestaña Doctores------------------------------
        
        //TaBL
        String[] columnsNames= {"Código", "Nombre Completo", "Género", "Edad", "Especialidad", "Correo"};
        
        JTable table_Doctores= new JTable();
        
        
        
        //------------Creando JFrame------------------
//        this.setExtendedState(MAXIMIZED_BOTH);  // Hacer que la ventana se abra maximizada
        this.setTitle("Administrador"); // TITULO DE LA VENTANA
        this.setSize(frameWidth, frameHeight); // TAMAÑO DE VENTANA
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        this.setResizable(false); // Deshabilitar la capacidad de cambiar el tamaño de la ventana
        this.setLocationRelativeTo(null); // DIMENSIONAR AL CENTRO
        //this.setLayout(null); // DECLARANDO LAYOUT
        this.setVisible(true); // Hacer la ventana visible
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
     
    }

}
