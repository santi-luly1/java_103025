package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Prenda;

public interface PrendaDAO {
	public boolean agregarPrenda(Prenda prenda) throws SQLException;
	public Prenda obtenerPrenda(int id) throws SQLException;
	public ArrayList<Prenda> obtenerTodo() throws SQLException;
	public boolean modificarPrenda(Prenda prenda) throws SQLException;
	public boolean eliminarPrenda(int id) throws SQLException;
	public void eliminarTodo() throws SQLException;
}