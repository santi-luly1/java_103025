package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Prenda;

public class PrendaDAOImpl implements PrendaDAO {
    private Connection conexion;

    public PrendaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void agregar(Prenda prenda) {
        String sql = "INSERT INTO prenda (descripcion, talle, color, precio, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, prenda.getDescripcion());
            stmt.setString(2, prenda.getTalle());
            stmt.setString(3, prenda.getColor());
            stmt.setDouble(4, prenda.getPrecio());
            stmt.setInt(5, prenda.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Prenda obtenerPorId(int id) {
        Prenda prenda = null;
        String sql = "SELECT * FROM prenda WHERE id_prenda=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                prenda = new Prenda(
                    rs.getInt("id_prenda"),
                    rs.getString("descripcion"),
                    rs.getString("talle"),
                    rs.getString("color"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prenda;
    }

    @Override
    public List<Prenda> listarTodas() {
        List<Prenda> lista = new ArrayList<>();
        String sql = "SELECT * FROM prenda";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prenda p = new Prenda(
                    rs.getInt("id_prenda"),
                    rs.getString("descripcion"),
                    rs.getString("talle"),
                    rs.getString("color"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Prenda prenda) {
        String sql = "UPDATE prenda SET descripcion=?, talle=?, color=?, precio=?, stock=? WHERE id_prenda=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, prenda.getDescripcion());
            stmt.setString(2, prenda.getTalle());
            stmt.setString(3, prenda.getColor());
            stmt.setDouble(4, prenda.getPrecio());
            stmt.setInt(5, prenda.getStock());
            stmt.setInt(6, prenda.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM prenda WHERE id_prenda=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

