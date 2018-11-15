package net.tfobz.Utilities;

import javax.swing.JOptionPane;

/**
 * Erleichtert das behandeln von Expetions und dem damit folgenden mitteilen an den Benutzer
 * @author Thomaser, Tschager
 *
 */
public class ErrorHandling {
	/**
	 * Zeigt einen Error Dialog mit der nachricht der Exception
	 */
	public static void showErrorMessage(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Ermöglicht es dem Benutzer einen Hinweis zu zeigen
	 */
	public static void showWarning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Fehler", JOptionPane.ERROR_MESSAGE);
	}
}
