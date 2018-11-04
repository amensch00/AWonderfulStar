package net.tfobz.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.tfobz.Daten.IMGProcessor;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.Dimension;

public class Photoshop extends JFrame {
	private JMenuBar menuBar;
	private MyButton newFile, openFile, options, exit, run;
	JPanel colorPicker;
	DisplayPanel mapDisplayer;
	// Colorpicker
	private JButton start, end, street, wall;
	private JLabel lblstart, lblend, lblstreet, lblwall;
	private Component horizontalStrut_1;
	private Rectangle highlighter;
	private ScrollPane scrollPane;
	private Scrollbar horizontalScroll;
	private Scrollbar verticalScroll;

	public Photoshop() {
		setBackground(Color.BLACK);
		setLocation(400, 150);
		setSize(1024, 800);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		MouseListener myListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == newFile) {
					
				} else if (e.getSource() == openFile) {
//					JFileChooser j = new JFileChooser();
//					j.setDialogTitle("Bild Datei auswählen");
//					j.setFileFilter(new FileNameExtensionFilter("PNG Dateien", "png"));
//
//					char[][] map = null;
//					
//					if (j.showOpenDialog(Photoshop.this) == JFileChooser.APPROVE_OPTION) {
//						BufferedImage img = null;
//						try {
//							img = ImageIO.read(new File(j.getSelectedFile().getPath()));
//							IMGProcessor converter = new IMGProcessor(img);
//							map = converter.convert();
//							mapDisplayer.setMap(map);
//							System.out.println("DONE");
//							mapDisplayer.repaint();
//						} catch (IOException ex) {
//							System.err.println("IO FEHLER");
//						}
//					}
//					
					
					char[][] map = null;
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File("C:\\Users\\Julian Tschager\\Documents\\testData.png"));
						IMGProcessor converter = new IMGProcessor(img);
						map = converter.convert();
						mapDisplayer.setMap(map);
						System.out.println("DONE");
						mapDisplayer.repaint();
					} catch (IOException ex) {
						System.err.println("IO FEHLER");
					}
				
				} else if (e.getSource() == options) {

				} else if (e.getSource() == exit) {
					ClosingDialog c = new ClosingDialog(
							(int) (Photoshop.this.getLocation().getX() + Photoshop.this.getWidth() / 2) - 100, (int) (Photoshop.this.getLocation().getY() + Photoshop.this.getHeight() / 2) - 75);
					// dispose();
					// System.exit(0);
				} else if (e.getSource() == run) {

				}
			}
		};

		ActionListener myColorListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// start.setIcon(new
				// ImageIcon("C:/Users/Julian%20Tschager/Desktop/JUL03715.jpg"));
			}

		};
		// Macht JFrame beweglich
		Point point = new Point(0, 0);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!e.isMetaDown()) {
					point.x = e.getX();
					point.y = e.getY();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (!e.isMetaDown()) {
					Point p = getLocation();
					setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
				}
			}
		});
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0x343434));
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);

		colorPicker = new JPanel();

		colorPicker.setBounds(0, menuBar.getHeight(), 90, this.getHeight() - menuBar.getHeight() - this.getInsets().top);
		colorPicker.setBackground(new Color(64, 64, 64));
		colorPicker.setLayout(null);
		getContentPane().add(colorPicker);

		mapDisplayer = new DisplayPanel(null);
		mapDisplayer.setBounds(90, 0, this.getWidth() - 90, this.getHeight() - menuBar.getHeight());
		mapDisplayer.setBackground(new Color(75, 75, 75));
		mapDisplayer.setLayout(null);
		
//		scrollPane = new ScrollPane();
//		scrollPane.setBounds(90, 0, this.getWidth() - 90, this.getHeight() - menuBar.getHeight());
//		scrollPane.add(mapDisplayer);
//		getContentPane().add(scrollPane);
		
		getContentPane().add(mapDisplayer);
		
		


		ButtonGroup buttonGroup = new ButtonGroup();

		start = new JButton();
		start.setBounds(10, 100, 70, 70);
		start.setBackground(new Color(0, 0, 255));
		start.setBorderPainted(false);
		buttonGroup.add(start);
		start.addActionListener(myColorListener);
		start.setSelected(true);
		colorPicker.add(start);
		

		lblstart = new JLabel("Start", SwingConstants.CENTER);
		lblstart.setBounds(10, 50, 70, 70);
		lblstart.setForeground(Color.WHITE);
		lblstart.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		colorPicker.add(lblstart);

		end = new JButton();
		end.setBounds(10, 220, 70, 70);
		end.setBackground(new Color(255, 255, 0));
		end.setBorderPainted(false);
		buttonGroup.add(end);
		end.addActionListener(myColorListener);
		colorPicker.add(end);

		lblend = new JLabel("End", SwingConstants.CENTER);
		lblend.setBounds(10, 170, 70, 70);
		lblend.setForeground(Color.WHITE);
		lblend.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		colorPicker.add(lblend);

		street = new JButton();
		street.setBounds(10, 340, 70, 70);
		street.setBackground(new Color(0, 255, 0));
		street.setFocusPainted(true);
		street.setBorderPainted(false);
		buttonGroup.add(street);
		street.addActionListener(myColorListener);
		colorPicker.add(street);

		lblstreet = new JLabel("Street", SwingConstants.CENTER);
		lblstreet.setBounds(10, 290, 70, 70);
		lblstreet.setForeground(Color.WHITE);
		lblstreet.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		colorPicker.add(lblstreet);

		wall = new JButton();
		wall.setBounds(10, 460, 70, 70);
		wall.setBackground(new Color(255, 0, 0));
		wall.setFocusPainted(true);
		wall.setBorderPainted(false);
		buttonGroup.add(wall);
		wall.addActionListener(myColorListener);
		colorPicker.add(wall);

		lblwall = new JLabel("Wall", SwingConstants.CENTER);
		lblwall.setBounds(10, 410, 70, 70);
		lblwall.setForeground(Color.WHITE);
		lblwall.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		colorPicker.add(lblwall);

//		highlighter = new Rectangle(start.getBounds());
		
		
		newFile = new MyButton(0, 0, 40, 40, "New file");
		newFile.setMinimumSize(new Dimension(20, 24));
		newFile.setBackground(new Color(0x343434));
		newFile.setLabelColor(Color.WHITE);
		newFile.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		newFile.addMouseListener(myListener);
		menuBar.add(newFile);

		openFile = new MyButton(0, 0, 40, 40, "Open file");
		openFile.setBackground(new Color(0x343434));
		openFile.setLabelColor(Color.WHITE);
		openFile.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		openFile.addMouseListener(myListener);
		menuBar.add(openFile);

		options = new MyButton(0, 0, 40, 40, "Options");
		options.setBackground(new Color(0x343434));
		options.setLabelColor(Color.WHITE);
		options.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		options.addMouseListener(myListener);
		menuBar.add(options);

		exit = new MyButton(0, 0, 40, 40, "Exit");
		exit.setBackground(new Color(0x343434));
		exit.setLabelColor(Color.WHITE);
		exit.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		exit.addMouseListener(myListener);
		menuBar.add(exit);

		run = new MyButton(0, 0, 40, 40, "Run");
		run.setBackground(new Color(0x343434));
		run.setLabelColor(Color.WHITE);
		run.setLabelFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		run.addMouseListener(myListener);

		horizontalStrut_1 = Box.createHorizontalStrut(573);
		menuBar.add(horizontalStrut_1);
		menuBar.add(run);
		
		

		setVisible(true);

	}

	public static void main(String[] args) {
		Photoshop p = new Photoshop();
	}
}
