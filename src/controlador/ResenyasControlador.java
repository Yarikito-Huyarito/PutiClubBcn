package controlador;

import dao.DAO;
import model.Resenyas;
import java.util.List;

public class ResenyasControlador {
    private final DAO dao;

    public ResenyasControlador(DAO dao) {
        this.dao = dao;
    }
    public void crear(Resenyas e) throws Exception {
        dao.create(e);
    }

    public Resenyas obtenirPerId(int id) throws Exception {
        return (Resenyas) dao.getById(id);
    }

    public List<Resenyas> obtenirTots() throws Exception {
        return dao.getAll();
    }

    public void actualitzar(Resenyas e) throws Exception {
        dao.update(e);
    }

    public void eliminar(int id) throws Exception {
        dao.delete(id);
    }
}
