package modele;

import java.util.EventObject;

/**
 * Classe Event du design patter MVC encapsulant la nouvelle suite
 * @author Bastien Baret, Thibault Dassonvill�, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SuiteChangedEvent extends EventObject{

	/**
	 * Nouveau mod�le � consid�rer
	 */
	private SuiteModel newSuite;
	
	/**
	 * Constructeur
	 * @param suiteModel Nouveau mod�le
	 */
	public SuiteChangedEvent(SuiteModel suiteModel){
		super(suiteModel);
		this.newSuite = suiteModel;
	}
	
	public SuiteModel getNewSuite(){
		return newSuite;
	}

}
