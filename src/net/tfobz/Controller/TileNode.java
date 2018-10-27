package net.tfobz.Controller;

import java.awt.Point;

public class TileNode {
	// Entfernung S nach K
	private double distance = 0;
	// Untere Schranke für die Entfernung K nach Z (Luftlinie)
	private double limit = 0;
	private int x = 0;
	private int y = 0;
	private Point ziel;
	// Vorgängerknoten für Rückwegbestimmung
	private TileNode previous = null;

	public TileNode(int x, int y, TileNode previous, Point ziel) {
		this.x = x;
		this.y = y;
		if (previous != null) {
			this.previous = previous;
			this.distance = this.previous.getDistance() + this.calculateDistance();
		}
		this.ziel = ziel;
		
		if (ziel != null) 
			this.limit = calculateLuftlinie();
		
		System.out.println("\tnew tileNode created: " + this.toString());
	}
	
	public TileNode(TileNode previous) {
		if (previous != null) {
			this.previous = previous;
			this.distance = this.previous.getDistance() + this.calculateDistance();
		}
		else 
			throw new NullPointerException();
	}

	private double calculateLuftlinie() {
		return Math.sqrt(Math.pow(x - ziel.x, 2) + Math.pow(y - ziel.y, 2));
	}

	/**
	 * Wenn die vorherige Tile diagonal zur derzeitigen ist
	 * wird die Wurzel aus 2 zurückgegeben
	 * ansonsten gitb es die distanz von 1 zurück
	 * @return Wurzel(2) wenn diagonal
	 * 		   1 ist normale Rückgabe
	 * 		   0 falls previous null ist
	 */
	private double calculateDistance() {
		if (previous != null) {
			// Wenn die vorherige Tile diagonal zur derzeitigen ist
			// wird die Wurzel aus 2 zurückgegeben
			// ansonsten gitb es die distanz von 1 zurück
			if (this.x != previous.x && this.y != previous.y)
				return Math.sqrt(2.0);
			else
				return 1;
		}
		return 0;
		
	}

	public Double getTotaleEntfernung() {
		return distance + limit;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public TileNode getPrevious() {
		return previous;
	}
	
	public void setPrevious(TileNode prev) {
		this.previous = prev;
	}
	
	@Override
	public String toString() {
		return "[Tile: " + x + "::" + y + "]";
	}

}
