package main;
/**
 * Clase principal del programa.
 * Inicia la aplicación creando el controlador y la vista (interfaz gráfica).
 * Desde aquí se ejecuta la ventana principal del sistema de prendas.
 *
 * @author:
 * @version: 1.0.38
 * @date: 07/11/2025
 */

import java.sql.SQLException;
import controlador.PrendaController;
import vista.PrendaVista;

public class Main {
	public static void main(String[] args) throws SQLException {
		// Crea la ventana principal del sistema y la hace visible.
		new PrendaVista(new PrendaController()).setVisible(true);
	}
}