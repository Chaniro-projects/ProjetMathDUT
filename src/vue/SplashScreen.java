package vue;

import javax.swing.JFrame;

/**
 * Vue du splash screen
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SplashScreen extends JFrame{
	
	public SplashScreen() {
		setSize(680, 188);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		
		init();
	}

	private void init() {
		
		this.add(new PanelSplashScreen());
	}
	
	public void display() {
		setVisible(true);
		repaint();
	}
	
	public void close() {
		this.dispose();
	}
	
}
