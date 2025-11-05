package dao;
import java.util.ArrayList;

import modelo.Prenda;

public interface PrendaDAO {
	public static void agregarPrenda(Prenda prenda) {
	}
	public static Prenda obtenerPrenda(int id) {
		return null;
	}
	public static ArrayList<Prenda> obtenerTodo() {
		return null;
	}
	public static boolean modificarPrenda(Prenda prenda) {
		return false;
	}
	public static boolean eliminarPrenda(int id) {
		return false;
	}
	public static boolean eliminarTodo() {
		return false;
	}
}
