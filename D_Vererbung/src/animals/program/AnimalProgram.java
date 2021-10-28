package animals.program;


import animals.model.AnimalList;

public class AnimalProgram {

	public static void main(String[] args) {
		
		AnimalList animals = new AnimalList();
		System.out.println("alleTiere");
		animals.showAll();
		System.out.println("Vegetarier");
		//animals.showVegetarian();
		System.out.println("Schwere Tiere");
		//animals.heavyVegetarian();
	}

}


