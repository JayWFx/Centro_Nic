package ni.uam.edu.centronic.Interfaces;

import java.util.List;

public interface CRUD<T> {
    void agregar(T entidad);
    List<T> obtenerRegistros();
}