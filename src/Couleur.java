import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.basic.BasicArrowButton;

import org.omg.CORBA.INITIALIZE;

public class Couleur extends JPanel {

	private BufferedImage buffer;
	private JComponent imageComp;
	private JCheckBox checkBox;
	private Image image;
	private Color couleur;
	private Color gris;
	private JLabel couleurLabel;
	private JLabel grisLabel;
	private JTextField rgb = new JTextField();
	private JTextField hexa = new JTextField();
	private int niveauGris;
	private MouseInput mouse;
	private Border border;
	private BasicArrowButton up;
	private BasicArrowButton down;
	private int x;

	public Couleur() {
		try {
			buffer = ImageIO.read(this.getClass().getResource("SpectrumBar.jpg"));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		image = Toolkit.getDefaultToolkit().getImage("Ressources/SpectrumBar.jpg");
		imageComp = new JComponent() {
			@Override
			public void paint(Graphics arg0) {
				super.paint(arg0);
				arg0.drawImage(image, 0, 0, this);
				arg0.drawRect(x-3, 0, 3 , 64);
			}
		};
		imageComp.setPreferredSize(new Dimension(368, 64));
		mouse = new MouseInput();
		// il faut ces deux pour detecter d'un côté les clics et les drags
		imageComp.addMouseListener(mouse);
		imageComp.addMouseMotionListener(mouse);

		checkBox = new JCheckBox();
		checkBox.setSelected(true);
		up = new BasicArrowButton(BasicArrowButton.NORTH);
		down = new BasicArrowButton(BasicArrowButton.SOUTH);

		border = new LineBorder(Color.BLACK, 3);
		gris = new Color(128, 128, 128);
		couleur = new Color(255, 255, 255);
		couleurLabel = new JLabel();
		couleurLabel.setBackground(couleur);
		couleurLabel.setOpaque(true);
		couleurLabel.setPreferredSize(new Dimension(64, 64));
		couleurLabel.setBorder(border);

		grisLabel = new JLabel();
		grisLabel.setBackground(gris);
		grisLabel.setOpaque(true);
		grisLabel.setPreferredSize(new Dimension(64, 64));
		grisLabel.setBorder(border);

		rgb.setText(couleur.getRed() + " , " + couleur.getGreen() + " , " + couleur.getBlue());
		rgb.setEditable(false);

		hexa.setText("" + Integer.toHexString(couleur.getRed()) + Integer.toHexString(couleur.getGreen())
				+ Integer.toHexString(couleur.getBlue()));
		hexa.setText("0x" + hexa.getText().toUpperCase());
		hexa.setEditable(false);

		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(checkBox);
		add(up);
		add(down);
		add(Box.createRigidArea(new Dimension(10, 25)));
		add(imageComp);
		add(Box.createRigidArea(new Dimension(30, 25)));
		add(couleurLabel);
		add(Box.createRigidArea(new Dimension(30, 25)));
		add(grisLabel);
		add(Box.createRigidArea(new Dimension(40, 25)));
		add(rgb);
		add(Box.createRigidArea(new Dimension(50, 25)));
		add(hexa);
		setSize(new Dimension(800, 70));
	}

	/**
	 * On utilise MouseInputAdapter pour avoir que les méthodes qu'on utilise
	 * 
	 * @author Christopher Caroni
	 *
	 */
	public class MouseInput extends MouseInputAdapter {
		@Override
		public void mouseDragged(MouseEvent arg0) {
			changeCouleur(arg0);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			changeCouleur(e);
		}
	}

	public void changeCouleur(MouseEvent e) {
		// ignore les bords noirs
		if (e.getX() >= 5 && e.getX() < image.getWidth(this) - 5) {
			x= e.getX();
			imageComp.repaint();
			couleur = new Color(buffer.getRGB(e.getX(), 50));
			couleurLabel.setBackground(couleur);
			// d'apres la formule du sujet
			niveauGris = (int) ((0.3 * couleur.getRed()) + (0.56 * couleur.getGreen()) + (0.11 * couleur.getBlue()));
			gris = new Color(niveauGris, niveauGris, niveauGris);
			grisLabel.setBackground(gris);
			// actualisation du code rgb
			rgb.setText(couleur.getRed() + " , " + couleur.getGreen() + " , " + couleur.getBlue());
			hexa.setText("" + Integer.toHexString(couleur.getRed()) + Integer.toHexString(couleur.getGreen())
					+ Integer.toHexString(couleur.getBlue()));
			hexa.setText("0x" + hexa.getText().toUpperCase());
			repaint();
		}
	}

	public Color getColor() {
		return this.couleur;
	}

	public Color getGris() {
		return this.gris;
	}

	public boolean isChecked() {
		return checkBox.isSelected();
	}

	public void setIsChecked(boolean b) {
		this.checkBox.setSelected(b);
	}

	public BasicArrowButton getUpButton() {
		return up;
	}

	public BasicArrowButton getDownButton() {
		return down;
	}
}
