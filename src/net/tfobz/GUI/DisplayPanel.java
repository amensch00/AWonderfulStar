package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import net.tfobz.Controller.Algorithm;
import net.tfobz.Controller.Map;
import net.tfobz.Controller.Observer;
import net.tfobz.Controller.TileNode;
import net.tfobz.Controller.TileType;
import net.tfobz.Daten.IMGProcessor;
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
	
	//TODO
	public void setMap(Map map) {
		this.map = map;
	}
	
	//TODO
	public void setMapAt(int y, int x, TileType type) {
		if (y >= 0 && y < map.getMapHeight() && x >= 0 && x < map.getMapWidth()) {
			if (type == TileType.START || type == TileType.ZIEL || type == TileType.STREET || type == TileType.WALL || type == TileType.DAWE)
				map[y][x] = type;
		}
		this.repaint();
	}

	public int getLength() {
		int tileNumber = 0;
		int size = 0;
		if (map.length < map[0].length) {
			tileNumber = map[0].length;
			size = this.getWidth();
		}
			
		else {
			tileNumber = map.length;
			size = this.getHeight();
		}
		
		return size / tileNumber - 1;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

//		System.out.println("painting");
		
		if (map != null) {

			int length = getLength();

			for (int y = 0; y < map.getMapHeight(); y++) {
				for (int x = 0; x < map.getMapWidth(); x++) {
					switch (map.getTileAt(x, y).getType()) {
					case START:
						g.setColor(new Color(0, 0, 255));
						break;
					case ZIEL:
						g.setColor(new Color(255, 255, 0));
						break;
					case STREET:
						g.setColor(new Color(0, 255, 0));
						break;
					case WALL:
						g.setColor(new Color(255, 0, 0));
						break;
					case DAWE:
						g.setColor(new Color(255, 0, 255));
						break;

					default:
						break;

					}

					g.fillRect(y * length, x * length + this.getInsets().top, length, length);
				}
			}
			
			if (gridOn) {
				g.setColor(new Color(255, 255, 255));
				int height = length * map.length - 1;
				int width = length * map[0].length - 1;

				for (int y = 1; y < map.length; y++) {
					g.drawLine(0, y * length, width, y * length);
				}

				for (int x = 1; x < map[0].length; x++) {
					g.drawLine(x * length, 0, x * length, height);
				}
			}

		}
		
		if (map != null) {

			int length = getLength();

			for (int y = 0; y < map[0].length; y++) {
				for (int x = 0; x < map.length; x++) {
					switch (map[x][y]) {
					case 'L':
						g.setColor(ColorPalette.BLAU);
						break;
					case 'Z':
						g.setColor(ColorPalette.GELB);
						break;
					case 'S':
						g.setColor(ColorPalette.GRUEN);
						break;
					case 'W':
						g.setColor(ColorPalette.ROT);
						break;
					case 'P':
						g.setColor(ColorPalette.VIOLETT);
						
					default:
						break;
					}

					g.fillRect(y * length, x * length, length, length);

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
		map = IMGProcessor.mapConverter(alg.getMap());
		repaint();
	}

}
