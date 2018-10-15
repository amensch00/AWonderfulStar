package net.tfobz.Daten;

import java.awt.Color;
import java.awt.Image;
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
		char[][] ret = new char[img.getWidth()][img.getHeight()];
		// TODO VERMEIDEN VON MEHREREN STARTS/ZIELEN

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				if (img.getRGB(y, x) == STREET)
					ret[x][y] = 'S';
				else if (img.getRGB(y, x) == START)
					ret[x][y] = 'L';
				else if (img.getRGB(y, x) == ZIEL)
					ret[x][y] = 'Z';
				else
					ret[x][y] = 'W';
			}
		}

		return ret;
	}
}
