package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Una hora de atención publicada por un doctor. */
public class HORARIO {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final int codigoDoctor;
    private final LocalDateTime fechaHora;

    public HORARIO(int codigoDoctor, LocalDateTime fechaHora) {
        this.codigoDoctor = codigoDoctor;
        this.fechaHora = fechaHora;
    }

    public int getCodigoDoctor() { return codigoDoctor; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override public String toString() { return fechaHora.format(FORMATO); }
}
