package net.tfobz.Utilities;

/**
 * Wird geworfen wenn sich im eingelesenen Bild eine nicht verwendbare Farbe befindet.
 * @author Thomaser, Tschager
 *
 */
public class IllegalColorException extends RuntimeException {
	public IllegalColorException() {
		super();
	}
	
	public IllegalColorException(String msg) {
		super(msg);
	}
}
