package vue;

import javax.swing.JFrame;

import controleur.SuiteController;
import modele.SuiteListener;

/**
 * Classe abstraite modélisant une vue de la suite
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */	
@SuppressWarnings("serial")
public abstract class SuiteView extends JFrame implements SuiteListener{

	/**
	 * Controleur du modèle
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
