import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;


public class Couleur extends JPanel {

	private BufferedImage buffer;
	private JLabel imageLabel;
	private ImageIcon image;
	private Color couleur;
	private Color gris;
	private JLabel couleurLabel;
	private JLabel grisLabel;
	private int niveauGris;
	private MouseInput mouse;
	
	public Couleur () {
		
		try {
		buffer = ImageIO.read(this.getClass().getResource("SpectrumBar.jpg"));
		} catch (IOException e) {
		      System.err.println(e.getMessage());
	    }
		image = new ImageIcon(Couleur.class.getResource("SpectrumBar.jpg"));
		imageLabel = new JLabel(image);
		imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		mouse = new MouseInput();
		// il faut ces deux pour detecter d'un côté les clics et les drags
		imageLabel.addMouseListener(mouse);
		imageLabel.addMouseMotionListener(mouse);
		
		Border border = new LineBorder(Color.BLACK, 3);
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
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(imageLabel);
		add(couleurLabel);
		add(grisLabel);
		setSize(new Dimension(800, 70));
	}
	
	/**
	 * On utilise MouseInputAdapter pour avoir que les méthodes qu'on utilise
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
		if (e.getX() >= 5 && e.getX() < image.getIconWidth()-5) {
			couleur = new Color(buffer.getRGB(e.getX(), 50));
			couleurLabel.setBackground(couleur);
			// d'apres la formule du sujet
			niveauGris = (int) ((0.3 * couleur.getRed()) + (0.56 * couleur.getGreen()) + (0.11 * couleur.getBlue()));
			gris = new Color(niveauGris, niveauGris, niveauGris);
			grisLabel.setBackground(gris);
			repaint();
		}
	}
}
