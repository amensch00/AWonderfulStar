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

	/**
	 * TODO Tschager bitte moch de fertig
	 * i hon keine ahnung wos des tuat xD
	 * @return
	 */
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
				if (field[x][y] == 'L')
					start = new TileNode(x, y, null, ziel);
		
		// Füge Startknoten zur Openlist
		openlist.add(start);
		
		// TODO Bedingung überarbeiten (wenn billigerer Node noch existiert wird dieser
		// aufgelöst)
		while (findCheapestNode().getX() != ziel.x && findCheapestNode().getY() != ziel.y) {
			System.out.println("auflösen...");
			dissolveNode(findCheapestNode(), ziel);
		}
		
		return field;
	}

	/**
	 * Findet die aktuell billigste Node die man
	 * auflösen kann und in der OpenListe drinnen steht
	 * @return Die billigste TileNode in der OpenListe
	 */
	public TileNode findCheapestNode() {
		int ret = 0;
		double merk = -1;
		
		if (openlist.size() > 0) {
			// Läuft alle Elemente der openListe durch,
			// auf das aktuell ausgewählte Objekt kann mit
			// tn zugegriffen werden
			for (TileNode tn : openlist) {
				if (merk < tn.getDistance() + tn.getLimit()) {
					ret = openlist.indexOf(tn);
					merk = tn.getDistance() + tn.getLimit();
				}
			}
		}
		
		return openlist.get(ret);
	}

	/**
	 * Auflösen des Knotens
	 */
	public void dissolveNode(TileNode node, Point ziel) {
		
		
		// HARDCODING LIKE A SCIACO CUZ MÜDE
		
		
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
