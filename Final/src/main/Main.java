// TODO: Analizar cada una de las clases y asegurarse de que tenga coherencia con lo que V'ictor pide.
// test
package main;
import java.sql.SQLException;

import conexion.ConexionBD;
import controlador.PrendaController;
import vista.PrendaVista;

public class Main {
	public static void main(String[] args) throws SQLException {
		new PrendaVista(new PrendaController(new ConexionBD())).setVisible(true);
	}
}