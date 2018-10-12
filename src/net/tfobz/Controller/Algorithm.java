package net.tfobz.Controller;

import java.awt.Point;
import java.util.ArrayList;

public class Algorithm {

	private char[][] field = null;
	private ArrayList<TileNode> openlist = null;

	public Algorithm(char[][] field) {
		this.field = field;
		openlist = new ArrayList<TileNode>();
	}

	public char[][] solve() {
		char[][] ret = null;
		TileNode start = null;
		Point ziel = new Point(0, 0);
		// Ermittle Ziel Koordinaten
		for (int x = 0; x < field.length; x++)
			for (int y = 0; y < field[0].length; y++)
				if (field[x][y] == 'Z')
					ziel = new Point(x, y);
		// Ermittle Startknoten
		for (int x = 0; x < field.length; x++)
			for (int y = 0; y < field[0].length; y++)
				if (field[x][y] == 'S')
					start = new TileNode(x, y, null, ziel);
		// F�ge Startknoten zur Openlist
		openlist.add(start);
		// TODO Bedingung �berarbeiten (wenn billigerer Node noch existiert wird dieser
		// aufgel�st)
		while (findCheapestNode().getX() != ziel.x && findCheapestNode().getX() != ziel.y) {
			dissolveNode(findCheapestNode(), ziel);
		}
		return ret;
	}

	public TileNode findCheapestNode() {
		int ret = 0;
		double merk = -1;
		if (openlist.size() > 0)
			for (int i = 0; i < openlist.size(); i++)
				if (merk < openlist.get(i).getDistance() + openlist.get(i).getLimit()) {
					ret = i;
					merk = openlist.get(i).getDistance() + openlist.get(i).getLimit();
				}
		return openlist.get(ret);
	}

	/**
	 * Aufl�sen des Knotens
	 */
	public void dissolveNode(TileNode node, Point ziel) {
		
		
		// HARDCODING LIKE A SCIACO CUZ M�DE
		
		
		// o o o
		// o x x
		// o o o
		if (node.x + 1 < field.length)
			if (field[node.y][node.x + 1] == 'S')
				openlist.add(new TileNode(node.y, node.x + 1, node, ziel));

		// o o o
		// o x o
		// o o x
		if (node.y + 1 < field[0].length && node.x + 1 < field.length)
			if (field[node.y + 1][node.x + 1] == 'S')
				openlist.add(new TileNode(node.y + 1, node.x + 1, node, ziel));

		// o o o
		// o x o
		// o x o
		if (node.y + 1 < field[0].length)
			if (field[node.y + 1][node.x] == 'S')
				openlist.add(new TileNode(node.y + 1, node.x, node, ziel));

		// o o o
		// o x o
		// x o o
		if (node.y + 1 < field[0].length && node.x - 1 >= 0) {
			if (field[node.y + 1][node.x - 1] == 'S') {
				openlist.add(new TileNode(node.y + 1, node.x - 1, node, ziel));
			}
		}
		// o o o
		// x x o
		// o o o
		
		
		if (node.x - 1 >= 0) {
			if (field[node.y][node.x - 1] == 'S') {
				openlist.add(new TileNode(node.y, node.x - 1, node, ziel));
			}
		}
		// x o o
		// o x o
		// o o o
		if (node.y - 1 >= 0 && node.x - 1 >= 0) {
			if (field[node.y - 1][node.x - 1] == 'S') {
				openlist.add(new TileNode(node.y - 1, node.x - 1, node, ziel));
			}
		}
		// o x o
		// o x o
		// o o o
		if (node.y - 1 >= 0) {
			if (field[node.y - 1][node.x] == 'S') {
				openlist.add(new TileNode(node.y + 1, node.x, node, ziel));
			}
		}
		// o o x
		// o x o
		// o o o
		if (node.y - 1 >= 0 && node.x + 1 < field.length)
			if (field[node.y - 1][node.x + 1] == 'S')
				openlist.add(new TileNode(node.y - 1, node.x + 1, node, ziel));

		openlist.remove(node);
	}

}