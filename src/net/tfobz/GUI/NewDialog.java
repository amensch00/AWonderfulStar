package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.Character.Subset;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.tfobz.Utilities.ErrorHandling;

/**
 * Dieses PopUp-Fenster wird geöffnet, sobald der User eine neue Eingabe-Map
 * erstellen will. Über dieses Fenster,
 * 
 * @author Julian Tschager, Elias Thomaser
 *
 */
public class NewDialog extends JDialog {

	private MyButton yes;
	private MyButton cancel;
	private JLabel newFile;
	private JFormattedTextField width, height;
	private boolean wasYesPressed = false;

	/**
	 * NewDialog Konstruktor der X bzw. Y Position des neuen Dialoges entgegennimmt,
	 * und daraufhin den Dialog initialisiert
	 * @param x : int
	 * @param y : int
	 */
	public NewDialog(int x, int y) {
		// Frame-Einstellungen
		this.getContentPane().setBackground(Color.DARK_GRAY);
		setModal(true);
		setUndecorated(true);
		setLayout(null);
		setBounds(x, y, 230, 200);

		newFile = new JLabel("Create a new file");
		newFile.setBounds(25, 20, 200, 40);
		newFile.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		newFile.setForeground(Color.WHITE);
		add(newFile);
		
		// Bestätigungs-Button, welcher Filerstellt
		yes = new MyButton(25, 140, 40, 40, "Ok");
		yes.setBackground(Color.DARK_GRAY);
		yes.setLabelColor(Color.WHITE);
		yes.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		add(yes);
		
		// Abbruchsbutton, zum Zurückgehen
		cancel = new MyButton(95, 140, 80, 40, "Cancel");
		cancel.setBackground(Color.DARK_GRAY);
		cancel.setLabelColor(Color.WHITE);
		cancel.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		add(cancel);
		
		//Feld - zur Eingabe von Breite der Testmap
		width = new JFormattedTextField("width..");
		width.setHorizontalAlignment(SwingConstants.CENTER);
		width.setBounds(25, 80, 80, 40);
		width.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		width.setForeground(new Color(180, 180, 180));
		width.setBackground(Color.DARK_GRAY);
		width.setSelectionColor(new Color(230, 126, 34));
		width.setCaretColor(Color.WHITE);
		width.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		add(width);
		//Feld - zur Eingabe von Höhe der Testmap
		height = new JFormattedTextField("height..");
		height.setHorizontalAlignment(SwingConstants.CENTER);
		height.setBounds(130, 80, 80, 40);
		height.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		height.setForeground(new Color(180, 180, 180));
		height.setBackground(Color.DARK_GRAY);
		height.setSelectionColor(new Color(230, 126, 34));
		height.setCaretColor(Color.WHITE);
		height.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		add(height);
		
		//Lässt nur Eingabe von Zahlen zu
		width.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (width.getText().contains("width..")) {
					width.setText("");
					width.setForeground(Color.WHITE);
				}
			}

			public void keyReleased(KeyEvent e) {
				if (width.getText().length() > 0 && (e.getKeyChar() < 48 || e.getKeyChar() > 57))
					width.setText(width.getText().substring(0, width.getText().length() - 1));
			}
		});
		//Lässt nur Eingabe von Zahlen zu
		height.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (height.getText().contains("height..")) {
					try {
						height.setText("");
					} catch (Exception e1) {
						ErrorHandling.showWarning("Eingabe nicht korrekt");
					}
					height.setForeground(Color.WHITE);
				}
			}

			public void keyReleased(KeyEvent e) {
				if (height.getText().length() > 0 && (e.getKeyChar() < 48 || e.getKeyChar() > 57))
					try {
						height.setText(width.getText().substring(0, height.getText().length() - 1));
					} catch (Exception e1) {
						ErrorHandling.showWarning("Eingabe nicht korrekt");
					}
			}
		});
		//Überprüft-Eingabe
		yes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int button = 0;
				try {
					Integer.parseInt(width.getText());
					button = 1;
					Integer.parseInt(height.getText());

					if (!(Integer.parseInt(width.getText()) >= 3)) {
						button = 0;
						throw new Exception();
					}

					if (!(Integer.parseInt(height.getText()) >= 3)) {
						button = 1;
						throw new Exception();
					}

					wasYesPressed = true;
					NewDialog.this.setVisible(false);
				} catch (Exception ex) {
					width.setForeground(Color.WHITE);
					height.setForeground(Color.WHITE);
					if (button == 0)
						width.setForeground(new Color(231, 76, 60));
					else
						height.setForeground(new Color(231, 76, 60));
				}

			}
		});
		//Rückgängig-Button
		cancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				NewDialog.this.setVisible(false);
			}
		});
		setVisible(true);

	}

	public boolean getWasYesPressed() {
		return wasYesPressed;
	}

	public int getArrayWidth() {
		return Integer.parseInt(width.getText());
	}

	public int getArrayHeight() {
		return Integer.parseInt(height.getText());
	}
}
