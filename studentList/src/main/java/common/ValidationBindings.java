package common;

import java.text.NumberFormat;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableBooleanValue;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;

public class ValidationBindings {

	private static final NumberFormat numberFormat = NumberFormat.getInstance();
	static {
		numberFormat.setMinimumFractionDigits(0);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setGroupingUsed(false);
	}

	public static final NumberFormat getNumberFormat() {
		return numberFormat;
	}

	public static BooleanBinding requiredBinding(TextInputControl textField, String name) {
		BooleanBinding binding = Bindings.createBooleanBinding(
				// die Callable-Implementierung für das Binding:
				// liefert true, wenn das Textfeld nicht leer ist
				() -> textField.getText() != null && !textField.getText().isEmpty(),
				// die Abhängigkeiten für das Binding:
				// das Binding ist vom Textfeld abhängig
				textField.textProperty());
		addRequiredValidation(textField, binding, name);
		return binding;
	}

	// validation binding for a required Datepicker field
	public static BooleanBinding requiredBinding(DatePicker datePicker, String name) {

		BooleanBinding binding = Bindings.createBooleanBinding(() -> datePicker.getValue() != null,
				// das Binding ist von der value Property abhängig
				datePicker.valueProperty());
		addRequiredValidation(datePicker, binding, name);
		return binding;
	}

	public static <T> ObservableBooleanValue requiredBinding(ComboBoxBase<T> comboBox, String name) {
		BooleanBinding binding = Bindings.createBooleanBinding(() -> comboBox.getValue() != null,
				// das Binding ist von der value Property abhängig
				comboBox.valueProperty());
		addRequiredValidation(comboBox, binding, name);
		return binding;
	}

	public static <T> ObservableBooleanValue requiredBinding(ToggleGroup toggleGroup, Parent parent) {
		BooleanBinding binding = Bindings.createBooleanBinding(() -> toggleGroup.getSelectedToggle() != null,
				// das Binding ist von der selectedToggle Property abhängig
				// bind to the textfield's text property
				toggleGroup.selectedToggleProperty());
		addValidation(parent, binding);
		return binding;
	}

	public static BooleanBinding integerInRange(TextInputControl textField, String name, int min, int max) {
		BooleanBinding binding = Bindings.createBooleanBinding(
				// liefert true, wenn der Text ein Integer im bereich von min und max ist
				() -> {
					try {
						String value = textField.getText();
						if (value != null && !value.isEmpty()) {
							int number = numberFormat.parse(value).intValue();
							return number >= min && number <= max;
						}
					} catch (Exception ex) {
						System.out.println("Error parsing string to int: " + ex.toString());

					}
					return false;
				},
				// die Abhängigkeiten für das Binding:
				// das Binding ist vom Textfeld abhängig
				textField.textProperty());
		addRequiredValidation(textField, binding, name);
		return binding;
	}

	// steuert für einen Knoten die validation-error Styles, in Abhängigkeit vom
	// angegebenen BooleanBinding. Liefert das Binding zurück, damit die Calls
	// verkettet werden können
	public static BooleanBinding addValidation(Node node, BooleanBinding binding) {

		// einen Listener für die Bedingung hinzufügen
		binding.addListener((o, oldVal, newVal) -> {
			node.pseudoClassStateChanged(
					PseudoClass.getPseudoClass("validation-error"), !newVal);
		});

		// falls es jetzt ungültig ist -> die Pseudo-Class gleich hinzufügen
		if (!binding.get()) {
			node.pseudoClassStateChanged(
					PseudoClass.getPseudoClass("validation-error"), true);
		}
		return binding;
	}

	// steuert für ein Control die validation-error Styles, in Anhängigkeit vom
	// angegebenen BooleanBinding. wenn das Control keinen Tooltip enthält, wird ein
	// allgemeiner Tooltip hinzugefügt
	public static BooleanBinding addValidation(Control ctrl, BooleanBinding binding, String tooltip) {
		if (ctrl.getTooltip() == null) {
			ctrl.setTooltip(new Tooltip(tooltip));
		}
		return addValidation(ctrl, binding);

	}

	// steuert für ein Control die validation-error Styles, in Anhängigkeit vom
	// angegebenen BooleanBinding. wenn das Control keinen Tooltip enthält, wird ein
	// allgemeiner Tooltip hinzugefügt
	public static BooleanBinding addRequiredValidation(Control ctrl, BooleanBinding binding, String name) {
		if (ctrl.getTooltip() == null) {
			ctrl.setTooltip(new Tooltip("Das Feld " + name + "ist erforderlich"));
		}
		return addValidation(ctrl, binding);

	}
//
//
//	public static boolean isValidInteger(String value, int min, int max) {
//		try {
//			if (value == null || value.isEmpty()) {
//				return false;
//			} else {
//				int number = numberFormat.parse(value).intValue();
//				return number >= min && number <= max;
//			}
//		} catch (Exception ex) {
//			System.out.println("Error parsing string to int: " + ex.toString());
//			return false;
//
//		}
//	}

//	// validation binding for an integer number field with a specified range
//	public static BooleanBinding createIntegerValidationBinding(TextField textField, int min, int max) {
//		BooleanBinding binding = Bindings.createBooleanBinding(
//				() -> isValidInteger(textField.getText(), min, max), textField.textProperty());
//
//		return createValidationBinding(textField, binding);
//	}

//	// brauchen wir in diesem Beispiel nicht
//	public static boolean isValidDouble(String value, double min, double max) {
//		try {
//			if (value == null || value.isEmpty()) {
//				return false;
//			} else {
//
//				double number = numberFormat.parse(value).doubleValue();
//				return number >= min && number <= max;
//			}
//		} catch (Exception ex) {
//			System.out.println("Error parsing string to double: " + ex.toString());
//			return false;
//
//		}
//	}
}
