package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;

import net.tfobz.Controller.Algorithm;
import net.tfobz.Controller.TileNode;

public class DisplayWindow extends JFrame {
	private char[][] map;
	Rectangle[][] grid;
	
	public DisplayWindow (final char[][] map) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		
		if (map == null)
			return;

		this.map = map;
		
		Algorithm alg = new Algorithm(map);
		
		// Die solve funnkt no nt wirklich
		TileNode t = alg.solve();
		alg.printBacktrack(t);
		
		char[][] test = alg.getField();
		
		for (int x = 0; x < test.length; x++) {
			for (int y = 0; y < test[1].length; y++) {
				System.out.print(test[x][y]+ " ");
			}
			System.out.println();
		}
		
		// I hon mi no nt gonz entschieden ob mir des
		// spater vlleicht no brauchen
		grid = new Rectangle[map.length][map[1].length];


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
				if (map.length > map[0].length) 
					tileNumber = map[0].length;
				else
					tileNumber = map.length;
				
				if (this.getContentPane().getWidth() > this.getContentPane().getHeight())
					g.fillRect(x * this.getContentPane().getWidth() / tileNumber + getInsets().left,
							   y * this.getContentPane().getHeight() / tileNumber + getInsets().top, 
							   this.getWidth() / tileNumber, 
							   this.getHeight() / tileNumber);
				else 
					g.fillRect(x * this.getContentPane().getWidth() / tileNumber + getInsets().left, 
							   y * this.getContentPane().getHeight() / tileNumber + getInsets().top, 
							   this.getWidth() / tileNumber, 
							   this.getHeight() / tileNumber);
			}
		}
	}
}