package net.tfobz.GUI;

import javax.swing.*;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;

public class Photoshop extends JFrame {
	private JMenuBar menuBar;
	private JMenu newFile, openFile, options, exit, run;
	
	private JButton btn1, btn2, btn3, btn4; 
	
	public Photoshop(int width, int heigth) {
		setBackground(Color.BLACK);
		setVisible(true);
//		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(187, 40, 811, 683);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 40, 167, 683);
		getContentPane().add(panel_1);
		setLocation(100,100);
		setSize(1024, 800);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBackground(new Color(0x343434));
		menuBar_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 40));
		setJMenuBar(menuBar_1);
		
		JMenu mnNewFile = new JMenu("new file");
		mnNewFile.setForeground(Color.WHITE);
		mnNewFile.setFont(new Font("Arial Nova Light", Font.PLAIN, 36));
		menuBar_1.add(mnNewFile);
		
		JSeparator separator = new JSeparator();
		separator.setMaximumSize(new Dimension(5, 36));
		separator.setSize(20, 36);
		separator.setOrientation(SwingConstants.VERTICAL);
		menuBar_1.add(separator);
		
		JMenu mnOpenFile = new JMenu("open file");
		mnOpenFile.setForeground(Color.WHITE);
		mnOpenFile.setFont(new Font("Arial Nova Light", Font.PLAIN, 36));
		menuBar_1.add(mnOpenFile);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setMaximumSize(new Dimension(5, 36));
		menuBar_1.add(separator_1);
		
		JMenu mnOptions = new JMenu("options");
		mnOptions.setForeground(Color.WHITE);
		mnOptions.setFont(new Font("Arial Nova Light", Font.PLAIN, 36));
		menuBar_1.add(mnOptions);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setMaximumSize(new Dimension(5, 36));
		menuBar_1.add(separator_2);
		
		JMenu mnExit = new JMenu("exit");
		mnExit.setForeground(Color.WHITE);
		mnExit.setFont(new Font("Arial Nova Light", Font.PLAIN, 36));
		menuBar_1.add(mnExit);
		
		JLabel label = new JLabel("             ");
		label.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 36));
		label.setForeground(Color.WHITE);
		menuBar_1.add(label);
		
		JMenu mnRun = new JMenu("run");
		mnRun.setForeground(Color.WHITE);
		mnRun.setFont(new Font("Arial Nova Light", Font.PLAIN, 36));
		menuBar_1.add(mnRun);
		mnRun.setHorizontalAlignment(SwingConstants.RIGHT);
		mnRun.setHorizontalTextPosition(SwingConstants.RIGHT);
		
		menuBar = new JMenuBar();
		
	}
}
