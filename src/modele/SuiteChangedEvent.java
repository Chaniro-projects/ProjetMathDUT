package modele;

import java.util.EventObject;

/**
 * Classe Event du design patter MVC encapsulant la nouvelle suite
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SuiteChangedEvent extends EventObject{

	/**
	 * Nouveau modèle à considérer
	 */
	private SuiteModel newSuite;
	
	/**
	 * Constructeur
	 * @param suiteModel Nouveau modèle
	 */
	public SuiteChangedEvent(SuiteModel suiteModel){
		super(suiteModel);
		this.newSuite = suiteModel;
	}
	
	public SuiteModel getNewSuite(){
		return newSuite;
	}

}
