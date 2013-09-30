package modele;

import java.util.ArrayList;
import java.util.EventObject;

/**
 * Classe Event du design patter MVC encapsulant l'ID du nouveau
 * point a mettre en évidence
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SuiteRecurrenceChangedEvent extends EventObject{

	/**
	 * Nouveau ID du point a mettre en évidence
	 */
	private ArrayList<Integer> occurence;
	
	/**
	 * Constructeur
	 * @param id ID du point
	 */
	public SuiteRecurrenceChangedEvent(ArrayList<Integer> id){
		super(id);
		this.occurence = id;
	}
	
	public ArrayList<Integer> getNewRecurrence(){
		return occurence;
	}

}
