package main;

public class VerificarPrenda {
	public static void validar(String descripcion, String talle, String color, double precio, int stock) throws PrendaInvalidaException {
		if (descripcion == null || descripcion.trim().isEmpty()) { // descripcio'on es null o est'a vac'ia
			throw new PrendaInvalidaException("La descripción no puede estar vacía.");
		}
		if (talle == null || talle.trim().isEmpty()) {
			throw new PrendaInvalidaException("El talle no puede estar vacío.");
		}
		if (color == null || color.trim().isEmpty()) {
			throw new PrendaInvalidaException("El color no puede estar vacío.");
		}
		if (precio < 0) {
			throw new PrendaInvalidaException("El precio debe ser mínimo 0.");
		}
		if (stock < 0) {
			throw new PrendaInvalidaException("El stock debe ser mínimo 0.");
		}
	}
}