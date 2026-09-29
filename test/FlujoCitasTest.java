import controlador.Main;
import java.time.LocalDateTime;
import modelo.CITA;
import modelo.DOCTOR;
import modelo.PACIENTE;

/** Prueba ejecutable sin dependencias de test externas. */
public class FlujoCitasTest {
    public static void main(String[] args) {
        DOCTOR doctor = new DOCTOR(1000, "Ana", "López", "clave", "Femenino", 35, "Pediatría", "");
        PACIENTE paciente = new PACIENTE("Luis", "Pérez", "clave", "Masculino", 22, 202400000);
        Main.listaDoctores.add(doctor);
        Main.listaPacientes.add(paciente);
        LocalDateTime hora = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);
        Main.publicarHorario(doctor, hora);
        assert Main.horarioDisponible(doctor.getCodigo(), hora);
        CITA cita = Main.solicitarCita(paciente, doctor, hora, "Consulta general");
        assert cita.getEstado() == CITA.Estado.PENDIENTE;
        assert Main.tieneCitaPendiente(paciente.getCode());
        assert !Main.horarioDisponible(doctor.getCodigo(), hora);
        try {
            Main.solicitarCita(paciente, doctor, hora, "Duplicada");
            throw new AssertionError("Se aceptó una segunda cita pendiente");
        } catch (IllegalArgumentException esperado) { }
        Main.cambiarEstadoCita(cita, doctor, CITA.Estado.COMPLETADA);
        assert !Main.tieneCitaPendiente(paciente.getCode());
        try {
            Main.cambiarEstadoCita(cita, doctor, CITA.Estado.RECHAZADA);
            throw new AssertionError("Se cambió una cita terminada");
        } catch (IllegalArgumentException esperado) { }
        System.out.println("Flujo de citas correcto");
    }
}
