package net.tfobz.Daten;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class IMGProcessor {
	private BufferedImage img;

	public IMGProcessor(BufferedImage img) {
		this.img = img;
	}

	public char[][] convert() {
		char[][] ret = new char[img.getWidth()][img.getHeight()];
		//TODO VERMEIDEN VON MEHREREN STARTS/ZIELEN
		int wall = new Color(255, 0, 0).getRGB();
		int street = new Color(0, 255, 0).getRGB();

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				if (img.getRGB(y, x) == wall) {
					ret[x][y] = 'W';
				}
				if (img.getRGB(y, x) == street) {
					ret[x][y] = 'S';
				}
			}
		}
		
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				System.out.print(ret[x][y]+ " ");
			}
			System.out.println();
		}
	
		return ret;
	}
}
