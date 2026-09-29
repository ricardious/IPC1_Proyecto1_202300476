package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Registro de una cita; el historial se conserva al atenderla o rechazarla. */
public class CITA {
    public enum Estado { PENDIENTE, COMPLETADA, RECHAZADA }
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final int numero;
    private final int codigoPaciente;
    private final int codigoDoctor;
    private final LocalDateTime fechaHora;
    private final String motivo;
    private Estado estado = Estado.PENDIENTE;

    public CITA(int numero, int codigoPaciente, int codigoDoctor, LocalDateTime fechaHora, String motivo) {
        this.numero = numero;
        this.codigoPaciente = codigoPaciente;
        this.codigoDoctor = codigoDoctor;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
    }

    public int getNumero() { return numero; }
    public int getCodigoPaciente() { return codigoPaciente; }
    public int getCodigoDoctor() { return codigoDoctor; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public String getFechaFormateada() { return fechaHora.format(FORMATO); }
}
