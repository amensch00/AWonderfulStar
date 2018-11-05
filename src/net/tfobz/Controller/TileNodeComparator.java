package net.tfobz.Controller;

import java.util.Comparator;

public class TileNodeComparator implements Comparator<TileNode> {

	@Override
	public int compare(TileNode tn, TileNode tn1) {
		return Double.compare(tn.getTotaleEntfernung(), tn1.getTotaleEntfernung());
	}

}
