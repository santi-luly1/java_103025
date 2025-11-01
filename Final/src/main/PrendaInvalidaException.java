package main;

public class PrendaInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	public PrendaInvalidaException(String mensaje) {
		super(mensaje);
	}
}
