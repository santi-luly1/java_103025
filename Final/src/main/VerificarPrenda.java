package main;
/**
 * Esta clase se encarga de validar los datos ingresados por el usuario
 * antes de crear o modificar una prenda. 
 * Si alguno de los valores no cumple las condiciones, lanza una excepción.
 *
 * @author:
 * @version: 1.0.38
 * @fecha: 07/11/2025
 */

public class VerificarPrenda {

	/**
	 * Valida los datos de la prenda ingresada.
	 * Verifica que los campos de texto no estén vacíos y que precio y stock sean válidos.
	 *
	 * @throws PrendaInvalidaException si algún valor no cumple las condiciones requeridas.
	 */
	public static void validar(String descripcion, String talle, String color, double precio, int stock)
			throws PrendaInvalidaException {

		// Comprueba que la descripción no esté vacía.
		if (descripcion == null || descripcion.trim().isEmpty()) {
			throw new PrendaInvalidaException("La descripción no puede estar vacía.");
		}

		// Verifica que el talle esté completo.
		if (talle == null || talle.trim().isEmpty()) {
			throw new PrendaInvalidaException("El talle no puede estar vacío.");
		}

		// Verifica que el color no esté vacío.
		if (color == null || color.trim().isEmpty()) {
			throw new PrendaInvalidaException("El color no puede estar vacío.");
		}

		// Verifica que el precio no sea negativo.
		if (precio < 0) {
			throw new PrendaInvalidaException("El precio debe ser mínimo 0.");
		}

		// Verifica que el stock no sea negativo.
		if (stock < 0) {
			throw new PrendaInvalidaException("El stock debe ser mínimo 0.");
		}
	}
}