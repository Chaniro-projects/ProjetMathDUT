package modele;

import java.util.EventListener;

/**
 * Interface listener du design pattern MVC
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
public interface SuiteListener extends EventListener {
	/**
	 * Met à jour la vue
	 * @param event Nouveau modèle
	 */
	public void suiteChanged(SuiteChangedEvent event);

	/**
	 * Met a jour le point à mettre en évidence
	 * @param suiteHightlightChangedEvent
	 */
	public void hightlightChanged(SuiteHightlightChangedEvent suiteHightlightChangedEvent);

	/**
	 * Met à jour la récurrence de la suite
	 * @param suiteRecurrenceChangedEvent
	 */
	public void recurrenceChanged(SuiteRecurrenceChangedEvent suiteRecurrenceChangedEvent);

	/**
	 * Met à jour les options d'affichage
	 * @param suiteOptionChangedEvent
	 */
	public void optionChanged(SuiteOptionChangedEvent suiteOptionChangedEvent);
}