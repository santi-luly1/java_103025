package Exe;

public class VerificarPersona {
	public static void validar(String nombre, String apellido, int edad, String sexo, String pais)
			throws PersonaInvalidaException {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new PersonaInvalidaException("El nombre no puede estar vacío.");
		}
		if (apellido == null || apellido.trim().isEmpty()) {
			throw new PersonaInvalidaException("El apellido no puede estar vacío.");
		}
		if (edad < 1 || edad > 127) {
			throw new PersonaInvalidaException("La edad debe estar entre 1 y 127.");
		}
		if (sexo == null) {
			throw new PersonaInvalidaException("Debe seleccionar un género.");
		}
		if (pais == null) {
			throw new PersonaInvalidaException("Debe seleccionar un país.");
		}
	}
}