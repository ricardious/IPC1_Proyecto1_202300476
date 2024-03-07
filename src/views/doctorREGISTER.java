package views;

import Control.Main;
import java.awt.Font;
import java.awt.HeadlessException;
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
public class doctorREGISTER extends JFrame implements ActionListener {

    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField especialidadField;
    private JPasswordField passwordField;
    private JComboBox<String> genderComboBox;
    private JTextField edadField;
    private JTextField telefonoField;
    private JButton registerButton;
    private JButton returnButton;

    public doctorREGISTER() {
        // Frame dimensions
        int frameWidth = 700;
        int frameHeight = 500;
        //--------------------Registro Doctor----------------------------
        // Label "Registro Doctor"
        JLabel titleLabel = new JLabel("Registro Doctor");
        titleLabel.setFont(new Font("Geostar", Font.BOLD, 20));
        int titleLabelWidth = titleLabel.getPreferredSize().width;
        int xTitleLabel = (frameWidth - titleLabelWidth) / 2;
        int yTitleLabel = 20;
        titleLabel.setBounds(xTitleLabel, yTitleLabel, titleLabelWidth, titleLabel.getPreferredSize().height);
        this.add(titleLabel);

        // Label nombres
        JLabel nombresLabel = new JLabel("Nombres*");
        int labelWidth = 100;
        int labelHeight = 25;
        int xFirstNameLabel = (frameWidth - labelWidth - 400) / 2;
        int yFirstNameLabel = yTitleLabel + 50;
        nombresLabel.setBounds(xFirstNameLabel, yFirstNameLabel, labelWidth, labelHeight);
        this.add(nombresLabel);

        //Field nombres
        nombresField = new JTextField();
        int fieldWidth = 400;
        int fieldHeight = 25;
        int xFirstNameField = xFirstNameLabel + labelWidth;
        int yFirstNameField = yFirstNameLabel;
        nombresField.setBounds(xFirstNameField, yFirstNameField, fieldWidth, fieldHeight);
        this.add(nombresField);

        // Label apellidos
        JLabel apellidosLabel = new JLabel("Apellidos*");
        int xLastNameLabel = xFirstNameLabel;
        int yLastNameLabel = yFirstNameLabel + 40;
        apellidosLabel.setBounds(xLastNameLabel, yLastNameLabel, labelWidth, labelHeight);
        this.add(apellidosLabel);

        // field apellidos
        apellidosField = new JTextField();
        int xLastNameField = xLastNameLabel + labelWidth;
        int yLastNameField = yLastNameLabel;
        apellidosField.setBounds(xLastNameField, yLastNameField, fieldWidth, fieldHeight);
        this.add(apellidosField);

        //Etiqueta para especialidad
        JLabel especialidadLabel = new JLabel("Especialidad*");
        int xEspecialidadLabel = xFirstNameLabel;
        int yEspecialidadLabel = yLastNameLabel + 40;
        especialidadLabel.setBounds(xEspecialidadLabel, yEspecialidadLabel, labelWidth, labelHeight);
        this.add(especialidadLabel);

        //Campo para la especialidad
        especialidadField = new JTextField();
        int xEspecialidadField = xFirstNameField;
        int yEspecialidadField = yLastNameField + 40;
        especialidadField.setBounds(xEspecialidadField, yEspecialidadField, fieldWidth, fieldHeight);
        especialidadField.addActionListener(this);
        this.add(especialidadField);

        //Etiqueta para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña*");
        int xPasswordLabel = xEspecialidadLabel;
        int yPasswordLabel = yEspecialidadLabel + 40;
        passwordLabel.setBounds(xPasswordLabel, yPasswordLabel, labelWidth, labelHeight);
        this.add(passwordLabel);
        //Campo para la contraseña
        passwordField = new JPasswordField();
        passwordField.setEchoChar((char) 0);// Establecer el carácter de eco para ocultar la contraseña
        int xPasswordField = xEspecialidadField;
        int yPasswordField = yEspecialidadField + 40;
        passwordField.setBounds(xPasswordField, yPasswordField, fieldWidth, fieldHeight);
        passwordField.addActionListener(this);
        this.add(passwordField);

        // ComboBox for gender
        JLabel genderLabel = new JLabel("Género*");
        int xGenderLabel = xPasswordLabel;
        int yGenderLabel = yPasswordLabel + 40;
        genderLabel.setBounds(xGenderLabel, yGenderLabel, labelWidth, labelHeight);
        this.add(genderLabel);

        String[] genders = {"Masculino", "Femenino"};
        genderComboBox = new JComboBox<>(genders);
        int xGenderComboBox = xGenderLabel + labelWidth;
        int yGenderComboBox = yGenderLabel;
        genderComboBox.setBounds(xGenderComboBox, yGenderComboBox, fieldWidth, fieldHeight);
        this.add(genderComboBox);

        //Telefono Lanbel
        JLabel telefonoLabel = new JLabel("Teléfono");
        int xTelefonoLabel = xGenderLabel;
        int yTelefonoLabel = yGenderLabel + 40;
        telefonoLabel.setBounds(xTelefonoLabel, yTelefonoLabel, labelWidth, labelHeight);
        this.add(telefonoLabel);

        // Telefono Field
        telefonoField = new JTextField();
        int xTelefonoField = xGenderComboBox;
        int yTelefonoField = yGenderComboBox + 40;
        telefonoField.setBounds(xTelefonoField, yTelefonoField, fieldWidth, fieldHeight);
        this.add(telefonoField);

        // Text field for edad
        JLabel edadLabel = new JLabel("Edad*");
        int xEdadLabel = xTelefonoLabel;
        int yEdadLabel = yTelefonoLabel + 40;
        edadLabel.setBounds(xEdadLabel, yEdadLabel, labelWidth, labelHeight);
        this.add(edadLabel);

        edadField = new JTextField();
        int xEdadField = xTelefonoField;
        int yEdadField = yTelefonoField + 40;
        edadField.setBounds(xEdadField, yEdadField, fieldWidth, fieldHeight);
        this.add(edadField);

        // Button for registration
        registerButton = new JButton("Registrar Doctor");
        int buttonWidth = 120;
        int buttonHeight = 30;
        int xRegisterButton = (frameWidth - buttonWidth) / 2;
        int yRegisterButton = yEdadLabel + 40;
        registerButton.setBounds(xRegisterButton, yRegisterButton, buttonWidth, buttonHeight);
        registerButton.addActionListener(this);
        this.add(registerButton);

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
        if (e.getSource() == registerButton) {
            // Obtener el texto ingresado en los campos de texto
            String nombres = nombresField.getText();
            String apellidos = apellidosField.getText();
            String especialidad = especialidadField.getText();
            // Convertir el array de caracteres de la contraseña a una cadena
            char[] contrasenaChars = passwordField.getPassword();
            String pwd = new String(contrasenaChars);
            String genero = (String) genderComboBox.getSelectedItem();
            String telefono = telefonoField.getText();
            int edad = 0;    
            //=================================================================
                    // Verificar que los campos obligatorios no estén vacíos
        if (nombres.isEmpty() || apellidos.isEmpty() || especialidad.isEmpty() || contrasenaChars.length == 0 || genero.isEmpty()) {
            // Mostrar un mensaje de error al usuario
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método actionPerformed si hay campos obligatorios vacíos
        }    
            //============================================================================================================
            
            try {
                // Obtener el texto del campo de texto para la edad
                String edadTexto = edadField.getText();

                // Verificar si el campo de texto para la edad está vacío
                if (edadTexto.isEmpty()) {
                    // Mostrar un mensaje de error al usuario
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese una edad válida.", "Error en la Edad", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed si la edad está vacía
                }
                // Convertir la cadena de texto en un entero
                edad = Integer.parseInt(edadTexto);
            } catch (NumberFormatException ex) {
                // Manejar el error de formato de edad aquí
                // Puedes mostrar un mensaje de error al usuario
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una edad válida.", "Error en la Edad", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método actionPerformed si hay un error en la edad
            }
            
            //==========================================================================================================  


        // Agregar el doctor solo si todos los campos obligatorios están llenos
        Main.agregarDoctor(Main.codigoDoctor, nombres, apellidos, genero, edad, especialidad, telefono);
        Main.codigoDoctor++;
        
        this.dispose();
        
        ADMINISTRADOR vtn_admin = new ADMINISTRADOR();
        
        // Si necesitas hacer algo con la contraseña, puedes hacerlo aquí
        // Nota: contraseña es una variable local en este bloque if y no puede ser accedida fuera de este bloque
        } 
    }

}
