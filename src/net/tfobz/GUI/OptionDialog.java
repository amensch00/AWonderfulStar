
package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.tfobz.Utilities.ColorPalette;
import net.tfobz.Utilities.ErrorHandling;

/**
 * Ein Eigenerstellter Costum Dialog um Optionen behandeln und entgegennemen zu können
 * @author Tschager, Thomaser
 *
 */
public class OptionDialog extends JDialog {

	private MyButton step;
	private MyButton normal;
	private JLabel question;
	private JLabel time;
	private int timeMs;
	private JTextField jtTime;
	private boolean selection;
	

	/**
	 * OptionDialog Konstruktor der X/Y Position, ob der Modus StepByStep ist bzw. die default stepTimeout zeit entgegennimmt
	 * @param x : int 
	 * @param y : int
	 * @param currentMode : boolean
	 * @param timeMs : int
	 */
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
	
	/**
	 * Parsed den Text im TextFeld und gibt in dann als int zurück
	 * @return das stepTimeout in ms
	 */
	public int getTime() {
		if (!jtTime.getText().isEmpty()) {
			try {
				timeMs =  Integer.parseInt(jtTime.getText());
			} catch (Exception e) {
				ErrorHandling.showWarning("Nur Ganzzahlen erlaubt!");
			}
		}
		return timeMs;
	}
}
