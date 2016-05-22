import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;


public class Couleur extends JPanel implements ChangeListener {

	private BufferedImage buffer;
	private JLabel imageLabel;
	private ImageIcon image;
	private JSlider couleurSLider;
	private JSlider grisSlider;
	private Color couleur;
	private Color gris;
	private JLabel couleurLabel;
	private JLabel grisLabel;
	private double grisValeur;
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
		couleurSLider = new JSlider(0, image.getIconWidth(), 50);
		grisSlider = new JSlider(0, 100, 50);
		couleurSLider.setPreferredSize(new Dimension(150 ,20));
		grisSlider.setPreferredSize(new Dimension(150 ,20));
		
		
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

		grisValeur = grisSlider.getValue();
		
		couleurSLider.addChangeListener(this);
		grisSlider.addChangeListener(this);

		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(imageLabel);
		add(couleurLabel);
		add(grisLabel);
		add(grisSlider);
		add(couleurSLider);
		setSize(new Dimension(800, 70));
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(grisSlider)) {
			grisValeur = grisSlider.getValue();
			grisValeur = grisValeur / 100;
			// a recopier la formule a partir de la vraie couleur et non diretement de nuances de gris
			gris = new Color((int) (grisValeur * 255), (int) (grisValeur * 255), (int) (grisValeur * 255));
			grisLabel.setBackground(gris);
		}
		repaint();
	}
	
	/**
	 * On utilise MouseInputAdapter pour avoir que les méthodes qu'on utilise
	 * @author Christopher Caroni
	 *
	 */
	public class MouseInput extends MouseInputAdapter {
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// ignore les bords noirs
			if (arg0.getX() >= 5 && arg0.getX() < image.getIconWidth()-5) {
				couleurSLider.setValue(arg0.getX());
				couleurLabel.setBackground(new Color(buffer.getRGB(arg0.getX(), 50)));
				repaint();
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// ignore les bords noirs
			if (e.getX() >= 5 && e.getX() < image.getIconWidth()-5) {
				couleurSLider.setValue(e.getX());
				couleurLabel.setBackground(new Color(buffer.getRGB(e.getX(), 50)));
				repaint();
			}
		}
	}
}
