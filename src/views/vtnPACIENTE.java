package views;

import Control.PACIENTE;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author T60
 */
public class vtnPACIENTE extends JFrame implements  ActionListener {

    public vtnPACIENTE() {
        // ====================TAMAÑO DEL FRAME O VENTANA=======================
        int frameWidth = 1000;
        int frameHeight = 600;
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); // Colocar las pestañas en la parte superior

        //Crear las pestañas del administrador 
        JPanel pest1 = new JPanel(null);
        JPanel pest2 = new JPanel(null);
        JPanel pest3 = new JPanel(null);
        
        
        //======================================================================
        JLabel labelArea = new JLabel();
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        // Dimensiones del JScrollPane
        int scrollPaneWidth = 600;
        int scrollPaneHeight = 400;
        
        int xScrollPane = (frameWidth - scrollPaneWidth) / 2;
        int yScrollPane = (frameHeight - scrollPaneHeight) / 2;
        
        scrollPane.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);
        pest1.add(scrollPane);

        //======================================================================
        //------------Creando JFrame------------------
        this.setTitle("Paciente"); // TITULO DE LA VENTANA
        this.setSize(frameWidth, frameHeight); // TAMAÑO DE VENTANA
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        this.setResizable(false); // Deshabilitar la capacidad de cambiar el tamaño de la ventana
        this.setLocationRelativeTo(null); // DIMENSIONAR AL CENTRO
        //this.setLayout(null); // DECLARANDO LAYOUT
        this.setVisible(true); // Hacer la ventana visible        
        
        getContentPane().add(tabbedPane);

        tabbedPane.addTab("Doctores", pest1);
        tabbedPane.addTab("Pacientes", pest2);
        tabbedPane.addTab("Productos", pest3);


        tabbedPane.getSelectedIndex();
    }
    

    

    

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    
}
