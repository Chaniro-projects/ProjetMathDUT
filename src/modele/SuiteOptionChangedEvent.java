package modele;

import java.util.EventObject;

/**
 * Classe Event du design patter MVC encapsulant les divers
 * options d'affichage
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SuiteOptionChangedEvent extends EventObject{

	/**
	 * Affichage ou non de la récurrence sur le graphique 1
	 */
	private boolean isRecurrence;
	
	/**
	 * Constructeur
	 * @param r Affichage ou non de la récurrence sur le graphique 1
	 */
	public SuiteOptionChangedEvent(boolean r){
		super(r);
		this.isRecurrence = r;
	}
	
	public boolean isRecurrence(){
		return isRecurrence;
	}

}
