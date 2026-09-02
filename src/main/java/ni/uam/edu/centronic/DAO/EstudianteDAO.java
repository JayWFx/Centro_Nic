package ni.uam.edu.centronic.DAO;

import ni.uam.edu.centronic.Interfaces.CRUD;
import ni.uam.edu.centronic.modelos.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO implements CRUD<Estudiante> {
    private final List<Estudiante> estudiantes;

    public EstudianteDAO() {
        this.estudiantes = new ArrayList<>();
    }

    @Override
    public void agregar(Estudiante entidad) {
        estudiantes.add(entidad);
    }

    @Override
    public List<Estudiante> obtenerRegistros() {
        return estudiantes;
    }

    public boolean existeUsuario(String usuario) {
        return estudiantes.stream()
                .anyMatch(e -> e.getUsuario().equalsIgnoreCase(usuario));
    }
}