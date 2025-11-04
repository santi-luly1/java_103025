package dao;
import java.util.List;

public interface PrendaDAO {
	void agregar(Prenda prenda);
	Prenda obtenerPorId(int id);
	List<Prenda> listarTodas();
	void actualizar(Prenda prenda);
	void eliminar(int id);

}
