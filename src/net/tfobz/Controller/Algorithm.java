package net.tfobz.Controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Algorithm implements Runnable {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	private char[][] field = null;
	
	private PriorityQueue<TileNode> openlist = null;
	private boolean[][] closed;

	public Algorithm(char[][] field, boolean isStepByStep) {
		this.field = field;
		openlist = new PriorityQueue<TileNode>(field.length * field[0].length, new TileNodeComparator());
		closed = new boolean[field.length][field[0].length];
	}
	
	
	/**
	 * TODO Tschager bitte moch de fertig
	 * i hon keine ahnung wos des tuat xD
	 * @return
	 */
	private TileNode solve() {
		TileNode start = null;
		Point ziel = null;
		
		// Ermittle Ziel Koordinaten
		for (int x = 0; x < field.length; x++)
			for (int y = 0; y < field[0].length; y++)
				if (field[x][y] == 'Z')
					ziel = new Point(y, x);
		
		// Ermittle Startknoten
		for (int x = 0; x < field.length; x++)
			for (int y = 0; y < field[0].length; y++)
				if (field[x][y] == 'L')
					start = new TileNode(y, x, null, ziel);
		
		// F�ge Startknoten zur Openlist
		openlist.add(start);
		
		TileNode currentNode;
		while ( true ) {
			
			currentNode = openlist.poll();
			
			if (currentNode == null) break; // Madonna wos ischn do passiert
			
			if (currentNode.getX() == ziel.x && currentNode.getY() == ziel.y) {
				// Ziel gefunden
				return new TileNode(currentNode);
			}
			
			closed[currentNode.getX()][currentNode.getY()] = true;
			
			dissolveNode(currentNode, ziel);
			
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("wups");
				e.printStackTrace();
			}
		}
		
		Utilities.print2DCharArray(field);
		
		return currentNode;
	}

	@Override
	public void run() {
		solve();
	}
	
	/**
	 * Aufl�sen des Knotens
	 */
	public void dissolveNode(TileNode node, Point ziel) {
		
		
		System.out.println("Current Node: [" + node.getX() + "||" + node.getY() + "]");
		
		for (TileNode t : openlist) {
			System.out.print(t.toString());
		}
		System.out.println();
			
	
		checkNeighbour (node , 0, 1, ziel);
		checkNeighbour (node , 1, 0, ziel);
		checkNeighbour (node , 1, 1, ziel);
		checkNeighbour (node , 0, -1, ziel);
		checkNeighbour (node , -1, 0, ziel);
		checkNeighbour (node , -1, -1, ziel);
		checkNeighbour (node , 1, -1, ziel);
		checkNeighbour (node , -1, 1, ziel);

		field[node.getY()][node.getX()] = 'X';
		notifyAllObservers();
		
		// o o o
		// o x x
		// o o o
//		if (node.x + 1 < field.length)
//			if (field[node.y][node.x + 1] == 'S')
//				openlist.add(new TileNode(node.y, node.x + 1, node, ziel));

		// o o o
		// o x o
		// o o x
//		if (node.y + 1 < field[0].length && node.x + 1 < field.length)
//			if (field[node.y + 1][node.x + 1] == 'S')
//				openlist.add(new TileNode(node.y + 1, node.x + 1, node, ziel));

		// o o o
		// o x o
		// o x o
//		if (node.y + 1 < field[0].length)
//			if (field[node.y + 1][node.x] == 'S')
//				openlist.add(new TileNode(node.y + 1, node.x, node, ziel));

		// o o o
		// o x o
		// x o o
//		if (node.y + 1 < field[0].length && node.x - 1 >= 0) {
//			if (field[node.y + 1][node.x - 1] == 'S') {
//				openlist.add(new TileNode(node.y + 1, node.x - 1, node, ziel));
//			}
//		}
		
		// o o o
		// x x o
		// o o o
//		if (node.x - 1 >= 0) {
//			if (field[node.y][node.x - 1] == 'S') {
//				openlist.add(new TileNode(node.y, node.x - 1, node, ziel));
//			}
//		}
		// x o o
		// o x o
		// o o o
//		if (node.y - 1 >= 0 && node.x - 1 >= 0) {
//			if (field[node.y - 1][node.x - 1] == 'S') {
//				openlist.add(new TileNode(node.y - 1, node.x - 1, node, ziel));
//			}
//		}
		// o x o
		// o x o
		// o o o
//		if (node.y - 1 >= 0) {
//			if (field[node.y - 1][node.x] == 'S') {
//				openlist.add(new TileNode(node.y + 1, node.x, node, ziel));
//			}
//		}
		// o o x
		// o x o
		// o o o
//		if (node.y - 1 >= 0 && node.x + 1 < field.length)
//			if (field[node.y - 1][node.x + 1] == 'S')
//				openlist.add(new TileNode(node.y - 1, node.x + 1, node, ziel));

		openlist.remove(node);
	}
	
	private void checkNeighbour (TileNode node , int y, int x, Point ziel) {		
		if (node.getY() + y < field.length && 
			node.getX() + x < field[0].length && 
			node.getY() + y >= 0 && 
			node.getX() + x >= 0) 
		{
			if (field[node.getY() + y][node.getX() + x] == 'W' || closed[node.getY() + y][node.getX() + x])
				return;
			
			//if ()
				openlist.add(new TileNode(node.getY() + y, node.getX() + x, node, ziel));
		}
	}
	
	public void printBacktrack(TileNode tn) {
		if (tn.getPrevious() == null) {
			return;
		} else {
			field[tn.getPrevious().getY()][tn.getPrevious().getX()] = 'X';
			printBacktrack(tn.getPrevious());
		}
	}
	
	public void setField(char[][] field) {
		this.field = field;
		notifyAllObservers();
	}
	
	public char[][] getField() {
		return field;
	}
	
	public void attach(Observer obst) {
		observers.add(obst);
	}
	
	private void notifyAllObservers() {
		for (Observer obst : observers)
			obst.update();
	}



}
