package views;

import controlador.Main;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.CITA;
import modelo.DOCTOR;
import modelo.HORARIO;
import modelo.PACIENTE;
import modelo.PRODUCTO;

/** Pantalla del paciente; conserva las tres pestañas originales. */
public class vtnPACIENTE extends JFrame {
    private final PACIENTE paciente;
    private final JComboBox<String> especialidades = new JComboBox<>();
    private final JComboBox<DOCTOR> doctores = new JComboBox<>();
    private final JComboBox<HORARIO> horarios = new JComboBox<>();
    private final JTextArea motivo = new JTextArea(5, 45);
    private final JButton solicitar = new JButton("Crear cita");
    private final DefaultTableModel modeloCitas = tabla("No.", "Fecha y hora", "Doctor", "Estado");
    private final DefaultTableModel modeloProductos = tabla("Código", "Producto", "Descripción", "Precio", "Cantidad");

    private static DefaultTableModel tabla(String... columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int fila, int columna) { return false; }
        };
    }

    public vtnPACIENTE(PACIENTE paciente) {
        this.paciente = paciente;
        setTitle("Paciente - " + paciente.getNombres());
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Solicitar Cita", construirSolicitud());
        tabs.addTab("Ver Estado de Cita", new JScrollPane(new JTable(modeloCitas)));
        tabs.addTab("Farmacia", new JScrollPane(new JTable(modeloProductos)));
        add(tabs, BorderLayout.CENTER);
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton perfil = new JButton("Editar perfil");
        perfil.addActionListener(e -> editarPerfil());
        JButton salir = new JButton("Cerrar sesión");
        salir.addActionListener(e -> { dispose(); new LOGIN(); });
        acciones.add(perfil);
        acciones.add(salir);
        add(acciones, BorderLayout.SOUTH);
        tabs.addChangeListener(e -> actualizar());
        especialidades.addActionListener(e -> actualizarDoctores());
        doctores.addActionListener(e -> actualizarHorarios());
        solicitar.addActionListener(e -> solicitarCita());
        actualizar();
        setVisible(true);
    }

    private JPanel construirSolicitud() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        JPanel filtros = new JPanel(new GridLayout(3, 2, 8, 8));
        filtros.setBorder(BorderFactory.createEmptyBorder(18, 30, 12, 30));
        filtros.add(new JLabel("Especialidad")); filtros.add(especialidades);
        filtros.add(new JLabel("Doctor")); filtros.add(doctores);
        filtros.add(new JLabel("Horario disponible")); filtros.add(horarios);
        panel.add(filtros, BorderLayout.NORTH);
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBorder(BorderFactory.createTitledBorder("Motivo de la cita"));
        motivo.setLineWrap(true);
        motivo.setWrapStyleWord(true);
        centro.add(new JScrollPane(motivo));
        panel.add(centro, BorderLayout.CENTER);
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pie.add(solicitar);
        panel.add(pie, BorderLayout.SOUTH);
        return panel;
    }

    private void actualizar() {
        String seleccion = (String) especialidades.getSelectedItem();
        especialidades.removeAllItems();
        ArrayList<String> nombres = new ArrayList<>();
        for (DOCTOR doctor : Main.listaDoctores)
            if (!nombres.contains(doctor.getEspecialidad())) nombres.add(doctor.getEspecialidad());
        nombres.sort(String.CASE_INSENSITIVE_ORDER);
        for (String nombre : nombres) especialidades.addItem(nombre);
        if (seleccion != null) especialidades.setSelectedItem(seleccion);
        actualizarDoctores();
        modeloCitas.setRowCount(0);
        for (CITA cita : Main.listaCitas) {
            if (cita.getCodigoPaciente() == paciente.getCode()) {
                DOCTOR doctor = Main.obtenerDoctorPorCodigo(cita.getCodigoDoctor());
                modeloCitas.addRow(new Object[]{cita.getNumero(), cita.getFechaFormateada(),
                    doctor == null ? "Doctor eliminado" : doctor.getNombres() + " " + doctor.getApellidos(), cita.getEstado()});
            }
        }
        modeloProductos.setRowCount(0);
        for (PRODUCTO producto : Main.listaProductos)
            if (producto.getCantidad() > 0) modeloProductos.addRow(new Object[]{producto.getCode(), producto.getNombre(),
                producto.getDescripcion(), producto.getPrecio(), producto.getCantidad()});
        solicitar.setEnabled(!Main.tieneCitaPendiente(paciente.getCode()));
    }

    private void actualizarDoctores() {
        DOCTOR seleccionado = (DOCTOR) doctores.getSelectedItem();
        doctores.removeAllItems();
        for (DOCTOR doctor : Main.listaDoctores)
            if (doctor.getEspecialidad().equals(especialidades.getSelectedItem())) doctores.addItem(doctor);
        if (seleccionado != null) doctores.setSelectedItem(seleccionado);
        actualizarHorarios();
    }

    private void actualizarHorarios() {
        DOCTOR doctor = (DOCTOR) doctores.getSelectedItem();
        horarios.removeAllItems();
        if (doctor == null) return;
        ArrayList<HORARIO> disponibles = new ArrayList<>();
        for (HORARIO horario : Main.listaHorarios)
            if (horario.getCodigoDoctor() == doctor.getCodigo()
                    && Main.horarioDisponible(doctor.getCodigo(), horario.getFechaHora())) disponibles.add(horario);
        disponibles.sort(Comparator.comparing(HORARIO::getFechaHora));
        for (HORARIO horario : disponibles) horarios.addItem(horario);
    }

    private void solicitarCita() {
        try {
            DOCTOR doctor = (DOCTOR) doctores.getSelectedItem();
            HORARIO horario = (HORARIO) horarios.getSelectedItem();
            CITA cita = Main.solicitarCita(paciente, doctor, horario == null ? null : horario.getFechaHora(), motivo.getText());
            JOptionPane.showMessageDialog(this, "Cita No. " + cita.getNumero() + " creada para " + cita.getFechaFormateada());
            motivo.setText("");
            actualizar();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "No se pudo crear la cita", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarPerfil() {
        JTextField nombres = new JTextField(paciente.getNombres());
        JTextField apellidos = new JTextField(paciente.getApellidos());
        JTextField edad = new JTextField(String.valueOf(paciente.getEdad()));
        JPasswordField clave = new JPasswordField(paciente.getContrasena());
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.add(new JLabel("Nombres")); panel.add(nombres);
        panel.add(new JLabel("Apellidos")); panel.add(apellidos);
        panel.add(new JLabel("Edad")); panel.add(edad);
        panel.add(new JLabel("Contraseña")); panel.add(clave);
        if (JOptionPane.showConfirmDialog(this, panel, "Editar perfil", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;
        try {
            int nuevaEdad = Integer.parseInt(edad.getText().trim());
            if (nombres.getText().isBlank() || apellidos.getText().isBlank() || clave.getPassword().length == 0 || nuevaEdad <= 0)
                throw new IllegalArgumentException("Complete todos los campos con datos válidos.");
            paciente.setNombres(nombres.getText().trim());
            paciente.setApellidos(apellidos.getText().trim());
            paciente.setContrasena(new String(clave.getPassword()));
            paciente.setEdad(nuevaEdad);
            setTitle("Paciente - " + paciente.getNombres());
            JOptionPane.showMessageDialog(this, "Perfil actualizado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
