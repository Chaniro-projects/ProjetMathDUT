package modele;

/**
 * Classe repr�sentant un point
 * @author Bastien Baret, Thibault Dassonvill�, Damien Bidaud
 * @version 1.0
 */
public class Point {

	/**
	 * Abscisse
	 */
	private double x;
	
	/**
	 * Ordonn�e
	 */
	private Fraction y;
	
	/**
	 * Constructeur
	 * @param x Abscisse
	 * @param y Ordonn�e
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
