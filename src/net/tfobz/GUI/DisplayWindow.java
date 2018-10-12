package net.tfobz.GUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import net.tfobz.Daten.IMGProcessor;

public class DisplayWindow extends JFrame {
	private JButton button1;
	private JButton button2;
	
	public DisplayWindow () {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		
		button1 = new JButton("Pfad berechnen");
		button2 = new JButton("Pfad berechnen Demo");
		
		button1.setBounds(100,100,200, 50);
		button2.setBounds(100,200,200, 50);
		
		add(button1);
		add(button2);
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				if (j.showOpenDialog(DisplayWindow.this) == JFileChooser.APPROVE_OPTION) {
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
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
	}
	

}