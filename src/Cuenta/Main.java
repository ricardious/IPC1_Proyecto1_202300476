package Cuenta;

import views.LOGIN;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Main{
    
    public static ArrayList<DOCTOR> listaDoctores = new ArrayList<>();
    
    public static void main(String[] args){ 

        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        LOGIN ventana_login = new LOGIN();
        //ADMINISTRADOR ventana_register = new ADMINISTRADOR();
    }
}