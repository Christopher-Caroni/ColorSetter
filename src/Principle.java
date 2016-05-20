import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Principle{

	List<Couleur> colors = new ArrayList<Couleur>();
	private JFrame frame;
	
	public Principle() {
		frame = new JFrame();
		colors.add(new Couleur());
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		JButton b = new JButton("Ajouter une couleur");
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colors.add(new Couleur());
				for(Couleur c : colors){
					frame.add(c.getPanel());
				}		
			}
		});
		
		frame.add(b);
		frame.add(new Couleur().getPanel());

		frame.setSize(new Dimension(800,500));
		frame.setTitle("Color setter");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		frame.setLocation((int) ((width/2 - frame.getWidth()/2)), (int) (height/2 - frame.getHeight()/2));
		frame.setVisible(true);
		
	}
}
