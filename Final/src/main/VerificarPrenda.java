package main;

public class VerificarPrenda {
	public static void validar(String nombre, String apellido, int edad, String sexo, String pais) // FIXME
			throws PrendaInvalidaException {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new PrendaInvalidaException("El nombre no puede estar vacío.");
		}
		if (apellido == null || apellido.trim().isEmpty()) {
			throw new PrendaInvalidaException("El apellido no puede estar vacío.");
		}
		if (edad < 1 || edad > 127) {
			throw new PrendaInvalidaException("La edad debe estar entre 1 y 127.");
		}
		if (sexo == null) {
			throw new PrendaInvalidaException("Debe seleccionar un género.");
		}
		if (pais == null) {
			throw new PrendaInvalidaException("Debe seleccionar un país.");
		}
	}
}