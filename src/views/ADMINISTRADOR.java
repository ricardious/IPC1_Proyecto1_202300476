package views;

/**
 *
 * @author Ricardious
 */

import Cuenta.Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import javax.swing.*;

public class ADMINISTRADOR extends JFrame implements ActionListener, FocusListener{

    public ADMINISTRADOR() {
        // ----------------------TAMAÑO DEL FRAME O VENTANA---------------------
        int frameWidth = 1000;
        int frameHeight = 600;
        
        //--------------------------------------------------------
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        
        //Crear las pestañas
        JPanel pest1 = new JPanel(new FlowLayout());
        JPanel pest2 = new JPanel(new FlowLayout());
        JPanel pest3 = new JPanel(new FlowLayout());
          
       // Ajustar el tamaño preferido de los paneles
//        pest1.setPreferredSize(new Dimension(800, 500));
//        pest2.setPreferredSize(new Dimension(800, 500));
//        pest3.setPreferredSize(new Dimension(800, 500));
        
        //---------------------------Pestaña Doctores---------------------------
//---------------------------Pestaña Doctores---------------------------
// Tabla
        String[] nombresColumnas = {"Código", "Nombre Completo", "Género", "Edad", "Especialidad", "Teléfono"};

// Crear Tabla Jtable
        JTable tableDoctores = new JTable(Main.convertirDatosDoctor_Tabla(), nombresColumnas);

        JScrollPane scrollPane = new JScrollPane(tableDoctores);
// Dimensiones del JScrollPane
        int scrollPaneWidth = 750;
        int scrollPaneHeight = 570;
// Calcular coordenadas x e y para centrar el JScrollPane dentro del panel pest1
        int xScrollPane = (frameWidth - scrollPaneWidth) / 2;
        int yScrollPane = (frameHeight - scrollPaneHeight) / 2;
        scrollPane.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);

        pest1.add(scrollPane);
        //----------------------------------------------------------------------
                
        getContentPane().add(tabbedPane);
        
        tabbedPane.addTab("Doctores", pest1);
        tabbedPane.addTab("Pacientes", pest2);
        tabbedPane.addTab("Productos", pest3);
        

        

        

        
        
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
