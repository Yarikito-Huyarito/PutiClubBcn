package dao.ImplementacionMySQL;

import conexion.Conexion;
import dao.DAO;
import model.Resenyas;

import java.util.List;
import java.util.ArrayList;
import conexion.Conexion.*;
import java.sql.*;

public class ResenyasMySQL implements DAO<Resenyas> {
    @Override
    public void create(Resenyas r) throws Exception {
        String sql = "INSERT INTO Ressenyes (nom_client,puntuacio,comentari) VALUES (?,?,?)";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,r.getNomClientResenya());
            ps.setInt(2,r.getPuntuacionResenya());
            ps.setString(3,r.getComentarioResenya());
            ps.executeUpdate();
        }
    }

    @Override
    public Resenyas getById(int id) throws Exception {
        String sql = "SELECT FROM ressenyes WHERE id = ?";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Resenyas(
                    rs.getInt("id"),
                    rs.getInt("prostibulo_id"),
                    rs.getString("nom_client"),
                    rs.getInt("puntuacio"),
                    rs.getString("comentari")
                );
            }
        }
        return null;
    }

    @Override
    public List<Resenyas> getAll() throws Exception {
        ArrayList<Resenyas> llista = new ArrayList<>();
        String sql = "SELECT * FROM ressenyes";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                llista.add(new Resenyas(
                        rs.getInt("id"),
                        rs.getInt("prostibulo_id"),
                        rs.getString("nom_client"),
                        rs.getInt("puntuacio"),
                        rs.getString("comentari")
                ));
            }
        }
        return llista;
    }

    @Override
    public void update(Resenyas r) throws Exception {
        String sql = "UPDATE ressenyes SET nom_client = ?, puntuacio = ?, comentari = ? WHERE id = ?";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,r.getNomClientResenya());
            ps.setInt(2,r.getPuntuacionResenya());
            ps.setString(3,r.getComentarioResenya());
            ps.setInt(4,r.getIdResenya());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM ressenyes WHERE id = ?";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.executeUpdate();
        }
    }
}
