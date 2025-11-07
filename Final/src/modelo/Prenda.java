package modelo;

public class Prenda {
	private String descripcion, talle, color;
	private int id, stock;
	private double precio;

	public Prenda(int id, String descripcion, String talle, String color, double precio, int stock) {
		this.id = id;
		this.descripcion = descripcion;
		this.talle = talle;
		this.color = color;
		this.precio = precio;
		this.stock = stock;
	}
	public Prenda() {
		this.id = -1; // id por defecto.
		this.descripcion = "";
		this.talle = "";
		this.color = "";
		this.precio = 0;
		this.stock = 0;
	}

	public int getId() {
		return id;
	}

	public int getIndex() {
		return id - 1;
	} // sql comienza por 1, Java por 0.

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return "Identificador: " + id + "\nDescripcion: " + descripcion + "\nTalle: " + talle + "\nColor: " + color + "\nPrecio: " + precio + "\nStock: " + stock;
	}
}