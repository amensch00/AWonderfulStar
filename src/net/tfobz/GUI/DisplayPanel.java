package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import net.tfobz.Controller.Algorithm;
import net.tfobz.Controller.Map;
import net.tfobz.Controller.Observer;
import net.tfobz.Controller.TileNode;
import net.tfobz.Daten.IMGProcessor;

public class DisplayPanel extends JPanel implements Observer {
	private char[][] map = null;
	boolean gridOn = false;
	Rectangle[][] grid;
	Algorithm alg;

	public DisplayPanel(final char[][] map, boolean gridOn) {
		setLayout(null);
		this.map = map;
		this.gridOn = gridOn;
		repaint();
		if (map == null)
			return;

	}

	public int getDisplayedWidth() {
		
		return getLength() * map[0].length;
		
		
	}

	public int getDisplayedHeight() {
	
		return getLength() * map.length;
	}

	public void setGridOn(boolean gridOn) {
		this.gridOn = gridOn;
	}

	public char[][] getMap() {
		return this.map;
	}
	//TODO
	public void setMap(final char[][] map) {
		this.map = map;
	}
	//TODO
	public void setMapAt(int row, int col, char c) {
		if (row >= 0 && row < map.length && col >= 0 && col < map[0].length) {
			if (c == 'L' || c == 'Z' || c == 'S' || c == 'W' || c == 'P')
				map[row][col] = c;
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

			for (int y = 0; y < map[0].length; y++) {
				for (int x = 0; x < map.length; x++) {
					switch (map[x][y]) {
					case 'L':
						g.setColor(new Color(52,152,219));
						break;
					case 'Z':
						g.setColor(new Color(241,196,15));
						break;
					case 'S':
						g.setColor(new Color(46,204,113));
						break;
					case 'W':
						g.setColor(new Color(231,76,60));
						break;
					case 'P':
						g.setColor(new Color(142,68,173));
						
					default:
						break;
					}

					g.fillRect(y * length, x * length, length, length);

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
