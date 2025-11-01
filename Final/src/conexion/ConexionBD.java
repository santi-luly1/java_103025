package conexion;

import modelo.Prenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConexionBD {
	private ArrayList<Prenda> lista;
	private Connection con;

	public ConexionBD() throws SQLException { //FIXME
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/prendasDB", "root", "test");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private Prenda crearPrenda(ResultSet rs) throws SQLException {
//								ID			nombre			apellido		edad			estudiante		sexo			pa'is			pa'isIndex
		return new Prenda(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getInt(8)); //FIXME
	}

	public ArrayList<Prenda> obtenerTodo() throws SQLException {
		lista = new ArrayList<Prenda>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prenda");

			while (rs.next()) { lista.add(crearPrenda(rs)); }

			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return lista;
	}

	public Prenda obtenerPrenda(int id) throws SQLException {
		Prenda p = null;

		try {
			if (lista.get(id - 1) != null) { return lista.get(id - 1); }

			PreparedStatement pst = con.prepareStatement("SELECT * FROM prenda WHERE id_prenda=?");

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			p = crearPrenda(rs);

			rs.close();
			pst.close();
//			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return p;
	}

	public void insertarPrenda(Prenda p) throws SQLException {
		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO prenda(nombre, apellido, edad, estudiante, sexo, pais, paisIndex) VALUES(?, ?, ?, ?, ?, ?, ?)");

			st.setString(1, p.getNombre());
			st.setString(2, p.getApellido());
			st.setInt(3, p.getEdad());
			st.setBoolean(4, p.getEstudiante());
			st.setString(5, p.getSexo());
			st.setString(6, p.getPais());
			st.setInt(7, p.getPaisIndex());

			if (st.executeUpdate() != 0) {
				if (!lista.contains(p)) {
					if (lista.size() > 1) {
						lista.set(p.getIndex(), p);
					} else {
						lista.add(p);
					}
				}
//				TODO
			}

			st.close();
//			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void modificarPrenda(Prenda p) throws SQLException {
		try {
			PreparedStatement pst = con.prepareStatement("UPDATE prenda SET nombre=?, apellido=?, edad=?, estudiante=?, sexo=?, pais=?, paisIndex=? WHERE id_prenda=?");

			pst.setString(1, p.getNombre());
			pst.setString(2, p.getApellido());
			pst.setInt(3, p.getEdad());
			pst.setBoolean(4, p.getEstudiante());
			pst.setString(5, p.getSexo());
			pst.setString(6, p.getPais());
			pst.setInt(7, p.getPaisIndex());
			pst.setInt(8, p.getId());

			if (pst.executeUpdate() != 0) {
				lista.set(p.getIndex(), p);
//				TODO
			}

			pst.close();
//			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void eliminarPrenda(Prenda p) throws SQLException {
		try {
			PreparedStatement pst = con.prepareStatement("DELETE FROM prenda WHERE id_prenda=?");

			pst.setInt(1, p.getId());

			if (pst.executeUpdate() != 0) {
				lista.remove(p);
//				TODO
			}

			pst.close();
//			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void eliminarTodo() throws SQLException {
		try {
			PreparedStatement pst = con.prepareStatement("TRUNCATE TABLE prenda");

			if (pst.executeUpdate() != 0) {
//				TODO
			}

			pst.close();
//			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
