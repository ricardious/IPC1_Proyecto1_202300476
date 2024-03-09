package views;

import modelo.DOCTOR;
import controlador.Main;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Ricardious
 */
public class doctorUPDATE extends JFrame implements ActionListener {

    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField especialidadField;
    private JPasswordField passwordField;
    private JComboBox<String> genderComboBox;
    private JTextField edadField;
    private JTextField telefonoField;
    private JButton actualizarButton;
    private JButton returnButton;
    private JTextField codeField;
    
    
    private JButton codeButton;
    // Método para obtener el género seleccionado
public String obtenerGeneroSeleccionado() {
    return (String) genderComboBox.getSelectedItem();
}


    public doctorUPDATE(int codigo, String nombres, String apellidos, String password, String genero, int edad, String especialidad, String telefono) {

// Frame dimensions
        int frameWidth = 700;
        int frameHeight = 550;

//--------------------Registro Doctor----------------------------
// Label "Registro Doctor"
        JLabel titleLabel = new JLabel("Actualizar Doctor");
        titleLabel.setFont(new Font("Geostar", Font.BOLD, 20));
        int titleLabelWidth = titleLabel.getPreferredSize().width;
        int xTitleLabel = (frameWidth - titleLabelWidth) / 2;
        int yTitleLabel = 20;
        titleLabel.setBounds(xTitleLabel, yTitleLabel, titleLabelWidth, titleLabel.getPreferredSize().height);
        this.add(titleLabel);

// Label code
        JLabel codeLabel = new JLabel("Código");
        int labelWidth = 100;
        int labelHeight = 25;
        int xCodeLabel = (frameWidth - labelWidth - 400) / 2;
        int yCodeLabel = yTitleLabel + 50;
        codeLabel.setBounds(xCodeLabel, yCodeLabel, labelWidth, labelHeight);
        this.add(codeLabel);

//Field code
        codeField = new JTextField();
        codeField.setText(String.valueOf(codigo));
        int fieldWidth = 400;
        int fieldHeight = 25;
        int xCodeField = xCodeLabel + labelWidth;
        int yCodeField = yCodeLabel;
        codeField.setBounds(xCodeField, yCodeField, fieldWidth, fieldHeight);
        codeField.setEnabled(false);
        codeField.addActionListener(this);
        this.add(codeField);

// Label nombres
        JLabel nombresLabel = new JLabel("Nombres");

        int xFirstNameLabel = xCodeLabel;
        int yFirstNameLabel = yCodeField + 40; // Adjusted position
        nombresLabel.setBounds(xFirstNameLabel, yFirstNameLabel, labelWidth, labelHeight);
        this.add(nombresLabel);

//Field nombres
        nombresField = new JTextField();
        nombresField.setText(nombres);
        int xFirstNameField = xCodeField;
        int yFirstNameField = yFirstNameLabel;
        nombresField.setBounds(xFirstNameField, yFirstNameField, fieldWidth, fieldHeight);
        this.add(nombresField);

// Label apellidos
        JLabel apellidosLabel = new JLabel("Apellidos");
        int xLastNameLabel = xCodeLabel;
        int yLastNameLabel = yFirstNameField + 40; // Adjusted position
        apellidosLabel.setBounds(xLastNameLabel, yLastNameLabel, labelWidth, labelHeight);
        this.add(apellidosLabel);

// field apellidos
        apellidosField = new JTextField();
        apellidosField.setText(apellidos);
        int xLastNameField = xCodeField;
        int yLastNameField = yLastNameLabel;
        apellidosField.setBounds(xLastNameField, yLastNameField, fieldWidth, fieldHeight);
        this.add(apellidosField);

//Etiqueta para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña*");
        int xPasswordLabel = xCodeLabel;
        int yPasswordLabel = yLastNameField + 40; // Adjusted position
        passwordLabel.setBounds(xPasswordLabel, yPasswordLabel, labelWidth, labelHeight);
        this.add(passwordLabel);

//Campo para la contraseña
        passwordField = new JPasswordField();
        passwordField.setEchoChar((char) 0);// Establecer el carácter de eco para ocultar la contraseña
        passwordField.setText(password);
        int xPasswordField = xCodeField;
        int yPasswordField = yPasswordLabel;
        passwordField.setBounds(xPasswordField, yPasswordField, fieldWidth, fieldHeight);
        passwordField.addActionListener(this);
        this.add(passwordField);

// ComboBox for gender
        JLabel genderLabel = new JLabel("Género");

        int xGenderLabel = xCodeLabel;
        int yGenderLabel = yPasswordField + 40; // Adjusted position
        genderLabel.setBounds(xGenderLabel, yGenderLabel, labelWidth, labelHeight);
        this.add(genderLabel);

        String[] genders = {"Masculino", "Femenino"};
        genderComboBox = new JComboBox<>(genders);

        int xGenderComboBox = xGenderLabel + labelWidth;
        int yGenderComboBox = yGenderLabel;
        genderComboBox.setBounds(xGenderComboBox, yGenderComboBox, fieldWidth, fieldHeight);
        this.add(genderComboBox);

//Etiqueta para especialidad
        JLabel especialidadLabel = new JLabel("Especialidad");
        int xEspecialidadLabel = xCodeLabel;
        int yEspecialidadLabel = yGenderLabel + 40; // Adjusted position
        especialidadLabel.setBounds(xEspecialidadLabel, yEspecialidadLabel, labelWidth, labelHeight);
        this.add(especialidadLabel);

//Campo para la especialidad
        especialidadField = new JTextField();
        especialidadField.setText(especialidad);
        int xEspecialidadField = xCodeField;
        int yEspecialidadField = yEspecialidadLabel;
        especialidadField.setBounds(xEspecialidadField, yEspecialidadField, fieldWidth, fieldHeight);
        especialidadField.addActionListener(this);
        this.add(especialidadField);

//Telefono Lanbel
        JLabel telefonoLabel = new JLabel("Teléfono");
        int xTelefonoLabel = xCodeLabel;
        int yTelefonoLabel = yEspecialidadField + 40; // Adjusted position
        telefonoLabel.setBounds(xTelefonoLabel, yTelefonoLabel, labelWidth, labelHeight);
        this.add(telefonoLabel);

// Telefono Field
        telefonoField = new JTextField();
        telefonoField.setText(telefono);
        int xTelefonoField = xCodeField;
        int yTelefonoField = yTelefonoLabel;
        telefonoField.setBounds(xTelefonoField, yTelefonoField, fieldWidth, fieldHeight);
        this.add(telefonoField);

// Text field for edad
        JLabel edadLabel = new JLabel("Edad");
        int xEdadLabel = xCodeLabel;
        int yEdadLabel = yTelefonoField + 40; // Adjusted position
        edadLabel.setBounds(xEdadLabel, yEdadLabel, labelWidth, labelHeight);
        this.add(edadLabel);

        edadField = new JTextField();
        edadField.setText(String.valueOf(edad));
        int xEdadField = xCodeField;
        int yEdadField = yEdadLabel;
        edadField.setBounds(xEdadField, yEdadField, fieldWidth, fieldHeight);
        this.add(edadField);


        
        // Button for registration
        actualizarButton = new JButton("Actualizar Doctor");
        int buttonWidth = 120;
        int buttonHeight = 30;
        int xRegisterButton = (frameWidth - buttonWidth) / 2;
        int yRegisterButton = yEdadLabel + 40;
        actualizarButton.setBounds(xRegisterButton, yRegisterButton, buttonWidth, buttonHeight);
        actualizarButton.addActionListener(this);
        this.add(actualizarButton);

        // Button for returning
        returnButton = new JButton("Regresar");
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
    
    if (e.getSource() == actualizarButton) { // Verificar si se presionó el botón de actualizar
//         Obtener los datos del doctor de los campos de texto
        int codigo = Integer.parseInt(codeField.getText());
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        String password = new String(passwordField.getPassword()); // Obtener la contraseña del campo de contraseña
        String genero = (String) genderComboBox.getSelectedItem();
        int edad = Integer.parseInt(edadField.getText());
        String especialidad = especialidadField.getText();
        String telefono = telefonoField.getText();

        Main.listaDoctores.remove(codigo-1000);

        DOCTOR doctor = new DOCTOR(codigo, nombres, apellidos, password, genero, edad, especialidad, telefono);
        Main.listaDoctores.add(doctor);
//         Cerrar la ventana de actualización
         
        
        
        this.dispose();
        ADMINISTRADOR admin = new ADMINISTRADOR();
    } else if (e.getSource() == returnButton) {
    this.dispose();
    ADMINISTRADOR admin = new ADMINISTRADOR();
    }
}


    
    
}
