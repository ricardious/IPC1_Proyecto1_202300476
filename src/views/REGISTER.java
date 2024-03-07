package views;

/**
 *
 * @author Ricardious
 */
import Control.Main;
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
    private JTextField edadField;

    private JComboBox<String> genderComboBox;


    JButton registerButton;
    //JButton returnButton;
    //----------------------------------------------------

    public REGISTER() {
        // Frame dimensions
        int frameWidth = 700;
        int frameHeight = 500;

        //-----------------Registro----------------------------
        // Label "Registro"
        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setFont(new Font("Kristen ITC", Font.BOLD, 20));
        int titleLabelWidth = titleLabel.getPreferredSize().width;
        int xTitleLabel = (frameWidth - titleLabelWidth) / 2;
        int yTitleLabel = 20;
        titleLabel.setBounds(xTitleLabel, yTitleLabel, titleLabelWidth, titleLabel.getPreferredSize().height);
        this.add(titleLabel);

        // Text field for first name
        JLabel firstNameLabel = new JLabel("Nombres:");
        int labelWidth = 100;
        int labelHeight = 25;
        int xFirstNameLabel = (frameWidth - labelWidth - 400) / 2;
        int yFirstNameLabel = yTitleLabel + 50;
        firstNameLabel.setBounds(xFirstNameLabel, yFirstNameLabel, labelWidth, labelHeight);
        this.add(firstNameLabel);

        firstNameField = new JTextField();
        int fieldWidth = 400;
        int fieldHeight = 25;
        int xFirstNameField = xFirstNameLabel + labelWidth;
        int yFirstNameField = yFirstNameLabel;
        firstNameField.setBounds(xFirstNameField, yFirstNameField, fieldWidth, fieldHeight);
        this.add(firstNameField);

        // Text field for last name
        JLabel lastNameLabel = new JLabel("Apellidos:");
        int xLastNameLabel = xFirstNameLabel;
        int yLastNameLabel = yFirstNameLabel + 40;
        lastNameLabel.setBounds(xLastNameLabel, yLastNameLabel, labelWidth, labelHeight);
        this.add(lastNameLabel);

        lastNameField = new JTextField();
        int xLastNameField = xLastNameLabel + labelWidth;
        int yLastNameField = yLastNameLabel;
        lastNameField.setBounds(xLastNameField, yLastNameField, fieldWidth, fieldHeight);
        this.add(lastNameField);

        //-------
        //Etiqueta para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        int xPasswordLabel = xFirstNameLabel;
        int yPasswordLabel = yLastNameLabel+40;
        passwordLabel.setBounds(xPasswordLabel, yPasswordLabel, labelWidth, labelHeight);
        this.add(passwordLabel);
        //Campo para la contraseña
        passwordField = new JPasswordField();
        passwordField.setEchoChar((char) 0);// Establecer el carácter de eco para ocultar la contraseña
        int xPasswordField = xFirstNameField;
        int yPasswordField = yLastNameField+40;
        passwordField.setBounds(xPasswordField, yPasswordField, fieldWidth, fieldHeight);
        passwordField.addActionListener(this);
        this.add(passwordField);
        
        // ComboBox for gender
        JLabel genderLabel = new JLabel("Género:");
        int xGenderLabel = xLastNameLabel;
        int yGenderLabel = yLastNameLabel + 80;
        genderLabel.setBounds(xGenderLabel, yGenderLabel, labelWidth, labelHeight);
        this.add(genderLabel);

        String[] genders = {"Masculino", "Femenino"};
        genderComboBox = new JComboBox<>(genders);
        int xGenderComboBox = xGenderLabel + labelWidth;
        int yGenderComboBox = yGenderLabel;
        genderComboBox.setBounds(xGenderComboBox, yGenderComboBox, fieldWidth, fieldHeight);
        this.add(genderComboBox);

        // Text field for edad
        JLabel edadLabel = new JLabel("Edad:");
        int xEdadLabel = xGenderLabel;
        int yEdadLabel = yGenderLabel + 40;
        edadLabel.setBounds(xEdadLabel, yEdadLabel, labelWidth, labelHeight);
        this.add(edadLabel);

        edadField = new JTextField();
        int xEdadField = xEdadLabel + labelWidth;
        int yEdadField = yEdadLabel;
        edadField.setBounds(xEdadField, yEdadField, fieldWidth, fieldHeight);
        this.add(edadField);

        // Button for registration
        registerButton = new JButton("Registrarse");
        int buttonWidth = 120;
        int buttonHeight = 30;
        int xRegisterButton = (frameWidth - buttonWidth) / 2;
        int yRegisterButton = yEdadLabel + 40;
        registerButton.setBounds(xRegisterButton, yRegisterButton, buttonWidth, buttonHeight);
        registerButton.addActionListener(this);
        this.add(registerButton);

        // Button for returning
        JButton returnButton = new JButton("Regresar");
        int xReturnButton = (frameWidth - buttonWidth) / 2;
        int yReturnButton = yRegisterButton + 40;
        returnButton.setBounds(xReturnButton, yReturnButton, buttonWidth, buttonHeight);
        returnButton.addActionListener(this);
        this.add(returnButton);

        //-------------------------------------Frame properties-------------------------------------------
        // Frame properties
        this.setTitle("Registro");
        this.setSize(frameWidth, frameHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
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
            String pwd = new String(contrasenaChars);
            int edad = Integer.parseInt(edadField.getText());
            
            Main.agregarPaciente(nombres, apellidos, pwd, genero, edad, Main.codigoPaciente);
            Main.codigoPaciente++;
            
            this.dispose();
            
            ADMINISTRADOR vtn_admin = new ADMINISTRADOR();
            // Asegurarse de que los campos no sean nulos antes de obtener el texto, exceptuando el numero de telefono
            if (contrasenaChars != null) {
                String contrasena = new String(contrasenaChars);
            }
            // Otros campos de texto y asignaciones

            // Realizar cualquier acción adicional con los datos recopilados, como validarlos o procesarlos
        } else if (e.getActionCommand().equals("Regresar")) {
            // Acción a realizar cuando se hace clic en el botón de regresar
            // Por ejemplo, cerrar la ventana de registro y volver a la ventana anterior
            
            this.dispose(); // Cierra la ventana actual
            LOGIN ventana_login = new LOGIN();
                    
            // Aquí podrías abrir la ventana anterior o realizar cualquier otra acción necesaria
        }
    }

}
