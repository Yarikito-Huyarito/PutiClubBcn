package dao.ImplementacionMySQL;

import model.*;
import dao.DAO;
import java.util.List;
import java.util.ArrayList;
import conexion.Conexion;
import java.sql.*;

public class ProstibuloMySQL implements DAO<Prostibulos> {
    @Override
    public void create(Prostibulos obj) throws Exception {
        String sql = "INSERT INTO prostibulos (nomProstibulo, direccionProstibulo, capacidadChicas, origen, travestit, drogas, armas, precio) VALUES (?,?,?,?,?,?,?,?)";;
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,obj.getNomProstibulo());
            ps.setString(1,obj.getDireccionProstibulo());
            ps.setInt(1,obj.getCapacidadChicas());
            ps.setString(1,obj.getOrigen().name().toLowerCase());
            ps.setBoolean(1,obj.isTravestit());
            ps.setBoolean(1,obj.isDrogas());
            ps.setBoolean(1,obj.isArmas());
            ps.setInt(1,obj.getPrecio());

            ps.executeUpdate();
        }
    }

    @Override
    public Prostibulos getById(int id) throws Exception {
        String sql ="SELECT * FROM prostibulos WHERE id = ?";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Prostibulos(
                        rs.getInt("idProstibulo"),
                        rs.getString("nomProstibulo"),
                        rs.getString("direccionProstibulo"),
                        rs.getInt("capacidadChicas"),
                        Prostibulos.OrigenEnum.valueOf(rs.getString("origen")),
                        rs.getBoolean("travestit"),
                        rs.getBoolean("drogas"),
                        rs.getBoolean("armas"),
                        rs.getInt("precio")
                );
            }
        }
        return null;
    }

    @Override
    public List<Prostibulos> getAll() throws Exception {
        ArrayList<Prostibulos> llista = new ArrayList<>();
        String sql = "SELECT * FROM prostibulos";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){
            while(rs.next()){
                llista.add(new Prostibulos(
                        rs.getInt("idProstibulo"),
                        rs.getString("nomProstibulo"),
                        rs.getString("direccionProstibulo"),
                        rs.getInt("capacidadChicas"),
                        Prostibulos.OrigenEnum.valueOf(rs.getString("origen")),
                        rs.getBoolean("travestit"),
                        rs.getBoolean("drogas"),
                        rs.getBoolean("armas"),
                        rs.getInt("precio")
                ));
            }
        }
        return llista;
    }

    @Override
    public void update(Prostibulos p) throws Exception {
        String sql = "UPDATE prostibulos SET " +
                "nomProstibulo = ?, " +
                "direccionProstibulo = ?, " +
                "capacidadChicas = ?, " +
                "origen = ?, " +
                "travestit = ?, " +
                "drogas = ?, " +
                "armas = ?, " +
                "precio = ? " +
                "WHERE idProstibulo = ?";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, p.getNomProstibulo());
            ps.setString(2, p.getDireccionProstibulo());
            ps.setInt(3, p.getCapacidadChicas());
            ps.setString(4, p.getOrigen().toString());
            ps.setBoolean(5, p.isTravestit());
            ps.setBoolean(6, p.isDrogas());
            ps.setBoolean(7, p.isArmas());
            ps.setInt(8, p.getPrecio());
            ps.setInt(9, p.getIdProstibulo());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM prostibulos WHERE id = ?";
        try(Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }
}
