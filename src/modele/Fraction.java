package modele;

/**
 * Classe représentant une fraction mathématique
 * Permet de ne pas arrondir les nombre en stockant la valeur exacte.
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
public class Fraction {
	/**
	 * Numérateur
	 */
	private int numerator;
	
	/**
	 * Dénominateur
	 */
    private int denominator;

    /**
     * Constructeur
     * @param numerator Numérateur
     * @param denominator Dénominateur
     */
    public Fraction(int numerator, int denominator) {
        if(denominator == 0) {
            throw new IllegalArgumentException("denominator is zero");
        }
        if(denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    /**
     * Constructeur par copie
     * @param f Fraction à copier
     */
    public Fraction(Fraction f) {
    	this.numerator = f.numerator;
    	this.denominator = f.denominator;
    }

    /**
     * Constructeur
     * @param numerator Numérateur
     */
    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    /**
     * Retourne la valeur de la fraction arrondi par un Double
     * @return Valeur de la fraction
     */
    public double doubleValue() {
    	return ((double) numerator)/((double) denominator);
    }
    
    /**
     * Multiplie la fraction par un int
     * @param a Nombre à multiplier
     * @return Résultat
     */
    public Fraction multiplier(int a) {
    	numerator *= a;
    	reduction();
    	return this;
    }
    
    /**
     * Multiplie 2 fractions entre elles
     * @param m Fraction à multiplier
     * @return Résultat
     */
    public Fraction multiplier(Fraction m) {
    	numerator *= m.numerator;
    	denominator *= m.denominator;
    	reduction();
    	return this;
    }
    
    public Fraction soustraire(int a) {
    	numerator -= a*denominator;
    	
    	reduction();
    	return this;
    }
    
    public Fraction soustraire(Fraction a) {
    	numerator *= a.denominator;
    	denominator *= a.denominator;
    	
    	a.numerator *= denominator;
    	a.denominator *= denominator;
    	
    	numerator -= a.numerator;
    	
    	reduction();
    	return this;
    }
    
    /**
     * Réduit une fraction grâce au PGCD
     */
    public void reduction() {
    	int pgcd = pgcd(Math.abs(numerator), Math.abs(denominator));
    	numerator /= pgcd;
    	denominator /= pgcd;
    }
    
    /**
     * Trouve le PGCD de manière récursive
     * @param a Premier nombre
     * @param b Deuxième nombre
     * @return PGCD de a et b
     */
    static int pgcd (int a, int b) {

        if(a<b)
          return (pgcd(b,a));
        else if(b==0)
          return (a);
        else
          return (pgcd(b,a%b));

      }
    
    public String toString() {
    	return numerator + "/" + denominator;
    }
    
    public void sqrt() {
    	numerator = (int) (Math.sqrt(numerator)*10000000);
    	denominator = (int) (Math.sqrt(denominator)*10000000);
    	reduction();
    }
}

