/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cuenta;

/**
 *
 * @author Ricardious
 */
public class PACIENTE {
    
    /**
     * aquí implementamos los atributos para la clase persona que seria los
     * siguiente nombre, documento, nombre de usuario, y la contraseña
     */
    private String nombres;
    private String apellidos;
    private String genero;
    private String nombreUser;
    private String contrasena;
    private int edad;
    static int code=202400000;

    public PACIENTE(String nombres, String apellidos, String genero, String nombreUser, String contrasena, int edad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.nombreUser = nombreUser;
        this.contrasena = contrasena;
        this.edad = edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public static int getCode() {
        return code;
    }

    public static void setCode(int code) {
        PACIENTE.code = code;
    }
    
    
}
