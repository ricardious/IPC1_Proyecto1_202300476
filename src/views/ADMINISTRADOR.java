package views;

/**
 *
 * @author Ricardious
 */
import modelo.DOCTOR;
import controlador.Main;
import static controlador.Main.convertirDatosDoctor_Tabla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import modelo.PACIENTE;
import modelo.PRODUCTO;
import modelo.CITA;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ADMINISTRADOR extends JFrame implements ActionListener, FocusListener {




    
    JButton crearDoctor = new JButton();
    JButton actualizarDoctor = new JButton();
    JButton eliminarDoctor = new JButton();
    JButton crearPaciente = new JButton();
    JButton actualizarPaciente = new JButton();
    JButton eliminarPaciente = new JButton();
    JButton crearProducto = new JButton();
    JButton actualizarProducto = new JButton();
    JButton eliminarProducto = new JButton();
    JButton logoutButton = new JButton();
    public static JTable tableDoctores = new JTable();

    public ADMINISTRADOR() {
        
        //======================TAMAÑO DEL FRAME O VENTANA======================
        int frameWidth = 1000;
        int frameHeight = 600;

        //======================================================================
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); // Colocar las pestañas en la parte superior

        //Crear las pestañas del administrador
        JPanel pest1 = new JPanel(null);
        JPanel pest2 = new JPanel(null);
        JPanel pest3 = new JPanel(null);


        //========================TABLA DOCTORES================================
        // Tab
        String[] nombresColumnas = {"Código", "Nombre Completo", "Género", "Edad", "Especialidad", "Teléfono"};

        // Crear Tabla Jtable
        tableDoctores = new JTable(Main.convertirDatosDoctor_Tabla(), nombresColumnas);
        // En algún lugar donde tengas acceso a tableDoctores



        JScrollPane scrollPane = new JScrollPane(tableDoctores);
        // Dimensiones del JScrollPane
        int scrollPaneWidth = 600;
        int scrollPaneHeight = 400;
        // Calcular coordenadas x e y para centrar el JScrollPane dentro del panel pest1
        int xScrollPane = (frameWidth - scrollPaneWidth) / 2 - 170;
        int yScrollPane = (frameHeight - scrollPaneHeight) / 2 - 40;
        scrollPane.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);
        pest1.add(scrollPane);
        //============================BOTONES===================================
        //Parametros para los botones
        // Espacio entre la tabla y los botones
        int verticalSpacing = 20;
        // Coordenadas x para los botones, alineadas con el lado derecho de la tabla
        int xBotones = xScrollPane + scrollPaneWidth + verticalSpacing;
        // Coordenadas y iniciales para los botones
        int yBotones = yScrollPane;
        // Ancho y alto de los botones
        int buttonWidth = 150;
        int buttonHeight = 30;
        //======================================================================

        //===============================DOCTORES===============================
        //Boton crear doctores
        crearDoctor = new JButton("Crear Doctor");
        crearDoctor.setBounds(xBotones, yBotones, buttonWidth, buttonHeight);
        yBotones += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        crearDoctor.setEnabled(true);
        crearDoctor.addActionListener(this);
        pest1.add(crearDoctor);

        //Boton Actualizar Doctor
        actualizarDoctor = new JButton("Actualizar Doctor");
        actualizarDoctor.setBounds(xBotones, yBotones, buttonWidth, buttonHeight);
        yBotones += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        actualizarDoctor.setEnabled(true);
        actualizarDoctor.addActionListener(this);
        pest1.add(actualizarDoctor);

        //Boton Eliminar Doctor
        eliminarDoctor = new JButton("Eliminar Doctor");
        eliminarDoctor.setBounds(xBotones, yBotones, buttonWidth, buttonHeight);
        eliminarDoctor.setEnabled(true);
        eliminarDoctor.addActionListener(this);
        pest1.add(eliminarDoctor);
        
        
        //Graficas
        // Gráficas
        // Estilos de graficas: http://www.java2s.com/Code/Java/Chart/CatalogChart.htm
        // Insertar nuestra data (valor, "categoria", "Leyenda de la columna")
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        Map<String, Integer> conteos = new HashMap<>();
        for (DOCTOR doctor : Main.listaDoctores) conteos.merge(doctor.getEspecialidad(), 1, Integer::sum);
        conteos.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
                .forEach(item -> datos.setValue(item.getValue(), "Doctores", item.getKey()));



        // Instancear gráfica de barras 3D
        JFreeChart grafico_barras = ChartFactory.createBarChart3D(
            "Especialidades", // Nombre del grafico
            "Especialidades", // Nombre de las barras o columnas
            "Cantidad", // Número de doctores
            datos, // Datos del grafico
            PlotOrientation.VERTICAL, // Orientacion
            true, // Leyenda de barras individuales por color
            true, // Herramientas
            false // Url del grafico
        );
        
        // Creación de un ChartPanel el cual almacenará nuestro gráfico
        ChartPanel cPanel = new ChartPanel(grafico_barras);
        // Habilitamos es scroll
        cPanel.setMouseWheelEnabled(true);
        // Asignamos la posición y las dimensiones de nuestro ChartPanel
        yBotones += buttonHeight + verticalSpacing;
        cPanel.setBounds(xBotones, yBotones, 270, 200);
        // Agregamos a nuestra pestaña el ChartPanel con nuestro gráfico
        pest1.add(cPanel);

        
        
        //======================================================================
        
        
        
        //--------------------------Table pacientes-----------------------------------------
        String[] pacientesColumnas = {"Código", "Nombre Completo", "Género", "Edad"};

        JTable tablePacientes = new JTable(Main.convertirDatosPaciente_Tabla(), pacientesColumnas);

        JScrollPane scrollPacientes = new JScrollPane(tablePacientes);
        scrollPacientes.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);

        pest2.add(scrollPacientes);
        //-------------------------------------------------------------------------------------------
        // Coordenadas y iniciales para los botones
        int yBoton = yScrollPane;

        //Boton agregar pacientes
        crearPaciente = new JButton("Crear Paciente");
        crearPaciente.setBounds(xBotones, yBoton, buttonWidth, buttonHeight);
        yBoton += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        crearPaciente.setEnabled(true);
        crearPaciente.addActionListener(this);
        pest2.add(crearPaciente);

        //Boton Actualizar pacientes
        actualizarPaciente = new JButton("Actualizar Paciente");
        actualizarPaciente.setBounds(xBotones, yBoton, buttonWidth, buttonHeight);
        yBoton += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        actualizarPaciente.setEnabled(true);
        actualizarPaciente.addActionListener(this);
        pest2.add(actualizarPaciente);

        // Boton eliminar pacientes
        eliminarPaciente = new JButton("Eliminar Paciente");
        eliminarPaciente.setBounds(xBotones, yBoton, buttonWidth, buttonHeight);
        eliminarPaciente.setEnabled(true);
        eliminarPaciente.addActionListener(this);
        pest2.add(eliminarPaciente);

        //==============================================================================================
        //--------------------------Table productos-----------------------------------------
        String[] productosColumnas = {"Código", "Nombre", "Cantidad", "Descripción", "Precio"};

        JTable tableProductos = new JTable(Main.convertirDatosProductos_Tabla(), productosColumnas);

        JScrollPane scrollProductos = new JScrollPane(tableProductos);
        scrollProductos.setBounds(xScrollPane, yScrollPane, scrollPaneWidth, scrollPaneHeight);

        pest3.add(scrollProductos);
        //------------------------------------------------------------------------------------------
        // Coordenadas y iniciales para los botones
        int yBotton = yScrollPane;

        //Boton agregar pacientes
        crearProducto = new JButton("Crear Producto");
        crearProducto.setBounds(xBotones, yBotton, buttonWidth, buttonHeight);
        yBotton += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        crearProducto.setEnabled(true);
        crearProducto.addActionListener(this);
        pest3.add(crearProducto);

        //Boton Actualizar pacientes
        actualizarProducto = new JButton("Actualizar Producto");
        actualizarProducto.setBounds(xBotones, yBotton, buttonWidth, buttonHeight);
        yBotton += buttonHeight + verticalSpacing; // Ajuste para el siguiente botón
        actualizarProducto.setEnabled(true);
        actualizarProducto.addActionListener(this);
        pest3.add(actualizarProducto);

        // Boton eliminar pacientes
        eliminarProducto = new JButton("Eliminar Producto");
        eliminarProducto.setBounds(xBotones, yBotton, buttonWidth, buttonHeight);
        eliminarProducto.setEnabled(true);
        eliminarProducto.addActionListener(this);
        pest3.add(eliminarProducto);
        DefaultCategoryDataset productosDatos = new DefaultCategoryDataset();
        Main.listaProductos.stream().sorted(Comparator.comparingInt(PRODUCTO::getCantidad).reversed()).limit(3)
                .forEach(producto -> productosDatos.setValue(producto.getCantidad(), "Unidades", producto.getNombre()));
        ChartPanel graficaProductos = new ChartPanel(ChartFactory.createBarChart(
                "Top 3 productos", "Producto", "Cantidad", productosDatos,
                PlotOrientation.VERTICAL, false, true, false));
        graficaProductos.setBounds(xBotones, yBotton + buttonHeight + verticalSpacing, 270, 200);
        pest3.add(graficaProductos);
        //=========================================================================================================
//        tabbedPane.setTabComponentAt(100, pest1);

        getContentPane().add(tabbedPane);

        tabbedPane.addTab("Doctores", pest1);
        tabbedPane.addTab("Pacientes", pest2);
        tabbedPane.addTab("Productos", pest3);

        logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(this);
        JPanel pie = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        pie.add(logoutButton);
        getContentPane().add(pie, java.awt.BorderLayout.SOUTH);


        tabbedPane.getSelectedIndex();

        //====================================================================================
        //------------Creando JFrame------------------
//        this.setExtendedState(MAXIMIZED_BOTH);  // Hacer que la ventana se abra maximizada
        this.setTitle("Administrador"); // TITULO DE LA VENTANA
        this.setSize(frameWidth, frameHeight); // TAMAÑO DE VENTANA
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        this.setResizable(false); // Deshabilitar la capacidad de cambiar el tamaño de la ventana
        this.setLocationRelativeTo(null); // DIMENSIONAR AL CENTRO
        //this.setLayout(null); // DECLARANDO LAYOUT
        this.setVisible(true); // Hacer la ventana visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == crearDoctor) {
            this.dispose();
            new doctorREGISTER();
        } else if (e.getSource() == actualizarDoctor) {
            Integer codigo = pedirCodigo("doctor a actualizar");
            if (codigo == null) return;
            DOCTOR doctor = Main.obtenerDoctorPorCodigo(codigo);
            if (doctor == null) { error("El doctor no existe."); return; }
            this.dispose();
            new doctorUPDATE(doctor.getCodigo(), doctor.getNombres(), doctor.getApellidos(), doctor.getPassword(),
                    doctor.getGenero(), doctor.getEdad(), doctor.getEspecialidad(), doctor.getTelefono());
        } else if (e.getSource() == eliminarDoctor) {
            Integer codigo = pedirCodigo("doctor a eliminar");
            if (codigo == null) return;
            DOCTOR doctor = Main.obtenerDoctorPorCodigo(codigo);
            if (doctor == null) { error("El doctor no existe."); return; }
            if (!confirmar("¿Eliminar al doctor " + doctor + "?")) return;
            for (CITA cita : Main.listaCitas)
                if (cita.getCodigoDoctor() == codigo && cita.getEstado() == CITA.Estado.PENDIENTE)
                    cita.setEstado(CITA.Estado.RECHAZADA);
            Main.listaHorarios.removeIf(horario -> horario.getCodigoDoctor() == codigo);
            Main.listaDoctores.remove(doctor);
            recargar();
        } else if (e.getSource() == crearPaciente) {
            this.dispose();
            new REGISTER(true);
        } else if (e.getSource() == actualizarPaciente) {
            Integer codigo = pedirCodigo("paciente a actualizar");
            if (codigo == null) return;
            PACIENTE paciente = Main.obtenerPacientePorCodigo(codigo);
            if (paciente == null) { error("El paciente no existe."); return; }
            editarPaciente(paciente);
        } else if (e.getSource() == eliminarPaciente) {
            Integer codigo = pedirCodigo("paciente a eliminar");
            if (codigo == null) return;
            PACIENTE paciente = Main.obtenerPacientePorCodigo(codigo);
            if (paciente == null) { error("El paciente no existe."); return; }
            if (!confirmar("¿Eliminar al paciente " + paciente.getNombres() + "?")) return;
            for (CITA cita : Main.listaCitas)
                if (cita.getCodigoPaciente() == codigo && cita.getEstado() == CITA.Estado.PENDIENTE)
                    cita.setEstado(CITA.Estado.RECHAZADA);
            Main.listaPacientes.remove(paciente);
            recargar();
        } else if (e.getSource() == crearProducto){
            this.dispose();
            new vtnPRODUCTO();
        } else if (e.getSource() == actualizarProducto) {
            Integer codigo = pedirCodigo("producto a actualizar");
            if (codigo == null) return;
            PRODUCTO producto = Main.obtenerProductoPorCodigo(codigo);
            if (producto == null) { error("El producto no existe."); return; }
            editarProducto(producto);
        } else if (e.getSource() == eliminarProducto) {
            Integer codigo = pedirCodigo("producto a eliminar");
            if (codigo == null) return;
            PRODUCTO producto = Main.obtenerProductoPorCodigo(codigo);
            if (producto == null) { error("El producto no existe."); return; }
            if (!confirmar("¿Eliminar el producto " + producto.getNombre() + "?")) return;
            Main.listaProductos.remove(producto);
            recargar();
        } else if (e.getSource() == logoutButton) {
            dispose();
            new LOGIN();
        }
    }

    private Integer pedirCodigo(String tipo) {
        String valor = JOptionPane.showInputDialog(this, "Ingrese el código del " + tipo + ":");
        if (valor == null) return null;
        try { return Integer.parseInt(valor.trim()); }
        catch (NumberFormatException ex) { error("Ingrese un código numérico válido."); return null; }
    }

    private void error(String mensaje) { JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE); }

    private boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void recargar() { dispose(); new ADMINISTRADOR(); }

    private void editarPaciente(PACIENTE paciente) {
        JTextField nombres = new JTextField(paciente.getNombres());
        JTextField apellidos = new JTextField(paciente.getApellidos());
        JTextField edad = new JTextField(String.valueOf(paciente.getEdad()));
        JPasswordField clave = new JPasswordField(paciente.getContrasena());
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.add(new JLabel("Nombres")); panel.add(nombres);
        panel.add(new JLabel("Apellidos")); panel.add(apellidos);
        panel.add(new JLabel("Edad")); panel.add(edad);
        panel.add(new JLabel("Contraseña")); panel.add(clave);
        if (JOptionPane.showConfirmDialog(this, panel, "Actualizar paciente", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;
        try {
            int nuevaEdad = Integer.parseInt(edad.getText().trim());
            if (nombres.getText().isBlank() || apellidos.getText().isBlank() || clave.getPassword().length == 0 || nuevaEdad <= 0)
                throw new IllegalArgumentException("Datos inválidos.");
            paciente.setNombres(nombres.getText().trim()); paciente.setApellidos(apellidos.getText().trim());
            paciente.setEdad(nuevaEdad); paciente.setContrasena(new String(clave.getPassword()));
            recargar();
        } catch (NumberFormatException ex) { error("La edad debe ser un número positivo."); }
        catch (IllegalArgumentException ex) { error(ex.getMessage()); }
    }

    private void editarProducto(PRODUCTO producto) {
        JTextField nombre = new JTextField(producto.getNombre());
        JTextField precio = new JTextField(String.valueOf(producto.getPrecio()));
        JTextField descripcion = new JTextField(producto.getDescripcion());
        JTextField cantidad = new JTextField(String.valueOf(producto.getCantidad()));
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.add(new JLabel("Nombre")); panel.add(nombre);
        panel.add(new JLabel("Precio")); panel.add(precio);
        panel.add(new JLabel("Descripción")); panel.add(descripcion);
        panel.add(new JLabel("Cantidad")); panel.add(cantidad);
        if (JOptionPane.showConfirmDialog(this, panel, "Actualizar producto", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;
        try {
            float nuevoPrecio = Float.parseFloat(precio.getText().trim());
            int nuevaCantidad = Integer.parseInt(cantidad.getText().trim());
            if (nombre.getText().isBlank() || descripcion.getText().isBlank() || !Float.isFinite(nuevoPrecio)
                    || nuevoPrecio < 0 || nuevaCantidad < 0) throw new IllegalArgumentException("Datos inválidos.");
            producto.setNombre(nombre.getText().trim()); producto.setPrecio(nuevoPrecio);
            producto.setDescripcion(descripcion.getText().trim()); producto.setCantidad(nuevaCantidad);
            recargar();
        } catch (NumberFormatException ex) { error("Precio y cantidad deben ser números válidos."); }
        catch (IllegalArgumentException ex) { error(ex.getMessage()); }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    // En la clase ADMINISTRADOR

}
