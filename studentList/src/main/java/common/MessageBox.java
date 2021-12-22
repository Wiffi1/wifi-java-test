package common;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MessageBox {

    public static ButtonType show(String title, String content) {
        return show(title, content, AlertType.INFORMATION, ButtonType.OK);
    }

    public static ButtonType show(String title, Exception error) {
        String msg = error.getMessage();
        for (Throwable cause = error.getCause(); cause != null; cause = cause.getCause()) {
            msg += "\n\t" + cause.getMessage();
        }
        return show(title, msg, AlertType.ERROR, ButtonType.OK);
    }

    public static ButtonType show(String title, String content, AlertType type, ButtonType... buttons) {

        Alert msg = new Alert(type, content, buttons);
        msg.setAlertType(type);
        msg.setHeaderText("");
        msg.setTitle(title);
        msg.setContentText(content);
        msg.setResizable(true);
        return msg.showAndWait().orElse(ButtonType.CANCEL);

    }

}
