package controlador;
import java.sql.SQLException;

import java.util.ArrayList;
import dao.PrendaDAOImpl;
import modelo.Prenda;

public class PrendaController {
	public ArrayList<Prenda> getPrendas() throws SQLException {
		return PrendaDAOImpl.obtenerTodo();
	}

	public boolean agregarPrenda(Prenda p) throws SQLException {
		return PrendaDAOImpl.insertarPrenda(p);
	}

	public boolean modificarPrenda(Prenda p) throws SQLException {
		return PrendaDAOImpl.modificarPrenda(p);
	}
	
	public boolean eliminarPrenda(int id) throws SQLException {
		return PrendaDAOImpl.eliminarPrenda(id);
	}

	public void eliminarTodo() throws SQLException {
		PrendaDAOImpl.eliminarTodo();
	}

	public Prenda obtenerId(int id) throws SQLException {
		return PrendaDAOImpl.obtenerPrenda(id);
	}
}