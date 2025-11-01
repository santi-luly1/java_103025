package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonaDB {
	private ArrayList<Persona> lista;
	private Connection con;

	public PersonaDB() throws SQLException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://localhost/personasDB", "root", "test");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private Persona crearPersona(ResultSet rs) throws SQLException {
//								ID			nombre			apellido		edad			estudiante		sexo			pa'is			pa'isIndex
		return new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getInt(8)); // El 8 es cosa m'ia, paisIndex.;
	}

	public ArrayList<Persona> obtenerTodos() throws SQLException {
		lista = new ArrayList<Persona>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM personas");

			while (rs.next()) {
				lista.add(crearPersona(rs));
			}

			rs.close();
			st.close();
//			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return lista;
	}

	public Persona obtenerPersona(int id) throws SQLException {
		Persona p = null;

		try {
			if (lista.get(id - 1) != null) {
				return lista.get(id - 1);
			}

			PreparedStatement pst = con.prepareStatement("SELECT * FROM personas WHERE id=?");

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			p = crearPersona(rs);

			rs.close();
			pst.close();
//			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return p;
	}

	public void insertarPersona(Persona p) throws SQLException {
		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO personas(nombre, apellido, edad, estudiante, sexo, pais, paisIndex) VALUES(?, ?, ?, ?, ?, ?, ?)");

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

	public void modificarPersona(Persona p) throws SQLException {
		try {
			PreparedStatement pst = con.prepareStatement("UPDATE personas SET nombre=?, apellido=?, edad=?, estudiante=?, sexo=?, pais=?, paisIndex=? WHERE id=?");

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

	public void eliminarPersona(Persona p) throws SQLException {
		try {
			PreparedStatement pst = con.prepareStatement("DELETE FROM personas WHERE id=?");

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
			PreparedStatement pst = con.prepareStatement("TRUNCATE TABLE personas");

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
