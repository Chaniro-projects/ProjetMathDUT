package modele;

import java.util.EventListener;

/**
 * Interface listener du design pattern MVC
 * @author Bastien Baret, Thibault Dassonvill�, Damien Bidaud
 * @version 1.0
 */
public interface SuiteListener extends EventListener {
	/**
	 * Met � jour la vue
	 * @param event Nouveau mod�le
	 */
	public void suiteChanged(SuiteChangedEvent event);

	/**
	 * Met a jour le point � mettre en �vidence
	 * @param suiteHightlightChangedEvent
	 */
	public void hightlightChanged(SuiteHightlightChangedEvent suiteHightlightChangedEvent);

	/**
	 * Met � jour la r�currence de la suite
	 * @param suiteRecurrenceChangedEvent
	 */
	public void recurrenceChanged(SuiteRecurrenceChangedEvent suiteRecurrenceChangedEvent);

	/**
	 * Met � jour les options d'affichage
	 * @param suiteOptionChangedEvent
	 */
	public void optionChanged(SuiteOptionChangedEvent suiteOptionChangedEvent);
}