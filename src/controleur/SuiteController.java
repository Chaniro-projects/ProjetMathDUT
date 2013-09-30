package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modele.Fraction;
import modele.Point;
import modele.Suite;
import modele.SuiteModel;
import vue.JFrameControlSuite;
import vue.JFrameRecurrenceSuite;
import vue.JFrameGraphiqueSuite;
import vue.JFrameTableSuite;
import vue.SplashScreen;

/**
 * Classe controleur du design pattern MVC
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
public class SuiteController implements KeyListener,
										MouseListener,
										MouseWheelListener,
										ActionListener,
										MouseMotionListener{

	/**
	 * Vue graphique de la suite
	 * @see JFrameGraphiqueSuite
	 */
	public JFrameGraphiqueSuite gView;
	
	/**
	 * Paneau de controle de la suite
	 * @see JFrameControlSuite
	 */
	public JFrameControlSuite cView;
	
	/**
	 * Vue sous forme de tableau de la suite
	 * @see JFrameControlSuite
	 */
	public JFrameTableSuite tView;
	
	
	public JFrameRecurrenceSuite g2View;
	/**
	 * Modèle de la suite
	 * @see SuiteModel
	 */
	private SuiteModel model;
	
	ArrayList<Integer> recurrence = new ArrayList<>();
	
	SplashScreen splash;
	
	/**
	 * Constructeur
	 * @param model Modele a stocker
	 */
	public SuiteController (SuiteModel model){
		this.model = model;
		
		gView = new JFrameGraphiqueSuite(this, model.getSuite());
		g2View = new JFrameRecurrenceSuite(this, model.getSuite());
		cView = new JFrameControlSuite(this, model.getSuite());
		tView = new JFrameTableSuite(this, model.getSuite());
		
		addListenersToModel();
	}

	/**
	 * Ajoute les vues comme écouteur du modèle
	 */
	private void addListenersToModel() {
		model.addSuiteListener(gView);
		model.addSuiteListener(g2View);
		model.addSuiteListener(cView);
		model.addSuiteListener(tView);
	}
	
	/**
	 * Affiche les vues
	 */
	public void displayViews(){
		cView.display();
		tView.display();
		gView.display();
		g2View.display();
		
		searchRecurrence();
	}
	
	/**
	 * Ferme les vues
	 */
	public void closeViews(){
		gView.close();
		g2View.close();
		cView.close();
		tView.close();
	}
	
	/**
	 * Notifie que le modèle a changé
	 * @param s Nouvelle suite a considérer
	 */
	public void notifySuiteChanged(Suite s){
		model.setSuite(s);
		searchRecurrence();
	}
	
	/**
	 * Notifie qu'un nouveau point doit etre mis en évidence
	 * @param i ID du point
	 */
	public void notifyHighlight(int i) {
		model.setHighlight(i);
	}

	/**
	 * Notifie que la récurrence à changé
	 * @param occurence Nouvelle récurrence
	 */
	public void notifyRecurrence(ArrayList<Integer> occurence) {
		model.setRecurrence(occurence);
	}

	/**
	 * Notifie que les options ont changés(afficher ou non la récurrence)
	 * @param r boolean definissant l'affichage de la récurrence
	 */
	public void notifyOptionChanged(boolean r) {
		model.setOption(r);
	}

	/**
	 * Gestion du bouton
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cView.getBt())) 
			changeSuite();
		if(e.getSource().equals(cView.getBt2())) {
			System.out.println(recurrence);
			if(recurrence.size() > 1) {
				int ordre = recurrence.get(1) - recurrence.get(0), debut = recurrence.get(0);
				if(debut == 0)
					JOptionPane.showMessageDialog(g2View, "<html>La suite présente <b>cycle</b> d'ordre <b>" + ordre + "</b>.");
				else
					JOptionPane.showMessageDialog(g2View, "<html>La suite présente <b>cycle</b> d'ordre <b>" + ordre + "</b> à partir du terme <b>u(" + debut + ")</b>.");
			}
			else {
				JOptionPane.showMessageDialog(g2View, "<html>La suite ne présente aucun <b>cycle</b>.<br/>" +
						"Vous pouvez réessayer en calculant plus de points<html>");
			}
		}
		if(e.getSource().equals(cView.getIsRecurrence()))
			notifyOptionChanged(cView.getIsRecurrence().isSelected());
	}

	/**
	 * Gestion de la molette de la souris
	 */
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		if(arg0.getWheelRotation() < 0) {
			model.getSuite().ajouterPoint();
			notifySuiteChanged(model.getSuite());
		}
		else if(arg0.getWheelRotation() > 0){
			model.getSuite().supprimerPoint();
			notifySuiteChanged(model.getSuite());
		}
	}

	/**
	 * Gestion du click de souris
	 */
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == tView.getJta())
			notifyHighlight(tView.getJta().getSelectedRow());
	}
	
	/**
	 * Gestion du déplacement de la souris
	 */
	public void mouseMoved(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		int old = gView.getPg().getInfoPts();
		gView.getPg().setInfoPts(-1);
		for(int i = 0; i<model.getSuite().getPoints().size(); i++) {
			Point tmp = gView.getPg().getPoints().get(i);
			if(x>tmp.getX()-gView.getPg().hitBoxSize && x<tmp.getX()+gView.getPg().hitBoxSize && y>gView.getPg().convertYToPanel(tmp.getY().doubleValue())-gView.getPg().hitBoxSize && y<gView.getPg().convertYToPanel(tmp.getY().doubleValue())+gView.getPg().hitBoxSize) {
				notifyHighlight(i);
			}
		}
		if(old != gView.getPg().getInfoPts())
			gView.getPg().repaint();
	}
	
	/**
	 * Gestion du clavier
	 */
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			closeViews();
		}
		
		if(e.getSource() == cView
				|| e.getSource() == gView
				|| e.getSource() == cView.getTxtNb()
				|| e.getSource() == cView.getTxtu0()) {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ADD) {
				model.getSuite().ajouterPoint();
				notifySuiteChanged(model.getSuite());
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
				model.getSuite().supprimerPoint();
				notifySuiteChanged(model.getSuite());
			}
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				changeSuite();
			}
		}
		
		if(e.getSource() == tView.getJta()) {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
				notifyHighlight(tView.getJta().getSelectedRow());
			}
		}
		
		if(e.getSource() == g2View) {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				g2View.getPg().drawNext();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT) {
				g2View.getPg().drawBack();
			}
		}
	}

	
	private void changeSuite() {
		int nb = 0;
		Fraction u0 = new Fraction(0);
		
		try {
		nb = Integer.parseInt(cView.getTxtNb().getText());
		u0 = convert(cView.getTxtu0().getText());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,"<html>Format de nombre <b>incorrect</b></html>");
		}
		if(u0 != null) {
			
			Suite s = new Suite(model.getSuite().getExpressions(), model.getSuite().getIntervalsExpression());
			
			
			if(u0.doubleValue() >= 0 && u0.doubleValue() <= 1) {
				s.calcul(nb, u0);
				notifySuiteChanged(s);
			}
			else
				JOptionPane.showMessageDialog(null,"<html>Veuillez saisir un nombre entre <b>0</b> et <b>1</b></html>"); 
		}
		else
			JOptionPane.showMessageDialog(null,"<html>Format de nombre <b>incorrect</b></html>");
		
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

	public void keyPressed(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}

	public void mouseDragged(MouseEvent arg0) {}

	public Fraction convert(String s){
		Fraction f;
		int a = s.indexOf("/"), b = s.indexOf("sqrt(");
		if(b >= 0) {
			String v = s.substring(5, s.length()-1);
			Fraction tmp = convert(v);
			tmp.reduction();
			tmp.sqrt();
			return tmp;
		}
		else if(a > 0) {
			try {
				f = new Fraction(Integer.parseInt(s.substring(0, a)), Integer.parseInt(s.substring(a+1, s.length())));
				f.reduction();
				return f;
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"<html>Format de nombre <b>incorrect</b></html>");
			}
		}
		else {
			double tmp = Double.parseDouble(s);
			f = new Fraction((int) (tmp*10000000), 10000000);
			f.reduction();
			return f;
		}
		
		return null;
	}
	
	public void searchRecurrence() {
		ArrayList<Double> val = new ArrayList<>();
		ArrayList<Integer> count = new ArrayList<>();
		ArrayList<Integer> occurence = new ArrayList<>();
		
		for(int i = 0; i<model.getSuite().getPoints().size(); i++) {
			double v = model.getSuite().getPoints().get(i).getY().doubleValue();
			boolean exist = false;
			for(int j = 0; j<val.size(); j++) {
				if(v == val.get(j)) {
					count.set(j, count.get(j) + 1);
					exist = true;
				}
			}
			if(!exist) {
				val.add(v);
				count.add(1);
			}
		}
		
		int max = 0;
		int index = -1;
		for(int i = 0; i<count.size(); i++) {
			if(count.get(i) > max) {
				index = i;
				max = count.get(i);
			}
		}

		for(int i = 0; i<model.getSuite().getPoints().size(); i++) {
			if(model.getSuite().getPoints().get(i).getY().doubleValue() == val.get(index)) {
				occurence.add(i);
			}
		}
		
		recurrence = occurence;
		notifyRecurrence(occurence);
		
	}

	public void displaySplashScreen() {
		splash = new SplashScreen();
		splash.display();
	}
	
	public void closeSplashScreen() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		splash.close();
	}
	
}
