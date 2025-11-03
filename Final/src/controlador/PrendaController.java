package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Prenda;

public class PrendaController {
	private ConexionBD pDB;

	public PrendaController(ConexionBD pDB) throws SQLException {
		this.pDB = pDB;
	}

	public ArrayList<Prenda> getPrendas() throws SQLException {
		return pDB.obtenerTodo();
	}

	public Prenda obtenerIndex(int index) throws SQLException {
		return pDB.obtenerPrenda(index);
	}

	public void agregarPrenda(Prenda p) throws SQLException {
		pDB.insertarPrenda(p);
	}

	public void modificarPrenda(Prenda p) throws SQLException {
		pDB.modificarPrenda(p);
	}

	public void eliminarPrenda(int id) throws SQLException {
		pDB.eliminarPrenda(id);
	}

	public void eliminarTodo() throws SQLException {
		pDB.eliminarTodo();
	}
}