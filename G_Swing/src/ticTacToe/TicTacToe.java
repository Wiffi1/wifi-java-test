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
        setSize(500, 500);

        setLayout(null);

        int startX = 0, startY = 20;
        final int zeilenHoehe = 50;
        final int startXspielfeld = 166;

        // Titelzeile
        JLabel lblTitel = new JLabel("TicTacToe");
        lblTitel.setBounds(0, startY, 500, 30);
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
        lblSpieler.setHorizontalAlignment(SwingConstants.CENTER);
        Font spielerFont = new Font("Arial", Font.BOLD | Font.ITALIC, 16);
        lblSpieler.setFont(spielerFont);
        lblSpieler.setBounds(startXspielfeld, startY, 150, 30);
        add(lblSpieler);

        startY = startY + 50;

        btnReset = new JButton("Reset");
        btnReset.setBounds(startXspielfeld, startY, 150, zeilenHoehe );
        btnReset.addActionListener(this::onKlickResetBtn);
        add(btnReset);

        startY = startY + 70;

        lblStatus = new JLabel("");
        lblStatus.setBounds(startX, startY, 500, 30);
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

//        System.out.printf("clickedFieldCount %d", clickedFieldCount);
        if (clickedFieldCount >= 9) {
            // do nothing das Spiel ist aus
        } else if (gewinner == ' ') {
            if (charTicTac[z][s] == spieler1 || charTicTac[z][s] == spieler2) {
                lblStatus.setText("Dieser Zug ist nicht möglich!");
            } else {
                spieler = (spieler == spieler1) ? spieler2 : spieler1;
                charTicTac[z][s] = spieler;
                btnTicToc[z][s].setText(String.valueOf(spieler));
                setLblSpieler(spieler);
                lblStatus.setText("");

                ++clickedFieldCount;

                if (hatSpielerGewonnen(spieler1)) {
                    gewinner = spieler1;
                    lblStatus.setText(String.format("Spieler %s hat gewonnen! ", spieler1));
                    setSpielfeldEnabled(false);
                } else if (hatSpielerGewonnen(spieler2)) {
                    gewinner = spieler2;
                    lblStatus.setText(String.format("Spieler %s hat gewonnen! ", spieler2));
                    setSpielfeldEnabled(false);
                } else if (clickedFieldCount >= 9) {
                    lblStatus.setText("Unentschieden, kein Zug mehr möglich!");
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
        lblStatus.setText("");
//        System.out.println(e);
    }

    private boolean hatSpielerGewonnen(char player) {
        boolean gewonnen = false;
        for (int z = 0; z < 3; z++) {
            if (charTicTac[z][0] == player && charTicTac[z][1] == player && charTicTac[z][2] == player) {
                gewonnen = true;
                break;
            }
        }
        for (int s = 0; s < 3; s++) {
            if (charTicTac[0][s] == player && charTicTac[1][s] == player && charTicTac[2][s] == player) {
                gewonnen = true;
                break;
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
