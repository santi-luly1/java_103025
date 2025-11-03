package conexion;

import modelo.Prenda;
import java.sql.*;
import java.util.ArrayList;

public class ConexionBD {
	private Connection getConexion() throws SQLException {
		return ConexionSingleton.getConnection();
	}

	private Prenda crearPrenda(ResultSet rs) throws SQLException {
		// FIXME
		return new Prenda(rs.getInt("id_prenda"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("edad"),
				rs.getBoolean("estudiante"), rs.getString("sexo"), rs.getString("pais"), rs.getInt("paisIndex"));
	}

	public ArrayList<Prenda> obtenerTodo() throws SQLException {
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

	public Prenda obtenerPrenda(int id) throws SQLException {
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

	public boolean insertarPrenda(Prenda p) throws SQLException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement(
						"INSERT INTO prenda(nombre, apellido, edad, estudiante, sexo, pais, paisIndex) VALUES(?, ?, ?, ?, ?, ?, ?)")) {

			pst.setString(1, p.getNombre());
			pst.setString(2, p.getApellido());
			pst.setInt(3, p.getEdad());
			pst.setBoolean(4, p.getEstudiante());
			pst.setString(5, p.getSexo());
			pst.setString(6, p.getPais());
			pst.setInt(7, p.getPaisIndex());

			return pst.executeUpdate() > 0;
		}
	}

	public boolean modificarPrenda(Prenda p) throws SQLException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement(
						"UPDATE prenda SET nombre=?, apellido=?, edad=?, estudiante=?, sexo=?, pais=?, paisIndex=? WHERE id_prenda=?")) {

			pst.setString(1, p.getNombre());
			pst.setString(2, p.getApellido());
			pst.setInt(3, p.getEdad());
			pst.setBoolean(4, p.getEstudiante());
			pst.setString(5, p.getSexo());
			pst.setString(6, p.getPais());
			pst.setInt(7, p.getPaisIndex());
			pst.setInt(8, p.getId());

			return pst.executeUpdate() > 0;
		}
	}

	public boolean eliminarPrenda(int id) throws SQLException {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement("DELETE FROM prenda WHERE id_prenda=?")) {

			pst.setInt(1, id);
			return pst.executeUpdate() > 0;
		}
	}

	public void eliminarTodo() throws SQLException {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement("TRUNCATE TABLE prenda")) {
			pst.executeUpdate();
		}
	}
}
