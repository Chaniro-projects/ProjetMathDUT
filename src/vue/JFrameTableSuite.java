package vue;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import modele.Suite;
import modele.SuiteChangedEvent;
import modele.SuiteHightlightChangedEvent;
import modele.SuiteOptionChangedEvent;
import modele.SuiteRecurrenceChangedEvent;

import controleur.SuiteController;

/**
 * Vue sous forme de tableau de points du modèle
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JFrameTableSuite extends SuiteView{

	private Suite suite;
	
	/**
	 * Tableau
	 */
	private JTable jta;
	
	private JScrollPane jsp;
	SuiteController sc;
	
	/**
	 * Constructeur
	 * @param sc Controlleur
	 * @param suite Suite(modèle) actuelle
	 */
	public JFrameTableSuite(SuiteController sc, Suite suite) {
		super(sc);
		
		this.sc = sc;
		
		this.suite = suite;
		
		jta = creerTable();
		jsp = new JScrollPane(jta);
		
		this.add(jsp);
		
		this.setSize(200, 300);
		this.setLocation(0, 200);
		this.setTitle("Tableau");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		addMouseWheelListener(sc);
		
		sc.searchRecurrence();
	}

	/**
	 * Met a jour la vue (le tableau) lors du changement du modèle
	 */
	public void suiteChanged(SuiteChangedEvent event) {
		suite = event.getNewSuite().getSuite();
		jta = creerTable();
		this.getContentPane().removeAll();
		jsp = new JScrollPane(jta);
		this.getContentPane().add(jsp, BorderLayout.CENTER);
		revalidate();
	}

	/**
	 * Affiche la vue
	 */
	public void display() {
		this.setVisible(true);
	}

	/**
	 * Ferme la vue
	 */
	public void close() {
		this.dispose();
	}
	
	public JTable creerTable() {
		Object[][] data = new Object[suite.getPoints().size()][2];
		for(int i = 0; i<suite.getPoints().size(); i++) {
			data[i][0] = i;
			data[i][1] = suite.getPoints().get(i).getY();
		}
		String[] entetes = {"<html><b>i</b></html>", "<html><b>u(i)</b></html>"};
		
		JTable ret = new JTable(data, entetes);
		
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); 
		for (int i=0 ; i<ret.getColumnCount() ; i++) 
			ret.getColumnModel().getColumn(i).setCellRenderer(custom); 
		ret.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ret.addMouseListener(sc);
		ret.addKeyListener(sc);
		
		return ret;
	}
	

	/**
	 * Met a jour la vue le points en surbrillance
	 */
	@Override
	public void hightlightChanged(SuiteHightlightChangedEvent suiteHightlightChangedEvent) {
		jta.setRowSelectionInterval(suiteHightlightChangedEvent.getNewHightightID(), suiteHightlightChangedEvent.getNewHightightID());
		jsp.getVerticalScrollBar().setValue((int) (((float)suiteHightlightChangedEvent.getNewHightightID()/suite.getPoints().size())*jsp.getVerticalScrollBar().getMaximum()));
	}

	@Override
	public void recurrenceChanged(
			SuiteRecurrenceChangedEvent suiteRecurrenceChangedEvent) {
		
	}

	@Override
	public void optionChanged(
			SuiteOptionChangedEvent suiteOptionChangedEvent) {
		
	}

	public JTable getJta() {
		return jta;
	}

	public void setJta(JTable jta) {
		this.jta = jta;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}
}
