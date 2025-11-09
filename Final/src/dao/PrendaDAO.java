package dao;
/**
 * Esta interfaz define los métodos básicos que deben implementarse
 * para manejar las prendas dentro del sistema. 
 * Actúa como una plantilla que las clases concretas (como PrendaDAOImpl)
 * deben seguir para interactuar con la base de datos.
 *
 * @author:
 * @version: 1.0.38
 * @fecha: 07/11/2025
 */

import java.util.ArrayList;
import modelo.Prenda;

public interface PrendaDAO {
	
	// Agrega una prenda nueva a la base de datos.
	public static void agregarPrenda(Prenda prenda) {
	}

	// Devuelve una prenda específica según su ID.
	public static Prenda obtenerPrenda(int id) {
		return null;
	}

	// Devuelve una lista con todas las prendas registradas.
	public static ArrayList<Prenda> obtenerTodo() {
		return null;
	}

	// Modifica una prenda existente en la base de datos.
	public static boolean modificarPrenda(Prenda prenda) {
		return false;
	}

	// Elimina una prenda según su ID.
	public static boolean eliminarPrenda(int id) {
		return false;
	}

	// Elimina todas las prendas almacenadas.
	public static boolean eliminarTodo() {
		return false;
	}
}