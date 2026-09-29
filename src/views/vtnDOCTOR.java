package views;

import controlador.Main;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.CITA;
import modelo.DOCTOR;
import modelo.HORARIO;
import modelo.PACIENTE;

/** Atención de citas y publicación de horarios del doctor autenticado. */
public class vtnDOCTOR extends JFrame {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final DOCTOR doctor;
    private final DefaultTableModel citas = new DefaultTableModel(
            new String[]{"No.", "Paciente", "Fecha y hora", "Motivo"}, 0) {
        @Override public boolean isCellEditable(int fila, int columna) { return false; }
    };
    private final DefaultTableModel horarios = new DefaultTableModel(new String[]{"Horario", "Disponible"}, 0) {
        @Override public boolean isCellEditable(int fila, int columna) { return false; }
    };
    private final JTable tablaCitas = new JTable(citas);

    public vtnDOCTOR(DOCTOR doctor) {
        this.doctor = doctor;
        setTitle("Doctor - " + doctor);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Citas", construirCitas());
        tabs.addTab("Asignar horario", construirHorarios());
        add(tabs, BorderLayout.CENTER);
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton perfil = new JButton("Editar perfil");
        perfil.addActionListener(e -> editarPerfil());
        JButton salir = new JButton("Cerrar sesión");
        salir.addActionListener(e -> { dispose(); new LOGIN(); });
        acciones.add(perfil); acciones.add(salir);
        add(acciones, BorderLayout.SOUTH);
        tabs.addChangeListener(e -> actualizar());
        actualizar();
        setVisible(true);
    }

    private JPanel construirCitas() {
        JPanel panel = new JPanel(new BorderLayout());
        tablaCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tablaCitas), BorderLayout.CENTER);
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton detalles = new JButton("Ver más");
        detalles.addActionListener(e -> {
            CITA cita = citaSeleccionada();
            if (cita != null) {
                PACIENTE paciente = Main.obtenerPacientePorCodigo(cita.getCodigoPaciente());
                JOptionPane.showMessageDialog(this, "Paciente: " + (paciente == null ? cita.getCodigoPaciente() : paciente.getNombres() + " " + paciente.getApellidos())
                    + "\nFecha: " + cita.getFechaFormateada() + "\nMotivo: " + cita.getMotivo());
            }
        });
        JButton atender = new JButton("Atender");
        atender.addActionListener(e -> cambiarEstado(CITA.Estado.COMPLETADA));
        JButton rechazar = new JButton("Rechazar");
        rechazar.addActionListener(e -> cambiarEstado(CITA.Estado.RECHAZADA));
        botones.add(detalles); botones.add(atender); botones.add(rechazar);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel construirHorarios() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(new JTable(horarios)), BorderLayout.CENTER);
        JPanel controles = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField entrada = new JTextField(20);
        entrada.setToolTipText("dd/MM/yyyy HH:mm");
        controles.add(new JLabel("Fecha y hora (dd/MM/yyyy HH:mm):"));
        controles.add(entrada);
        JButton agregar = new JButton("Asignar");
        agregar.addActionListener(e -> {
            try {
                Main.publicarHorario(doctor, LocalDateTime.parse(entrada.getText().trim(), FORMATO));
                entrada.setText("");
                actualizar();
                JOptionPane.showMessageDialog(this, "Horario publicado.");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Use el formato dd/MM/yyyy HH:mm.", "Fecha inválida", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Horario inválido", JOptionPane.ERROR_MESSAGE);
            }
        });
        controles.add(agregar);
        panel.add(controles, BorderLayout.SOUTH);
        return panel;
    }

    private CITA citaSeleccionada() {
        int fila = tablaCitas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita.");
            return null;
        }
        int numero = (Integer) citas.getValueAt(tablaCitas.convertRowIndexToModel(fila), 0);
        for (CITA cita : Main.listaCitas) if (cita.getNumero() == numero) return cita;
        return null;
    }

    private void cambiarEstado(CITA.Estado estado) {
        CITA cita = citaSeleccionada();
        if (cita == null) return;
        if (JOptionPane.showConfirmDialog(this, "¿" + (estado == CITA.Estado.COMPLETADA ? "Atender" : "Rechazar")
                + " la cita No. " + cita.getNumero() + "?", "Confirmar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        Main.cambiarEstadoCita(cita, doctor, estado);
        actualizar();
    }

    private void actualizar() {
        citas.setRowCount(0);
        for (CITA cita : Main.listaCitas) {
            if (cita.getCodigoDoctor() == doctor.getCodigo() && cita.getEstado() == CITA.Estado.PENDIENTE) {
                PACIENTE paciente = Main.obtenerPacientePorCodigo(cita.getCodigoPaciente());
                citas.addRow(new Object[]{cita.getNumero(), paciente == null ? cita.getCodigoPaciente() : paciente.getNombres() + " " + paciente.getApellidos(),
                    cita.getFechaFormateada(), cita.getMotivo()});
            }
        }
        horarios.setRowCount(0);
        for (HORARIO horario : Main.listaHorarios)
            if (horario.getCodigoDoctor() == doctor.getCodigo()) horarios.addRow(new Object[]{horario.toString(),
                Main.horarioDisponible(doctor.getCodigo(), horario.getFechaHora()) ? "Sí" : "No"});
    }

    private void editarPerfil() {
        JTextField nombres = new JTextField(doctor.getNombres());
        JTextField apellidos = new JTextField(doctor.getApellidos());
        JTextField edad = new JTextField(String.valueOf(doctor.getEdad()));
        JTextField especialidad = new JTextField(doctor.getEspecialidad());
        JPasswordField clave = new JPasswordField(doctor.getPassword());
        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));
        panel.add(new JLabel("Nombres")); panel.add(nombres);
        panel.add(new JLabel("Apellidos")); panel.add(apellidos);
        panel.add(new JLabel("Edad")); panel.add(edad);
        panel.add(new JLabel("Contraseña")); panel.add(clave);
        panel.add(new JLabel("Especialidad")); panel.add(especialidad);
        if (JOptionPane.showConfirmDialog(this, panel, "Editar perfil", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;
        try {
            int nuevaEdad = Integer.parseInt(edad.getText().trim());
            if (nombres.getText().isBlank() || apellidos.getText().isBlank() || especialidad.getText().isBlank()
                    || clave.getPassword().length == 0 || nuevaEdad <= 0) throw new IllegalArgumentException("Complete todos los campos con datos válidos.");
            doctor.setNombres(nombres.getText().trim());
            doctor.setApellidos(apellidos.getText().trim());
            doctor.setEdad(nuevaEdad);
            doctor.setPassword(new String(clave.getPassword()));
            doctor.setEspecialidad(especialidad.getText().trim());
            setTitle("Doctor - " + doctor);
            JOptionPane.showMessageDialog(this, "Perfil actualizado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
