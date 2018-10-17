package net.tfobz.Controller;

/**
 * Beinhaltet diverse Methode die zu diversen Zwecken verwendet werden können
 * @author Elias Thomaser
 *
 */
public class Utilities {
	/**
	 * Printed ein 2D Char Array im STDOUT aus
	 * @param field : Ein 2D Char Array
	 */
	public static void print2DCharArray(char[][] field) {
		System.out.println();
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[1].length; y++) {
				System.out.print(field[x][y]+ " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
