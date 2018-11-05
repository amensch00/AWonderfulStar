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