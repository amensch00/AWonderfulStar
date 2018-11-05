package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class MyButton extends JPanel {
	private JLabel jLabel = new JLabel();

	public MyButton(int x, int y, int width, int height) {
		
		this.setBounds(x, y, width, height);
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
			jLabel.setForeground(new Color(180, 180, 180));
		}

		public void mouseExited(MouseEvent e) {
			jLabel.setForeground(Color.WHITE);
		}
	}

}
