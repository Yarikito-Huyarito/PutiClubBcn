package dao;

import java.util.List;


public interface DAO<Tipo> {
    //Funcion de crear:
    void create(Tipo obj) throws Exception;
    //Funcion de obtener por id:
    Tipo getById(int id) throws Exception;
    //Obtener tot:
    List<Tipo> getAll() throws Exception;
    //Actualizar elemento:
    void update(Tipo obj) throws Exception;
    //Eliminar elemento:
    void delete(int id) throws Exception;
}
