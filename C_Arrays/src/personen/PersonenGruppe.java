package personen;

/**
 * Diese Klasse kapselt eine Gruppe von Person-Objekten in einem Array
 * 
 * @author Michaela
 *
 */
public class PersonenGruppe {

	// Array für die Personen
	private	Person[] gruppe;
	private int anzahl;

	public PersonenGruppe(int maxAnzahl) {
		gruppe = new Person[maxAnzahl];
	}

	/**
	 * Eine Person anmelden. Die Person wird im Array am nächsten freien Platz
	 * hinzugefügt
	 * 
	 * @param p die Person, die angemeldet werden soll
	 */
	public void anmelden(Person p) {
		if (anzahl == gruppe.length) {
			throw new IllegalStateException("Die Gruppe ist voll!");
		}

		// sonst die Person eintragen
//		gruppe[anzahl] = p;
//		anzahl++;
		gruppe[anzahl++] = p;
	}

	/**
	 * alle Personen anzeigen
	 */
	public void alleAnzeigen() {
		System.out.printf("In der Gruppe sind %d Personen: \n", anzahl);
		// nur so viele Personen anzeigen, wie angemeldet sind.
		for (int i = 0; i < anzahl; i++) {
			System.out.printf("\t%s\n", gruppe[i]);
		}
	}

	/**
	 * eine Person anzeigen
	 * @param nr
	 */
	public void anzeigen(int nr) {
		int index = findePersonIndex(nr);
		if (index < 0) {
			System.out.printf("Person mit nummer %d existiert nicht\n", nr);
		} else {
			Person tmpPerson = gruppe[index];
			System.out.printf("Person gefunden %s\n", tmpPerson);
		}
	}
	
	/**
	 * eine Person abmelden. Die Person wird aus dem Array entfernt. Dahinterliegede
	 * Objekte werden nach vorne verschoben
	 * 
	 * @param nr die Nummer Person die abgemeldet wird
	 * @return das Person-Objekt
	 */
	public Person abmelden(int nr) {
		int index = findePersonIndex(nr);

		if (index < 0) {
			throw new IllegalArgumentException("Person mit Nummer " + nr + " existiert nicht.");
		}
		Person geloescht = gruppe[index];
		// Die im Array dahinterliegenden Referenzen um einen Platz nach vorne verschieben.
		for (int i = index; i < anzahl - 1; i++) {
			gruppe[i] = gruppe[i+1];
		}

		// Anzahl vermindern und das nicht mehr benötigte Element auf null setzen
//		anzahl--;
//		gruppe[anzahl] = null;

		// Anzahl vermindern und das nicht mehr benötigte Element auf null setzen
		gruppe[--anzahl] = null;
		return geloescht;
	}
	// Den Index der Person im Array suchen
	private int findePersonIndex(int nr) {
		for (int i = 0; i < anzahl; i++) {
			// wenn das Objekt die gesuchte Nummer hat,
			if (gruppe[i].getNr() == nr) {
				return i;
			}
		}
		// Wenn wir die Nummer nicht gefunden haben, einen ungültigen Index zurückliefern.
		return -1;
	}
}
