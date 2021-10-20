package bitoperatoren;

public class DatumKompaktDemo {

	public static void main(String[] args) {
		DatumKompakt datum = new DatumKompakt(15, 3, 2021);
		System.out.println(datum/*.toString()*/);
		
		datum = new DatumKompakt(29, 11, 2099);
		System.out.println(datum/*.toString()*/);
		System.out.println("-----");
		System.out.println(datum.toString());
		
		// 1100011101111101

	}

}
