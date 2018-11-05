package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

import net.tfobz.Controller.Algorithm;
import net.tfobz.Controller.Map;
import net.tfobz.Controller.Observer;
import net.tfobz.Controller.TileType;

public class DisplayWindow extends JFrame implements Observer {
	private Map map;
	private Algorithm alg;
	private boolean isStepByStep;

	public DisplayWindow(Map map, boolean isStepByStep) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setLayout(null);
		setResizable(false);
		setVisible(true);

		if (map == null)
			throw new NullPointerException("map darf nicht null sein::: DisplayWindow");

		this.map = map;
		this.isStepByStep = isStepByStep;

		Map m = new Map(4, 4);

		/*
		 * B S S S S W W S S S W S S S W Z
		 * 
		 * B = Start S = Street W = Wall Z = Ziel
		 */

		m.setTileAt(0, 0, TileType.START);
		m.setTileAt(1, 0, TileType.STREET);
		m.setTileAt(2, 0, TileType.STREET);
		m.setTileAt(3, 0, TileType.STREET);

		m.setTileAt(0, 1, TileType.STREET);
		m.setTileAt(1, 1, TileType.WALL);
		m.setTileAt(2, 1, TileType.WALL);
		m.setTileAt(3, 1, TileType.STREET);

		m.setTileAt(0, 2, TileType.STREET);
		m.setTileAt(1, 2, TileType.STREET);
		m.setTileAt(2, 2, TileType.WALL);
		m.setTileAt(3, 2, TileType.STREET);

		m.setTileAt(0, 3, TileType.STREET);
		m.setTileAt(1, 3, TileType.STREET);
		m.setTileAt(2, 3, TileType.WALL);
		m.setTileAt(3, 3, TileType.ZIEL);

		m.setStart(new Point(0, 0));
		m.setZiel(new Point(3, 3));

		alg = new Algorithm(map, isStepByStep);
		alg.attach(this);

		Thread algThread = new Thread(alg);

		algThread.start();
	}

	
	public int getLength() {
		int tileNumber = 0;
		if (this.map.getMapHeight() < this.map.getMapWidth())
			tileNumber = this.map.getMapWidth();
		else
			tileNumber = this.map.getMapHeight();

		return this.getWidth() / tileNumber;
	}
	
	public void paint(Graphics g) {
		super.paint(g);

		// System.out.println("painting");
		super.paint(g);

		// System.out.println("painting");
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

		}

		// for (int y = 0; y < map.getMapHeight(); y++) {
		// for (int x = 0; x < map.getMapWidth(); x++) {
		// switch (map.getTileAt(x, y).getType()) {
		// case START:
		// g.setColor(new Color(0, 0, 255));
		// break;
		// case ZIEL:
		// g.setColor(new Color(255, 255, 0));
		// break;
		// case STREET:
		// g.setColor(new Color(0, 255, 0));
		// break;
		// case WALL:
		// g.setColor(new Color(255, 0, 0));
		// break;
		// case DAWE:
		// g.setColor(new Color(255, 0, 255));
		// break;
		//
		// default:
		// break;
		// }
		//
		// int tileNumber = 0;
		// if (map.getMapWidth() > map.getMapHeight())
		// tileNumber = map.getMapHeight();
		// else
		// tileNumber = map.getMapWidth();
		//
		// if (this.getContentPane().getWidth() > this.getContentPane().getHeight())
		// g.fillRect(x * this.getContentPane().getWidth() / tileNumber +
		// getInsets().left,
		// y * this.getContentPane().getHeight() / tileNumber + getInsets().top,
		// this.getWidth() / tileNumber, this.getHeight() / tileNumber);
		// else
		// g.fillRect(x * this.getContentPane().getWidth() / tileNumber +
		// getInsets().left,
		// y * this.getContentPane().getHeight() / tileNumber + getInsets().top,
		// this.getWidth() / tileNumber, this.getHeight() / tileNumber);
		// }
		// }
	}

	@Override
	public void update() {
		map = alg.getMap();
		repaint();
	}
}