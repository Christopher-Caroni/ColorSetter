import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;


public class Apercu extends JFrame {

	private List<Couleur> liste;
	private List<Rectangle> listRect;
	private final int ARROW_SIZE = 6;
	private List<Integer> hauteurHistogrammes;
	private final int MAX_HEIGHT;
	private	final int VERTICAL_OFFSET = 75;
	private int numberOfColors = 0;
	
	
	public Apercu(List<Couleur> liste) {
		this.liste = liste;
		listRect = new ArrayList<>();
		hauteurHistogrammes = new ArrayList<>();
		Random r = new Random();
		for (int i=0;i<liste.size();i++) {
			if (liste.get(i).isChecked()) {
				numberOfColors++;
			}
			hauteurHistogrammes.add( (int) ((r.nextInt(26) +10) * 10));
		}
		MAX_HEIGHT = Collections.max(hauteurHistogrammes);
		setTitle("Aperçu des couleurs");
		setSize( ((numberOfColors * 70) * 2) - 40 + 100 + 150 , MAX_HEIGHT + (VERTICAL_OFFSET * 2) + 50);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) ((width/2 - this.getWidth()/2)), (int) (height/2 - this.getHeight()/2));
		setBackground(Color.WHITE);
		setVisible(true);
		
	}
	
	public void paint (Graphics g1){

        Graphics2D g = (Graphics2D) g1.create();
        
		/* Histogrammes */
		int x = 75;
		int y = 1;

		g.setColor(Color.BLACK);
		// 2e x = offset + largeur rect + 20 + 5 car 70 = largeur rect + 20
		drawArrow(g, x-25, getHeight() - VERTICAL_OFFSET + y, numberOfColors * 70 + x + 5, getHeight() - VERTICAL_OFFSET + y); // axe X
		drawArrow(g, x-25, getHeight() - VERTICAL_OFFSET + y, x-25, getHeight() - VERTICAL_OFFSET - MAX_HEIGHT - 50 + y); // axe Y

		for (int i = 0; i< liste.size();i++) {
			if (liste.get(i).isChecked()) {
				g.setColor(Color.BLACK);
				g.drawString("Couleur " + (i+1), x,  getHeight() - ((int)(VERTICAL_OFFSET / 1.5)) );
				g.fillRect(x-1, getHeight()-hauteurHistogrammes.get(i) - VERTICAL_OFFSET-1, 50+2, hauteurHistogrammes.get(i)+2); // rectangle noir en dessous = border
				g.setColor(liste.get(i).getColor());
				g.fillRect(x, getHeight()-hauteurHistogrammes.get(i) - VERTICAL_OFFSET, 50, hauteurHistogrammes.get(i));
				listRect.add(new Rectangle(x, getHeight()-hauteurHistogrammes.get(i) - VERTICAL_OFFSET, 50, hauteurHistogrammes.get(i)));
				x += 70;
			}
		}

		x = getWidth() / 2 + 50;

		g.setColor(Color.BLACK);
		drawArrow(g, x-25, getHeight() - VERTICAL_OFFSET + y, numberOfColors * 70 + x + 5, getHeight() - VERTICAL_OFFSET + y); // axe X
		drawArrow(g, x-25, getHeight() - VERTICAL_OFFSET + y, x-25, getHeight() - VERTICAL_OFFSET - MAX_HEIGHT - 50 + y); // axe Y
		
		for (int i = 0; i< liste.size();i++) {
			if (liste.get(i).isChecked()) {
				g.setColor(Color.BLACK);
				g.drawString("Couleur " + (i+1), x,  getHeight() - ((int)(VERTICAL_OFFSET / 1.5)) );
				g.fillRect(x-1, getHeight()-hauteurHistogrammes.get(i) - VERTICAL_OFFSET-1, 50+2, hauteurHistogrammes.get(i)+2);
				g.setColor(liste.get(i).getGris());
				g.fillRect(x, getHeight()-hauteurHistogrammes.get(i) - VERTICAL_OFFSET, 50, hauteurHistogrammes.get(i));
				listRect.add(new Rectangle(x, getHeight()-hauteurHistogrammes.get(i) - VERTICAL_OFFSET, 50, hauteurHistogrammes.get(i)));
				x += 70;
			}
		}
	}
	
	/**
	 *  Pris de http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
	 * @param g1 le graphics du component
	 * @param x1 X du premier point
	 * @param y1 Y du premier point
	 * @param x2 X du second point
	 * @param y2 Y du second point
	 */
    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        
        g.setStroke(new BasicStroke(2));
        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len-2, 0);
        g.fillPolygon(new int[] {len, len-ARROW_SIZE, len-ARROW_SIZE, len},
                      new int[] {0, -ARROW_SIZE, ARROW_SIZE, 0}, 4);
    }

}
