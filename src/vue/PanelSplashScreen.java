package vue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * JPanel du splash screen
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PanelSplashScreen extends JPanel{

	Image logo;
	
	public PanelSplashScreen() {
		logo = new ImageIcon("img/logo.gif").getImage();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(logo);
		g.drawImage(logo, 0, 0, null);
	}
}
