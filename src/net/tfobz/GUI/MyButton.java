package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

/**
 * Diese Klasse stellt einen besonderen Button zur Verfügung, welcher sich
 * dadurch auszueichnet, dass er so simple wie möglich ist und zu einer
 * schlichten UI-Schnittstelle beiträgt. Er besteht aus einem JPanel und hat
 * eine Hoveranimation. Zudem stellt Sie einige Methoden zur Verfügung, welche
 * es ermöglichen den Button Inhalt, sowie Style festzulegen.
 * 
 * @author Julian Tschager, Elias Thomaser
 *
 */
public class MyButton extends JPanel {
	private JLabel jLabel = new JLabel();
	private Color color = new Color(255, 255, 255);

	public MyButton(int x, int y, int width, int height) {
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		jLabel.setBounds(x, y, width, height);
		this.addMouseListener(new buttonHoverListener());
		add(Box.createHorizontalGlue());
		add(jLabel);
		add(Box.createHorizontalGlue());
	}

	public MyButton(int x, int y, int width, int height, String text) {
		super();
		this.setBounds(x, y, width, height);

		jLabel = new JLabel(text);
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		jLabel.setBounds(x, y, width, height);
		this.addMouseListener(new buttonHoverListener());
		add(Box.createHorizontalGlue());
		add(jLabel);
		add(Box.createHorizontalGlue());

	}

	public void setLabelColor(Color color) {
		this.color = color;
		jLabel.setForeground(color);
	}

	public void setLabelFont(Font font) {
		jLabel.setFont(font);
	}

	public void setText(String text) {
		this.jLabel.setText(text);
	}

	public String getText() {
		return jLabel.getText();
	}

	private class buttonHoverListener extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			if (color == Color.WHITE)
				jLabel.setForeground(new Color(180, 180, 180));
			else
				jLabel.setForeground(new Color(color.getRed() - 20, color.getGreen() - 20, color.getBlue() - 20));
		}

		public void mouseExited(MouseEvent e) {
			jLabel.setForeground(color);
		}
	}

}
