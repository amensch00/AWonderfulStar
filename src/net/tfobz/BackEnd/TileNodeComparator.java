package net.tfobz.BackEnd;

import java.util.Comparator;

/**
 * Dieser Comparator, vergleicht zwei TileNodes (tn, tn1), anhand ihrer totalen
 * Entfernungen (Luftlinie + bisher gefahrene Strecke) und liefert einen
 * int-Wert zurück (kleiner = neg, gleich = 0, groeßer = positiv)
 * 
 * @author Elias Thomaser, Julian Tschager
 *
 */
public class TileNodeComparator implements Comparator<TileNode> {

	@Override
	public int compare(TileNode tn, TileNode tn1) {
		return Double.compare(tn.getTotaleEntfernung(), tn1.getTotaleEntfernung());
	}

}
