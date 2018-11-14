package net.tfobz.Controller;

import java.util.ArrayList;
import java.util.PriorityQueue;
import net.tfobz.GUI.Photoshop;

/**
 * A* Algorithmus den man als Thread starten kann.<br><br>
 * <b>Nicht thread sicher.</b>
 * @author Elias Thomaser
 *
 */
public class Algorithm implements Runnable {
	private Map map;
	private Photoshop ph;

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private PriorityQueue<TileNode> openlist = null;
	
	private boolean[][] closed;
	private boolean isStepByStep;
	
	public Algorithm(Map map, boolean isStepByStep, Photoshop ph) {
		this.map = map;
		this.ph = ph;
		this.isStepByStep = isStepByStep;
		
		openlist = new PriorityQueue<TileNode>(map.getMapWidth() * map.getMapHeight(), new TileNodeComparator());
		closed = new boolean[map.getMapWidth()][map.getMapHeight()];
	}

	public Map getMap() {
		return map;
	}
	
	/**
	 * Fügt einer Observer zur Liste hinzu die dafür zuständig ist
	 * allen Observern zu sagen dass die beobachten Daten sich geändert haben.
	 */
	public void attach(Observer obst) {
		observers.add(obst);
	}

	@Override
	public void run() {
		TileNode solvedFinalNode = null;
		
		try {
			solvedFinalNode = solve();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			backtrackAndPrintTakenPath(solvedFinalNode);
			notifyAllObservers();
		}
	}

	/**
	 * Löst den Weg auf und gibt die Ziel-<code>TileNode</code> zurück.
	 * @return Die Ziel-<code>TileNode</code> zurück
	 */
	private TileNode solve() {
	
		// Fï¿½ge Startknoten zur Openlist
		openlist.add(map.getStart());
	
		TileNode currentNode;
		while (true) {
	
			// for (TileNode t : openlist) {
			// System.out.print(t.toString() + "\t");
			// }
	
			currentNode = openlist.poll();
	
			if (currentNode == null)
				break; // Madonna wos ischn do passiert
	
			if (currentNode.equals(map.getZiel()))
				return map.getTileAt(currentNode.getX(), currentNode.getY());
	
			openlist.remove(currentNode);
			closed[currentNode.getX()][currentNode.getY()] = true;
	
			dissolveNode(currentNode);
	
			map.setTileAt(currentNode.getX(), currentNode.getY(), currentNode.getType(), TileOverlay.INCLOSED);
	
			notifyAllObservers();
			if (isStepByStep)
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					System.out.println("wups");
					e.printStackTrace();
				}
		}
	
		return null;
	}

	private void dissolveNode(TileNode node) {
	
		// System.out.println("Current Node: [" + node.getX() + "||" + node.getY() +
		// "]");
	
		// System.out.println();System.out.println();
		
		checkNeighbour(node, 0, 1);
		checkNeighbour(node, 0, -1);
		checkNeighbour(node, 1, 0);
		checkNeighbour(node, -1, 0);
		checkNeighbour(node, 1, 1);
		checkNeighbour(node, 1, -1);
		checkNeighbour(node, -1, 1);
		checkNeighbour(node, -1, -1);
	
		notifyAllObservers();
	}

	private void checkNeighbour(TileNode node, int y, int x) {
		// Schaut ob der Nachbar innerhalb der Karte liegt
		if (node.getX() + x < 0 || node.getY() + y < 0 || node.getX() + x >= map.getMapWidth()
				|| node.getY() + y >= map.getMapHeight())
			return;
	
		// if gewï¿½hlte position gleich mauer zrughupfen
		if (map.getTileAt(node.getX() + x, node.getY() + y).getType() == TileType.WALL
				|| closed[node.getX() + x][node.getY() + y])
			return;
	
		// P T1
		// T2 Z
		// checked ob T1 ODER T2 mauer sind,
		// wenn ja, dann returned die methode
		if (map.getTileAt(node.getX(), node.getY() + y).getType() == TileType.WALL || map.getTileAt(node.getX() + x, node.getY()).getType() == TileType.WALL)
			return;
	
		// Distanz von Start zu nachbar
		double currScore = node.getDistance() + node.calculateDistanceTo(map.getTileAt(node.getX() + x, node.getY() + y));
	
		
		if (currScore >= map.getTileAt(node.getX() + x, node.getY() + y).getDistance())
			return;
		
		map.setPreviousOfTileAt(node.getX() + x, node.getY() + y, node);
		map.getTileAt(node.getX() + x, node.getY() + y).setDistanz(currScore);
		map.getTileAt(node.getX() + x, node.getY() + y).setTotaleEntfernung(currScore + map.getTileAt(node.getX() + x, node.getY() + y).getLuftlinie());
		
		if (!openlist.contains(map.getTileAt(node.getX() + x, node.getY() + y)))
			openlist.add(map.getTileAt(node.getX() + x, node.getY() + y));
	}

	/**
	 * Takes the final node and backtracks it to the start
	 * and sets the TileType of all the tile in the path
	 * to DAWE (The Way), making for a visually more appeasing
	 * look.
	 */
	private void backtrackAndPrintTakenPath(TileNode tn) {
		if (tn != null && tn.getPrevious() != null) {
			map.setOverlayAt(tn.getX(), tn.getY(), TileOverlay.DAWE);
			
			backtrackAndPrintTakenPath(tn.getPrevious());
		}
		
		ph.setStateAndColorPickerVisibility(State.AVAILABLE, true);
	}

	/**
	 * Zwingt alle Observer die zuvor "attached" wurden, die beobachteten
	 * Daten zu aktualisieren.
	 */
	private void notifyAllObservers() {
		for (Observer obst : observers)
			obst.update();
	}


}
