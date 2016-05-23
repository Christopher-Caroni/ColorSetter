import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;


public class Apercu extends JFrame {

	private List<Couleur> liste;
	private List<Rectangle> listRect;
	private final int ARR_SIZE = 6;
	private int[] hauteurHistogrammes = new int[]{200,250,175,150,165};
	private List<Integer> array;
	
	
	public Apercu(List<Couleur> liste) {
		this.liste = liste;
		listRect = new ArrayList<>();
		array = new ArrayList<>();
		array.add(200);
		
		setTitle("Aperçu des couleurs");
		setSize( ((liste.size() * 70) * 2) - 40 + 100 + 150 , 500);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setLocation((int) ((width/2 - this.getWidth()/2)), (int) (height/2 - this.getHeight()/2));
		setBackground(Color.WHITE);
		setVisible(true);
		
	}
	
	public void paint (Graphics g){
			
		/* Histogrammes */
		int x = 75;
		int y = 1;

		g.setColor(Color.BLACK);
		// 2e x = offset + largeur rect + 20 + 5 car 70 = largeur rect + 20
		drawArrow(g, x-25, getHeight() - 50 + y, liste.size() * 70 + x + 5, getHeight() - 50 + y);
		drawArrow(g, x-25, getHeight() - 50 + y, x-25, getHeight() - 50 - 300 + y);

		for (int i = 0; i< liste.size();i++) {
			g.setColor(liste.get(i).getColor());
			g.fillRect(x, getHeight()-hauteurHistogrammes[i] - 50, 50, hauteurHistogrammes[i]);
			listRect.add(new Rectangle(x, getHeight()-hauteurHistogrammes[i] - 50, 50, hauteurHistogrammes[i]));
			x += 70;
		}

		x = getWidth() / 2 + 50;

		g.setColor(Color.BLACK);
		drawArrow(g, x-25, getHeight() - 50 + y, liste.size() * 70 + x + 5, getHeight() - 50 + y);
		drawArrow(g, x-25, getHeight() - 50 + y, x-25, getHeight() - 50 - 300 + y);
		
		for (int i = 0; i< liste.size();i++) {
			g.setColor(liste.get(i).getGris());
			g.fillRect(x, getHeight()-hauteurHistogrammes[i] -50, 50, hauteurHistogrammes[i]);
			listRect.add(new Rectangle(x, getHeight()-hauteurHistogrammes[i]-50, 50, hauteurHistogrammes[i]));
			x += 70;
		}
	}
	
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
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

}
