package modele;

/**
 * Classe représentant un point
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
public class Point {

	/**
	 * Abscisse
	 */
	private double x;
	
	/**
	 * Ordonnée
	 */
	private Fraction y;
	
	/**
	 * Constructeur
	 * @param x Abscisse
	 * @param y Ordonnée
	 */
	public Point(double x, Fraction y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public Fraction getY() {
		return y;
	}

	public void setY(Fraction y) {
		this.y = y;
	}
	
	public String toString() {
		return "Point [x:" + x + ", y:" + y + "]";
	}
}
