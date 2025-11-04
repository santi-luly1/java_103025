package dao;
import java.util.List;

import modelo.Prenda;

public interface PrendaDAO {
	void agregar(Prenda prenda);
	Prenda obtenerPorId(int id);
	List<Prenda> listarTodas();
	void actualizar(Prenda prenda);
	void eliminar(int id);

}
