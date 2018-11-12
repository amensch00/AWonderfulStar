package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import net.tfobz.Controller.Algorithm;
import net.tfobz.Controller.Map;
import net.tfobz.Controller.Observer;
import net.tfobz.Controller.TileType;
import net.tfobz.Utilities.ColorPalette;

public class DisplayPanel extends JPanel implements Observer {
	private Map map = null;
	boolean gridOn = false;
	Rectangle[][] grid;
	Algorithm alg;

	public DisplayPanel(final Map map, boolean gridOn) {
		setLayout(null);
		this.map = map;
		this.gridOn = gridOn;

		repaint();
	}

	public int getDisplayedWidth() {
		return getLength() * map.getMapWidth();
	}

	public int getDisplayedHeight() {
		return getLength() * map.getMapHeight();
	}

	public void setGridOn(boolean gridOn) {
		this.gridOn = gridOn;
	}

	public Map getMap() {
		return this.map;
	}

	// TODO
	public void setMap(Map map) {
		this.map = map;
	}

	// TODO
	public void setMapAt(int y, int x, TileType type) {
		if (y >= 0 && y < map.getMapHeight() && x >= 0 && x < map.getMapWidth()) {
			if (type == TileType.START || type == TileType.ZIEL || type == TileType.STREET || type == TileType.WALL)
				map.setTileAt(x, y, type);
		}
		this.repaint();
	}

	public int getLength() {
		int tileNumber = 0;
		if (map.getMapHeight() < map.getMapWidth())
			tileNumber = map.getMapWidth();
		else
			tileNumber = map.getMapHeight();
		return (int) Math.floor(this.getHeight() / tileNumber);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// System.out.println("painting");

		if (map != null) {

			int length = getLength();

			for (int y = 0; y < map.getMapHeight(); y++) {
				for (int x = 0; x < map.getMapWidth(); x++) {
					switch (map.getTileAt(x, y).getType()) {
					case START:
						g.setColor(ColorPalette.BLAU);
						break;
					case ZIEL:
						g.setColor(ColorPalette.GELB);
						break;
					case STREET:
						g.setColor(ColorPalette.GRUEN);
						break;
					case WALL:
						g.setColor(ColorPalette.ROT);
						break;
					default:
						break;
					}
					g.fillRect(y * length, x * length + this.getInsets().top, length, length);

					// TODO x y schun widr verkehrt

					switch (map.getTileAt(x, y).getOverlay()) {
					case NOTHING:
						break;
					case INOPEN:
						g.setColor(ColorPalette.INOPEN);
						break;
					case INCLOSED:
						g.setColor(ColorPalette.INCLOSED);
						break;
					case DAWE:
						g.setColor(ColorPalette.DAWE);
						break;
					default:
						break;
					}
					g.fillRect(y * length + length / 4, x * length + this.getInsets().top + length / 4, length / 2,
							length / 2);
				}
			}

			if (gridOn) {
				g.setColor(new Color(255, 255, 255));
				int height = length * map.getMapWidth() - 1;
				int width = length * map.getMapHeight() - 1;

				for (int x = 1; x < map.getMapWidth(); x++) {
					g.drawLine(0, x * length, width, x * length);
				}

				for (int y = 1; y < map.getMapHeight(); y++) {
					g.drawLine(y * length, 0, y * length, height);
				}
			}

		}

	}

	public void startAlg(Map map, boolean isStepByStep) {
		alg = new Algorithm(map, isStepByStep);
		alg.attach(this);

		Thread algThread = new Thread(alg);

		algThread.start();
	}

	@Override
	public void update() {
		map = alg.getMap();
		repaint();
	}

}
