package modelo;
/**
 * Representa una prenda dentro del sistema.
 * Contiene los atributos principales como descripción, talle, color, precio y stock.
 * Además incluye métodos para acceder y modificar sus valores.
 *
 * @author:
 * @version: 1.0.38
 * @date: 07/11/2025
 */

public class Prenda {
	private String descripcion, talle, color;
	private int id, stock;
	private double precio;

 // Constructor: crea una nueva prenda con sus datos.
	public Prenda(int id, String descripcion, String talle, String color, double precio, int stock) {
		this.id = id;
		this.descripcion = descripcion;
		this.talle = talle;
		this.color = color;
		this.precio = precio;
		this.stock = stock;
	}

	// Devuelve el ID de la prenda.
	public int getId() {
		return id;
	}

	// Devuelve el índice equivalente (id - 1) porque SQL comienza en 1 y Java en 0.
	public int getIndex() {
		return id - 1;
	}

	// Modifica el ID de la prenda.
	public void setId(int id) {
		this.id = id;
	}

	// Devuelve la descripción.
	public String getDescripcion() {
		return descripcion;
	}

	// Modifica la descripción.
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// Devuelve el talle.
	public String getTalle() {
		return talle;
	}

	// Modifica el talle.
	public void setTalle(String talle) {
		this.talle = talle;
	}

	// Devuelve el color.
	public String getColor() {
		return color;
	}

	// Modifica el color.
	public void setColor(String color) {
		this.color = color;
	}

	// Devuelve el precio.
	public double getPrecio() {
		return precio;
	}

	// Modifica el precio.
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	// Devuelve el stock actual.
	public int getStock() {
		return stock;
	}

	// Modifica el stock.
	public void setStock(int stock) {
		this.stock = stock;
	}
}