package fomenu;

import sakktabla.AdatTarolo;
import sakktabla.ReplayTabla;
import sakktabla.Tabla;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Menu implements Serializable {

    private List<Jatekos> toplista = new ArrayList<Jatekos>();
   
    private JFrame frame = new JFrame("Fomenu");

    public Menu() throws IOException {
//        toplista.add(new Jatekos("asd1",0));
//        toplista.add(new Jatekos("asd2",5));
//        toplista.add(new Jatekos("asd3",10));
//        toplista.add(new Jatekos("asd4",3));
//        toplista.add(new Jatekos("asd5",0));


        listaBetoltes();
        setupUI();
    }

    public void listaBetoltes()
    {
        try {
            String filename="toplista";
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            toplista = (List<Jatekos>) ois.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("nincs toplista");
        }
    }

    public void eredmenyKezeles(String nyertes, String feherNev, String feketeNev)
    {
        boolean benneVanFeher = false;
        boolean benneVanFekete = false;
        for(Jatekos elem : toplista)
        {
            if(elem.nev.equals(feherNev)){
                benneVanFeher=true;
            }
            if(elem.nev.equals(feketeNev)){
                benneVanFekete=true;
            }
        }
        if(!benneVanFeher){
            toplista.add(new Jatekos(feherNev,0));
        }
        if(!benneVanFekete){
            toplista.add(new Jatekos(feketeNev,0));
        }
        for(Jatekos elem : toplista)
        {
            if(elem.nev.equals(nyertes))
                elem.pont++;
        }


            //TODO
        String filename = "toplista";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(toplista);
            oos.close();
            //JOptionPane.showMessageDialog(null,"Sikeres mentés");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void setupUI() throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        //frame.setResizable(false);



        JPanel kontener = new JPanel(null); // Kézi pozicionálás a váltás miatt
        kontener.setLayout(new BoxLayout(kontener,BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel();
        //mainPanel.setBackground(new Color(71, 68, 67));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JButton startButton = new JButton("Játék indítása");


        startButton.setFocusPainted(false); // Eltávolítja a fókuszkeretet
        startButton.setBackground(new Color(149,117 ,205 )); // Modern kék háttér
        startButton.setForeground(Color.WHITE); // Fehér szöveg
        startButton.setFont(new Font("Arial", Font.BOLD, 14)); // Modern, félkövér betűtípus
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Margók
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kézkurzor hover állapotban


        JButton loadButton = new JButton("Játék betöltése");

        loadButton.setFocusPainted(false); // Eltávolítja a fókuszkeretet
        loadButton.setBackground(new Color(149,117 ,205 )); // Modern kék háttér
        loadButton.setForeground(Color.WHITE); // Fehér szöveg
        loadButton.setFont(new Font("Arial", Font.BOLD, 14)); // Modern, félkövér betűtípus
        loadButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Margók
        loadButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kézkurzor hover állapotban

        JButton replayButton = new JButton("Játék visszanézése");
        JButton toplistaButton = new JButton("Toplista");
        JButton exitButton = new JButton("Kilépés");
        // Margók hozzáadása
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        loadButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        replayButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        toplistaButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);


        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(new Color(128,128,128));
        exitButton.setFocusPainted(false);
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(new Color(56, 142, 60)); // Sötétebb zöld
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(new Color(76, 175, 80)); // Eredeti szín
            }
        });



        mainPanel.add(Box.createVerticalGlue()); // Üres tér felül
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(30)); // Térköz
        mainPanel.add(loadButton);
        mainPanel.add(Box.createVerticalStrut(30)); // Térköz
        mainPanel.add(replayButton);
        mainPanel.add(Box.createVerticalStrut(30)); // Térköz
        mainPanel.add(toplistaButton);
        mainPanel.add(Box.createVerticalStrut(30)); // Térköz
        mainPanel.add(exitButton);
        mainPanel.add(Box.createVerticalGlue()); // Üres tér alul
        mainPanel.setVisible(true);

        // Toplista panel
        JPanel toplistaPanel = new JPanel();
        toplistaPanel.setLayout(new BoxLayout(toplistaPanel, BoxLayout.Y_AXIS));
        //Lista output rendezes
        JLabel toplistaLabel = new JLabel("toplista");
        toplistaLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        toplistaLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Vízszintes középre igazítás
        toplistaLabel.setVerticalAlignment(SwingConstants.CENTER);    // Függőleges középre igazítás
        JButton visszaButton = new JButton("Vissza");
        visszaButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        toplistaPanel.add(toplistaLabel);
        toplistaPanel.add(Box.createVerticalStrut(30)); // Térköz
        toplistaPanel.add(visszaButton);
        toplistaPanel.setVisible(false);

        //kontenerbe adas
        kontener.add(mainPanel);
        kontener.add(toplistaPanel);
        frame.setContentPane(kontener);
        frame.setLocationRelativeTo(null); // Ablak középre helyezése
        frame.setVisible(true);

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
                Jatekos seged = new Jatekos("",0);
                frame.setVisible(false); // A fő ablak elrejtése
                Tabla t = new Tabla(() -> { // Callback: amikor a Tabla véget ér
                    frame.setVisible(true);
                    if(!seged.nev.equals("")){
                        eredmenyKezeles(seged.nev,feherNev,feketeNev);
                    }

                },feherNev,feketeNev,seged);

            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Válassz egy fájlt");

                FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser files", "ser");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                int userSelection = fileChooser.showOpenDialog(null);
                if(userSelection == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();

                    //System.out.println(selectedFile.getName());
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile));
                        AdatTarolo adatok = (AdatTarolo) ois.readObject();
                        Jatekos seged = new Jatekos("",0);
                        String feherNev = adatok.getFehernev();
                        String feketeNev = adatok.getFeketenev();
                        frame.setVisible(false);
                        Tabla t = new Tabla(() -> {
                            frame.setVisible(true);
                            if(!seged.nev.equals("")){
                                eredmenyKezeles(seged.nev,feherNev,feketeNev);
                            }
                        },adatok,seged);

                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }


            }
        });

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Válassz egy fájlt");

                FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser2 files", "ser2");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                int userSelection = fileChooser.showOpenDialog(null);
                if(userSelection == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();

                    System.out.println(selectedFile.getName());
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile));
                        AdatTarolo adatok = (AdatTarolo) ois.readObject();
                        frame.setVisible(false);
                        ReplayTabla rt = new ReplayTabla(() -> {
                            frame.setVisible(true);

                        },adatok);

                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }


            }

        });


        toplistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainPanel.setVisible(false);
                toplista.sort(Comparator.comparingInt(Jatekos::getPont).reversed());
                StringBuilder output = new StringBuilder("<html> Név | Győzelmek<br>");
                for(Jatekos elem : toplista)
                {
                    output.append(elem.nev+" "+elem.pont +"<br>");
                }
                output.append("</html>");
                toplistaLabel.setText(output.toString());
                toplistaPanel.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        visszaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toplistaPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });


    }
}


