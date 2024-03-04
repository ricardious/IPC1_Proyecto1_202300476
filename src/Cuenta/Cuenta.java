package Cuenta;

import com.formdev.flatlaf.FlatLightLaf;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Cuenta{
    
    public static ArrayList<DOCTOR> listaDoctores = new ArrayList<>();
    
    public static void main(String[] args){ 
        
        
//        try {
////            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//          Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        //LOGIN ventana_login = new LOGIN();
        ADMINISTRADOR ventana_register = new ADMINISTRADOR();
    }
}