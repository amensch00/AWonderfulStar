package net.tfobz.BackEnd;

/**
 * Nodes, welche benutzt werden, um die Map aufzubauen. Sie enthalten
 * Informationen, wie die Koordinaten, das zuvor angefahrene TN, der Typ, das
 * derzeitige Overlay, die Distanzen und die Map indem es sich befindet.
 * 
 * @author Elias Thomaser, Julian Tschager
 *
 */
public class TileNode {
	// Position der TileNode
	private int xPos;
	private int yPos;
	
	// Die vorherig verfolgte TileNode
	private TileNode previousTN;
	
	// Unterliegender TileTyp, bestimmt ob gehbar oder nicht, bzw. Ziel und Start
	private TileType type;
	// Overlay für TileNode, um den Weg später besser darstellen zu können
	private TileOverlay overlay;
	
	/**
	 * Zurückgelegte Distanz vom Ziel bis zu dieser TileNode<br>distanz[StartNode] = 0
	 */
	private double distanz;
	/**
	 * Distanz + Luftlinie zu Ziel
	 */
	private double totaleEntfernung;
	
	private Map map;

	public TileNode(int x, int y) {
		this.xPos = x;
		this.yPos = y;

		this.type = TileType.STREET;
		this.overlay = TileOverlay.NOTHING;
		
		this.distanz = Double.POSITIVE_INFINITY;
		this.totaleEntfernung = Double.POSITIVE_INFINITY;

		// System.out.println("\tnew tileNode created: " + this.toString());
	}

	public Double getTotaleEntfernung() {
		return totaleEntfernung;
	}
	
	public double getDistance() {
		return distanz;
	}
	
	public int getX() {
		return xPos;
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

	public TileType getType() {
		return type;
	}

	public TileOverlay getOverlay() {
		return overlay;
	}

	public void setOverlay(TileOverlay overlay) {
		this.overlay = overlay;
	}

	public void setPrevious(TileNode prev) {
		this.previousTN = prev;
	}

	public void setTileType(TileType type) {
		this.type = type;
	}

	public void setTotaleEntfernung(double totaleEntfernung) {
		this.totaleEntfernung = totaleEntfernung;
	}

	public void setDistanz(double distanz) {
		this.distanz = distanz;
	}

	public void setXPos(int x) {
		this.xPos = x;
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

	public double getLuftlinie() {
		return Math.sqrt(Math.pow(xPos - map.getZiel().getX(), 2) + Math.pow(yPos - map.getZiel().getY(), 2));
	}

	/**
	 * Wenn die vorherige Tile diagonal zur derzeitigen ist wird die Wurzel aus 2
	 * zurï¿½ckgegeben ansonsten gitb es die distanz von 1 zurï¿½ck
	 * 
	 * @return Wurzel(2) wenn diagonal 1 ist normale Rï¿½ckgabe 0 falls previous
	 *         null ist
	 */
	public double calculateDistanceTo(TileNode tn) {
		// Wenn die vorherige Tile diagonal zur derzeitigen ist
		// wird die Wurzel aus 2 zurï¿½ckgegeben
		// ansonsten gitb es die distanz von 1 zurï¿½ck
		if (this.getX() != tn.getX() && this.getY() != tn.getY())
			return Math.sqrt(2.0);
		else
			return 1;
	}

}
