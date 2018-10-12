package net.tfobz.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.tfobz.Controller.WindowController;

public class EntryWindow extends JFrame {
	private final int width = 250;
	private final int height = 500;
	private JButton btnEinlesen;
	private JButton btnEinlesenStepByStep;
	private JLabel lblTitel;
	private WindowController wnd;
	
	public EntryWindow() {
		EinlesenAction einlesen = new EinlesenAction();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		
		lblTitel = new JLabel("AWonderfulStar\nA* Implementation by T&T");
		lblTitel.setLocation((width / 2), height - height / 5);
		
		btnEinlesen = new JButton("Einlesen");
		btnEinlesenStepByStep.setName("btnEin");
		btnEinlesen.setBounds(width - width / 4, height - height / 3, 200, 50);
		btnEinlesen.addActionListener(einlesen);
		
		btnEinlesenStepByStep = new JButton("Einlesen mit StepByStep darstellung");
		btnEinlesenStepByStep.setName("btnEinMitStep");
		btnEinlesenStepByStep.setBounds(width - width / 4, height - 2 * height / 3, 200, 50);
		btnEinlesenStepByStep.addActionListener(einlesen);
	}
	
	private class EinlesenAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e != null)
				if (((JButton)e.getSource()).getName().equals("btnEin"))
					wnd.einlesen();
				else
					wnd.einlesenStep();
		}
		
	}
}
