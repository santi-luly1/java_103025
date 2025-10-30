package Exe;

public class PersonaInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersonaInvalidaException(String mensaje) {
		super(mensaje);
	}
}
