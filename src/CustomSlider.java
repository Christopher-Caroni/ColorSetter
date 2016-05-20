import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JSlider;

public class CustomSlider extends JSlider
{
    private Image img = null;
    ClassLoader classLoader = getClass().getClassLoader();
    public CustomSlider() {
    	super();
        try
        {
        	img = ImageIO.read(new File(classLoader.getResource("SpectrumBar.jpg").getFile()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public CustomSlider(int a, int b, int c) {
    	super(a,b,c);
        try
        {
            img = ImageIO.read(new File(classLoader.getResource("SpectrumBar.jpg").getFile()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        // Draw the previously loaded image to Component
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
    }
}