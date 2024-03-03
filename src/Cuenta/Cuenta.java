package Cuenta;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Cuenta{
    public static void main(String[] args){ 
        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LOGIN.class.getName()).log(Level.SEVERE, null, ex);
        }


        
        LOGIN ventana_login = new LOGIN();
    }
}