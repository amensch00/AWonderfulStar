package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;

import net.tfobz.Controller.Algorithm;

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
		char[][] test = alg.solve();
		
		for (int x = 0; x < test.length; x++) {
			for (int y = 0; y < test[1].length; y++) {
				System.out.print(test[x][y]+ " ");
			}
			System.out.println();
		}
		
		grid = new Rectangle[map.length][map[1].length];


		repaint();
	}
	
	private void createGrid() {
		
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		System.out.println("paining");
		
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
				
				g.fillRect(x * 150, y * 150, 150, 150);
				System.out.println(map[y][x]);
			}
		}
	}
}