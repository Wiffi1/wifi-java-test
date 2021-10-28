package objectBox;

// Allgemeine Box, in der jeder beliebige Typ (umgewandelt nach Object)
// abgelegt werden kann
public class ObjectBox {
	private Object value;
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("ObjectBox mit Wert %s", value);
	}
}
