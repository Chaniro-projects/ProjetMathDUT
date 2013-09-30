package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

import controleur.SuiteController;

import modele.Suite;
import modele.SuiteOptionChangedEvent;

/**
 * JPanel affichant la récurrence
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PanelRecurrence extends JPanel{
	
	/**
	 * Suite considérée
	 */
	private Suite suite;
	
	/**
	 * Options graphiques
	 */
	public int bordExterieur = 20,
			bordInterieur = 30,
			rayonPoint = 2,
			tailleCroix = 4,
			hitBoxSize = 10;
	
	/**
	 * Zone du graphique
	 */
	private Rectangle zone;
	
	/**
	 * Option de l'objet Graphics pour les pointillés
	 */
	private Stroke drawingStroke;
	
	
	/**
	 * Indique quel est l'étape actuelle de la récurrence
	 * (-1 si aucun point n'est pointé)
	 */
	private int actuel;
	
	/**
	 * Liste des points delimitant la recurrence
	 */
	private ArrayList<Integer> recurrence;
	
	/**
	 * Constructeur
	 * @param s Suite
	 * @param sc Controleur
	 */
	public PanelRecurrence(Suite s, SuiteController sc) {
		actuel = -1;
		suite = s;
		
		recurrence = new ArrayList<>();
		
		addMouseWheelListener(sc);
		
		zone = new Rectangle();
		float dash1[] = {5.0f};
		drawingStroke = new BasicStroke(1.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f, dash1, 0.0f);
	}
	
	/**
	 * Redéfinition de {@link JPanel#paintComponents(Graphics)} afin de dessiner le graphique
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
        Stroke basicStroke = g2d.getStroke();
        
		updateZone();
		
		//fond
		g.setColor(Color.white);
		g.fillRect(zone.x, zone.y, zone.width, zone.height);
		
		//bords
		g.setColor(Color.black);
		g.drawLine(bordExterieur, bordExterieur, getWidth()-bordExterieur, bordExterieur);
		g.drawLine(bordExterieur, bordExterieur, bordExterieur, getHeight()-bordExterieur);
		g.drawLine(getWidth()-bordExterieur, bordExterieur, getWidth()-bordExterieur, getHeight()-bordExterieur);
		g.drawLine(bordExterieur, getHeight()-bordExterieur, getWidth()-bordExterieur, getHeight()-bordExterieur);
		
		//repère
		g.setColor(Color.blue);
		g.drawLine(zone.x, zone.y + zone.height - bordInterieur, zone.x + zone.width, zone.y + zone.height - bordInterieur);
		g.drawLine(zone.x + bordInterieur, zone.y, zone.x + bordInterieur, zone.y + zone.height);
		
		//graduation
		g.drawString("0", zone.x + 15, zone.height +5 );
		g.drawString("1", zone.x + 15, convertYToPanel(1) + 17);
		g.drawString("1", convertXToPanel(1)-2, convertYToPanel(0)+15);
		g.drawLine(convertXToPanel(1), convertYToPanel(0)-3, convertXToPanel(1), convertYToPanel(0)+3);
		
		//f(x)
		g.drawLine(convertXToPanel(0), convertYToPanel(0), convertXToPanel(0.5d), convertYToPanel(1));
		g.drawLine(convertXToPanel(1), convertYToPanel(0), convertXToPanel(0.5d), convertYToPanel(1));
		g.drawLine(convertXToPanel(0), convertYToPanel(0), convertXToPanel(1), convertYToPanel(1));
		
		//graph
		String mess = "", mess2 = "";
		if(recurrence.size() <= 1)
			mess = "Aucun cycle trouvé.";
		else if(actuel == -1) {
			mess = "Utilisez les fleches du clavier pour";
			mess2 = "afficher le cycle pas à pas";
		}
		else if(actuel >= 0)
			mess = "Etape actuelle du cycle: " + (actuel+1) + "/" + (recurrence.get(recurrence.size()-1) - recurrence.get(recurrence.size()-2));
		
		if(actuel >= 0) {
			for(int i = 0; i<=actuel; i++) {
				if((recurrence.get(1) - recurrence.get(0)) >= 1) {
					int a = i+(recurrence.get(0));
					g.setColor(Color.red);
					g2d.setStroke(drawingStroke);
					int x = convertXToPanel(suite.getPoints().get(a).getY().doubleValue());
					int y = convertYToPanel(suite.getPoints().get(a+1).getY().doubleValue());
					int lastY;
					
					if(i == 0)
						lastY = convertYToPanel(0);
					else
						lastY = convertYToPanel(suite.getPoints().get(a).getY().doubleValue());
					
					g.drawLine(x, convertYToPanel(0), x, y);
					g.drawLine(convertXToPanel(0), y, convertXToPanel(suite.getPoints().get(a+1).getY().doubleValue()), y);
					
					g2d.setStroke(basicStroke);
					g.drawLine(x, y, convertXToPanel(suite.getPoints().get(a+1).getY().doubleValue()), y);	
					
					if(i == 0) {
						lastY = convertYToPanel(suite.getPoints().get(recurrence.get(0)).getY().doubleValue());
					}
					g.drawLine(x, lastY, x, y);
					
					//unite
					g.setColor(Color.blue);
					g.drawString(suite.getPoints().get(a).getY()+"", x-7, convertYToPanel(0) + 15);
					g.drawString(suite.getPoints().get(a+1).getY()+"", convertXToPanel(0)-23, y+4);
				}
				else {
					mess2 = "";
					mess = "Pas de récurence";
				}
			}
		}
		
		g.setColor(Color.black);
		g.drawRect(getWidth() - bordExterieur - 200, bordExterieur, 200, 35);
		g.setColor(Color.white);
		g.fillRect(getWidth() - bordExterieur - 199, bordExterieur+1, 199, 34);
		
		
		
		g.setColor(Color.black);
		g.drawString(mess, getWidth() - bordExterieur - 195, bordExterieur + 15);
		g.drawString(mess2, getWidth() - bordExterieur - 195, bordExterieur + 30);
	}
	
	/**
	 * Converti l'abscisse en pixel pour l'affichage
	 * (tiens compte des marges)
	 * @param x Abscisse
	 * @return Abscisse convertie
	 */
	private int convertXToPanel(double x) {
		return (int) (bordExterieur + bordInterieur + x*(zone.width-bordInterieur-20));
	}
	
	/**
	 * Converti l'ordonnée en pixel pour l'affichage
	 * (tiens compte des marges)
	 * @param y Coordonnée à convertir
	 * @return Coordonnée convertie
	 */
	private int convertYToPanel(double y) {
		return (int) (getHeight() - (bordExterieur + bordInterieur + (y*(zone.height-bordInterieur*2))));
	}
	
	/**
	 * Met a jour l'affichage
	 * @param s Nouvelle suite à afficher
	 */
	public void update(Suite s) {
		suite = s;
		actuel = -1;
		repaint();
	}

	/**
	 * Met a jour la zone du graphique
	 */
	public void updateZone() {
		zone.x = bordExterieur;
		zone.y = bordExterieur;
		zone.width = getWidth() - 2*bordExterieur;
		zone.height = getHeight() - 2*bordExterieur;
	}

	public void setHighlight(int newHightightID) {
		repaint();
	}

	public void setRecurrence(ArrayList<Integer> newRecurrence) {
		recurrence = newRecurrence;
		actuel = -1;
	}
	
	public void drawNext() {
		if(recurrence.size() > 1) {
			actuel++;
			if(actuel > recurrence.get(recurrence.size()-1) - recurrence.get(recurrence.size()-2) - 1)
				actuel = -1;
		}
		else
			actuel = -1;
		
		repaint();
	}
	
	public void drawBack() {
		if(recurrence.size() > 1) {
			actuel--;
			if(actuel < -1)
				actuel = recurrence.get(recurrence.size()-1) - recurrence.get(recurrence.size()-2) - 1;
		}
		else
			actuel = -1;
		repaint();
	}

	public void setOption(SuiteOptionChangedEvent suiteOptionChangedEvent) {
	}
	
	
}
