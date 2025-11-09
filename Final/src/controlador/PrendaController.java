package controlador;
/**
 * Controlador que comunica la vista (interfaz gráfica) con la capa de datos.
 * Recibe las acciones del usuario desde la vista y las traduce en llamadas
 * a los métodos del DAO que interactúan con la base de datos.
 *
 * @author:
 * @version: 1.0.38
 * @date: 07/11/2025
 */

import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrendaDAO;
import dao.PrendaDAOImpl;
import modelo.Prenda;

public class PrendaController {
	private PrendaDAO pDAO;
	public PrendaController() {
		this.pDAO = new PrendaDAOImpl();
	}
	public PrendaController(PrendaDAO pDAO) {
		this.pDAO = pDAO;
	}

	// Devuelve la lista completa de prendas.
	public ArrayList<Prenda> getPrendas() throws SQLException {
		return pDAO.obtenerTodo();
	}

	// Agrega una nueva prenda.
	public boolean agregarPrenda(Prenda p) throws SQLException {
		return pDAO.agregarPrenda(p);
	}

	// Modifica una prenda existente.
	public boolean modificarPrenda(Prenda p) throws SQLException {
		return pDAO.modificarPrenda(p);
	}
	
	// Elimina una prenda por su ID.
	public boolean eliminarPrenda(int id) throws SQLException {
		return pDAO.eliminarPrenda(id);
	}

	// Elimina todas las prendas de la base.
	public void eliminarTodo() throws SQLException {
		pDAO.eliminarTodo();
	}

	// Devuelve una prenda específica según su ID.
	public Prenda obtenerId(int id) throws SQLException {
		return pDAO.obtenerPrenda(id);
	}
}