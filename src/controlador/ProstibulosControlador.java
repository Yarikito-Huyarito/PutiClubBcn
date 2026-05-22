package controlador;

import dao.DAO;
import model.Prostibulos;
import java.util.List;

public class ProstibulosControlador {
    private final DAO dao;

    public ProstibulosControlador(DAO dao) {
        this.dao = dao;
    }
    public void crear(Prostibulos e) throws Exception {
        dao.create(e);
    }

    public Prostibulos obtenirPerId(int id) throws Exception {
        return (Prostibulos) dao.getById(id);
    }

    public List<Prostibulos> obtenirTots() throws Exception {
        return dao.getAll();
    }

    public void actualitzar(Prostibulos e) throws Exception {
        dao.update(e);
    }

    public void eliminar(int id) throws Exception {
        dao.delete(id);
    }
}
