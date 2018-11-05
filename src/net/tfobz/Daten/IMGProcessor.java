package net.tfobz.Daten;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import net.tfobz.Controller.Map;
import net.tfobz.Controller.TileType;

public class IMGProcessor {
	private BufferedImage img;

	private final int RWALL = new Color(255, 0, 0).getRGB();
	private final int RSTREET = new Color(0, 255, 0).getRGB();
	private final int RSTART = new Color(0, 0, 255).getRGB();
	private final int RZIEL = new Color(255, 255, 0).getRGB();

	public IMGProcessor(BufferedImage img) {
		this.img = img;
	}

	public Map convert() {
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
}
