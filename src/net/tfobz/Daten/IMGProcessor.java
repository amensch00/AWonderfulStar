package net.tfobz.Daten;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class IMGProcessor {
	private BufferedImage img;

	private final int WALL = new Color(255, 0, 0).getRGB();
	private final int STREET = new Color(0, 255, 0).getRGB();
	private final int START = new Color(0, 0, 255).getRGB();
	private final int ZIEL = new Color(255, 255, 0).getRGB();

	public IMGProcessor(BufferedImage img) {
		this.img = img;
	}

	public char[][] convert() {
		char[][] ret = new char[img.getHeight()][img.getWidth()];
		// TODO VERMEIDEN VON MEHREREN STARTS/ZIELEN

		// I find des super, dass x und y in der Reihenfolge
		// oanfoch es restliche Programm schwieriger mocht 
		// zu lesen und verstian *facepalm*
		/**
		 * @author Flapp
		 */
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				if (img.getRGB(x, y) == STREET)
					ret[y][x] = 'S';
				else if (img.getRGB(x, y) == START)
					ret[y][x]  = 'L';
				else if (img.getRGB(x, y) == ZIEL)
					ret[y][x]  = 'Z';
				else
					ret[y][x]  = 'W';
			}
		}

		return ret;
	}
}
