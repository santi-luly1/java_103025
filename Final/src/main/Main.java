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