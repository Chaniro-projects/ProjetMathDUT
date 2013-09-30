package vue;

import controleur.SuiteController;

import modele.Suite;
import modele.SuiteChangedEvent;
import modele.SuiteHightlightChangedEvent;
import modele.SuiteOptionChangedEvent;
import modele.SuiteRecurrenceChangedEvent;

/**
 * Vue sous forme graphique du modèle
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */

@SuppressWarnings("serial")
public class JFrameGraphiqueSuite extends SuiteView{

	/**
	 * JPanel gérant le graphique
	 */
	private PanelGraphique pg;
	
	
	/**
	 * Constructeur
	 * @param sc Controlleur
	 * @param suite Suite actuelle
	 */
	public JFrameGraphiqueSuite(SuiteController sc, Suite suite) {
		super(sc);
		
		this.setSize(500, 350);
		this.setLocation(800, 0);
		this.setTitle("Suites récurrentes");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		pg = new PanelGraphique(suite, sc);
		addKeyListener(sc);
		this.add(pg);
	}

	/**
	 * Met a jour le graphique lors du changement du modèle
	 */
	public void suiteChanged(SuiteChangedEvent event) {
		pg.update(event.getNewSuite().getSuite());
	}

	/**
	 * Affiche la vue
	 */
	public void display() {
		this.setVisible(true);
		pg.updateZone();
		pg.updatePoints();
	}

	/**
	 * Ferme la vue
	 */
	public void close() {
		this.dispose();
	}
	

	@Override
	public void hightlightChanged(SuiteHightlightChangedEvent suiteHightlightChangedEvent) {
		pg.setHighlight(suiteHightlightChangedEvent.getNewHightightID());
		
	}

	@Override
	public void recurrenceChanged(
			SuiteRecurrenceChangedEvent suiteRecurrenceChangedEvent) {
		pg.setRecurrence(suiteRecurrenceChangedEvent.getNewRecurrence());
		
	}

	@Override
	public void optionChanged(SuiteOptionChangedEvent suiteOptionChangedEvent) {
		pg.setOption(suiteOptionChangedEvent);
		
	}

	public PanelGraphique getPg() {
		return pg;
	}

	public void setPg(PanelGraphique pg) {
		this.pg = pg;
	}
	
}
