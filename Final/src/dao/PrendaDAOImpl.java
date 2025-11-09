package dao;
/**
 * Implementación concreta de la interfaz PrendaDAO.
 * Esta clase contiene toda la lógica para conectar con la base de datos
 * y realizar operaciones CRUD (crear, leer, actualizar, eliminar) sobre la tabla 'prenda'.
 *
 * @author:
 * @version: 1.0.38
 * @fecha: 07/11/2025
 */

import modelo.Prenda;
import conexion.ConexionSingleton;
import java.sql.*;
import java.util.ArrayList;

public class PrendaDAOImpl implements PrendaDAO {

	// Obtiene una conexión activa desde el Singleton.
	private static Connection getConexion() {
	    Connection con = null;
	    try {
	        con = ConexionSingleton.getConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return con;
	}

	// Crea un objeto Prenda con los datos obtenidos desde la base.
	private static Prenda crearPrenda(ResultSet rs) throws SQLException {
		return new Prenda(
			rs.getInt("id_prenda"),
			rs.getString("descripcion"),
			rs.getString("talle"),
			rs.getString("color"),
			rs.getDouble("precio"),
			rs.getInt("stock")
		);
	}
	
	// Devuelve todas las prendas registradas.
	public static ArrayList<Prenda> obtenerTodo() throws SQLException {
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

	// Devuelve una prenda según su ID.
	public static Prenda obtenerPrenda(int id) throws SQLException {
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

	// Inserta una nueva prenda en la base de datos.
	public static boolean insertarPrenda(Prenda p) throws SQLException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement(
					"INSERT INTO prenda(descripcion, talle, color, precio, stock) VALUES(?, ?, ?, ?, ?)"
				)) {

			pst.setString(1, p.getDescripcion());
			pst.setString(2, p.getTalle());
			pst.setString(3, p.getColor());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getStock());
			
			int afectados = pst.executeUpdate();
			
			// Si se insertó correctamente, asigna el nuevo ID a la prenda.
			if (afectados > 0) {
				try (Statement st = con.createStatement();
		             ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()")) {
		            if (rs.next()) {
		                p.setId(rs.getInt(1));
		            }
		        }
			}
			return afectados > 0;
		}
	}

	// Actualiza una prenda existente.
	public static boolean modificarPrenda(Prenda p) throws SQLException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement(
					"UPDATE prenda SET descripcion=?, talle=?, color=?, precio=?, stock=? WHERE id_prenda=?"
				)) {

			pst.setString(1, p.getDescripcion());
			pst.setString(2, p.getTalle());
			pst.setString(3, p.getColor());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getStock());
			pst.setInt(6, p.getId());

			return pst.executeUpdate() > 0;
		}
	}

	// Elimina una prenda según su ID.
	public static boolean eliminarPrenda(int id) throws SQLException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("DELETE FROM prenda WHERE id_prenda=?")) {

			pst.setInt(1, id);
			return pst.executeUpdate() > 0;
		}
	}

	// Elimina todas las prendas.
	public static void eliminarTodo() throws SQLException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("TRUNCATE TABLE prenda")) {
			pst.executeUpdate();
		}
	}
}