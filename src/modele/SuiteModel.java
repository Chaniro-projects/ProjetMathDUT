package modele;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

/**
 * Mod�le du design pattern MVC
 * @author Bastien Baret, Thibault Dassonvill�, Damien Bidaud
 * @version 1.0
 */
public class SuiteModel {

	/**
	 * Suite actuelle
	 */
	private Suite suite;
	
	/**
	 * Liste des vues � l'�coute de ce mod�le
	 */
	private EventListenerList listeners;
	
	/**
	 * Constructeur
	 * @param s Initialise la suite
	 */
	public SuiteModel(Suite s) {
		listeners = new EventListenerList();
		suite = s;
	}

	public Suite getSuite() {
		return suite;
	}

	public void setSuite(Suite suite) {
		this.suite = suite;
		fireSuiteChanged();
	}
	
	/**
	 * Ajouter un �couteur du mod�le
	 * @param listener Nouvel �couteur
	 */
	public void addSuiteListener(SuiteListener listener){
		listeners.add(SuiteListener.class, listener);
	}
	
	/**
	 * Supprime un �couteur du mod�le
	 * @param l Ecouteur � supprimer
	 */
	public void removeSuiteListener(SuiteListener l){
		 listeners.remove(SuiteListener.class, l);
	}
	
	/**
	 * Signale auc �couteurs que le mod�le a chang�
	 */
	public void fireSuiteChanged(){
		SuiteListener[] listenerList = (SuiteListener[])listeners.getListeners(SuiteListener.class);
		
		for(SuiteListener listener : listenerList){
			listener.suiteChanged(new SuiteChangedEvent(this));
		}
	}

	public void setHighlight(int i) {
		fireSuiteHightlightChanged(i);
	}

	private void fireSuiteHightlightChanged(int i) {
		SuiteListener[] listenerList = (SuiteListener[])listeners.getListeners(SuiteListener.class);
		
		for(SuiteListener listener : listenerList){
			listener.hightlightChanged(new SuiteHightlightChangedEvent(i));
		}
	}

	public void setRecurrence(ArrayList<Integer> occurence) {
		fireSuiteRecurranceChanged(occurence);
	}

	private void fireSuiteRecurranceChanged(ArrayList<Integer> occurence) {
		SuiteListener[] listenerList = (SuiteListener[])listeners.getListeners(SuiteListener.class);
		
		for(SuiteListener listener : listenerList){
			listener.recurrenceChanged(new SuiteRecurrenceChangedEvent(occurence));
		}
	}

	public void setOption(boolean r) {
		fireOptionChanged(r);
	}

	private void fireOptionChanged(boolean r) {
		SuiteListener[] listenerList = (SuiteListener[])listeners.getListeners(SuiteListener.class);
		
		for(SuiteListener listener : listenerList){
			listener.optionChanged(new SuiteOptionChangedEvent(r));
		}
	}
}
