package modele;

import java.util.EventObject;

/**
 * Classe Event du design patter MVC encapsulant l'ID du nouveau
 * point a mettre en évidence
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SuiteHightlightChangedEvent extends EventObject{

	/**
	 * Nouveau ID du point a mettre en évidence
	 */
	private int id;
	
	/**
	 * Constructeur
	 * @param id ID du point
	 */
	public SuiteHightlightChangedEvent(int id){
		super(id);
		this.id = id;
	}
	
	public int getNewHightightID(){
		return id;
	}

}
