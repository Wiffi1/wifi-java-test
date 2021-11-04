package introPackage;

import javax.swing.*;
import java.awt.*;

// Klasse für unser Hauptfenster
public class HelloSwing extends JFrame {

    public HelloSwing() {
        super("Erstes Swing Programm");
        setSize(300, 400);

        // Kein automatisches Layout verwenden, da wir müssen aboluste
        // Positionen und Größen angeben
        setLayout(null);
        int startX = 20, startY = 20, abstand = 5;
        final int breite = 70, hoehe = 20;

        // Titelzeile
        JLabel lbTitel = new JLabel("Hallo bei Swing");
        lbTitel.setBounds(startX, startY, 225, 30);
        lbTitel.setHorizontalAlignment(SwingConstants.CENTER);
        // Größere Schrift
        Font titleFont = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
        lbTitel.setFont(titleFont);
        add(lbTitel);

        // 1. Zeile: Name
        startY += hoehe * 2;
        // Beschriftungsfeld
        JLabel lbName = new JLabel("Dein Name:");
        lbName.setSize(breite, 20);
        lbName.setLocation(startX, startY);
        add(lbName);

        JTextField txtName = new JTextField();
        txtName.setBounds(startX + abstand + breite, startY, 100, hoehe);
        add(txtName);

        // 2. Zeile: Button
        JButton btnGruesse = new JButton("Klick mich!");
        startY += + abstand + hoehe;
        btnGruesse.setBounds(startX + abstand + breite, startY, 100, 20);

        add(btnGruesse);

        // 3. Feld für Message
        startY += + abstand + hoehe;
        JLabel lbMessage = new JLabel("");
        lbMessage.setSize(breite, 20);
        lbMessage.setLocation(startX, startY);
        lbMessage.setBounds(startX, startY, 200, hoehe);
        lbMessage.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        add(lbMessage);

        // Callback für das Klick-Ereignis
        btnGruesse.addActionListener(e -> {
            System.out.println("Click auf btngrüße. name = " + txtName.getText());
            lbMessage.setText("Hallo, " + txtName.getText());
        });

        // Standard-Operation  ist Verbergen
        // stattdessen das Fenster beim Schließen zerstöre
        // damit wird das Programm bbeendet, wenn dieses Fenster das letzte
        // Fenster der Anwendung ist
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        HelloSwing hauptfenster = new HelloSwing();
        hauptfenster.setVisible(true);
    }

}
