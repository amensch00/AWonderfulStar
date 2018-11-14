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

	public Map getMap() {
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
	
}
