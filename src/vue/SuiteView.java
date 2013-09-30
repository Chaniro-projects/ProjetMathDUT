package vue;

import javax.swing.JFrame;

import controleur.SuiteController;
import modele.SuiteListener;

/**
 * Classe abstraite mod�lisant une vue de la suite
 * @author Bastien Baret, Thibault Dassonvill�, Damien Bidaud
 * @version 1.0
 */	
@SuppressWarnings("serial")
public abstract class SuiteView extends JFrame implements SuiteListener{

	/**
	 * Controleur du mod�le
	 */
	private SuiteController controleur;
	
	/**
	 * Constructeur
	 * @param controller Controleur actuel
	 */
	public SuiteView(SuiteController controller){
		super();
		
		this.controleur = controller;
	}
	
	public SuiteController getController(){
		return controleur;
	}
	
	/**
	 * Affiche la vue
	 */
	public abstract void display();
	
	/**
	 * Ferme la vue
	 */
	public abstract void close();
}
