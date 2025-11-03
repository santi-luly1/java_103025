package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSingleton {
	private static Connection con;

	private ConexionSingleton() {
	}

	/**
	 * M'etodo est'atico que retorna la conexi'on
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 **/
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (con == null || con.isClosed()) {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/prendasDB", "root", "test");
		}

		return con;
	}
}
