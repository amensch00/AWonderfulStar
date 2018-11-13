package net.tfobz.Controller;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.SynchronousQueue;

import net.tfobz.GUI.Photoshop;

/**
 * @author Elias Thomaser
 *
 */
public class Algorithm implements Runnable {
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	private Map map;

	private PriorityQueue<TileNode> openlist = null;
	private boolean[][] closed;
	
	private Photoshop ph;

	public Algorithm(Map map, boolean isStepByStep, Photoshop ph) {
		this.map = map;
		this.ph = ph;
		openlist = new PriorityQueue<TileNode>(map.getMapWidth() * map.getMapHeight(), new TileNodeComparator());
		closed = new boolean[map.getMapWidth()][map.getMapHeight()];
	}

	private TileNode solve() {

		// Fï¿½ge Startknoten zur Openlist
		openlist.add(map.getStart());

		TileNode currentNode;
		while (true) {

//			for (TileNode t : openlist) {
//				System.out.print(t.toString() + "\t");
//			}
			
			currentNode = openlist.poll();

			if (currentNode == null) break; // Madonna wos ischn do passiert

			if (currentNode.equals(map.getZiel()))
				return map.getTileAt(currentNode.getX(), currentNode.getY());

			closed[currentNode.getX()][currentNode.getY()] = true;

			dissolveNode(currentNode);
			
			map.setTileAt(currentNode.getX(), currentNode.getY(), currentNode.getType(), TileOverlay.INCLOSED);
			
			notifyAllObservers();

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				System.out.println("wups");
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public void run() {
		printDaWe(solve());
		notifyAllObservers();
	}
	
	private void printDaWe(TileNode tn) {
		
		if (tn == null || tn.getPrevious() == null)
			ph.setStateAndColorPickerVisibility(State.AVAILABLE, true);
		else {
			//System.out.println(tn.toString());
			map.setTileAt(tn.getX(), tn.getY(), TileType.STREET, TileOverlay.DAWE);
			printDaWe(tn.getPrevious());
		}
	}

	public void dissolveNode(TileNode node) {

//		System.out.println("Current Node: [" + node.getX() + "||" + node.getY() + "]");

		
//		System.out.println();System.out.println();

		checkNeighbour(node, 0, 1);
		checkNeighbour(node, 1, 0);
		checkNeighbour(node, 1, 1);
		checkNeighbour(node, 0, -1);
		checkNeighbour(node, -1, 0);
		checkNeighbour(node, -1, -1);
		checkNeighbour(node, 1, -1);
		checkNeighbour(node, -1, 1);

		notifyAllObservers();
		
		openlist.remove(node);
//		System.out.println("removed: " + openlist.remove(node));
	}

	private void checkNeighbour(TileNode node, int y, int x) {
		if (node.getX() + x < 0 || node.getY() + y < 0 || node.getX() + x >= map.getMapWidth()
				|| node.getY() + y >= map.getMapHeight())
			return;

		// if gewählte position gleich mauer zrughupfen
		if (map.getTileAt(node.getX() + x, node.getY() + y).getType() == TileType.WALL
				|| closed[node.getX() + x][node.getY() + y])
			return;
		
		// P T1
		// T2 Z
		// checked ob T1 ODER T2 mauer sind,
		// wenn ja, dann returned die methode
		if (map.getTileAt(node.getX(), node.getY() + y).getType() == TileType.WALL || map.getTileAt(node.getX() + x, node.getY()).getType() == TileType.WALL)
			return;
		
		

		if (map.getTileAt(node.getX() + x, node.getY() + y).getTotaleEntfernung() > node.getTotaleEntfernung() || openlist.contains(map.getTileAt(node.getX() + x, node.getY() + y)))
			return;
		
		map.getTileAt(node.getX() + x, node.getY() + y).setPrevious(
				map.getTileAt(node.getX(), node.getY()));
		
		openlist.add(map.getTileAt(node.getX() + x, node.getY() + y));
	}

	public void attach(Observer obst) {
		observers.add(obst);
	}

	private void notifyAllObservers() {
		for (Observer obst : observers)
			obst.update();
	}

	public Map getMap() {
		return map;
	}
	
}
