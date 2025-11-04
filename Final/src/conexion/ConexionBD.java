package conexion;

import modelo.Prenda;
import java.sql.*;
import java.util.ArrayList;

public class ConexionBD {
	private Connection getConexion() {
	    Connection con = null;
	    
	    try {
	        con = ConexionSingleton.getConnection();
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    return con;
	}

	private Prenda crearPrenda(ResultSet rs) throws SQLException {
		return new Prenda(rs.getInt("id_prenda"), rs.getString("descripcion"), rs.getString("talle"), rs.getString("color"),
				rs.getDouble("precio"), rs.getInt("stock"));
	}

	public ArrayList<Prenda> obtenerTodo() throws SQLException, ClassNotFoundException {
		ArrayList<Prenda> prendas = new ArrayList<>();

		try (Connection con = getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM prenda")) {

			while (rs.next()) {
				prendas.add(crearPrenda(rs));
			}
		}

		return prendas;
	}

	public Prenda obtenerPrenda(int id) throws SQLException, ClassNotFoundException {
		Prenda prenda = null;

		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("SELECT * FROM prenda WHERE id_prenda=?")) {

			pst.setInt(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					prenda = crearPrenda(rs);
				}
			}
		}

		return prenda;
	}

	public boolean insertarPrenda(Prenda p) throws SQLException, ClassNotFoundException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("INSERT INTO prenda(descripcion, talle, color, precio, stock) VALUES(?, ?, ?, ?, ?)")) {

			pst.setString(1, p.getDescripcion());
			pst.setString(2, p.getTalle());
			pst.setString(3, p.getColor());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getStock());

			return pst.executeUpdate() > 0;
		}
	}

	public boolean modificarPrenda(Prenda p) throws SQLException, ClassNotFoundException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("UPDATE prenda SET descripcion=?, talle=?, color=?, precio=?, stock=? WHERE id_prenda=?")) {

			pst.setString(1, p.getDescripcion());
			pst.setString(2, p.getTalle());
			pst.setString(3, p.getColor());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getStock());
			pst.setInt(6, p.getId());

			return pst.executeUpdate() > 0;
		}
	}

	public boolean eliminarPrenda(int id) throws SQLException, ClassNotFoundException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("DELETE FROM prenda WHERE id_prenda=?")) {

			pst.setInt(1, id);
			return pst.executeUpdate() > 0;
		}
	}

	public void eliminarTodo() throws SQLException, ClassNotFoundException {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement("TRUNCATE TABLE prenda")) {
			pst.executeUpdate();
		}
	}
}
