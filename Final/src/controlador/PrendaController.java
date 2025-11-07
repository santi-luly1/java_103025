package controlador;
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
	public PrendaController(PrendaDAOImpl pDAO) {
		this.pDAO = pDAO;
	}
	
	public ArrayList<Prenda> getPrendas() throws SQLException {
		return pDAO.obtenerTodo();
	}

	public boolean agregarPrenda(Prenda p) throws SQLException {
		return pDAO.agregarPrenda(p);
	}

	public boolean modificarPrenda(Prenda p) throws SQLException {
		return pDAO.modificarPrenda(p);
	}
	
	public boolean eliminarPrenda(int id) throws SQLException {
		return pDAO.eliminarPrenda(id);
	}

	public void eliminarTodo() throws SQLException {
		pDAO.eliminarTodo();
	}

	public Prenda obtenerId(int id) throws SQLException {
		return pDAO.obtenerPrenda(id);
	}
}