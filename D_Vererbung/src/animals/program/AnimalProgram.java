package animals.program;


import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram {

	public static void main(String[] args) {
		
		AnimalList animals = new AnimalList();
		System.out.println("alleTiere");
		animals.showAll();
		System.out.println("Vegetarier");
		//animals.showVegetarian();
		HerbivoreFilter filter1 = new HerbivoreFilter();
		// Implizite Umwandlaung von HerbivoreFilter nach Animalfilter
		// (dh. Umwandlung von der immplementierenden Klasse nach Bsisinterface)
		animals.showAnimals(filter1);

		System.out.println("Schwere Tiere");
		//animals.heavyVegetarian();

		// schwere Tiere >= 200 kg
		// implizite Umwandlung von HeavyAnimalFilter nach Animalfilter
		AnimalFilter filter2 = new HeavyAnimalFilter(200);
		animals.showAnimals(filter2);

		// schwere Tiere >= 500 kg
		// implizite Umwandlung von HeavyAnimalFilter nach Animalfilter
		System.out.println("Schwere Tiere (>= 500");
		animals.showAnimals(new HeavyAnimalFilter(500));

	}

}


