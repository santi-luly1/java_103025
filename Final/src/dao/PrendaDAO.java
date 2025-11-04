package dao;
import java.util.ArrayList;

import modelo.Prenda;

public interface PrendaDAO {
	int id = 0;
	String descripcion = "";
	String talla = "";
	String color = "";
	double precio = 0;
	int stock = 0;
	
	void agregar(Prenda prenda);
	Prenda obtenerPorId(int id);
	ArrayList<Prenda> listarTodas();
	void actualizar(Prenda prenda);
	void eliminar(int id);

}
