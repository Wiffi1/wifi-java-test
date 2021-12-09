package animalsStream;

import java.util.Arrays;

/**
 * Klasse für ein (Zoo-)Tier mit mehreren Eigenschaften wie Name, Lateinischer
 * Name oder Gewicht
 * 
 * @author Michaela
 *
 */
public class Animal implements Comparable<Animal> {
	private String name, latinName;

	private int weight;

	private boolean herbivore;

	public Animal(String name, String lateinischerName, int gewicht, boolean pflanzenfresser) {
		this.name = name;
		this.latinName = lateinischerName;
		this.weight = gewicht;
		this.herbivore = pflanzenfresser;
	}

	public String getName() {
		return name;
	}

	public String getLatinName() {
		return latinName;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isHerbivore() {
		return herbivore;
	}

	@Override
	public String toString() {
		return String.format("%s, %s - %d kg - Vegetarier: %s",
				name, latinName, weight, herbivore ? "ja" : "nein");
	}

	// Standardsortierreihenfolge (Natürliche Sortierreihenfolge) für Animal-Object
	@Override
	public int compareTo(Animal o) {
		// nach Name sortieren -> den Vergleich an dien name-Eigenschaft delegieren
//		return this.getName().compareTo(o.getName());
		int ret = this.getName().compareTo(o.getName());
		System.out.printf("compareTo  %s mit %s: ret=%d\n", this.getName(), o.getName(), ret);
		return ret;
	}
}
