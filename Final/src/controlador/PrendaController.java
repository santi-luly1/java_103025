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

	public ArrayList<Prenda> getPrendas() throws SQLException, ClassNotFoundException {
		return pDB.obtenerTodo();
	}

	public Prenda obtenerIndex(int index) throws SQLException, ClassNotFoundException {
		return pDB.obtenerPrenda(index + 1);
	}

	public void agregarPrenda(Prenda p) throws SQLException, ClassNotFoundException {
		pDB.insertarPrenda(p);
	}

	public void modificarPrenda(Prenda p) throws SQLException, ClassNotFoundException {
		pDB.modificarPrenda(p);
	}

	public void eliminarPrenda(int id) throws SQLException, ClassNotFoundException {
		pDB.eliminarPrenda(id);
	}

	public void eliminarTodo() throws SQLException, ClassNotFoundException {
		pDB.eliminarTodo();
	}
}