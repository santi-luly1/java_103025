package main;
/**
 * Clase que define una excepción personalizada para prendas inválidas.
 * Se utiliza cuando alguno de los datos ingresados no cumple las validaciones.
 *
 * @author:
 * @version: 1.0.38
 * @date: 07/11/2025
 */

public class PrendaInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	// Constructor que recibe el mensaje de error y lo envía a la superclase Exception.
	public PrendaInvalidaException(String mensaje) {
		super(mensaje);
	}
}