package Control;

import views.LOGIN;
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.ArrayList;
import javax.swing.UIManager;
import views.vtnPACIENTE;


public class Main{
    // ArrayListas para almacenar instancias de las clases DOCTOR, PACIENTE y PRODUCTO
    public static ArrayList<DOCTOR> listaDoctores = new ArrayList<>();
    public static ArrayList<PACIENTE> listaPacientes = new ArrayList<>();
    public static ArrayList<PRODUCTO> listaProductos = new ArrayList<>();
    
    // Códigos iniciales para pacientes, médicos y productos
    public static int codigoPaciente = 202400000;
    public static int codigoDoctor = 1000;
    public static int codigoProducto;
    
    // Método principal
    public static void main(String[] args){ 
        
        //----------------------------------------------------------------------------------------------
        try {
            // Configurando el aspecto visual a FlatDarkLaf
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            // Manejo de excepciones que ocurran durante la inicialización del aspecto visual
            System.err.println( "Failed to initialize LaF" );
        }
        //----------------------------------------------------------------------------------------------
        
        // Creando una instancia de la vista LOGIN
        vtnPACIENTE ventana_login = new vtnPACIENTE();
    }
    //===============================================================================================
  
    
    // Método para convertir datos de pacientes en un formato de tabla
    public static Object[][] convertirDatosPaciente_Tabla() {
        int filas = listaPacientes.size(); 
        Object[][] arreglo = new Object[filas][6]; // 6 columnas: código, nombre completo, género, edad, columnas adicionales pueden añadirse si es necesario
        
        for (int i = 0; i < filas; i++) {
            PACIENTE temp_Paciente = listaPacientes.get(i); // Obteniendo el i-ésimo paciente de la lista
            arreglo[i][0] = temp_Paciente.getCode(); // Almacenando el código del paciente en la primera columna
            String nombreCompleto = temp_Paciente.getNombres()+ " " + temp_Paciente.getApellidos(); // Combinando nombres y apellidos
            arreglo[i][1] = nombreCompleto; // Almacenando el nombre completo en la segunda columna
            arreglo[i][2] = temp_Paciente.getGenero(); // Almacenando el género en la tercera columna
            arreglo[i][3] = temp_Paciente.getEdad(); // Almacenando la edad en la cuarta columna
            // Columnas adicionales pueden agregarse aquí
        }
        
        return arreglo; // Retornando el arreglo que contiene los datos de los pacientes
    }
    
    // Método para agregar un nuevo paciente a la lista
    public static void agregarPaciente(String nombres, String apellidos, String contrasena, String genero, int edad, int code){
        listaPacientes.add(new PACIENTE(nombres, apellidos, contrasena, genero, edad, code)); // Creando una nueva instancia de la clase PACIENTE y añadiéndola a la lista
    }
    
    //=========================================================================================
    // Método para convertir datos de médicos en un formato de tabla
    public static Object[][] convertirDatosDoctor_Tabla() {
        int filas = listaDoctores.size(); 
        Object[][] arreglo = new Object[filas][6]; // 6 columnas: código, nombre completo, género, edad, especialidad, teléfono
        
        for (int i = 0; i < filas; i++) {
            DOCTOR temp_Doctor = listaDoctores.get(i); // Obteniendo el i-ésimo médico de la lista
            arreglo[i][0] = temp_Doctor.getCodigo(); // Almacenando el código del paciente en la primera columna
            String nombreCompleto = temp_Doctor.getNombres()+ " " + temp_Doctor.getApellidos(); // Combinando nombres y apellidos
            arreglo[i][1] = nombreCompleto; // Almacenando el nombre completo en la segunda columna
            arreglo[i][2] = temp_Doctor.getGenero(); // Almacenando el género en la tercera columna
            arreglo[i][3] = temp_Doctor.getEdad(); // Almacenando la edad en la cuarta columna
            arreglo[i][4] = temp_Doctor.getEspecialidad(); // Almacenando la especialidad en la quinta columna
            arreglo[i][5] = temp_Doctor.getTelefono(); // Almacenando el teléfono en la sexta columna
        }
        
        return arreglo; // Retornando el arreglo que contiene los datos de los médicos
    }
    
     // Método para agregar un nuevo médico a la lista
    public static void agregarDoctor(int codigo, String nombres, String apellidos, String genero, int edad, String especialidad, String telefono){
        listaDoctores.add(new DOCTOR(codigo, nombres, apellidos, genero, edad, especialidad, telefono)); // Creando una nueva instancia de la clase DOCTOR y añadiéndola a la lista
    }
    
    //============================================================================================================
    // Método para convertir datos de productos en un formato de tabla
        public static Object[][] convertirDatosProductos_Tabla() {
        int filas = listaProductos.size(); // El número de filas es igual al número de productos
        Object[][] arreglo = new Object[filas][5]; // 5 columnas: código, nombre, cantidad, descripción, precio
        
        for (int i = 0; i < filas; i++) {
            PRODUCTO temp_Producto = listaProductos.get(i); // Obteniendo el i-ésimo producto de la lista
            arreglo[i][0] = temp_Producto.getCode(); // Almacenando el código del producto en la primera columna
            arreglo[i][1] = temp_Producto.getNombre(); // Almacenando el nombre del producto en la segunda columna
            arreglo[i][2] = temp_Producto.getCantidad(); // Almacenando la cantidad del producto en la tercera columna
            arreglo[i][3] = temp_Producto.getDescripcion(); // Almacenando la descripción del producto en la cuarta columna
            arreglo[i][4] = temp_Producto.getPrecio(); // Almacenando el precio del producto en la quinta columna
        }
        
        return arreglo; // Retornando el arreglo que contiene los datos de los productos
    }
        
    // Método para agregar un nuevo producto a la lista
    public static void agregarProducto(int code, String nombre, int cantidad, String descripcion, float precio){
        listaProductos.add(new PRODUCTO(code, nombre, cantidad, descripcion, precio)); // Creando una nueva instancia de la clase PRODUCTO y añadiéndola a la lista
    }
    
    public static boolean existeDoctor(int codigo) {
    for (DOCTOR doctor : listaDoctores) {
        if (doctor.getCodigo() == codigo) {
            return true; // El doctor existe
        }
    }
    return false; // El doctor no existe
}

    public static DOCTOR obtenerDoctorPorCodigo(int codigo) {
    for (DOCTOR doctor : listaDoctores) {
        if (doctor.getCodigo() == codigo) {
            return doctor; // Devuelve el doctor si se encuentra en la lista
        }
    }
    return null; // Si no se encuentra ningún doctor con el código proporcionado, devuelve null
}
    
    
}