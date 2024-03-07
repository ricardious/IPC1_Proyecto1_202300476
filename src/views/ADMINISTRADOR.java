package views;

/**
 *
 * @author Ricardious
 */

import beans.Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class ADMINISTRADOR extends JFrame implements ActionListener, FocusListener{
    
    JButton crearDoctor = new JButton();
    JButton actualizarDoctor = new JButton();
    JButton eliminarDoctor = new JButton();
    JButton exitPacientes = new JButton();
    JButton registerPacientes = new JButton();

    public ADMINISTRADOR() {
        // ----------------------TAMAÑO DEL FRAME O VENTANA---------------------
        int frameWidth = 1000;
        int frameHeight = 600;
        
        //--------------------------------------------------------
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        
        //Crear las pestañas
        JPanel pest1 = new JPanel(new FlowLayout());
        JPanel pest2 = new JPanel(new FlowLayout());
        JPanel pest3 = new JPanel(new FlowLayout());
          
       // Ajustar el tamaño preferido de los paneles
//        pest1.setPreferredSize(new Dimension(800, 500));
//        pest2.setPreferredSize(new Dimension(800, 500));
//        pest3.setPreferredSize(new Dimension(800, 500));
        
        //---------------------------Pestaña Doctores---------------------------
        //Boton crear doctores
        crearDoctor = new JButton("Crear Doctor");
        crearDoctor.setBounds(800, 35, 180, 50);
        crearDoctor.setEnabled(true);
        crearDoctor.addActionListener(this);
        pest1.add(crearDoctor);
        
        //Boton Actualizar Doctor
        actualizarDoctor  = new JButton("Actualizar Doctor");
        actualizarDoctor.setBounds(1000, 35, 180, 50);
        actualizarDoctor.setEnabled(true);
        actualizarDoctor.addActionListener(this);
        pest1.add(actualizarDoctor);
        
        //Boton Eliminar Doctor
        eliminarDoctor = new JButton("Eliminar Doctor");
        eliminarDoctor.setBounds(1200, 35, 180, 50);
        eliminarDoctor.setEnabled(true);
        eliminarDoctor.addActionListener(this);
        pest1.add(eliminarDoctor);
        
        
// Tabla
        String[] nombresColumnas = {"Código", "Nombre Completo", "Género", "Edad", "Especialidad", "Teléfono"};

// Crear Tabla Jtable
        JTable tableDoctores = new JTable(Main.convertirDatosDoctor_Tabla(), nombresColumnas);

        JScrollPane scrollPane = new JScrollPane(tableDoctores);
// Dimensiones del JScrollPane
        int scrollPaneWidth = 850;
        int scrollPaneHeight = 870;
// Calcular coordenadas x e y para centrar el JScrollPane dentro del panel pest1
        int xScrollPane = (frameWidth - scrollPaneWidth) / 2-100;
        int yScrollPane = (frameHeight - scrollPaneHeight) / 2;
        scrollPane.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);

        pest1.add(scrollPane);
        
        //----------------------------------------------------------------------
        
        //Boton agregar pacientes
        registerPacientes = new JButton("Agregar");
        registerPacientes.setBounds(800, 35, 180, 50);
        registerPacientes.setEnabled(true);
        registerPacientes.addActionListener(this);
        pest2.add(registerPacientes);
        
        //Boton Elminar pacientes
        exitPacientes  = new JButton("Salir");
        exitPacientes.setBounds(1000, 35, 180, 50);
        exitPacientes.setEnabled(true);
        exitPacientes.addActionListener(this);
        pest2.add(exitPacientes);
            
        //------------------------Table pacientes-----------------------------------------
        String[] pacientesColumnas = {"Código", "Nombre Completo", "Género", "Edad"};
        
        JTable tablePacientes = new JTable(Main.convertirDatosPaciente_Tabla(), pacientesColumnas);
        
        JScrollPane scrollPacientes = new JScrollPane(tablePacientes);
        

        pest2.add(scrollPacientes);
        
        
        
        
                
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
        if (e.getSource() == exitPacientes){
            this.dispose();
            LOGIN vtn_login = new LOGIN();
            
        } else if (e.getSource() == registerPacientes) {
            this.dispose();
            REGISTER vtn_register = new REGISTER();
        }
        
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
     
    }

}
