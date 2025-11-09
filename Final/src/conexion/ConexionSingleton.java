/**
 * Esta clase se encarga de establecer una única conexión a la base de datos.
 * Se utiliza el patrón Singleton para asegurar que solo haya una conexión
 * activa durante toda la ejecución del programa.
 *
 * @author: Ian Mieres
 * @version: 1.0.38
 * @date: 09/11/2025
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSingleton {

    // Variable 'con' guarda la conexión a la base de datos.
    // Es estática para que solo exista una única conexión (patrón Singleton).
    private static Connection con;

    // Constructor privado: impide crear objetos de esta clase desde fuera.
    // Así se garantiza que solo se use la conexión compartida.
    private ConexionSingleton() {}

    /**
     * Método estático que retorna la conexión a la base de datos.
     * Si la conexión es nula o está cerrada, se crea una nueva conexión.
     * Usa el driver de MariaDB para conectarse a la base "prendasDB".
     *
     * @return Connection: objeto de conexión activo.
     * @throws SQLException: si ocurre un error durante la conexión.
     */
    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                // Carga el driver que permite la comunicación con MariaDB.
                Class.forName("org.mariadb.jdbc.Driver");

                // Establece la conexión con la base de datos (URL, usuario y contraseña).
                con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/prendasDB",
                    "root",
                    "test"
                );
            } catch (SQLException | ClassNotFoundException e) {
                // Si ocurre un error, lo muestra en la consola.
                System.out.println(e.getMessage());
            }
        }

        // Devuelve la conexión lista para usar.
        return con;
    }
}