package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modele.Suite;
import modele.SuiteChangedEvent;
import modele.SuiteHightlightChangedEvent;
import modele.SuiteOptionChangedEvent;
import modele.SuiteRecurrenceChangedEvent;
import controleur.SuiteController;

/**
 * Vue controlant le modèle
 * @author Bastien Baret, Thibault Dassonvillé, Damien Bidaud
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JFrameControlSuite extends SuiteView {

	private JPanel pan;
	private Suite suite;
	
	/**
	 * Champs de texte de choix du premier terme u(0)
	 */
	private JTextField txtu0;
	
	/**
	 * Chanmps de texte du choix du nombre de points
	 */
	private JTextField txtNb;
	
	/**
	 * Bouton lancant le tracé
	 */
	private JButton bt;
	
	/**
	 * Bouton info
	 */
	private JButton bt2;
	
	private JCheckBox isRecurrence;
	
	
	/**
	 * Constructeur
	 * @param sc Controlleur
	 * @param suite Suite(modèle) actuelle
	 */
	public JFrameControlSuite(SuiteController sc, Suite suite) {
		super(sc);
		
		pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Configuration"));
		pan.setLayout(new GridLayout(7, 2));
		
		pan.add(new JLabel());
		pan.add(new JLabel());
		
		txtu0 = new JTextField("2/7");
		txtu0.setColumns(7);
		pan.add(new JLabel("u(0) = "));
		pan.add(txtu0);
		
		pan.add(new JLabel());
		pan.add(new JLabel());
		
		JLabel t1 = new JLabel("Termes : ");
		pan.add(t1);
		txtNb = new JTextField("10");
		txtNb.setColumns(7);
		pan.add(txtNb);
		
		pan.add(new JLabel());
		pan.add(new JLabel());
		
		pan.add(new JLabel("Cycles: "));
		isRecurrence = new JCheckBox();
		isRecurrence.addActionListener(sc);
		pan.add(isRecurrence);
		
		pan.add(new JLabel());
		pan.add(new JLabel());
		
		bt = new JButton("Tracer");
		bt.addActionListener(sc);
		JPanel pb = new JPanel();
		pb.add(bt);
		
		bt2 = new JButton("Informations");
		bt2.addActionListener(sc);
		pb.add(bt2);
		this.add(pb, BorderLayout.SOUTH);
		this.add(pan, BorderLayout.CENTER);
		
		this.setSize(200, 200);
		this.setLocation(0, 0);
		this.setTitle("Options");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.suite = suite;
		
		addMouseWheelListener(sc);
		txtu0.addKeyListener(sc);
		txtNb.addKeyListener(sc);
		addKeyListener(sc);
	}

	/**
	 * Met a jour la vue (les champs de saisie) lors du changement du modèle
	 */
	public void suiteChanged(SuiteChangedEvent event) {
		suite = event.getNewSuite().getSuite();
		txtNb.setText(suite.getPoints().size()+"");
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
	

	public JTextField getTxtu0() {
		return txtu0;
	}

	public void setTxtu0(JTextField txtu0) {
		this.txtu0 = txtu0;
	}

	public JTextField getTxtNb() {
		return txtNb;
	}

	public void setTxtNb(JTextField txtNb) {
		this.txtNb = txtNb;
	}

	public JButton getBt() {
		return bt;
	}

	public void setBt(JButton bt) {
		this.bt = bt;
	}

	public JCheckBox getIsRecurrence() {
		return isRecurrence;
	}

	public void setIsRecurrence(JCheckBox isRecurrence) {
		this.isRecurrence = isRecurrence;
	}

	@Override
	public void hightlightChanged(
			SuiteHightlightChangedEvent suiteHightlightChangedEvent) {
		
	}

	@Override
	public void recurrenceChanged(
			SuiteRecurrenceChangedEvent suiteRecurrenceChangedEvent) {
		
	}

	@Override
	public void optionChanged(
			SuiteOptionChangedEvent suiteOptionChangedEvent) {
		
	}

	public JButton getBt2() {
		return bt2;
	}
}
