import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;


public class Apercu extends JFrame {

	private List<Couleur> liste;
	
	public Apercu(List<Couleur> liste) {
		setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) ((width/2 - this.getWidth()/2)), (int) (height/2 - this.getHeight()/2));
		this.liste = liste;
		setSize( ((liste.size() * 70) * 2) + 50, 500);
		setBackground(Color.WHITE);
	}
	
	public void paint (Graphics g){
		int x = 25;
		int[] length = new int[]{200,250,175,150,165};
		for (int i = 0; i< liste.size();i++) {
			g.setColor(liste.get(i).getColor());
			g.fillRect(x, getHeight()-length[i], 50, length[i]);
			x += 70;
		}
		
		x = getWidth()/2 + 25;
		
		for (int i = 0; i< liste.size();i++) {
			g.setColor(liste.get(i).getGris());
			g.fillRect(x, getHeight()-length[i], 50, length[i]);
			x += 70;
		}
	}

}
