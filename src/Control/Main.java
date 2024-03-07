package Control;

import views.LOGIN;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Main{
    
    public static ArrayList<DOCTOR> listaDoctores = new ArrayList<>();
    public static ArrayList<PACIENTE> listaPacientes = new ArrayList<>();
    public static int codigoPaciente = 202400000;
    public static int codigoDoctor = 1000;
    public static void main(String[] args){ 
        
        //ListaDoctores---------------------------------------------------------
        
//        listaDoctores.add(new DOCTOR (1,"Alex Ricardo", "Castañeda Rodríguez", "Hombre", 19, "Doctor", "59573806"));
//        listaDoctores.add(new DOCTOR (2,"Alex Ricardo", "Castañeda Rodríguez", "Hombre", 19, "Doctor", "59573806"));
//        listaDoctores.add(new DOCTOR (3,"Alex Ricardo", "Castañeda Rodríguez", "Hombre", 19, "Doctor", "59573806"));
//        listaDoctores.add(new DOCTOR (4,"Alex Ricardo", "Castañeda Rodríguez", "Hombre", 19, "Doctor", "59573806"));

        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        LOGIN ventana_login = new LOGIN();

    }
        public static Object[][] convertirDatosPaciente_Tabla() {
        int filas = listaPacientes.size();
        Object[][] arreglo = new Object[filas][6];
        
        for (int i = 0; i < filas; i++) {
            PACIENTE temp_Paciente = listaPacientes.get(i);
            arreglo[i][0] = temp_Paciente.getCode();
            String nombreCompleto = temp_Paciente.getNombres()+ " " + temp_Paciente.getApellidos();
            arreglo[i][1] = nombreCompleto;
            arreglo[i][2] = temp_Paciente.getGenero();
            arreglo[i][3] = temp_Paciente.getEdad();
        }
        
        return arreglo;
    }
    
    public static void agregarPaciente(String nombres, String apellidos, String contrasena, String genero, int edad, int code){
        listaPacientes.add(new PACIENTE(nombres, apellidos, contrasena, genero, edad, code));
    }
    
    public static Object[][] convertirDatosDoctor_Tabla() {
        int filas = listaDoctores.size();
        Object[][] arreglo = new Object[filas][6];
        
        for (int i = 0; i < filas; i++) {
            DOCTOR temp_Doctor = listaDoctores.get(i);
            arreglo[i][0] = temp_Doctor.getCodigo();
            String nombreCompleto = temp_Doctor.getNombres()+ " " + temp_Doctor.getApellidos();
            arreglo[i][1] = nombreCompleto;
            arreglo[i][2] = temp_Doctor.getGenero();
            arreglo[i][3] = temp_Doctor.getEdad();
            arreglo[i][4] = temp_Doctor.getEspecialidad();
            arreglo[i][5] = temp_Doctor.getTelefono();
        }
        
        return arreglo;
    }
    
    public static void agregarDoctor(int codigo, String nombres, String apellidos, String genero, int edad, String especialidad, String telefono){
        listaDoctores.add(new DOCTOR(codigo, nombres, apellidos, genero, edad, especialidad, telefono));
    }
    
    // Método para buscar un doctor en la lista de doctores por su código
    private DOCTOR buscarDoctor(int codigo) {
        for (DOCTOR doctor : Main.listaDoctores) {
            if (doctor.getCodigo() == codigo) {
                return doctor;
            }
        }
        return null; // No se encontró el doctor
    }


}
