package views;

/**
 *
 * @author Ricardious
 */

import Control.Main;
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
    JButton crearPaciente = new JButton();
    JButton actualizarPaciente = new JButton();
    JButton eliminarPaciente = new JButton();
    JButton crearProducto = new JButton();
    JButton actualizarProducto = new JButton();
    JButton eliminarProducto = new JButton();

    public ADMINISTRADOR() {
        // ====================TAMAÑO DEL FRAME O VENTANA======================
        int frameWidth = 1000;
        int frameHeight = 600;
        
        //--------------------------------------------------------
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        
        //Crear las pestañas
        JPanel pest1 = new JPanel(null);
        JPanel pest2 = new JPanel(null);
        JPanel pest3 = new JPanel(null);        
        // Tabla
        String[] nombresColumnas = {"Código", "Nombre Completo", "Género", "Edad", "Especialidad", "Teléfono"};

        // Crear Tabla Jtable
        JTable tableDoctores = new JTable(Main.convertirDatosDoctor_Tabla(), nombresColumnas);

        JScrollPane scrollPane = new JScrollPane(tableDoctores);
        // Dimensiones del JScrollPane
        int scrollPaneWidth = 600;
        int scrollPaneHeight = 400;
        // Calcular coordenadas x e y para centrar el JScrollPane dentro del panel pest1
        int xScrollPane = (frameWidth - scrollPaneWidth) / 2-100;
        int yScrollPane = (frameHeight - scrollPaneHeight) / 2-40;
        scrollPane.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);

        pest1.add(scrollPane);
        
        // Espacio entre la tabla y los botones
        int verticalSpacing = 20;

        // Coordenadas x para los botones, alineadas con el lado derecho de la tabla
        int xBotones = xScrollPane + scrollPaneWidth + verticalSpacing;

        // Coordenadas y iniciales para los botones
        int yBotones = yScrollPane;

        // Ancho y alto de los botones
        int buttonWidth = 150;
        int buttonHeight = 30;

                     
        //---------------------------Pestaña Doctores---------------------------
        //Boton crear doctores
        crearDoctor = new JButton("Crear Doctor");
        crearDoctor.setBounds(xBotones, yBotones, buttonWidth, buttonHeight);
        yBotones += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        crearDoctor.setEnabled(true);
        crearDoctor.addActionListener(this);
        pest1.add(crearDoctor);
        
        //Boton Actualizar Doctor
        actualizarDoctor  = new JButton("Actualizar Doctor");
        actualizarDoctor.setBounds(xBotones, yBotones, buttonWidth, buttonHeight);
        yBotones += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        actualizarDoctor.setEnabled(true);
        actualizarDoctor.addActionListener(this);
        pest1.add(actualizarDoctor);
        
        //Boton Eliminar Doctor
        eliminarDoctor = new JButton("Eliminar Doctor");
        eliminarDoctor.setBounds(xBotones, yBotones, buttonWidth, buttonHeight);
        eliminarDoctor.setEnabled(true);
        eliminarDoctor.addActionListener(this);
        pest1.add(eliminarDoctor);
        
        //=======================================================================

            
        //--------------------------Table pacientes-----------------------------------------
        String[] pacientesColumnas = {"Código", "Nombre Completo", "Género", "Edad"};
        
        JTable tablePacientes = new JTable(Main.convertirDatosPaciente_Tabla(), pacientesColumnas);
        
        JScrollPane scrollPacientes = new JScrollPane(tablePacientes);
        scrollPacientes.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);

        pest2.add(scrollPacientes);
        
        // Coordenadas y iniciales para los botones
        int yBoton = yScrollPane;
                
        //Boton agregar pacientes
        crearPaciente = new JButton("Crear Paciente");
        crearPaciente.setBounds(xBotones, yBoton, buttonWidth, buttonHeight);
        yBoton += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        crearPaciente.setEnabled(true);
        crearPaciente.addActionListener(this);
        pest2.add(crearPaciente);
        
        //Boton Actualizar pacientes
        actualizarPaciente  = new JButton("Actualizar Paciente");
        actualizarPaciente.setBounds(xBotones, yBoton, buttonWidth, buttonHeight);
        yBoton += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        actualizarPaciente.setEnabled(true);
        actualizarPaciente.addActionListener(this);
        pest2.add(actualizarPaciente);
        
        // Boton eliminar pacientes
        eliminarPaciente = new JButton("Eliminar Paciente");
        eliminarPaciente.setBounds(xBotones, yBoton, buttonWidth, buttonHeight);
        eliminarPaciente.setEnabled(true);
        eliminarPaciente.addActionListener(this);
        pest2.add(eliminarPaciente);

                //=======================================================================

            
        //--------------------------Table pacientes-----------------------------------------
        String[] productosColumnas = {"Código", "Nombre", "Cantidad", "Descripción", "Precio"};
        
        JTable tableProductos = new JTable(Main.convertirDatosProductos_Tabla(), productosColumnas);
        
        JScrollPane scrollProductos = new JScrollPane(tableProductos);
        scrollProductos.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);

        pest3.add(scrollProductos);
        
        // Coordenadas y iniciales para los botones
        int yBotton = yScrollPane;
                
        //Boton agregar pacientes
        crearProducto = new JButton("Crear Producto");
        crearProducto.setBounds(xBotones, yBotton, buttonWidth, buttonHeight);
        yBotton += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        crearProducto.setEnabled(true);
        crearProducto.addActionListener(this);
        pest3.add(crearProducto);
        
        //Boton Actualizar pacientes
        actualizarProducto  = new JButton("Actualizar Producto");
        actualizarProducto.setBounds(xBotones, yBotton, buttonWidth, buttonHeight);
        yBotton += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        actualizarProducto.setEnabled(true);
        actualizarProducto.addActionListener(this);
        pest3.add(actualizarProducto);
        
        // Boton eliminar pacientes
        eliminarProducto = new JButton("Eliminar Producto");
        eliminarProducto.setBounds(xBotones, yBotton, buttonWidth, buttonHeight);
        eliminarProducto.setEnabled(true);
        eliminarProducto.addActionListener(this);
        pest3.add(eliminarProducto);
                
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
        if (e.getSource() == crearDoctor){
            this.dispose();
            doctorREGISTER vtn_login = new doctorREGISTER();
            
        } else if (e.getSource() == crearPaciente) {
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
