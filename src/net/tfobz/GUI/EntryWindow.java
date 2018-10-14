package net.tfobz.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.tfobz.Controller.WindowController;

public class EntryWindow extends JFrame {
	private final int width = 500;
	private final int height = 500;
	private JButton btnEinlesen;
	private JButton btnEinlesenStepByStep;
	private JLabel lblTitel;
	
	public EntryWindow() {
		EinlesenAction einlesen = new EinlesenAction();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		
		lblTitel = new JLabel("AWonderfulStar -- A* Implementation by T&T");
		lblTitel.setFont(new Font("Courier New", Font.BOLD, 18));
		lblTitel.setBounds(50, 50, 400, 50);
		
		btnEinlesen = new JButton("Einlesen");
		btnEinlesen.setName("btnEin");
		btnEinlesen.setBounds(150, 150, 250, 50);
		btnEinlesen.addActionListener(einlesen);
		
		btnEinlesenStepByStep = new JButton("Einlesen mit StepByStep darstellung");
		btnEinlesenStepByStep.setName("btnEinMitStep");
		btnEinlesenStepByStep.setBounds(150, 300, 250, 50);
		btnEinlesenStepByStep.addActionListener(einlesen);
		
		add(lblTitel);
		add(btnEinlesen);
		add(btnEinlesenStepByStep);
	}
	
	private class EinlesenAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e != null)
				if (((JButton)e.getSource()).getName().equals("btnEin"))
					WindowController.einlesen();
				else
					WindowController.einlesenStep();
		}
		
	}
}
