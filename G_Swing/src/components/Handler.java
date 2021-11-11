package components;


import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Handler implements ActionListener, ItemListener, DocumentListener {

	private TeilnehmerFenster meinFenster;

	public Handler(TeilnehmerFenster fenster) {
		this.meinFenster = fenster;

	}

	// Handler-Code für das Action-Event der beiden Buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "OK" -> onOK();
			case "CANCEL" -> onCancel();
			default -> System.out.println("Ungültiges Acrioncomman: " + e.getActionCommand());
		}

	}

	private void onOK() {
		System.out.println("OK Button ausgelöst");
		// ein Teilnehmer-Objekt erzeugen und mit den eingegebenen Werten initialisieren
		Teilnehmer tnNeu = new Teilnehmer();
		tnNeu.setZuname(meinFenster.txtZuname.getText());
		tnNeu.setVorname(meinFenster.txtVorname.getText());
		tnNeu.setStrasse(meinFenster.txtStrasse.getText());
		tnNeu.setPlz(meinFenster.txtPLZ.getText());
		tnNeu.setOrt(meinFenster.txtOrt.getText());

		// TODO weitere Werte


		String info = tnNeu.toString();
		System.out.println("Daten erfasst: ");
		System.out.println(info);
		
		// TODO in Message box anzeigen
	}

	private void onCancel() {
		System.out.println("Cancel Button ausgelöst");
		// alle Eingaben löschen
		meinFenster.txtZuname.setText("");
		meinFenster.txtVorname.setText("");
		meinFenster.txtPLZ.setText("");
		meinFenster.txtOrt.setText("");
		meinFenster.txtStrasse.setText("");
		
		// Default-radio-Button selektieren, der anderen wird automatisch de-selektiert 
		meinFenster.rbMann.setSelected(true);
		
		meinFenster.cbWindows.setSelected(false);
		meinFenster.cbUnix.setSelected(false);
		meinFenster.cbProgrammierung.setSelected(false);
		
		meinFenster.lbWindowsVersionen.clearSelection();
		meinFenster.lbUnixVersionen.clearSelection();
		
		meinFenster.taVorkenntnisse.setText("");
	}


	private void checkValid() {
		boolean valid = !meinFenster.txtVorname.getText().isEmpty() && !meinFenster.txtZuname.getText().isEmpty()
				&& !meinFenster.txtPLZ.getText().isEmpty() && !meinFenster.txtOrt.getText().isEmpty()
				&& !meinFenster.txtStrasse.getText().isEmpty();

		System.out.println("checkValid: gültig=" + valid);

		// TODO: je nach Gültigkeit enablen oder disablen

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("ItemEvent: neuer Status " + e);
		boolean istSelektiert
	}

	@Override
	public void insertUpdate(DocumentEvent e) {

	}

	@Override
	public void removeUpdate(DocumentEvent e) {

	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}
}
