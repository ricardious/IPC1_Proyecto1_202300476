package views;

/**
 *
 * @author Ricardious
 */

import controlador.Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import javax.swing.*;
import modelo.DOCTOR;
import modelo.PACIENTE;


public class LOGIN extends JFrame implements ActionListener, FocusListener{

    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JCheckBox cb1;
    private JToggleButton toggleButton;
    
    public LOGIN(){

        
        
        // TAMAÑO DEL FRAME O VENTANA
        int frameWidth = 700;
        int frameHeight = 500;
        
        
    //-----------------WELCOME----------------------------
        
        // Etiqueta welcome
        JLabel titleLabel = new JLabel("WELCOME");
        titleLabel.setFont(new Font("Kristen ITC", Font.ITALIC, 25));
        // Calculamos la posición en el eje x para centrar la etiqueta horizontalmente
        int xTitleLabel = (frameWidth - titleLabel.getPreferredSize().width) / 2;
        // La posicion en el eje y será 10, para que esté justo en la parte superior
        int yTitleLabel = 10;
        // Establecemos la ubicación y el tamaño de la etiqueta "WELCOME"
        titleLabel.setBounds(xTitleLabel, yTitleLabel, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height);
        // Agregamos la etiqueta al frame
        this.add(titleLabel);

    //--------------------------------Imagen------------------------------------
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
        
    //------------------------------Nombre de Usuario------------------------------------------
        // ETIQUETA PARA EL NOMBRE DEL USUARIO
        JLabel usernameLabel = new JLabel("Código:");
        //Formato de fuente
        usernameLabel.setFont(new Font("Cambria", Font.BOLD, 12)); // Establece el tipo de letra y el tamaño
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
        usernameField = new JTextField("202300476");
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
    
        
    //---------------------------------Contraseña------------------------------------------
        // ETIQUETA PARA LA CONTRASEÑA
        JLabel passwordLabel = new JLabel("Contraseña:");
        //formateado de fuente
        passwordLabel.setFont(new Font("Cambria", Font.BOLD, 12)); // Establece el tipo de letra y el tamaño
        // CALCULAR POSICIÓN PARA COLOCARLO DEBAJO DEL CAMPO DE TEXTO DE USUARIO
        int yPasswordField = yField + fieldHeight + 10; // Espacio de 10 píxeles entre los campos
        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        passwordLabel.setBounds(xLabel, yPasswordField, labelWidth, labelHeight);
        // AGREGAR LA ETIQUETA AL FRAME
        this.add(passwordLabel);

        
        // CAMPO DE CONTRASEÑA
        passwordField = new JPasswordField("proyecto1IPC1");
        passwordField.setEchoChar('\u25CF');// Establecer el carácter de eco para ocultar la contraseña
        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        passwordField.setBounds(xField, yPasswordField, fieldWidth, fieldHeight);
        passwordField.addFocusListener(this);
        // AGREGAR EL CAMPO DE CONTRASEÑA AL FRAME
        this.add(passwordField);  

        
    //-----------------------------------------Registro---------------------------------
        // Botón de Registro
        registerButton = new JButton("Registrarse");
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

        
    //------------------------------------------------Inicio de Sesión--------------------------------------------
        // Botón de Inicio de Sesión
        loginButton = new JButton("Iniciar Sesión");
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

        
    //------------------------------------------Ver Password------------------------------------------------------
        // BOTÓN PARA VER PASSWORD
        ImageIcon eyeIcon = new ImageIcon(getClass().getResource("../Images/ver.png")); // Imagen del ojo abierto
        ImageIcon eyeClosedIcon = new ImageIcon(getClass().getResource("../Images/ocultar.png")); // Imagen del ojo cerrado
        toggleButton = new JToggleButton(eyeIcon); // Inicialmente, mostrar el ojo abierto
        // Escalar la imagen del ojo abierto para que se ajuste al tamaño del botón
        Image eyeImage = eyeIcon.getImage().getScaledInstance(fieldHeight, fieldHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledEyeIcon = new ImageIcon(eyeImage);
        toggleButton.setIcon(scaledEyeIcon);// Usar setIcon() para establecer la imagen cuando el botón está seleccionado
        // Escalar la imagen del ojo cerrado para que se ajuste al tamaño del campo de contraseña
        Image eyeClosedImage = eyeClosedIcon.getImage().getScaledInstance(fieldHeight, fieldHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledEyeClosedIcon = new ImageIcon(eyeClosedImage);
        toggleButton.setSelectedIcon(scaledEyeClosedIcon);// Usar setIcon() para establecer la imagen cuando el botón no está seleccionado
        // Establecer el tamaño fijo del botón y posicionarlo al lado del campo de contraseña
        int xToggleButton = xField + fieldWidth + 5; // Añadimos un pequeño espacio entre el campo de contraseña y el botón
        int yToggleButton = yPasswordField;
        toggleButton.setBounds(xToggleButton, yToggleButton, fieldHeight, fieldHeight);
        toggleButton.setContentAreaFilled(false); // Establecer el fondo del botón como transparente
        toggleButton.addActionListener(this);
        this.add(toggleButton); // Mostrar la funcion ver/ocultar

    //-------------------------------------Propiedades del FRAME-------------------------------------------
        
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
        // Deshabilitar la capacidad de cambiar el tamaño de la ventana
        this.setResizable(false);
        // LA VENTANA SEA VISIBLE
        this.setVisible(true);

    }

    //------------------------------------------Eventos------------------------------------------
// Dentro del método actionPerformed en la clase LOGIN
    @Override
    public void actionPerformed(ActionEvent Ae) {
        if (Ae.getSource() == toggleButton) {
            // Lógica para mostrar u ocultar la contraseña
        } else if (Ae.getSource() == loginButton) {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            String pwd = new String(password);

            System.out.println("Usuario: " + username);
            System.out.println("Password: " + pwd);

            boolean loginSuccessful = false;

            // Validar el inicio de sesión para el administrador
            if (username.equals("202300476") && pwd.equals("proyecto1IPC1")) {
                loginSuccessful = true;
                // Iniciar sesión como administrador
                ADMINISTRADOR ventana_admin = new ADMINISTRADOR();
                System.out.println("Welcome admin");
                this.dispose(); // Cerrar la ventana de inicio de sesión
            } else {
                // Iterar sobre la lista de pacientes para validar el inicio de sesión
                for (PACIENTE paciente : Main.listaPacientes) {
                    if (Integer.toString(paciente.getCode()).equals(username) && paciente.getContrasena().equals(pwd)) {
                        loginSuccessful = true;
                        vtnPACIENTE vtn_pacientes = new vtnPACIENTE();
                        break;
                    }
                }

                // Si no se inicia sesión como paciente, intentar iniciar sesión como doctor
                if (!loginSuccessful) {
                    // Iterar sobre la lista de doctores para validar el inicio de sesión
                    for (DOCTOR doctor : Main.listaDoctores) {
                        if (Integer.toString(doctor.getCodigo()).equals(username) && doctor.getPassword().equals(pwd)) {
                            loginSuccessful = true;
                            // Iniciar sesión como doctor
                            // Por ejemplo:
                            // DOCTOR_WINDOW doctorWindow = new DOCTOR_WINDOW();
                            // doctorWindow.setVisible(true);
                            // this.dispose();
                            break;
                        }
                    }
                }
            }
        
        if (!loginSuccessful) {
            // Mostrar mensaje de error si las credenciales son incorrectas
            JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectos.", "Error con el inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    } else if (Ae.getSource() == registerButton) {
        REGISTER ventana_register = new REGISTER();
        this.dispose();
    }
    System.out.println("================================================");
}

    @Override
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }
}