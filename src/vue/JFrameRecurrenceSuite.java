package vue;

import modele.Suite;
import modele.SuiteChangedEvent;
import modele.SuiteHightlightChangedEvent;
import modele.SuiteOptionChangedEvent;
import modele.SuiteRecurrenceChangedEvent;
import controleur.SuiteController;

/**
 * Vue affichant la récurrence âs à pas
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JFrameRecurrenceSuite extends SuiteView{

	/**
	 * JPanel d'affichage
	 */
	private PanelRecurrence pg;
	
	/**
	 * Constructeur
	 * @param sc Controleur
	 * @param suite Suite
	 */
	public JFrameRecurrenceSuite(SuiteController sc, Suite suite) {
		super(sc);
		
		this.setSize(600, 500);
		this.setLocation(200, 0);
		this.setTitle("Suites récurrentes");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pg = new PanelRecurrence(suite, sc);
		addKeyListener(sc);
		this.add(pg);
	}

	/**
	 * Met a jour la vue (les champs de saisie) lors du changement du modèle
	 */
	public void suiteChanged(SuiteChangedEvent event) {
		pg.update(event.getNewSuite().getSuite());
	}

	/**
	 * Met a jour la vue avec le nouveau point à mettre en évidence
	 */
	public void hightlightChanged(
			SuiteHightlightChangedEvent suiteHightlightChangedEvent) {}

	/**
	 * Met à jour la récurrence
	 */
	public void recurrenceChanged(
			SuiteRecurrenceChangedEvent suiteRecurrenceChangedEvent) {
		pg.setRecurrence(suiteRecurrenceChangedEvent.getNewRecurrence());
	}

	/**
	 * Affiche la vue
	 */
	public void display() {
		setVisible(true);
	}

	/**
	 * Ferme la vue
	 */
	public void close() {
		dispose();
	}

	/**
	 * Met à jour les options d'affichage
	 */
	public void optionChanged(
			SuiteOptionChangedEvent suiteOptionChangedEvent) {
		
	}

	public PanelRecurrence getPg() {
		return pg;
	}

	public void setPg(PanelRecurrence pg) {
		this.pg = pg;
	}

}
