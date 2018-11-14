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
		JOptionPane.showConfirmDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Erm�glicht es dem Benutzer einen Hinweis zu zeigen
	 */
	public static void showWarning(String msg) {
		JOptionPane.showConfirmDialog(null, msg, "Hinweis", JOptionPane.INFORMATION_MESSAGE);
	}
}
