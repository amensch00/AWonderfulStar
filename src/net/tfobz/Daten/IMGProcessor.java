package net.tfobz.Daten;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import net.tfobz.Controller.Map;
import net.tfobz.Controller.TileNode;
import net.tfobz.Controller.TileType;
import net.tfobz.Utilities.ColorPalette;

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
		Map ret = new Map(img.getHeight(),img.getWidth());

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				if (img.getRGB(x, y) == RSTREET)
					ret.setTileAt(y, x, TileType.STREET);
				else if (img.getRGB(x, y) == RSTART) {
					ret.setTileAt(y, x, TileType.START);
					ret.setStart(new Point(y, x));
				} else if (img.getRGB(x, y) == RZIEL) {
					ret.setTileAt(y, x, TileType.ZIEL);
					ret.setZiel(new Point(y, x));
				} else
					ret.setTileAt(y, x, TileType.WALL);
			}
		}
		return ret;
	}

	public static Map arrayConverter(char arr[][]) {
		Map ret = new Map(arr.length, arr[0].length);

		for (int y = 0; y < arr[0].length; y++) {
			for (int x = 0; x < arr.length; x++) {
				if (arr[x][y] == 'L') {
					ret.setTileAt(x, y, TileType.START);
					ret.setStart(new Point(x, y));
				} else if (arr[x][y] == 'Z') {
					ret.setTileAt(x, y, TileType.ZIEL);
					ret.setZiel(new Point(x, y));
				} else if (arr[x][y] == 'W') {
					ret.setTileAt(x, y, TileType.WALL);
				} else 
					ret.setTileAt(x, y, TileType.STREET);
					
			}
		}
		return ret;

	}

	public static char[][] mapConverter(Map map) {
		char[][] ret = new char[map.getMapWidth()][map.getMapHeight()];

		for (TileNode tn : map.getAllTNs())

			if (tn.getType() == TileType.STREET)
				ret[tn.getX()][tn.getY()] = 'S';
			else if (tn.getType() == TileType.START)
				ret[tn.getX()][tn.getY()] = 'L';
			else if (tn.getType() == TileType.ZIEL)
				ret[tn.getX()][tn.getY()] = 'Z';
			else if (tn.getType() == TileType.WALL)
				ret[tn.getX()][tn.getY()] = 'W';
			else 
				ret[tn.getX()][tn.getY()] = 'P';

		return ret;

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
				if (img.getRGB(x, y) == RSTREET)
					ret[y][x] = 'S';
				else if (img.getRGB(x, y) == RSTART)
					ret[y][x] = 'L';
				else if (img.getRGB(x, y) == RZIEL)
					ret[y][x] = 'Z';
				else if (img.getRGB(x, y) == RWALL)
					ret[y][x] = 'W';
				else 
					ret[y][x] = 'P';
				// >>>>>>> get-aStar-alg-working
			}
		}

		return ret;
	}
}
