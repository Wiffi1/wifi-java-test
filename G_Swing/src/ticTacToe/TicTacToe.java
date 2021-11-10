package ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame {
    // Attribute für alle Controls, die wir in den Callbacks usw. brauchen

    final JButton[][] btnTicToc = new JButton[3][3];
    final JButton btnReset;
//    final JLabel lblStatus;
//    final JLabel lblSpieler;

    private char[][] charTicTac = {
            {'x', 'x', 'x'},
            {'x', 'x', 'x'},
            {'x', 'x', 'x'}
    };

//    JTextField txtName, JLabel lblMessage, JButton[][] btnPlay
    public TicTacToe() {
        super("Erstes Swing Programm");
        setSize(600, 800);

        setLayout(null);

        int startX = 20, startY = 20, abstand = 5;
        final int breite = 70, hoehe = 20;

        // Titelzeile
        JLabel lblTitel = new JLabel("TicTacToe");
        lblTitel.setBounds(startX, startY, 225, 30);
        // Textausrichtung zentriert
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        // Größere Schrift, fett und kursiv
        Font titleFont = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
        lblTitel.setFont(titleFont);
        add(lblTitel);

        startY = startY + 50;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                btnTicToc[x][y] = new JButton("x");
                btnTicToc[x][y].setBounds(50 * x, startY + 50 * y, 50, 50 );
                btnTicToc[x][y].addActionListener(this::onKlickTicTocBtn);
                add(btnTicToc[x][y]);
            }
        }

        startY = startY + 350;
        btnReset = new JButton("Reset");
        btnReset.setBounds(100, startY, 150, 50 );
        btnReset.addActionListener(this::onKlickResetBtn);
        add(btnReset);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        TicTacToe hauptfenster = new TicTacToe();
        // das Hauptfenster anzeigen
        hauptfenster.setVisible(true);
    }

    private void onKlickTicTocBtn(ActionEvent e) {
//        System.out.println("Click auf btnGruesse, name = " + txtName.getText());
//        lblMessage.setText("Hallo, " + txtName.getText());
        System.out.println(e);
    }

    private void onKlickResetBtn(ActionEvent e) {
//        System.out.println("Click auf btnGruesse, name = " + txtName.getText());
//        lblMessage.setText("Hallo, " + txtName.getText());
        System.out.println(e);
    }

/*    private static void initCharTicToc () {

    } */

}
