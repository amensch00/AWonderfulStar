package net.tfobz.Controller;

import java.awt.Point;

/**
 * 
 * @author Elias Thomaser
 *
 */
public class Map {
	private TileNode[] allTNs;
	private Point ziel;
	private Point start;
	private int mapWidth;
	private int mapHeight;

	public Map(int mapWidth, int mapHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;

		allTNs = new TileNode[mapWidth * mapHeight];
	}

	public void setTileAt(int x, int y, TileType type) {
		if (allTNs[y * mapWidth + x] == null)
			allTNs[y * mapWidth + x] = new TileNode(x, y);

		allTNs[y * mapWidth + x].setXPos(x);
		allTNs[y * mapWidth + x].setYPos(y);

		allTNs[y * mapWidth + x].setTileType(type);
		allTNs[y * mapWidth + x].setMap(this);
	}

	public TileNode getZiel() {
		return allTNs[ziel.y * mapWidth + ziel.x];
	}

	public TileNode getStart() {
		return allTNs[start.y * mapWidth + start.x];
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public void setZiel(Point ziel) {
		this.ziel = ziel;
	}

	public TileNode getTileAt(int x, int y) {
		return allTNs[y * mapWidth + x];
	}

	public void setPreviousOfTileAt(int x, int y, TileNode previous) {
		if (previous == null)
			throw new NullPointerException("previous darf nicht null sein");

		allTNs[y * mapWidth + x].setPrevious(previous);
	}
}
