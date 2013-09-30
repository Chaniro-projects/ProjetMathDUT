package modele;

import java.util.ArrayList;


/**
 * Classe representant une suite mathématique
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
public class Suite {
	
	/**
	 * Liste des points de la suite (fini)
	 */
	private ArrayList<Point> points;
	
	/**
	 * Expressions mathématique de la suite
	 */
	private ArrayList<String> expressions;
	
	/**
	 * Intervals des expressions
	 */
	private ArrayList<String> intervalsExpression;
	
	/**
	 * Arrondi (au millieme par defaut)
	 */
	int arrondi = 3;
	
	/**
	 * Premier terme u(0)
	 */
	Fraction depart;
	
	/**
	 * Constructeur
	 * @param e Expressions de la suite
	 * @param i Intervals des expressions
	 */
	public Suite(ArrayList<String> e, ArrayList<String> i) {
		expressions = e;
		intervalsExpression = i;
		points = new ArrayList<>();
	}
	
	/**
	 * Calcule des termes de la suite
	 * @param nb Nombre de termes a calculer
	 * @param u0 Therme du début : u(0)
	 */
	public void calcul(int nb, Fraction u0) {
		depart = u0;
		points = new ArrayList<>();
		points.add(new Point(0, u0));
		
		for(int j = 1; j<nb; j++) {
			if(points.get(j-1).getY().doubleValue() >= 0 && points.get(j-1).getY().doubleValue() < 0.5d) {
				Fraction tmp = new Fraction(points.get(j-1).getY());
				tmp.multiplier(2);
				Point tmp2 = new Point(j, tmp);
				points.add(tmp2);
			}
			if(points.get(j-1).getY().doubleValue() >= 0.5d && points.get(j-1).getY().doubleValue() <= 1) {
				Fraction tmp = new Fraction(points.get(j-1).getY());
				tmp.soustraire(1);
				tmp.multiplier(-2);
				Point tmp2 = new Point(j, tmp);
				points.add(tmp2);
			}
		}
	}
	
	/**
	 * Ajouter un terme à la suite
	 */
	public void ajouterPoint() {
		int x = points.size();
		if(points.get(x-1).getY().doubleValue() >= 0 && points.get(x-1).getY().doubleValue() < 0.5d) {
			Fraction tmp = new Fraction(points.get(x-1).getY());
			tmp.multiplier(2);
			Point tmp2 = new Point(x, tmp);
			points.add(tmp2);
		}
		if(points.get(x-1).getY().doubleValue() >= 0.5d && points.get(x-1).getY().doubleValue() <= 1) {
			Fraction tmp = new Fraction(points.get(x-1).getY());
			tmp.soustraire(1);
			tmp.multiplier(-2);
			Point tmp2 = new Point(x, tmp);
			points.add(tmp2);
		}
	}
	
	/**
	 * Supprime le dernier terme
	 */
	public void supprimerPoint() {
		if(points.size() > 1)
			points.remove(points.size()-1);
	}
	
	/**
	 * Vérifie si un nombre appartient à un interval
	 * @param interval Interval a considérer
	 * @param nb Nombre en question
	 * @return Vrai si le nombre appartient a l'unterval, sinon false
	 */
	public boolean appartient(String interval, double nb) {
		double nb1, nb2;
		
		nb1 = Double.parseDouble(interval.substring(1, interval.indexOf(";")));
		nb2 = Double.parseDouble(interval.substring(interval.indexOf(";")+1, interval.length()-1));
		
		if(nb == nb1) {
			if(interval.substring(0, 1).equals("["))
				return true;
			else
				return false;
		}
		else if(nb == nb2) {
			if(interval.substring(interval.length()-1, interval.length()).equals("]"))
				return true;
			else
				return false;
		}
		else if(nb1 < nb && nb < nb2)
			return true;
		else
			return false;
	}
	
	/**
	 * Arrondi un nombre
	 * @see Suite#depart
	 * @param in Nombre à arrondir
	 * @return Nombre arrondi
	 */
	public double arrondir(double in)
	{
		double v = ((Math.round(in * (Math.pow(10, arrondi)))) / (Math.pow(10, arrondi)));
		return v;
	}
	
	public String toString() {
		String ret = "Suite :\n";
		for(int i = 0; i<points.size(); i++) {
			ret += "\t" + i + " : " + points.get(i) + "\n";
		}
		
		return ret;
	}
	
	
	public ArrayList<Point> getPoints() {
		return points;
	}

	public Fraction getDepart() {
		return depart;
	}

	public void setDepart(Fraction depart) {
		this.depart = depart;
	}
	public ArrayList<String> getExpressions() {
		return expressions;
	}

	public void setExpressions(ArrayList<String> expressions) {
		this.expressions = expressions;
	}

	public ArrayList<String> getIntervalsExpression() {
		return intervalsExpression;
	}

	public void setIntervalsExpression(ArrayList<String> intervalsExpression) {
		this.intervalsExpression = intervalsExpression;
	}

	public int getArrondi() {
		return arrondi;
	}

	public void setArrondi(int arrondi) {
		this.arrondi = arrondi;
	}
	
}
