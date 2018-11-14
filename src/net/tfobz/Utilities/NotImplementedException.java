package net.tfobz.Utilities;

/**
 * Eine Exception die w�hrend der Entwicklung praktisch ist, da
 * man Methoden definieren kann, aber noch nicht ausprogrammieren muss,
 * und sp�ter sich dann nicht wundert warum etwas nicht funktioniert weil man
 * vergessen hat etwas zu implementieren.
 * Stattdessen wird das Programm abst�rzen und laut jammern dass etwas noch
 * nicht implementiert wurde.
 * 
 * Die Idee einer solchen Exception kam nicht von mir,
 * sondern habe ich frecherweise von C# "geliehen".
 * 
 * Sie war w�hrend der Entwicklung dieses Programmes sehr praktisch.
 * 
 * @author Elias Thomaser
 *
 */
public class NotImplementedException extends RuntimeException {
	public NotImplementedException() {
		super();
	}

	public NotImplementedException(String msg) {
		super(msg);
	}
}
