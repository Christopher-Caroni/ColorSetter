import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principle extends JFrame{

	private JPanel panel;
	
	public Principle() {
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JButton b = new JButton("Ajouter une couleur");
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.add(new Couleur());
				validate();
				repaint();
			}
		});
		
		panel.add(b);
		panel.add(new Couleur());
		add(panel);

		setSize(new Dimension(1000,500));
		setTitle("Color setter");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) ((width/2 - this.getWidth()/2)), (int) (height/2 - this.getHeight()/2));
		setVisible(true);
		
	}
}
