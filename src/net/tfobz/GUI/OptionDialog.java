
package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

import net.tfobz.Utilities.ColorPalette;

public class OptionDialog extends JDialog {

	private MyButton step;
	private MyButton normal;
	private JLabel question;
	private boolean selection;
	

	public OptionDialog(int x, int y, boolean currentMode) {
		this.getContentPane().setBackground(Color.DARK_GRAY);

		setModal(true);
		setUndecorated(true);
		setLayout(null);
		setBounds(x, y, 300, 150);
		question = new JLabel("<html>Please select the <br/> execution mode!</html>");
		question.setBounds(25, 20, 250, 80);
		question.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		question.setForeground(Color.WHITE);
		add(question);
		step = new MyButton(25, 100, 140, 40, "Step-by-step");
		normal = new MyButton(170, 100, 80, 40, "Normal");
		step.setBackground(Color.DARK_GRAY);
		normal.setBackground(Color.DARK_GRAY);
		if (currentMode) {
			step.setLabelColor(ColorPalette.ROT);
			normal.setLabelColor(Color.WHITE);
		}
		else {
			step.setLabelColor(Color.WHITE);
			normal.setLabelColor(ColorPalette.ROT);
		}
		step.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		normal.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		add(step);
		add(normal);
		step.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				OptionDialog.this.setVisible(false);
				selection = true;
			}
		});

		normal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				OptionDialog.this.setVisible(false);
				selection = false;
			}
		});
		setVisible(true);

	}

	public boolean getSelection() {
		return selection;
	}
}
