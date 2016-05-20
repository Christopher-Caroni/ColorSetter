import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.scene.image.Image;

public class Couleur extends JPanel{

	private JLabel imageLabel;
	private ImageIcon image;
	private JSlider couleurSLider;
	private JSlider grisSlider;
	private JLabel couleurLabel;
	private JLabel grisLabel;
	private int grisValeur;
	private int couleurValeur;
	
	public Couleur () {
		
		image = new ImageIcon(Couleur.class.getResource("SpectrumBar.jpg"));
		imageLabel = new JLabel(image);
		imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		
		couleurSLider = new JSlider(0, 100, 50);
		grisSlider = new JSlider(0, 100, 50);
		couleurLabel = new JLabel("label");
		couleurLabel.setBackground(Color.WHITE);
		grisLabel = new JLabel("label");
		grisLabel.setBackground(Color.WHITE);
		couleurSLider.setPreferredSize(new Dimension(100 ,20));
		grisSlider.setPreferredSize(new Dimension(100 ,20));
		
		grisValeur = 0;
		couleurValeur = 0;
		
		couleurSLider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				couleurValeur = couleurSLider.getValue();
			}
		});
		grisSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				grisValeur = grisSlider.getValue();
			}
		});
		
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(imageLabel);
		add(couleurLabel);
		add(grisLabel);
		
		JPanel panelSlider = new JPanel();
		panelSlider.add(grisSlider);
		add(panelSlider);
		setSize(new Dimension(800, 70));
				
	}
}
