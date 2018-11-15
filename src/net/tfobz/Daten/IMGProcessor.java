package net.tfobz.Daten;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.tfobz.BackEnd.Map;
import net.tfobz.BackEnd.TileNode;
import net.tfobz.BackEnd.TileType;
import net.tfobz.Utilities.ErrorHandling;
import net.tfobz.Utilities.IllegalColorException;

public class IMGProcessor {
	private BufferedImage img;

	// Farben Konstanten für das Einlesen von PNG Dateien die als Map benutzt werden können
	private static final int RWALL = new Color(255, 0, 0).getRGB();
	private static final int RSTREET = new Color(0, 255, 0).getRGB();
	private static final int RSTART = new Color(0, 0, 255).getRGB();
	private static final int RZIEL = new Color(255, 255, 0).getRGB();

	/**
	 * Wirft eine <code>IllegalArgumentException</code> wenn das Image <code>null</code> ist.
	 * @param BufferedImage img
	 */
	public IMGProcessor(BufferedImage img) {
		if (img == null)
			throw new IllegalArgumentException();
		
		this.img = img;
	}

	public BufferedImage getImg() {
		return img;
	}
	
	/**
	 * Gibt das im Konstruktor übergebene Bild als <code>Map</code> Objekt zurück
	 * und setzt den Start und Ziel punkt in der Map
	 * @return Bild als Map objekt
	 */
	public Map getMapFromImage() {
		if (!isImageValid(img))
			ErrorHandling.showWarning("Bild ist nicht Valide, ausführen wird nicht möglich sein!");
		
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
	
	/**
	 * Überprüft ob das übergebene Image überhapt verwendbar ist den Weg aufzulösen
	 * @param <code>BufferedImage</code> image
	 * @return <code>true</code> wenn das BufferedImage verwendbar ist, sonst <code>false</code>
	 */
	public static boolean isImageValid(BufferedImage image) {
		int startCounter = 0, zielCounter = 0;
		
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (image.getRGB(x, y) != RSTREET && image.getRGB(x, y) != RSTART && image.getRGB(x, y) != RWALL && image.getRGB(x, y) != RZIEL)
					throw new IllegalColorException("Bild enthält nicht verwendbare farben");
				
				if (image.getRGB(x, y) == RSTART)
					startCounter++;
				else if (image.getRGB(x, y) == RZIEL)
					zielCounter++;
				
				if (startCounter > 1 || zielCounter > 1)
					return false;
			}
		}
		if (startCounter != 1 || zielCounter != 1)
			return false;
		
		return true;
	}
	
	/**
	 * Überprüft ob die übergebene Map überhapt verwendbar ist den Weg aufzulösen
	 * @param <code>Map</code> map
	 * @return <code>true</code> wenn die map verwendbar ist, sonst <code>false</code>
	 */
	public static boolean isMapValid(Map map) {
		int startCounter = 0, zielCounter = 0;
		
		for (TileNode tn : map.getAllTNs()) {
				if (tn.getType() == TileType.START)
					startCounter++;
				else if (tn.getType() == TileType.ZIEL)
					zielCounter++;
				
				if (startCounter > 1 || zielCounter > 1)
					return false;
		}
		if (startCounter != 1 || zielCounter != 1)
			return false;
		
		return true;
	}
	
	/**
	 * Speichert eine Map in eine Datei ab,
	 * überprüft davor aber ob die Map valide ist
	 * @param map die gepeichert werden soll
	 * @param outFilePath der Dateipfad
	 */
	public void saveMapToImage(Map map, String outFilePath) {
		if (!isMapValid(map))
			throw new IllegalArgumentException("Diese Map ist nicht Valide! Zuviele Start/Ziele ?");
		
		BufferedImage out = new BufferedImage(map.getMapHeight(), map.getMapWidth(), img.getType());
		
		for (TileNode tn : map.getAllTNs()) {
			if (tn.getType() == TileType.START)
				out.setRGB(tn.getY(), tn.getX(), RSTART);
			else if (tn.getType() == TileType.ZIEL)
				out.setRGB(tn.getY(), tn.getX(), RZIEL);
			else if (tn.getType() == TileType.STREET)
				out.setRGB(tn.getY(), tn.getX(), RSTREET);
			else
				out.setRGB(tn.getY(), tn.getX(), RWALL);
		}
		
		try {
			ImageIO.write(out, "png", new File(outFilePath));
		} catch (IOException e) {
			ErrorHandling.showErrorMessage(e);
		}
	}
	
	
}
