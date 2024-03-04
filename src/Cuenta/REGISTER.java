package Cuenta;



/**
 *
 * @author Ricardious
 */

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;


public class REGISTER extends JFrame implements ActionListener {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
private JPasswordField passwordField;

    private JComboBox<String> genderComboBox;

    JButton registerButton;
    JButton returnButton;
    //----------------------------------------------------
    public REGISTER() {
        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setFont(new Font("Kristen ITC", Font.BOLD, 20));
        titleLabel.setBounds(200, 10, 100, 30);
        this.add(titleLabel);

        JLabel firstNameLabel = new JLabel("Nombres:");
        firstNameLabel.setBounds(70, 50, 80, 25);
        this.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(130, 50, 260, 25);
        this.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Apellidos:");
        lastNameLabel.setBounds(70, 90, 80, 25);
        this.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(130, 90, 260, 25);
        this.add(lastNameField);
        
                // ETIQUETA PARA LA CONTRASEÑA
        JLabel passwordLabel = new JLabel("Contraseña:");
//        //formateado de fuente
//        passwordLabel.setFont(new Font("Cambria", Font.BOLD, 12)); // Establece el tipo de letra y el tamaño
//        // CALCULAR POSICIÓN PARA COLOCARLO DEBAJO DEL CAMPO DE TEXTO DE USUARIO
//        int yPasswordField = yField + fieldHeight + 10; // Espacio de 10 píxeles entre los campos
//        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        passwordLabel.setBounds(10, 300, 20, 100);
        // AGREGAR LA ETIQUETA AL FRAME
        this.add(passwordLabel);

        
        // CAMPO DE CONTRASEÑA
        passwordField = new JPasswordField("Password");
        // DIMENSIONES - POSX, POSY, TAMX, TAMY
        passwordField.setBounds(10, 150, 120, 10);
        // AGREGAR EL CAMPO DE CONTRASEÑA AL FRAME
        this.add(passwordField); 
        

//        JLabel emailLabel = new JLabel("Correo:");
//        emailLabel.setBounds(80, 130, 80, 25);
//        this.add(emailLabel);
//
//        emailField = new JTextField();
//        emailField.setBounds(130, 130, 260, 25);
//        this.add(emailField);

        JLabel genderLabel = new JLabel("Género:");
        genderLabel.setBounds(80, 170, 80, 25);
        this.add(genderLabel);

        String[] genders = {"Masculino", "Femenino"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(130, 170, 150, 25);
        this.add(genderComboBox);

        registerButton = new JButton("Registrarse");
        registerButton.setBounds(180, 220, 150, 25);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(214, 225, 50));
        registerButton.addActionListener(this);
        this.add(registerButton);
        
        returnButton = new JButton("Regresar");
        returnButton.setBounds(180, 220, 150, 25);
        returnButton.setForeground(Color.WHITE);
        returnButton.setBackground(new Color(214, 225, 50));
        returnButton.addActionListener(this);
        this.add(returnButton);
        
        this.setTitle("Registro");
        this.setBounds(650, 400, 500, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == registerButton) {
        // Obtener el texto ingresado en los campos de texto
        String nombres = firstNameField.getText();
        String apellidos = lastNameField.getText();
        String genero = (String) genderComboBox.getSelectedItem();
                // Convertir el array de caracteres de la contraseña a una cadena
        char[] contrasenaChars = passwordField.getPassword();
        // Asegurarse de que el campo de contraseña no sea nulo antes de obtener el texto
        if (contrasenaChars != null) {
            String contrasena = new String(contrasenaChars);
        }
        // Otros campos de texto y asignaciones
        
        // Realizar cualquier acción adicional con los datos recopilados, como validarlos o procesarlos
    } else if (e.getSource() == returnButton) {
        // Acción a realizar cuando se hace clic en el botón de regresar
        // Por ejemplo, cerrar la ventana de registro y volver a la ventana anterior
        this.dispose(); // Cierra la ventana actual
        // Aquí podrías abrir la ventana anterior o realizar cualquier otra acción necesaria
    }
    }
    
    
}
