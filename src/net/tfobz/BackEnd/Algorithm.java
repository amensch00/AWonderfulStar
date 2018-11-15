package net.tfobz.BackEnd;

import java.util.ArrayList;
import java.util.PriorityQueue;
import net.tfobz.GUI.Photoshop;
import net.tfobz.Utilities.ErrorHandling;

/**
 * A* Algorithmus den man als Thread starten kann.<br><br>
 * <b>Nicht thread sicher.</b>
 * @author Elias Thomaser, Tschager
 *
 */
public class Algorithm implements Runnable {
	private Map map;
	private Photoshop ph;

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private PriorityQueue<TileNode> openlist = null;
	
	private boolean[][] closed;
	private boolean isStepByStep;
	
	// Macht es möglich den Trhead zu stoppen
	private boolean isRunning = true;
	
	// timeOut zwischen einzelenen Steps im stepByStep modus
	private int stepTimeout = 50;
	
	public Algorithm(Map map, boolean isStepByStep, Photoshop ph, int stepTimeout) {
		this.map = map;
		this.ph = ph;
		this.isStepByStep = isStepByStep;
		this.stepTimeout = stepTimeout;
		
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
		while ( true ) {	
			currentNode = openlist.poll();
			
			// Kein Weg gefunden
			if (currentNode == null) {
				ErrorHandling.showWarning("Kein Weg gefunden!");
				return null;
			}
	
			// Schaut ob die Aktuelle TileNode das Ziel ist
			// Wenn ja, hat man den Weg gefunden
			if (currentNode.equals(map.getZiel()))
				return map.getTileAt(currentNode.getX(), currentNode.getY());
	
			// Entfernt die derzeitige Node von der OpenList und
			// zugleich fügt sie sie zur closedList hinzu
			openlist.remove(currentNode);
			closed[currentNode.getX()][currentNode.getY()] = true;
	
			// Löst die currentNode auf
			dissolveNode(currentNode);
	
			// Aktualisert die Darstellung der TileNodes in der closedList
			map.setTileAt(currentNode.getX(), currentNode.getY(), currentNode.getType(), TileOverlay.INCLOSED);
			notifyAllObservers();
			
			if (!isRunning)
				return null;
			
			// Wenn step by step true ist, wird eine kleine Verzögerung eingebaut um die
			// einzelnen Schritte zu sehen
			if (isStepByStep)
				try {
					Thread.sleep(stepTimeout);
				} catch (InterruptedException ex) {
					isRunning = false;
				} catch (Exception e) {
					System.out.println("wups");
					e.printStackTrace();
				}
		}
	}

	/**
	 * Geht alle Nachbarn der <code>node</code> durch und löst sie wenn möglich auf
	 */
	private void dissolveNode(TileNode node) {
		// Nicht diagonale Nachbarn
		checkNeighbour(node, 0, 1);
		checkNeighbour(node, 0, -1);
		checkNeighbour(node, 1, 0);
		checkNeighbour(node, -1, 0);
		
		// Diagonale Nachbarn
		checkNeighbour(node, 1, 1);
		checkNeighbour(node, 1, -1);
		checkNeighbour(node, -1, 1);
		checkNeighbour(node, -1, -1);
		
		// Teilt allen Observern mit, dass die Daten sich geändert haben
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
	 * Takes the final node and backtracks it to the start and sets the TileOverlay
	 * of all the tile in the path to DAWE (The Way), making it easier to spot the
	 * path that was taken.
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
