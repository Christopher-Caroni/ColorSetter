import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Principle extends JFrame {

	List<Couleur> colors = new ArrayList<Couleur>();
	
	public Principle() {
		colors.add(new Couleur());
		
		setLayout(new GridLayout(11,1));;
		JButton b = new JButton("Ajouter une couleur");
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colors.add(new Couleur());
				for(Couleur c : colors){
					add(c);
				}		
			}
		});
		
		add(b);
		

		setSize(new Dimension(500,500));
		setTitle("Color setter");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) ((width/2 - getWidth()/2)), (int) (height/2 - getHeight()/2));
		setVisible(true);
		
	}
}
