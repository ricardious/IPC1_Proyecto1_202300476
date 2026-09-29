import controlador.Main;
import java.time.LocalDateTime;
import modelo.CITA;
import modelo.DOCTOR;
import modelo.PACIENTE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Prueba ejecutable sin dependencias de test externas. */
public class FlujoCitasTest {
    @BeforeEach
    void limpiar() {
        Main.listaDoctores.clear();
        Main.listaPacientes.clear();
        Main.listaProductos.clear();
        Main.listaHorarios.clear();
        Main.listaCitas.clear();
        Main.numeroCita = 1;
    }

    @Test
    void reservaBloqueoYAtencion() {
        DOCTOR doctor = new DOCTOR(1000, "Ana", "López", "clave", "Femenino", 35, "Pediatría", "");
        PACIENTE paciente = new PACIENTE("Luis", "Pérez", "clave", "Masculino", 22, 202400000);
        Main.listaDoctores.add(doctor);
        Main.listaPacientes.add(paciente);
        LocalDateTime hora = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);
        Main.publicarHorario(doctor, hora);
        assertTrue(Main.horarioDisponible(doctor.getCodigo(), hora));
        CITA cita = Main.solicitarCita(paciente, doctor, hora, "Consulta general");
        assertEquals(CITA.Estado.PENDIENTE, cita.getEstado());
        assertTrue(Main.tieneCitaPendiente(paciente.getCode()));
        assertFalse(Main.horarioDisponible(doctor.getCodigo(), hora));
        assertThrows(IllegalArgumentException.class,
                () -> Main.solicitarCita(paciente, doctor, hora, "Duplicada"));
        Main.cambiarEstadoCita(cita, doctor, CITA.Estado.COMPLETADA);
        assertFalse(Main.tieneCitaPendiente(paciente.getCode()));
        assertThrows(IllegalArgumentException.class,
                () -> Main.cambiarEstadoCita(cita, doctor, CITA.Estado.RECHAZADA));
        assertFalse(Main.horarioDisponible(doctor.getCodigo(), hora));
    }

    @Test
    void rechazoLiberaHorarioYDuplicadosSeBloquean() {
        DOCTOR doctor = new DOCTOR(1000, "Ana", "López", "clave", "Femenino", 35, "Pediatría", "");
        PACIENTE primero = new PACIENTE("Luis", "Pérez", "clave", "Masculino", 22, 202400000);
        PACIENTE segundo = new PACIENTE("Eva", "Ruiz", "clave", "Femenino", 23, 202400001);
        Main.listaDoctores.add(doctor);
        Main.listaPacientes.add(primero);
        Main.listaPacientes.add(segundo);
        LocalDateTime hora = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);
        Main.publicarHorario(doctor, hora);
        assertThrows(IllegalArgumentException.class, () -> Main.publicarHorario(doctor, hora));
        CITA cita = Main.solicitarCita(primero, doctor, hora, "Dolor de cabeza");
        assertThrows(IllegalArgumentException.class,
                () -> Main.solicitarCita(segundo, doctor, hora, "Consulta"));
        Main.cambiarEstadoCita(cita, doctor, CITA.Estado.RECHAZADA);
        assertTrue(Main.horarioDisponible(doctor.getCodigo(), hora));
        assertEquals(CITA.Estado.PENDIENTE, Main.solicitarCita(segundo, doctor, hora, "Consulta").getEstado());
    }
}
