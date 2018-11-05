package net.tfobz.Daten;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import net.tfobz.Controller.Map;
import net.tfobz.Controller.TileType;

public class IMGProcessor {
	private BufferedImage img;

	private static final int RWALL = new Color(255, 0, 0).getRGB();
	private static final int RSTREET = new Color(0, 255, 0).getRGB();
	private static final int RSTART = new Color(0, 0, 255).getRGB();
	private static final int RZIEL = new Color(255, 255, 0).getRGB();

	public IMGProcessor(BufferedImage img) {
		this.img = img;
	}

	public Map convertToMap() {
		Map ret = new Map(img.getWidth(), img.getHeight());

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				if (img.getRGB(x, y) == RSTREET)
					ret.setTileAt(x, y, TileType.STREET);
				else if (img.getRGB(x, y) == RSTART) {
					ret.setTileAt(x, y, TileType.START);
					ret.setStart(new Point(x, y));
				} else if (img.getRGB(x, y) == RZIEL) {
					ret.setTileAt(x, y, TileType.ZIEL);
					ret.setZiel(new Point(x, y));
				} else
					ret.setTileAt(x, y, TileType.WALL);
			}
		}
		return ret;
	}

	public static Map arrayConverter(char arr[][]) {
		Map ret = new Map(arr.length, arr[0].length);
		
		for (int y = 0; y < arr[0].length; y++) {
			for (int x = 0; x < arr.length; x++) {
				if (arr[x][y] == 'S')
					ret.setTileAt(x, y, TileType.STREET);
				else if (arr[x][y] == 'L') {
					ret.setTileAt(x, y, TileType.START);
					ret.setStart(new Point(x, y));
				} else if (arr[x][y] == 'Z') {
					ret.setTileAt(x, y, TileType.ZIEL);
					ret.setZiel(new Point(x, y));
				} else
					ret.setTileAt(x, y, TileType.WALL);
			}
		}
		return ret;

	}
	
//	public static arr[][] mapConverter(Map map) {
//		char[][] ret = new char[][];
//		
//		for (int y = 0; y < arr[0].length; y++) {
//			for (int x = 0; x < arr.length; x++) {
//				if (arr[x][y] == 'S')
//					ret.setTileAt(x, y, TileType.STREET);
//				else if (arr[x][y] == 'L') {
//					ret.setTileAt(x, y, TileType.START);
//					ret.setStart(new Point(x, y));
//				} else if (arr[x][y] == 'Z') {
//					ret.setTileAt(x, y, TileType.ZIEL);
//					ret.setZiel(new Point(x, y));
//				} else
//					ret.setTileAt(x, y, TileType.WALL);
//			}
//		}
//		return ret;
//
//	}

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
				if (img.getRGB(x, y) == RSTREET)
					ret[y][x] = 'S';
				else if (img.getRGB(x, y) == RSTART)
					ret[y][x] = 'L';
				else if (img.getRGB(x, y) == RZIEL)
					ret[y][x] = 'Z';
				else
					ret[y][x] = 'W';
				// >>>>>>> get-aStar-alg-working
			}
		}

		return ret;
	}
}
