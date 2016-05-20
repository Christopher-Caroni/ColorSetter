import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Couleur extends JPanel{

	private BufferedImage image;
	private JSlider couleurSLider;
	private JSlider grisSlider;
	private JLabel couleurLabel;
	private JLabel grisLabel;
	private int grisValeur;
	private int couleurValeur;
	
	public Couleur () {
		image = null;
		try {
			image = ImageIO.read(new File("strawberry.jpg"));
		} catch (IOException e) {
		}
		couleurSLider = new JSlider(0, 100, 50);
		grisSlider = new JSlider(0, 100, 50);
		couleurLabel = new JLabel();
		grisLabel = new JLabel();
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
		
	//	panel.add(image);
		add(couleurLabel);
		add(grisLabel);
		add(grisSlider);
		
		
	}
}
