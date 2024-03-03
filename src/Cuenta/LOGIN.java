package Cuenta;

/**
 *
 * @author Ricardious
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;


public class LOGIN extends JFrame implements ActionListener, FocusListener{

    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JCheckBox cb1;
    
    
    public LOGIN(){

        
        
        // TAMAÑO DEL FRAME O VENTANA
        int frameWidth = 700;
        int frameHeight = 500;
        
        
        
        
// Etiqueta welcome
JLabel titleLabel = new JLabel("WELCOME");
titleLabel.setFont(new Font("arial", Font.ITALIC, 25));

// Calculamos la posición en el eje x para centrar la etiqueta horizontalmente
int xTitleLabel = (frameWidth - titleLabel.getPreferredSize().width) / 2;

// La posicion en el eje y será 10, para que esté justo en la parte superior
int yTitleLabel = 10;

// Establecemos la ubicación y el tamaño de la etiqueta "WELCOME"
titleLabel.setBounds(xTitleLabel, yTitleLabel, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height);

// Agregamos la etiqueta al frame
this.add(titleLabel);

        
        
        
        
        // Cargar la imagen
        ImageIcon imagenIcon_ID = new ImageIcon(getClass().getResource("../Images/DoctorRegistration.png"));
        Image imageDemension = imagenIcon_ID.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT); // Cambio a SCALE_DEFAULT
        ImageIcon adjustedImageIcon = new ImageIcon(imageDemension);
                
        // JLabel Para mostrar la imagen        
        JLabel imageLabel = new JLabel(adjustedImageIcon);
        // Dimensiones de la imagen
        int imageWidth = 200;
        int imageHeight = 200;
        // CALCULAR POSICIÓN PARA CENTRAR LA IMAGEN
        int xImage = (frameWidth - imageWidth) / 2;
        int yImage = 30; // Posición vertical
        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        imageLabel.setBounds(xImage, yImage, imageWidth, imageHeight);
        this.add(imageLabel);
        
        
        // ETIQUETA PARA EL NOMBRE DEL USUARIO
        JLabel usernameLabel = new JLabel("Código:");
        // TAMAÑO DEL LABEL
        int labelWidth = 80;
        int labelHeight = 25;        
        // CALCULAR POSICIÓN PARA CENTRAR EL LABEL HORIZONTALMENTE
        int xLabel = (frameWidth - labelWidth - 260) / 2-10;
        int yLabel = (frameHeight - labelHeight) / 2;
        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        usernameLabel.setBounds(xLabel, yLabel, labelWidth, labelHeight);
        // AGREGAR EL LABEL AL FRAME
        this.add(usernameLabel);

        // CAMPO DE TEXTO PARA EL USER
        usernameField = new JTextField();
        // TAMAÑO DEL CAMPO DE TEXTO
        int fieldWidth = 260;
        int fieldHeight = 25;
        // CALCULAR POSICIÓN PARA CENTRAR EL CAMPO DE TEXTO
        int xField = xLabel + labelWidth;
        int yField = (frameHeight - fieldHeight) / 2;
        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        usernameField.setBounds(xField, yField, fieldWidth, fieldHeight);
        // AGREGAR EL CAMPO DE TEXTO AL FRAME
        this.add(usernameField);
    

        
        
        // ETIQUETA PARA LA CONTRASEÑA
        JLabel passwordLabel = new JLabel("Contraseña:");
        // CALCULAR POSICIÓN PARA COLOCARLO DEBAJO DEL CAMPO DE TEXTO DE USUARIO
        int yPasswordField = yField + fieldHeight + 10; // Espacio de 10 píxeles entre los campos
        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        passwordLabel.setBounds(xLabel, yPasswordField, labelWidth, labelHeight);
        // AGREGAR LA ETIQUETA AL FRAME
        this.add(passwordLabel);

        // CAMPO DE CONTRASEÑA
        passwordField = new JPasswordField();
        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        passwordField.setBounds(xField, yPasswordField, fieldWidth, fieldHeight);
        // AGREGAR EL CAMPO DE CONTRASEÑA AL FRAME
        this.add(passwordField);  

        
        //**************************************
        // Botón de Registro
        JButton registerButton = new JButton("Registrarse");
        // Dimensiones del botón de registro
        int buttonWidth = 120;
        int buttonHeight = 30;
        // Calculamos la posición en el eje x para centrar el botón de registro horizontalmente
        int xRegisterButton = (frameWidth - 2 * buttonWidth - 10) / 2; // Dividimos por 2 para centrar, y restamos el ancho de ambos botones más el margen entre ellos
        // Calculamos la posición vertical del botón de registro
        int yRegisterButton = yField + fieldHeight + 70; // Posicionamos el botón debajo del campo de contraseña
        // Establecemos la ubicación y el tamaño del botón de registro
        registerButton.setBounds(xRegisterButton, yRegisterButton, buttonWidth, buttonHeight);
        //Evento
        registerButton.addActionListener(this);
        // Agregamos el botón de registro al frame
        this.add(registerButton);

        // Botón de Inicio de Sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        // Calculamos la posición en el eje x para centrar el botón de inicio de sesión horizontalmente
        int xLoginButton = xRegisterButton + buttonWidth + 10; // Añadimos el ancho del botón de registro y un pequeño margen
        // Calculamos la posición vertical del botón de inicio de sesión (usamos la misma posición vertical que el botón de registro)
        int yLoginButton = yRegisterButton;
        // Establecemos la ubicación y el tamaño del botón de inicio de sesión
        loginButton.setBounds(xLoginButton, yLoginButton, buttonWidth, buttonHeight);
        //Evento
        loginButton.addActionListener(this);
        // Agregamos el botón de inicio de sesión al frame
        this.add(loginButton);



        
        
        // PROPIEDADES DE LA VENTANA
        // TITULO
        this.setTitle("LOGIN");
        // TAMAÑO DE VENTANA
        this.setSize(frameWidth, frameHeight);
        // TERMINAR LA EJECUCION
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // DIMENSIONAR AL CENTRO
        this.setLocationRelativeTo(null);
        // DECLARANDO LAYOUT
        this.setLayout(null);
        // LA VENTANA SEA VISIBLE
        this.setVisible(true);
        
        
      
        
    }

    @Override
    public void actionPerformed(ActionEvent Ae) {
        if (Ae.getActionCommand().equals("Iniciar Sesión")) {
            System.out.println("HOLA LOCSO");
        }else if(Ae.getActionCommand().equals("Registrarse")) {
            System.out.println("print");
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
       
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }
    
    
}
