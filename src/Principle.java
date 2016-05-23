import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class Principle extends JFrame{

	private JPanel panel;
	
	public Principle() {
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,  1));
				
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JButton button = new JButton("Ajouter une couleur");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panel.getComponentCount() < 11) {
					panel.add(new Couleur());
					panel.setPreferredSize(new Dimension(850, 74 * (panel.getComponentCount()+2) ));	
					panel.setLayout(new GridLayout(panel.getComponentCount()+2, 1));
					validate();
					repaint();
				}
			}
		});
		JLabel legend = new JLabel("                            Selectionner une couleur                               Couleur       Niveau de gris                    Code RGB");
		panel.add(legend);
		panel.add(new Couleur());
		panel.setPreferredSize(new Dimension(850, 74 * panel.getComponentCount()));
		JScrollPane scroll = new JScrollPane(panel);
		Border border = BorderFactory.createTitledBorder("Liste des couleurs");
	    scroll.setBorder(border);
	    scroll.getVerticalScrollBar().setUnitIncrement(10);;
		add(scroll);
		add(button);

		setSize(new Dimension(850, 400));
		setTitle("Color setter");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) ((width/2 - this.getWidth()/2)), (int) (height/2 - this.getHeight()/2));
		setVisible(true);
		
	}
}
