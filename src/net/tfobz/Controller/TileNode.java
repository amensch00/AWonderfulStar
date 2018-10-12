package net.tfobz.Controller;

import java.awt.Point;

public class TileNode {
	// Entfernung S nach K
	double distance = 0;
	// Untere Schranke für die Entfernung K nach Z (Luftlinie)
	double limit = 0;
	int x = 0;
	int y = 0;
	Point ziel;
	// Vorgängerknoten für Rückwegbestimmung
	TileNode previous = null;

	public TileNode(int x, int y, TileNode previous, Point ziel) {
		this.x = x;
		this.y = y;
		if (previous != null) {
			this.previous = previous;
			this.distance = this.previous.getDistance() + this.calculateDistance();
		}
		this.ziel = ziel;
		
	}

	private double calculateBeeLine() {
		return Math.sqrt(Math.pow(x - ziel.x, 2) + Math.pow(y - ziel.y, 2));
	}

	private double calculateDistance() {
		if (previous != null) {
			if (this.x != previous.x && this.y != previous.y)
				return Math.sqrt(2.0);
			else
				return 1;
		}
		return 0;
		
	}

	public double getDistance() {
		return distance;
	}

	public double getLimit() {
		return limit;
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

}
