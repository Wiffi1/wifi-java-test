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

    private int clickedFieldCount = 0;

    private char spieler1 = 'x';
    private char spieler2 = '0';
    private char spieler = spieler1;
    private char gewinner = ' ';

    private char[][] charTicTac = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public TicTacToe() {
//        super("Erstes Swing Programm");
        setSize(600, 600);

        setLayout(null);

        int startX = 20, startY = 20, abstand = 5;
//        final int breite = 70, hoehe = 20;
        final int zeilenHoehe = 50;
        final int startXspielfeld = 150;

        // Titelzeile
        JLabel lblTitel = new JLabel("TicTacToe");
        lblTitel.setBounds(startX, startY, 225, 30);
        // Textausrichtung zentriert
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        // Größere Schrift, fett und kursiv
        Font titleFont = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
        lblTitel.setFont(titleFont);
        add(lblTitel);

        startY = startY + zeilenHoehe;

        for (int s = 0; s < 3; s++) {
            for (int z = 0; z < 3; z++) {
                btnTicToc[z][s] = new JButton(String.valueOf(charTicTac[z][s]));
                btnTicToc[z][s].setBounds(startXspielfeld + s * zeilenHoehe, startY + z * zeilenHoehe, zeilenHoehe, zeilenHoehe );
                btnTicToc[z][s].addActionListener(this::onKlickTicTocBtn);
                btnTicToc[z][s].setActionCommand(String.valueOf(z*3 + s));
                add(btnTicToc[z][s]);
            }
        }

        startY = startY + 180;

        lblSpieler = new JLabel("Spieler");
        lblSpieler.setBounds(70, startY, 225, 30);
        // Textausrichtung zentriert
        lblSpieler.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblSpieler);

        startY = startY + 50;

        btnReset = new JButton("Reset");
        btnReset.setBounds(startXspielfeld, startY, 150, zeilenHoehe );
        btnReset.addActionListener(this::onKlickResetBtn);
        add(btnReset);

        startY = startY + 70;

        lblStatus = new JLabel("Status1");
        lblStatus.setBounds(startX, startY, 400, 30);
        // Textausrichtung zentriert
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        // Größere Schrift, fett und kursiv
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
        lblStatus.setFont(font);
        add(lblStatus);

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
        int z = clickedBtnId / 3;

        System.out.printf("clickedFieldCount %d", clickedFieldCount);
        if (clickedFieldCount >= 9) {
            // do nothing das Spiel ist aus
        } else if (gewinner == ' ') {
            if (charTicTac[z][s] == spieler1 || charTicTac[z][s] == spieler2) {
                lblStatus.setText("Status: Fehler");
            } else {
                spieler = (spieler == spieler1) ? spieler2 : spieler1;
                charTicTac[z][s] = spieler;
                btnTicToc[z][s].setText(String.valueOf(spieler));
                setLblSpieler(spieler);
                lblStatus.setText("Status");

                ++clickedFieldCount;

                if (hatSpielerGewonnen(spieler1)) {
                    gewinner = spieler1;
                    lblStatus.setText(String.format("SPIELER %s HAT GEWONNEN!! ", spieler1));
                    setSpielfeldEnabled(false);
                } else if (hatSpielerGewonnen(spieler2)) {
                    gewinner = spieler2;
                    lblStatus.setText(String.format("SPIELER %s HAT GEWONNEN!! ", spieler2));
                    setSpielfeldEnabled(false);
                } else if (clickedFieldCount >= 9) {
                    lblStatus.setText(String.format("UNENTSCHIEDEN, es ist kein Zug mehr möglich", spieler1));
                    setSpielfeldEnabled(false);
                }

            }
        }
    }

    private void onKlickResetBtn(ActionEvent e) {
        for (int z = 0; z < 3; z++) {
            for (int s = 0; s < 3; s++) {
                charTicTac[z][s] = ' ';
                btnTicToc[z][s].setText(" ");
            }
        }
        gewinner = ' ';
        clickedFieldCount = 0;
        setSpielfeldEnabled(true);
        System.out.println(e);
    }

    private boolean hatSpielerGewonnen(char player) {
        boolean gewonnen = false;
        for (int z = 0; z < 3; z++) {
            if (charTicTac[z][0] == player && charTicTac[z][1] == player && charTicTac[z][2] == player) {
                gewonnen = true;
            }
        }
        for (int s = 0; s < 3; s++) {
            if (charTicTac[0][s] == player && charTicTac[1][s] == player && charTicTac[2][s] == player) {
                gewonnen = true;
            }
        }

        if (charTicTac[0][0] == player && charTicTac[1][1] == player && charTicTac[2][2] == player) {
            gewonnen = true;
        } else if (charTicTac[0][2] == player && charTicTac[1][1] == player && charTicTac[2][0] == player) {
            gewonnen = true;
        }
        return gewonnen;
    }

    private void setSpielfeldEnabled(boolean b) {
        for (int z = 0; z < 3; z++) {
            for (int s = 0; s < 3; s++) {
                btnTicToc[z][s].setEnabled(b);
            }
        }
    }

    private void setLblSpieler(char spieler) {
        lblSpieler.setText("Spieler: " + spieler);
    }
}
