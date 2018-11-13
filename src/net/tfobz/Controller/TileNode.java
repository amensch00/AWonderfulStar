package net.tfobz.Controller;

/**
 * Nodes, welche benutzt werden, um die Map aufzubauen. Sie enthalten
 * Informationen, wie die Koordinaten, das zuvor angefahrene TN, der Typ, das
 * derzeitige Overlay, die Distanzen und die Map indem es sich befindet.
 * 
 * @author Elias Thomaser, Julian Tschager
 *
 */
public class TileNode {
	private int xPos;
	private int yPos;
	private TileNode previousTN;
	private TileType type;
	private TileOverlay overlay;
	private double luftlinie;
	private double distanz;
	private Map map;

	public TileNode(int x, int y) {
		this.xPos = x;
		this.yPos = y;

		this.type = TileType.STREET;
		this.overlay = TileOverlay.NOTHING;

		// System.out.println("\tnew tileNode created: " + this.toString());
	}

	public TileNode(TileNode previous) {
		if (previous != null) {
			this.previousTN = previous;
			this.distanz = this.previousTN.getDistance() + this.calculateDistance();
		} else
			throw new NullPointerException();
	}

	private double calculateLuftlinie() {
		return Math.sqrt(Math.pow(xPos - map.getZiel().getX(), 2) + Math.pow(yPos - map.getZiel().getY(), 2));
	}

	/**
	 * Wenn die vorherige Tile diagonal zur derzeitigen ist wird die Wurzel aus 2
	 * zur�ckgegeben ansonsten gitb es die distanz von 1 zur�ck
	 * 
	 * @return Wurzel(2) wenn diagonal 1 ist normale R�ckgabe 0 falls previous
	 *         null ist
	 */
	private double calculateDistance() {
		if (previousTN != null) {
			// Wenn die vorherige Tile diagonal zur derzeitigen ist
			// wird die Wurzel aus 2 zur�ckgegeben
			// ansonsten gitb es die distanz von 1 zur�ck
			if (this.xPos != previousTN.xPos && this.yPos != previousTN.yPos)
				return Math.sqrt(2.0);
			else
				return 1;
		}
		return 0;

	}

	public Double getTotaleEntfernung() {
		return distanz + luftlinie;
	}

	public double getDistance() {
		return distanz;
	}

	public int getX() {
		return xPos;
	}

	public void setXPos(int x) {
		this.xPos = x;
	}

	public int getY() {
		return yPos;
	}

	public void setYPos(int y) {
		this.yPos = y;
	}

	public TileNode getPrevious() {
		return previousTN;
	}

	public void setPrevious(TileNode prev) {
		this.previousTN = prev;

		distanz = prev.getDistance() + calculateDistance();
		luftlinie = calculateLuftlinie();
	}

	public void setTileType(TileType type) {
		this.type = type;
	}

	public TileType getType() {
		return type;
	}

	public TileOverlay getOverlay() {
		return overlay;
	}

	public void setOverlay(TileOverlay overlay) {
		this.overlay = overlay;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "[Tile: " + xPos + "::" + yPos + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TileNode)
			return ((TileNode) obj).getX() == this.xPos && ((TileNode) obj).getY() == this.yPos
					&& ((TileNode) obj).getType() == this.type;
		else
			return false;
	}

}
