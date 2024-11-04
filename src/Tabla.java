import javax.swing.*;
import java.awt.*;

public class Tabla {

    public void inic()
    {
        JFrame frame = new JFrame("asd");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null); // Ablak középre helyezése

        // Panel létrehozása az elemekhez
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,8,0,0));
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();

                // Színek beállítása
                if ((row + col) % 2 == 0) {
                    square.setBackground(Color.WHITE); // Világos mező
                } else {
                    square.setBackground(Color.GRAY); // Sötét mező
                }

                // Gombok letiltása, hogy ne kattinthatók legyenek (csak megjelenítésre)
                //square.setEnabled(false);
                square.setFocusPainted(false); // Fókusz keret kikapcsolása
                square.setBorderPainted(false); // Szegély kikapcsolása
                square.setContentAreaFilled(true); // Kitöltés megtartása, de nyomás effekt nélkül

                // Gomb hozzáadása a panelhez
                panel.add(square);
            }
        }
        frame.add(panel);

        // Keret megjelenítése
        frame.setVisible(true);
        //frame.pack();
        // Panel létrehozása az elemekhez

    }

}
