package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import net.tfobz.Controller.Algorithm;
import net.tfobz.Controller.TileNode;

public class DisplayPanel extends JPanel {
	private char[][] map = null;
	Rectangle[][] grid;
	
	public DisplayPanel(final char[][] map) {

		setLayout(null);
		this.map = map;
		System.out.println("YO");
		if (map == null)
			return;

		
//		Algorithm alg = new Algorithm(map);
//
//		// Die solve funnkt no nt wirklich
//		TileNode t = alg.solve();
//		alg.printBacktrack(t);
//
//		char[][] test = alg.getField();
//
//		for (int x = 0; x < test.length; x++) {
//			for (int y = 0; y < test[1].length; y++) {
//				System.out.print(test[x][y] + " ");
//			}
//			System.out.println();
//		}
//
//		// I hon mi no nt gonz entschieden ob mir des
//		// spater vlleicht no brauchen
//		grid = new Rectangle[map.length][map[1].length];

		// I woas nt ob man des bracuht
		// Hon no nt ohne ausprobierrt
		repaint();
	}

	private void createGrid() {

	}

	/**
	 * Paint - Jo molt holt awian
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		System.out.println("painting");
		if (map != null)
			for (int y = 0; y < map[1].length; y++) {
				for (int x = 0; x < map.length; x++) {
					switch (map[y][x]) {
					case 'L':
						g.setColor(new Color(0, 0, 255));
						break;
					case 'Z':
						g.setColor(new Color(255, 255, 0));
						break;
					case 'S':
						g.setColor(new Color(0, 255, 0));
						break;
					case 'W':
						g.setColor(new Color(255, 0, 0));
						break;

					default:
						break;
					}
					int tileNumber = 0;
					int length = 0;
					if (map.length < map[0].length)
						tileNumber = map[0].length;

					else
						tileNumber = map.length;
					
					length = ((this.getHeight()) / tileNumber) - 1;
					
					g.fillRect(x * length, y * length, length, length);
					
					// if (this.getWidth() > this.getHeight())
					// g.fillRect(x * this.getWidth() / tileNumber, y * this.getHeight() /
					// tileNumber,
					// this.getWidth() / tileNumber, this.getHeight() / tileNumber);
					// else
					// g.fillRect(x * this.getWidth() / tileNumber, y * this.getHeight() /
					// tileNumber,
					// this.getWidth() / tileNumber, this.getHeight() / tileNumber);
				}
			}
	}

	public void setMap(final char[][] map) {
		this.map = map;
	}
}
