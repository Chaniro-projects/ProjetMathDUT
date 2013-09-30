package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

import controleur.SuiteController;

import modele.Point;
import modele.Suite;
import modele.SuiteOptionChangedEvent;

/**
 * JPanel affichant une suite
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PanelGraphique extends JPanel{
	
	/**
	 * Suite à afficher
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
	 * Liste des points affichés pour les hits-boxs
	 */
	private ArrayList<Point> points;
	
	/**
	 * Indique quel points est pointé par la souris
	 * (-1 si aucun point n'est pointé)
	 */
	private int infoPts;
	
	/**
	 * Liste des points delimitant la recurrence
	 */
	private ArrayList<Integer> recurrence;
	
	/**
	 * Affiche ou non la récurrrence
	 */
	private boolean afficherRecurrence = false;
	
	/**
	 * Constructeur
	 * @param s Suite
	 * @param sc Controleur
	 */
	public PanelGraphique(Suite s, SuiteController sc) {
		infoPts = -1;
		suite = s;
		
		recurrence = new ArrayList<>();
		
		addMouseListener(sc);
		addMouseMotionListener(sc);
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
		
		//pointille
		g2d.setStroke(drawingStroke);
		g2d.drawLine(convertXToPanel(0), convertYToPanel(1), getWidth()-bordExterieur, convertYToPanel(1));
		g2d.drawLine(convertXToPanel(0), convertYToPanel(0.5), getWidth()-bordExterieur, convertYToPanel(0.5));
		g2d.setStroke(basicStroke);
		
		//graduation
		g.drawString("0", zone.x + 15, zone.height +5 );
		g.drawString("1", zone.x + 15, convertYToPanel(1) + 17);
		g.drawString("1/2", zone.x + 10, convertYToPanel(0.5) + 17);
		
		int pas = 1; 
		if(suite.getPoints().size() >= 20) pas = suite.getPoints().size()/15;
		if(suite.getPoints().size() >= 100) pas = suite.getPoints().size()/18;
		if(suite.getPoints().size() >= 500) pas = suite.getPoints().size()/20;

		
		for(int i = 0; i<suite.getPoints().size(); i+=pas) {
			int x = convertXToPanel(i);
			int y = convertYToPanel(0);
				int taille = (int)(150*(1f/suite.getPoints().size()));
				if(taille > 15) taille = 15;
				if(taille < 13) taille = 13;
				g.setFont(new Font("TimesRoman", Font.PLAIN, taille));
				if(i != 0)
					g.drawString(i+"", x-3, zone.height + 5);
			g.drawLine(x, y, x, y-5);
		}
		
		
		//Points
		g.setColor(Color.red);
		
		for(int i = 0; i<suite.getPoints().size(); i++) {
			Point tmp = suite.getPoints().get(i);
			int x = convertXToPanel(tmp.getX());
			int y = convertYToPanel(tmp.getY().doubleValue());
			
			g.fillOval(x-rayonPoint,
					y-rayonPoint,
					rayonPoint*2,
					rayonPoint*2);
			g.drawLine(x, y-tailleCroix, x, y+tailleCroix);
			g.drawLine(x-tailleCroix, y, x+tailleCroix, y);
		}
		
		//recurrence
		if(afficherRecurrence) {
			if(recurrence.size() > 1) {
				g.setColor(Color.black);
				g2d.setStroke(drawingStroke);
				for(int i = 0; i<recurrence.size(); i++) {
					int x = convertXToPanel(recurrence.get(i));
					g.drawLine(x, convertYToPanel(0), x, convertYToPanel(1));
				}
			}
		}
		
		//info point
		if(infoPts >= 0) {
			int decalX = 0;
			g2d.setStroke(basicStroke);
			g.setFont(new Font("Courier", Font.PLAIN, 13));
			g.setColor(Color.white);
			int x = (int) points.get(infoPts).getX();
			int y = (int) convertYToPanel(points.get(infoPts).getY().doubleValue());
			g.setColor(Color.white);
			
			if(x+63 >= getWidth())
				decalX = -63;
			
			g.fillRoundRect(x+decalX, y-30, 63, 30, 10, 10);
			g.setColor(Color.black);
			g.drawRoundRect(x+decalX, y-30, 63, 30, 10,10);
			g.drawString("X:" + (int)suite.getPoints().get(infoPts).getX(), x+4+decalX, y-5);
			g.drawString("Y:" + suite.getPoints().get(infoPts).getY(), x+4+decalX, y-17);
			
			g2d.setStroke(drawingStroke);
			g.drawLine(x, convertYToPanel(0), x, y);
			g.drawLine(x, y, convertXToPanel(0), y);
		}
		
	}
	
	/**
	 * Converti l'abscisse en pixel pour l'affichage
	 * (tiens compte des marges)
	 * @param x Abscisse
	 * @return Abscisse convertie
	 */
	public int convertXToPanel(double x) {
		return (int) (bordExterieur + bordInterieur + x*(zone.width-bordInterieur) / suite.getPoints().size());
	}
	
	/**
	 * Converti l'ordonnée en pixel pour l'affichage
	 * (tiens compte des marges)
	 * @param y Coordonnée à convertir
	 * @return Coordonnée convertie
	 */
	public int convertYToPanel(double y) {
		return (int) (getHeight() - (bordExterieur + bordInterieur + (y*(zone.height-bordInterieur*2))));
	}
	
	/**
	 * Met a jour l'affichage
	 * @param s Nouvelle suite à afficher
	 */
	public void update(Suite s) {
		suite = s;
		updatePoints();
		repaint();
	}
	
	/**
	 * Met à jour la liste des points affichés pour les hits-boxs
	 */
	public void updatePoints() {
		
		points = new ArrayList<>();
		
		for(int i = 0; i<suite.getPoints().size(); i++) {
			Point tmp = suite.getPoints().get(i);
			points.add(new Point(convertXToPanel(tmp.getX()), tmp.getY()));
		}
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
		infoPts = newHightightID;
		repaint();
	}

	public void setRecurrence(ArrayList<Integer> newRecurrence) {
		recurrence = newRecurrence;
	}

	public void setOption(SuiteOptionChangedEvent suiteOptionChangedEvent) {
		afficherRecurrence = suiteOptionChangedEvent.isRecurrence();
		repaint();
	}

	public int getInfoPts() {
		return infoPts;
	}

	public void setInfoPts(int infoPts) {
		this.infoPts = infoPts;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
}
