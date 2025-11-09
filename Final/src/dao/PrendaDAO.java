package dao;
/**
 * Esta interfaz define los métodos básicos que deben implementarse
 * para manejar las prendas dentro del sistema. 
 * Actúa como una plantilla que las clases concretas (como PrendaDAOImpl)
 * deben seguir para interactuar con la base de datos.
 *
 * @author:
 * @version: 1.0.38
 * @date: 07/11/2025
 */

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Prenda;

public interface PrendaDAO {
	// Agrega una prenda nueva a la base de datos.
	public boolean agregarPrenda(Prenda prenda) throws SQLException;

	// Devuelve una prenda específica según su ID.
	public Prenda obtenerPrenda(int id) throws SQLException;

	// Devuelve una lista con todas las prendas registradas.
	public ArrayList<Prenda> obtenerTodo() throws SQLException;

	// Modifica una prenda existente en la base de datos.
	public boolean modificarPrenda(Prenda prenda) throws SQLException;

	// Elimina una prenda según su ID.
	public boolean eliminarPrenda(int id) throws SQLException;

	// Elimina todas las prendas almacenadas.
	public void eliminarTodo() throws SQLException;
}