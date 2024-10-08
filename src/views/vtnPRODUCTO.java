/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Ricardious
 * 
 * 
 * 
 * 
 */




public class vtnPRODUCTO extends JFrame implements ActionListener{
    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField especialidadField;
    private JPasswordField passwordField;
    private JComboBox<String> genderComboBox;
    private JTextField edadField;
    private JTextField telefonoField;
    private JButton registerButton;
    private JButton returnButton;

    public vtnPRODUCTO()  {
                // Frame dimensions
                
        int frameWidth = 700;
        int frameHeight = 500;
        //--------------------Registro Doctor----------------------------
        // Label "Registro Doctor"
        JLabel titleLabel = new JLabel("Registro Productos");
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
        JLabel apellidosLabel = new JLabel("Precio*");
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
        JLabel especialidadLabel = new JLabel("Descripción*");
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
        JLabel passwordLabel = new JLabel("Cantidad*");
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


        //=======================================================================================
        // Button for registration
        registerButton = new JButton("Registrar Doctor");
        int buttonWidth = 120;
        int buttonHeight = 30;
        int xRegisterButton = (frameWidth - buttonWidth) / 2;
        int yRegisterButton = yEspecialidadLabel + 40;
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
    }
    

}
