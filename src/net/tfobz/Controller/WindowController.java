package net.tfobz.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import net.tfobz.Daten.IMGProcessor;
import net.tfobz.GUI.DisplayWindow;
import net.tfobz.GUI.EntryWindow;

public class WindowController {
	static EntryWindow w;
	
	public static void main(String[] args) {
		w = new EntryWindow();
	}
	
	public void einlesen() {
		JFileChooser j = new JFileChooser();
		
		if (j.showOpenDialog(w) == JFileChooser.APPROVE_OPTION) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File(j.getSelectedFile().getPath()));
			    IMGProcessor converter = new IMGProcessor(img);
			    converter.convert();
			} catch (IOException ex) {
				System.err.println("IO FEHLER");
			}
		}
	}
	
	public void einlesenStep() {
		einlesen();
	}
}
