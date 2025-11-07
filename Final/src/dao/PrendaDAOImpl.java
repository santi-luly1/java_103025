package dao;

import modelo.Prenda;
import conexion.ConexionSingleton;
import java.sql.*;
import java.util.ArrayList;

public class PrendaDAOImpl implements PrendaDAO {
	private Connection getConexion() { // obtiene la conexi'on del singleton.
	    Connection con = null;
	    
	    try {
	        con = ConexionSingleton.getConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return con;
	}

	private Prenda crearPrenda(ResultSet rs) throws SQLException { // crea objetos Prenda, ya sea para la lista o para retornarlas.
		return new Prenda(rs.getInt("id_prenda"), rs.getString("descripcion"), rs.getString("talle"), rs.getString("color"),
				rs.getDouble("precio"), rs.getInt("stock"));
	}
	
	@Override
	public ArrayList<Prenda> obtenerTodo() throws SQLException { // obtine un ArrayList de todas las prendas en la base de datos.
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

	@Override
	public Prenda obtenerPrenda(int id) throws SQLException { // obtiene una prenda seg'un el id, si no la encuentra, retornar'a null.
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

	@Override
	public boolean agregarPrenda(Prenda p) throws SQLException { // agregamos una nueva prenda a la base de datos
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("INSERT INTO prenda(descripcion, talle, color, precio, stock) VALUES(?, ?, ?, ?, ?)")) {

			pst.setString(1, p.getDescripcion());
			pst.setString(2, p.getTalle());
			pst.setString(3, p.getColor());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getStock());
			
			int afectados = pst.executeUpdate();
			
			if (afectados > 0) {
				try (Statement st = con.createStatement();
		                 ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()")) {
		                if (rs.next()) {
		                    p.setId(rs.getInt(1)); // actualiza la prenda a su id correspondiente (yeeey no m'as bug)
		                }
		            }
			}

			return afectados > 0;
		}
	}

	@Override
	public boolean modificarPrenda(Prenda p) throws SQLException { // modificamos una prenda en la base de datos, la prenda que recive debe de ser la prenda que sustitir'a la prenda existente
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

	@Override
	public boolean eliminarPrenda(int id) throws SQLException { // elimina una prenda de la base de datos
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("DELETE FROM prenda WHERE id_prenda=?")) {

			pst.setInt(1, id);
			return pst.executeUpdate() > 0;
		}
	}

	@Override
	public void eliminarTodo() throws SQLException { // elimina toda la base de datos.
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("TRUNCATE TABLE prenda")) {
			pst.executeUpdate();
		}
	}
}
