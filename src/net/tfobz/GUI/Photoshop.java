package net.tfobz.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.tfobz.Controller.Algorithm;
import net.tfobz.Controller.Map;
import net.tfobz.Controller.Observer;
import net.tfobz.Controller.TileType;
import net.tfobz.Daten.IMGProcessor;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
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
	private JButton start_hl, end_hl, street_hl, wall_hl;
	private JLabel lblstart, lblend, lblstreet, lblwall;
	private Component horizontalStrut_1;

	private ScrollPane scrollPane;
	private Scrollbar horizontalScroll;
	private Scrollbar verticalScroll;
	// 1 = start; 2 = end; 3 = wall; 4 = street
	private int cursor = 1;
	private Algorithm alg;
	private Map map;

	public Photoshop() {

		setBackground(Color.BLACK);
		setLocation(400, 150);
		setSize(1024, 800);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		map = new Map(15, 20);
		for (int i = 0; i < map.getMapHeight(); i++)
			for (int j = 0; j < map.getMapWidth(); j++)
				map.setTileAt(i, j, TileType.STREET);

		// Für Menü-Leiste zuständig
		MouseListener myListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == newFile) {
					NewDialog nd = new NewDialog(
							(int) (Photoshop.this.getLocation().getX() + Photoshop.this.getWidth() / 2) - 200,
							(int) (Photoshop.this.getLocation().getY() + Photoshop.this.getHeight() / 2) - 100);

					if (nd.getWasYesPressed()) {
						map = new Map(nd.getArrayWidth(), nd.getArrayHeight());
						for (int i = 0; i < map.getMapHeight(); i++)
							for (int j = 0; j < map.getMapWidth(); j++)
								map.setTileAt(i, j, TileType.STREET);

						mapDisplayer.setMap(map);
						mapDisplayer.setGridOn(true);
						mapDisplayer.setBounds(90, 0, getWidth() - 90, getHeight() - menuBar.getHeight());
						mapDisplayer.setBackground(new Color(75, 75, 75));
						mapDisplayer.setLayout(null);
						getContentPane().add(mapDisplayer);
						mapDisplayer.repaint();
					}

				} else if (e.getSource() == openFile) {
					JFileChooser j = new JFileChooser();
					j.setDialogTitle("Bild Datei auswählen");
					j.setFileFilter(new FileNameExtensionFilter("PNG Dateien", "png"));

					char[][] map = null;

					if (j.showOpenDialog(Photoshop.this) == JFileChooser.APPROVE_OPTION) {
						BufferedImage img = null;
						try {
							img = ImageIO.read(new File(j.getSelectedFile().getPath()));
							IMGProcessor converter = new IMGProcessor(img);
							map = converter.convert();

							mapDisplayer.setMap(converter.convertToMap());
							System.out.println("DONE");
							mapDisplayer.repaint();
						} catch (IOException ex) {
							System.err.println("IO FEHLER");
						}
					}

//					char[][] map = null;
//					BufferedImage img = null;
//					try {
//						img = ImageIO.read(new File("C:\\Users\\Julian Tschager\\Documents\\testData1.png"));
//						IMGProcessor converter = new IMGProcessor(img);
//
//						map = converter.convert();
//						mapDisplayer.setGridOn(false);
//						mapDisplayer.setMap(map);
//
//						System.out.println("DONE");
//						mapDisplayer.repaint();
//					} catch (IOException ex) {
//						System.err.println("IO FEHLER");
//					}

				} else if (e.getSource() == options) {

				} else if (e.getSource() == exit) {
					ClosingDialog c = new ClosingDialog(
							(int) (Photoshop.this.getLocation().getX() + Photoshop.this.getWidth() / 2) - 100,
							(int) (Photoshop.this.getLocation().getY() + Photoshop.this.getHeight() / 2) - 75);

				} else if (e.getSource() == run) {
					mapDisplayer.startAlg(map, false);
				}
			}
		};

		ActionListener myColorListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				start_hl.setVisible(false);
				end_hl.setVisible(false);
				wall_hl.setVisible(false);
				street_hl.setVisible(false);

				if (e.getSource() == start) {
					start_hl.setVisible(true);
					cursor = 1;
				}

				if (e.getSource() == end) {
					end_hl.setVisible(true);
					cursor = 2;
				}
				if (e.getSource() == wall) {
					wall_hl.setVisible(true);
					cursor = 3;
				}
				if (e.getSource() == street) {
					street_hl.setVisible(true);
					cursor = 4;
				}
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

		colorPicker.setBounds(0, menuBar.getHeight(), 90,
				this.getHeight() - menuBar.getHeight() - this.getInsets().top);
		colorPicker.setBackground(new Color(64, 64, 64));
		colorPicker.setLayout(null);
		getContentPane().add(colorPicker);

		mapDisplayer = new DisplayPanel(map, false);
		mapDisplayer.setBounds(90, 0, this.getWidth() - 90, this.getHeight() - menuBar.getHeight());
		mapDisplayer.setBackground(new Color(75, 75, 75));
		mapDisplayer.setLayout(null);
		mapDisplayer.setGridOn(true);

		getContentPane().add(mapDisplayer);

		mapDisplayer.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image cursorimg = null;
				Point cursorHotSpot = new Point(0, 31);

				try {
					switch (cursor) {

					case 1:
						cursorimg = toolkit.getImage(getClass().getResource("CursorStart.png").getPath());
						break;
					case 2:
						cursorimg = toolkit.getImage(getClass().getResource("CursorEnd.png").getPath());
						break;
					case 3:
						cursorimg = toolkit.getImage(getClass().getResource("CursorWall.png").getPath());
						break;
					case 4:
						cursorimg = toolkit.getImage(getClass().getResource("CursorStreet.png").getPath());
						break;
					}
					setCursor(toolkit.createCustomCursor(cursorimg, cursorHotSpot, "CCursor"));

				} catch (Exception ex) {
					System.out.println("Cursor nicht gefunden!");
					setCursor(Cursor.getDefaultCursor());
				}
//				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

			}

			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});

		// ERMÖGLICHT MALEN
		// TODO
		mapDisplayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getY() <= ((DisplayPanel) e.getSource()).getDisplayedHeight()
						&& e.getX() <= ((DisplayPanel) e.getSource()).getDisplayedWidth()) {

					int col = 0;
					int row = 0;
					int x = ((DisplayPanel) e.getSource()).getLength();

					while (col * x < e.getX())
						if (col * x < e.getX())
							col += 1;

					x = ((DisplayPanel) e.getSource()).getLength();
					while (row * x < e.getY())
						if (row * x < e.getY())
							row += 1;

					switch (cursor) {
					case 1:
						((DisplayPanel) e.getSource()).setMapAt(row - 1, col - 1, TileType.START);
						break;
					case 2:
						((DisplayPanel) e.getSource()).setMapAt(row - 1, col - 1, TileType.ZIEL);
						break;
					case 3:
						((DisplayPanel) e.getSource()).setMapAt(row - 1, col - 1, TileType.WALL);
						break;
					case 4:
						((DisplayPanel) e.getSource()).setMapAt(row - 1, col - 1, TileType.STREET);
						break;
					}
				}
			}
		});

		// scrollPane = new ScrollPane();
		// scrollPane.setBounds(90, 0, this.getWidth() - 90, this.getHeight() -
		// menuBar.getHeight());
		// scrollPane.add(mapDisplayer);
		// getContentPane().add(scrollPane);

		ButtonGroup buttonGroup = new ButtonGroup();

		start = new JButton();
		start.setBounds(10, 100, 70, 70);
		start.setBackground(new Color(52, 152, 219));
		start.setBorderPainted(false);
		buttonGroup.add(start);
		start.addActionListener(myColorListener);
		start.setSelected(true);
		colorPicker.add(start);

		start_hl = new JButton();
		start_hl.setBounds(9, 99, 72, 72);
		start_hl.setBackground(Color.WHITE);
		start_hl.setBorderPainted(false);
		buttonGroup.add(start_hl);
		start_hl.addActionListener(myColorListener);
		start_hl.setEnabled(false);
		start_hl.setVisible(true);
		colorPicker.add(start_hl);

		lblstart = new JLabel("Start", SwingConstants.CENTER);
		lblstart.setBounds(10, 50, 70, 70);
		lblstart.setForeground(Color.WHITE);
		lblstart.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		colorPicker.add(lblstart);

		end = new JButton();
		end.setBounds(10, 220, 70, 70);
		end.setBackground(new Color(241, 196, 15));
		end.setBorderPainted(false);
		buttonGroup.add(end);
		end.addActionListener(myColorListener);
		colorPicker.add(end);

		end_hl = new JButton();
		end_hl.setBounds(9, 219, 72, 72);
		end_hl.setBackground(Color.WHITE);
		end_hl.setBorderPainted(false);
		buttonGroup.add(end_hl);
		end_hl.addActionListener(myColorListener);
		end_hl.setEnabled(false);
		end_hl.setVisible(false);
		colorPicker.add(end_hl);

		lblend = new JLabel("End", SwingConstants.CENTER);
		lblend.setBounds(10, 170, 70, 70);
		lblend.setForeground(Color.WHITE);
		lblend.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		colorPicker.add(lblend);

		street = new JButton();
		street.setBounds(10, 340, 70, 70);
		street.setBackground(new Color(46, 204, 113));
		street.setFocusPainted(true);
		street.setBorderPainted(false);
		buttonGroup.add(street);
		street.addActionListener(myColorListener);
		colorPicker.add(street);

		street_hl = new JButton();
		street_hl.setBounds(9, 339, 72, 72);
		street_hl.setBackground(Color.WHITE);
		street_hl.setBorderPainted(false);
		buttonGroup.add(street_hl);
		street_hl.addActionListener(myColorListener);
		street_hl.setEnabled(false);
		street_hl.setVisible(false);
		colorPicker.add(street_hl);

		lblstreet = new JLabel("Street", SwingConstants.CENTER);
		lblstreet.setBounds(10, 290, 70, 70);
		lblstreet.setForeground(Color.WHITE);
		lblstreet.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		colorPicker.add(lblstreet);

		wall = new JButton();
		wall.setBounds(10, 460, 70, 70);
		wall.setBackground(new Color(231, 76, 60));
		wall.setFocusPainted(true);
		wall.setBorderPainted(false);
		buttonGroup.add(wall);
		wall.addActionListener(myColorListener);
		colorPicker.add(wall);

		wall_hl = new JButton();
		wall_hl.setBounds(9, 459, 72, 72);
		wall_hl.setBackground(Color.WHITE);
		wall_hl.setBorderPainted(false);
		buttonGroup.add(wall_hl);
		wall_hl.addActionListener(myColorListener);
		wall_hl.setEnabled(false);
		wall_hl.setVisible(false);
		colorPicker.add(wall_hl);

		lblwall = new JLabel("Wall", SwingConstants.CENTER);
		lblwall.setBounds(10, 410, 70, 70);
		lblwall.setForeground(Color.WHITE);
		lblwall.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		colorPicker.add(lblwall);

		// highlighter = new Rectangle(start.getBounds());

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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Photoshop p = new Photoshop();
	}
}
