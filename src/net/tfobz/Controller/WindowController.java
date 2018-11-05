package net.tfobz.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
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
	 * Liest eine Datei ein und stellt sie in einem DisplayWindow dar Lesbare
	 * Dateiendungen sind : .png
	 */
	public static void einlesen() {
		JFileChooser j = new JFileChooser();
		j.setDialogTitle("Bild Datei auswählen");
		j.setFileFilter(new FileNameExtensionFilter("PNG Dateien", "png"));

		if (j.showOpenDialog(w) == JFileChooser.APPROVE_OPTION) {
			BufferedImage img = null;
			IMGProcessor converter;
			try {
				img = ImageIO.read(new File(j.getSelectedFile().getPath()));
				converter = new IMGProcessor(img);

				DisplayWindow dpWin = new DisplayWindow(converter.convert(), false);
				dpWin.setVisible(true);
			} catch (IOException ex) {
				System.err.println("IO FEHLER");
			}
		}

	}

	public static void einlesenStep() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:/Users/Tronics-PC/Desktop/testData.png"));
			IMGProcessor converter = new IMGProcessor(img);

			DisplayWindow dpWin = new DisplayWindow(converter.convert(), true);
			dpWin.setVisible(true);
		} catch (IOException ex) {
			System.err.println("IO FEHLER");
		}
	}
}
