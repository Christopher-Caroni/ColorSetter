import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Couleur extends JPanel implements ChangeListener{

	private JLabel imageLabel;
	private ImageIcon image;
	private JSlider couleurSLider;
	private JSlider grisSlider;
	private Color couleur;
	private Color gris;
	private JLabel couleurLabel;
	private JLabel grisLabel;
	private double grisValeur;
	private int couleurValeur;
	
	public Couleur () {
		
		image = new ImageIcon(Couleur.class.getResource("SpectrumBar.jpg"));
		imageLabel = new JLabel(image);
		imageLabel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		
		
		Border border = new LineBorder(Color.BLACK, 3);
		couleurSLider = new JSlider(0, 16777215, 50);
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

		couleurValeur = couleurSLider.getValue();
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
		if (e.getSource().equals(couleurSLider)) {
			couleurValeur = couleurSLider.getValue();
			couleurLabel.setBackground(Color.decode("0x" + Integer.toHexString(couleurValeur)));
		} else {
			grisValeur = grisSlider.getValue();
			grisValeur = grisValeur / 100;
			// a recopier la formule a partir de la vraie couleur et non diretement de nuances de gris
			gris = new Color((int) (grisValeur * 255), (int) (grisValeur * 255), (int) (grisValeur * 255));
			grisLabel.setBackground(gris);
		}
		repaint();
	}
}
