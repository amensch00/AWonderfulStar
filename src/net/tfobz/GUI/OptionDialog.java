
package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.tfobz.Utilities.ColorPalette;

public class OptionDialog extends JDialog {

	private MyButton step;
	private MyButton normal;
	private JLabel question;
	private JLabel time;
	private int timeMs;
	private JTextField jtTime;
	private boolean selection;
	

	public OptionDialog(int x, int y, boolean currentMode, int timeMs) {
		this.timeMs = timeMs;
		this.getContentPane().setBackground(Color.DARK_GRAY);
		setModal(true);
		setUndecorated(true);
		setLayout(null);
		setBounds(x, y, 300, 200);
		question = new JLabel("<html>Please select the <br/> execution mode!</html>");
		question.setBounds(25, 20, 250, 80);
		question.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		question.setForeground(Color.WHITE);
		add(question);
		
		time = new JLabel("Time:");
		time.setBounds(25, 140, 70, 50);
		time.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		time.setForeground(Color.WHITE);
		add(time);
		
		//Feld - zur Eingabe von Breite der Tiles
		jtTime = new JTextField(timeMs);
		jtTime.setHorizontalAlignment(SwingConstants.CENTER);
		jtTime.setBounds(75, 140, 80, 50);
		jtTime.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		jtTime.setForeground(Color.WHITE);
		jtTime.setBackground(Color.DARK_GRAY);
		jtTime.setSelectionColor(new Color(230, 126, 34));
		jtTime.setCaretColor(Color.WHITE);
		jtTime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtTime.addKeyListener(new KeyAdapter() {
		
			public void keyReleased(KeyEvent e) {
				if (jtTime.getText().length() > 0 && (e.getKeyChar() < 48 || e.getKeyChar() > 57))
					jtTime.setText(jtTime.getText().substring(0, jtTime.getText().length() - 1));
			}
		});
		add(jtTime);
		
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
	
	public int getTime() {
		return timeMs;
	}
}
