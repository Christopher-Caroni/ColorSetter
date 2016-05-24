import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.basic.BasicArrowButton;

@SuppressWarnings("serial")
public class Principle extends JFrame implements ActionListener {

	private JPanel panel;
	private List<Couleur> list;
	private JButton ajouterButton;
	private Apercu apercu;
	private JButton clear;
	private JButton apercuButton;
	private JLabel legend;
	private JPanel topPanel;
	private JCheckBox topCheckBox;
	private JScrollPane scroll;
	private Border border;
	private JPanel buttonPanel;
	private HashMap<String,JButton> buttonCache;
	
	public Principle() {
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,  1));
		list = new ArrayList<>();
		buttonCache = new HashMap<String,JButton>();
				
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		topPanel = new JPanel();
		legend = new JLabel("                                          Selectionnez une couleur                                                      Couleur              Niveau de gris                  Code RGB                  Code Héxadécimal");
		topCheckBox = new JCheckBox();
		topCheckBox.setSelected(true);
		topCheckBox.addActionListener(this);
		topPanel.add(topCheckBox);
		topPanel.add(legend);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(topPanel);
		list.add(new Couleur());
		panel.add(list.get(0));
		list.get(0).getUpButton().addActionListener(this);
		list.get(0).getDownButton().addActionListener(this);
		list.get(list.size()-1).getUpButton().setActionCommand("" + 0);
		list.get(list.size()-1).getDownButton().setActionCommand("" + 1);
		buttonCache.put("" + 0, list.get(0).getUpButton());
		buttonCache.put("" + 1, list.get(0).getDownButton());
		
		panel.setPreferredSize(new Dimension(800, 74 * (panel.getComponentCount()+2)));
		scroll = new JScrollPane(panel);
		border = BorderFactory.createTitledBorder("Liste des couleurs");
	    scroll.setBorder(border);
	    scroll.getVerticalScrollBar().setUnitIncrement(10);
		add(scroll);
		
		ajouterButton = new JButton("Ajouter une couleur");
		ajouterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		ajouterButton.addActionListener(this);
		
		apercuButton = new JButton("Aperçu");
		clear = new JButton("Reset");
		clear.addActionListener(this);
		apercuButton.addActionListener(this);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(ajouterButton);
		buttonPanel.add(apercuButton);
		buttonPanel.add(clear);
		buttonPanel.setPreferredSize(new Dimension(500, 35));
		add(buttonPanel);

		setSize(new Dimension(1000, 400));
		setMinimumSize(getSize());
		setTitle("Color setter");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) ((width/2 - this.getWidth()/2)), (int) (height/2 - this.getHeight()/2));
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof BasicArrowButton) {
			// up
			if ( ((Double.parseDouble(e.getActionCommand())) % 2) == 0.0 ) {
				int place = (Integer.parseInt(e.getActionCommand())) /2;
				if ( place > 0) {
					Collections.swap(list, place, place-1);
					panel.removeAll();
					topCheckBox.setSelected(true);
					panel.add(topPanel);
					
					int count = 0;
					for (int i=0; i<list.size();i++) {
						panel.add(list.get(i));
						list.get(i).getUpButton().setActionCommand("" + count);
						count++;
						list.get(i).getDownButton().setActionCommand("" + count);
						count++;
					}
					validate();
					repaint();
				}
				// down
			} else if ( ((Double.parseDouble(e.getActionCommand())) % 2) != 0.0 ) {
				int place = (Integer.parseInt(e.getActionCommand()) -1 ) /2;
				if ( place < list.size()-1) {
					Collections.swap(list, place, place+1);
					panel.removeAll();
					topCheckBox.setSelected(true);
					panel.add(topPanel);
					
					int count = 0;
					for (int i=0; i<list.size();i++) {
						panel.add(list.get(i));
						list.get(i).getUpButton().setActionCommand("" + count);
						count++;
						list.get(i).getDownButton().setActionCommand("" + count);
						count++;
					}
					validate();
					repaint();
				}
			}
		}
		if (e.getSource().equals(ajouterButton)) {
			if (panel.getComponentCount() < 11) {
				list.add(new Couleur());
				panel.add(list.get(list.size()-1));
				
				list.get(list.size()-1).getUpButton().addActionListener(this);
				list.get(list.size()-1).getDownButton().addActionListener(this);
				list.get(list.size()-1).getUpButton().setActionCommand("" + (list.size()));
				list.get(list.size()-1).getDownButton().setActionCommand("" + (list.size()+1));
				buttonCache.put("" + (list.size()-1), list.get(0).getUpButton());
				buttonCache.put("" + (list.size()-1), list.get(0).getDownButton());
				
				
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
		} else if (e.getSource().equals(clear)) {
			list.clear();
			panel.removeAll();
			topCheckBox.setSelected(true);
			panel.add(topPanel);
			list.add(new Couleur());
			panel.add(list.get(0));
			panel.setLayout(new GridLayout(4,  1));
			panel.setPreferredSize(new Dimension(800, 74 * (panel.getComponentCount()+2)));
		}  else if (e.getSource().equals(topCheckBox)) {
			if (topCheckBox.isSelected()) {
				for (Couleur c : list) {
					c.setIsChecked(true);
				}
			} else {
				for (Couleur c : list) {
					c.setIsChecked(false);
				}
			}
		} else if (e.getSource().equals(apercuButton)){
			boolean tousFalse = true;
			for (Couleur c : list) {
				if (c.isChecked()) {
					tousFalse = false;
				}
			}
			if (!tousFalse) {
				apercu = new Apercu(list);
				apercu.repaint();
			} else {
				Object[] options = { "Oui, merci !", "Non merci !" };
				int n = JOptionPane.showOptionDialog(this, "Vous n'avez validée aucune couleur. Voulez-vous tous les valider et passer à l'aperçu ?", "Aucune couleur validée",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (n == JOptionPane.YES_OPTION) {
					for (Couleur c : list) {
						c.setIsChecked(true);
					}
					topCheckBox.setSelected(true);
					apercu = new Apercu(list);
					apercu.repaint();
				}
			}
		}
		validate();
		repaint();
	}
	
	public class Mouse extends MouseInputAdapter {
		
	}
}
