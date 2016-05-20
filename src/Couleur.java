import java.awt.Color;
import java.awt.Dimension;
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

public class Couleur{

	private JPanel panel;
	private JLabel imageLabel;
	private ImageIcon image;
	private JSlider couleurSLider;
	private JSlider grisSlider;
	private JLabel couleurLabel;
	private JLabel grisLabel;
	private int grisValeur;
	private int couleurValeur;
	
	public Couleur () {
		panel = new JPanel();
		
		image = new ImageIcon(Couleur.class.getResource("SpectrumBar.jpg"));
		imageLabel = new JLabel(image);
		imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		
		couleurSLider = new JSlider(0, 100, 50);
		grisSlider = new JSlider(0, 100, 50);
		couleurLabel = new JLabel("label");
		couleurLabel.setBackground(Color.WHITE);
		grisLabel = new JLabel("label");
		grisLabel.setBackground(Color.WHITE);
		couleurSLider.setPreferredSize(new Dimension(50 ,20));
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
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(imageLabel);
		panel.add(couleurLabel);
		panel.add(grisLabel);
		
		JPanel panelSlider = new JPanel();
		panelSlider.setPreferredSize(new Dimension(100,70));
		panelSlider.add(grisSlider);
		panel.add(panelSlider);
		panel.setSize(new Dimension(800, 70));
				
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
