package net.tfobz.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class ClosingDialog extends JDialog {

	private MyButton yes;
	private MyButton cancel;
	private JLabel leave;

	public ClosingDialog(int x, int y) {
		this.getContentPane().setBackground(Color.DARK_GRAY);

		setModal(true);
		setUndecorated(true);
		setLayout(null);
		setBounds(x, y, 200, 150);
		leave = new JLabel("<html>Do you want<br/>to leave?</html>");
		leave.setBounds(25,20,120,80);
		leave.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		leave.setForeground(Color.WHITE);
		add(leave);
		yes = new MyButton(25, 100, 40, 40, "Yes");
		cancel = new MyButton(95, 100, 80, 40, "Cancel");
		yes.setBackground(Color.DARK_GRAY);
		cancel.setBackground(Color.DARK_GRAY);
		yes.setLabelColor(Color.WHITE);
		cancel.setLabelColor(Color.WHITE);
		yes.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		cancel.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		add(yes);
		add(cancel);
		yes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				System.exit(0);
				
			}
		});
		
		cancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ClosingDialog.this.setVisible(false);
			}
		});
		setVisible(true);

	}
}
