package views;

import controlador.Main;
import java.awt.*;
import javax.swing.*;

/** Registro de productos de la farmacia. */
public class vtnPRODUCTO extends JFrame {
    public vtnPRODUCTO() {
        setTitle("Registro Productos");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel formulario = new JPanel(new GridLayout(4, 2, 12, 18));
        formulario.setBorder(BorderFactory.createEmptyBorder(40, 70, 35, 70));
        JTextField nombre = new JTextField();
        JTextField precio = new JTextField();
        JTextField descripcion = new JTextField();
        JTextField cantidad = new JTextField();
        formulario.add(new JLabel("Nombre*")); formulario.add(nombre);
        formulario.add(new JLabel("Precio*")); formulario.add(precio);
        formulario.add(new JLabel("Descripción*")); formulario.add(descripcion);
        formulario.add(new JLabel("Cantidad*")); formulario.add(cantidad);
        add(formulario, BorderLayout.CENTER);
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        JButton registrar = new JButton("Registrar producto");
        registrar.addActionListener(e -> {
            try {
                float valor = Float.parseFloat(precio.getText().trim());
                int unidades = Integer.parseInt(cantidad.getText().trim());
                if (nombre.getText().isBlank() || descripcion.getText().isBlank() || !Float.isFinite(valor)
                        || valor < 0 || unidades < 0) throw new IllegalArgumentException("Ingrese datos válidos en todos los campos.");
                int codigo = Main.codigoProducto++;
                Main.agregarProducto(codigo, nombre.getText().trim(), unidades, descripcion.getText().trim(), valor);
                JOptionPane.showMessageDialog(this, "Producto registrado. Código: " + codigo);
                dispose();
                new ADMINISTRADOR();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y cantidad deben ser números válidos.", "Datos inválidos", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton regresar = new JButton("Regresar");
        regresar.addActionListener(e -> { dispose(); new ADMINISTRADOR(); });
        botones.add(registrar); botones.add(regresar);
        add(botones, BorderLayout.SOUTH);
        setVisible(true);
    }
}
