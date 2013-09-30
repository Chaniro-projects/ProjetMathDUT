package vue;

import java.util.ArrayList;

import javax.swing.UIManager;

import modele.Fraction;
import modele.Suite;
import modele.SuiteModel;
import controleur.SuiteController;

/**
 * <b>Programme permettant d'étudier la récursivité d'une suite mathématique
 * via des graphiques</b>
 * 
 * 
 * Classe de test contenant la méthode main
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
public class Main {

	/**
	 * Debut du programme
	 * @param args Arguments donnés
	 */
	public static void main(String[] args) {
		//Active le thème windows
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Expressions mathématique
		ArrayList<String> exp = new ArrayList<>();
		//Intervals applicables de ces expressions
		ArrayList<String> inter = new ArrayList<>();
		
		// x -> 2x  pour x appartenant à [0;0.5[
		exp.add("2*x");
		inter.add("[0;0.5[");
		
		// x -> 2(1-x)  pour x appartenant à [0.5;1[
		exp.add("2*(1-x)");
		inter.add("[0.5;1]");
		
		//On creer la suite et lance le calcule des 10 premiers termes par defaut
		Suite suite = new Suite(exp, inter);
		suite.calcul(10, new Fraction(2, 7));
		
		//Creation du model
		SuiteModel sm = new SuiteModel(suite);
		
		//Creation du controlleur
		SuiteController sc = new SuiteController(sm);
		
		sc.displaySplashScreen();
		
		//Affichage des vues
		sc.displayViews();
		
		sc.closeSplashScreen();
	}
}
