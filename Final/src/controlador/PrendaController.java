package controlador;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Prenda;

public class PrendaController {
	private ConexionBD pDB;

	public PrendaController(ConexionBD pDB) throws SQLException { this.pDB = pDB; }
	
	public ArrayList<Prenda> getPrendas() throws SQLException	{ return pDB.obtenerTodo(); }
	public Prenda obtenerIndex(int index) throws SQLException	{ return pDB.obtenerPrenda(index); }
	
	public void agregarPrenda(Prenda Prenda) throws SQLException { pDB.insertarPrenda(Prenda); }
	
	public void modificarPrenda(Prenda Prenda) throws SQLException { pDB.modificarPrenda(Prenda); }
	
	public void eliminarPrenda(Prenda p) throws SQLException	{ pDB.eliminarPrenda(p); }
	public void eliminarTodo() throws SQLException				{ pDB.eliminarTodo(); }
}