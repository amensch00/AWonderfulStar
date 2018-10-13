package net.tfobz.Controller;

import java.awt.DisplayMode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.tfobz.Daten.IMGProcessor;
import net.tfobz.GUI.DisplayWindow;
import net.tfobz.GUI.EntryWindow;

public class WindowController {
	static EntryWindow w;
	
	public static void main(String[] args) {
		w = new EntryWindow();
		w.setVisible(true);
	}
	
	
	/**
	 * Liest eine Datei ein und  stellt sie
	 * in einem DisplayWindow dar
	 * Lesbare Dateiendungen sind :
	 *     .png
	 */
	public static void einlesen() {
		JFileChooser j = new JFileChooser();
		j.setDialogTitle("Bild Datei auswählen");
		j.setFileFilter(new FileNameExtensionFilter("PNG Dateien", "png"));
		
		char[][] map = null;
		
		if (j.showOpenDialog(w) == JFileChooser.APPROVE_OPTION) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File(j.getSelectedFile().getPath()));
			    IMGProcessor converter = new IMGProcessor(img);
			    map = converter.convert();
			} catch (IOException ex) {
				System.err.println("IO FEHLER");
			}
		}
		
		DisplayWindow dpWin = new DisplayWindow(map);
		dpWin.setVisible(true);
	}
	
	public static void einlesenStep() {
		einlesen();
	}
}
