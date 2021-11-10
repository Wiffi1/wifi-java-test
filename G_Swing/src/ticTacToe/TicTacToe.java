package ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame {
    // Attribute für alle Controls, die wir in den Callbacks usw. brauchen

    final JButton[][] btnTicToc = new JButton[3][3];
    final JButton btnReset;
    final JLabel lblStatus;
    final JLabel lblSpieler;

    private char[][] charTicTac = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

//    JTextField txtName, JLabel lblMessage, JButton[][] btnPlay
    public TicTacToe() {
        super("Erstes Swing Programm");
        setSize(600, 600);

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

        for (int s = 0; s < 3; s++) {
            for (int z = 0; z < 3; z++) {
                btnTicToc[z][s] = new JButton(String.valueOf(charTicTac[z][s]));
                btnTicToc[z][s].setBounds(50 * s, startY + 50 * z, 50, 50 );
                btnTicToc[z][s].addActionListener(this::onKlickTicTocBtn);
                btnTicToc[z][s].setActionCommand(String.valueOf(z*3 + s));
                add(btnTicToc[z][s]);
            }
        }

        startY = startY + 350;
        btnReset = new JButton("Reset");
        btnReset.setBounds(100, startY, 150, 50 );
        btnReset.addActionListener(this::onKlickResetBtn);
        add(btnReset);

        startY = startY + 50;

        lblStatus = new JLabel("Status");
        lblStatus.setBounds(startX, startY, 225, 30);
        // Textausrichtung zentriert
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        // Größere Schrift, fett und kursiv
//        Font titleFont = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
//        lblTitel.setFont(titleFont);
        add(lblStatus);

        startY = startY + 50;

        lblSpieler = new JLabel("Spieler");
        lblSpieler.setBounds(startX, startY, 225, 30);
        // Textausrichtung zentriert
        lblSpieler.setHorizontalAlignment(SwingConstants.CENTER);
        // Größere Schrift, fett und kursiv
        Font spielerFont = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
        lblSpieler.setFont(spielerFont);
        add(lblSpieler);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        TicTacToe hauptfenster = new TicTacToe();
        // das Hauptfenster anzeigen
        hauptfenster.setVisible(true);
    }

    private void onKlickTicTocBtn(ActionEvent e) {
        String cmdStr = e.getActionCommand();
        int clickedBtnId = Integer.parseInt(cmdStr);
        int s = clickedBtnId % 3;
        int z = (clickedBtnId - s) / 3;
        System.out.printf("clicked: %d \n ", clickedBtnId);
        System.out.printf("s: %d\n ", s);
        System.out.printf("z: %d\n ", z);
        System.out.printf("z=%d s=%d \n ", z, s);

        if (charTicTac[z][s] == 'o') {
            lblStatus.setText("Status: Fehler");
        } else {
            charTicTac[z][s] = 'o';
            btnTicToc[z][s].setText("o");
            lblStatus.setText("Status");
        }

    }

    private void onKlickResetBtn(ActionEvent e) {
        for (int z = 0; z < 3; z++) {
            for (int s = 0; s < 3; s++) {
                charTicTac[z][s] = ' ';
                btnTicToc[z][s].setText(" ");
            }
        }
        System.out.println(e);
    }


}
