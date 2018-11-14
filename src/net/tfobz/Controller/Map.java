package net.tfobz.Controller;

import java.awt.Point;

/**
 * Diese Klasse ist in der Lage eine Karte in Form von Nodes abzuspeichren.
 * 
 * @author Elias Thomaser, Julian Tschager
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

	public TileNode[] getAllTNs() {
		return this.allTNs;
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

	public TileNode getTileAt(int x, int y) {
		return allTNs[y * mapWidth + x];
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public void setZiel(Point ziel) {
		this.ziel = ziel;
	}

	public void setTileAt(int x, int y, TileType type) {
		if (allTNs[y * mapWidth + x] == null)
			allTNs[y * mapWidth + x] = new TileNode(x, y);
	
		allTNs[y * mapWidth + x].setXPos(x);
		allTNs[y * mapWidth + x].setYPos(y);
		
		if (type == TileType.START) {
			allTNs[y * mapWidth + x].setDistanz(0);
		}
	
		allTNs[y * mapWidth + x].setTileType(type);
		allTNs[y * mapWidth + x].setMap(this);
	}

	public void setTileAt(int x, int y, TileType type, TileOverlay overlay) {
		if (allTNs[y * mapWidth + x] == null)
			allTNs[y * mapWidth + x] = new TileNode(x, y);
	
		allTNs[y * mapWidth + x].setXPos(x);
		allTNs[y * mapWidth + x].setYPos(y);
		
		if (type == TileType.START) {
			allTNs[y * mapWidth + x].setDistanz(0);
		}
	
		allTNs[y * mapWidth + x].setTileType(type);
		allTNs[y * mapWidth + x].setOverlay(overlay);
		allTNs[y * mapWidth + x].setMap(this);
	}
	
	public void setOverlayAt(int x, int y, TileOverlay overlay) {
		allTNs[y * mapWidth + x].setOverlay(overlay);
	}

	public void setPreviousOfTileAt(int x, int y, TileNode previous) {
		if (previous == null)
			throw new NullPointerException("previous darf nicht null sein");
	
		allTNs[y * mapWidth + x].setPrevious(previous);
	}

	public void clearOverlay() {
		for (int i = 0; i < allTNs.length; i++)
			allTNs[i].setOverlay(TileOverlay.NOTHING);
	}
}
