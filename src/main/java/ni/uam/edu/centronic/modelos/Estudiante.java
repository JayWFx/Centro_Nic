package ni.uam.edu.centronic.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    private String nombres;
    private String apellidos;
    private String usuario;
    private String password;
    private LocalDate fechaNacimiento;
    private String departamento;
    private String curso;
    private String modalidad;
    private String horario;
    private boolean aceptoNormas;

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}