package Cuenta;

import java.util.ArrayList;

/**
 *
 * @author Ricardious
 */
public class pacientes {
    private ArrayList<PACIENTE> listaPersona;

    public pacientes() {
        listaPersona = new ArrayList<>();
    }
    
    /**
     * 
     * @param nombreUser
     * @return en este método estamos buscando en las listas de persona si ya
     * hay un usuario con ese mismo nombre de usuario
     */

    public PACIENTE buscarPersona(String nombreUser) {
        for (int i = 0; i < listaPersona.size(); i++) {
            PACIENTE persona = listaPersona.get(i);
            if (persona != null) {
                if (persona.getNombreUser().equals(nombreUser)) {
                    return persona;
                }

            }
        }
        return null;
    }

    
    /**
     * 
     * @param persona
     * @return en este método estamos agregando a una persona a la lista de persona
     */
    public boolean agregraPersona(PACIENTE persona) {
        PACIENTE aux = buscarPersona(persona.getNombreUser());
        if (aux == null) {
            listaPersona.add(persona);
            return true;
        }
        return false;
    }

}
