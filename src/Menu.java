import sakktabla.Tabla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JFrame frame = new JFrame("Fomenu");

    public Menu() {
        setupUI();
    }

    public void setupUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JButton startButton = new JButton("Játék indítása");
        JButton button2 = new JButton("Menüpont 2");
        JButton button3 = new JButton("Menüpont 3");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String feherNev=JOptionPane.showInputDialog(null,"1. jatekos neve");
                String feketeNev=JOptionPane.showInputDialog(null,"2. jatekos neve");
                if(feherNev.isEmpty() || feketeNev.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Hibas nev");
                    return;
                }
                frame.setVisible(false); // A fő ablak elrejtése
                Tabla t = new Tabla(() -> {
                    // Callback: amikor a Tabla véget ér
                    frame.setVisible(true);
                },feherNev,feketeNev);


            }
        });

        // Margók hozzáadása
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button2.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button3.setAlignmentX(JButton.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalGlue()); // Üres tér felül
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(30)); // Térköz
        mainPanel.add(button2);
        mainPanel.add(Box.createVerticalStrut(30)); // Térköz
        mainPanel.add(button3);
        mainPanel.add(Box.createVerticalGlue()); // Üres tér alul


        frame.add(mainPanel);
        frame.setLocationRelativeTo(null); // Ablak középre helyezése
        frame.setVisible(true);
        //asd();
    }
}


