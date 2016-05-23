import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Principle extends JFrame{

	private JPanel panel;
	private List<Couleur> list;
	
	public Principle() {
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,  1));
		list = new ArrayList<>();
				
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JButton button = new JButton("Ajouter une couleur");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panel.getComponentCount() < 11) {
					list.add(new Couleur());
					panel.add(list.get(list.size()-1));
					if (panel.getComponentCount() > 3) {
						panel.setPreferredSize(new Dimension(800, 74 * panel.getComponentCount()));
						panel.setLayout(new GridLayout(panel.getComponentCount(), 1));
					} else {
						panel.setPreferredSize(new Dimension(800, 74 * (panel.getComponentCount() + 1)));
						panel.setLayout(new GridLayout(panel.getComponentCount() + 1, 1));
					}
					validate();
					repaint();
				}
			}
		});
		
		JLabel legend = new JLabel("                            Selectionner une couleur                               Couleur       Niveau de gris                    Code RGB");
		panel.add(legend);
		list.add(new Couleur());
		panel.add(list.get(0));
		panel.setPreferredSize(new Dimension(800, 74 * (panel.getComponentCount()+2)));
		JScrollPane scroll = new JScrollPane(panel);
		Border border = BorderFactory.createTitledBorder("Liste des couleurs");
	    scroll.setBorder(border);
	    scroll.getVerticalScrollBar().setUnitIncrement(10);
		add(scroll);
		//add(button);
		
		JButton apercu = new JButton("Aperçu");
		//add(apercu);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(button);
		buttonPanel.add(apercu);
		buttonPanel.setPreferredSize(new Dimension(500, 35));
		add(buttonPanel);

		setSize(new Dimension(850, 400));
		setMinimumSize(getSize());
		setTitle("Color setter");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) ((width/2 - this.getWidth()/2)), (int) (height/2 - this.getHeight()/2));
		setVisible(true);
		
	}
}
