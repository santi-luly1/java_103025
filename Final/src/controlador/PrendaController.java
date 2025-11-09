package controlador;
/**
 * Controlador que comunica la vista (interfaz gráfica) con la capa de datos.
 * Recibe las acciones del usuario desde la vista y las traduce en llamadas
 * a los métodos del DAO que interactúan con la base de datos.
 *
 * @author:
 * @version: 1.0.38
 * @fecha: 07/11/2025
 */

import java.sql.SQLException;
import java.util.ArrayList;
import dao.PrendaDAOImpl;
import modelo.Prenda;

public class PrendaController {

	// Devuelve la lista completa de prendas.
	public ArrayList<Prenda> getPrendas() throws SQLException {
		return PrendaDAOImpl.obtenerTodo();
	}

	// Agrega una nueva prenda.
	public boolean agregarPrenda(Prenda p) throws SQLException {
		return PrendaDAOImpl.insertarPrenda(p);
	}

	// Modifica una prenda existente.
	public boolean modificarPrenda(Prenda p) throws SQLException {
		return PrendaDAOImpl.modificarPrenda(p);
	}
	
	// Elimina una prenda por su ID.
	public boolean eliminarPrenda(int id) throws SQLException {
		return PrendaDAOImpl.eliminarPrenda(id);
	}

	// Elimina todas las prendas de la base.
	public void eliminarTodo() throws SQLException {
		PrendaDAOImpl.eliminarTodo();
	}

	// Devuelve una prenda específica según su ID.
	public Prenda obtenerId(int id) throws SQLException {
		return PrendaDAOImpl.obtenerPrenda(id);
	}
}