package fomenu;

import sakktabla.ReplayTabla;
import sakktabla.Tabla;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * A főmenüt megvalósító osztály
 */
public class Menu implements Serializable {

    private List<Jatekos> toplista = new ArrayList<Jatekos>();
   
    private JFrame frame = new JFrame("Fomenu");

    public Menu() throws IOException {
        listaBetoltes();
//        toplista.add(new Jatekos("asd1",0));
//        toplista.add(new Jatekos("asd2",5));
//        toplista.add(new Jatekos("asd3",10));
//        toplista.add(new Jatekos("asd4",3));
//        toplista.add(new Jatekos("asd5",0));
        setupUI();
    }

    /**
     * Ez a függvény tölti be a korábban lementett toplistát
     */
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

    /**
     * Kezeli az eredmény elmentését a toplistába, és frissíti a mentését
     * @param nyertes a nyertes neve
     * @param feherNev a fehér játékos neve
     * @param feketeNev a fekete játékos neve
     */
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

    /**
     * Setupolja az alap menüt, amit a felhasznéló rögtön lát
     * @throws IOException Ha nem találja a framehez tartozó icont
     */
    public void setupUI() throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        Image icon = ImageIO.read(this.getClass().getResource("/ikonok/icon.png"));
        frame.setIconImage(icon);


        JPanel kontener = new JPanel(null); // Kézi pozicionálás a váltás miatt
        kontener.setLayout(new BoxLayout(kontener,BoxLayout.Y_AXIS));

        BackgroundPanel mainPanel = new BackgroundPanel(this.getClass().getResource("/ikonok/bg.png"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JButton startButton = ButtonDesign("Játék indítása");
        JButton loadButton = ButtonDesign("Játék betöltése");
        JButton replayButton = ButtonDesign("Játék visszanézése");
        JButton toplistaButton = ButtonDesign("Toplista");
        JButton exitButton = ButtonDesign("Kilépés");

        mainPanel.add(Box.createVerticalGlue()); // Üres tér felül
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(15)); // Térköz
        mainPanel.add(loadButton);
        mainPanel.add(Box.createVerticalStrut(15)); // Térköz
        mainPanel.add(replayButton);
        mainPanel.add(Box.createVerticalStrut(15)); // Térköz
        mainPanel.add(toplistaButton);
        mainPanel.add(Box.createVerticalStrut(15)); // Térköz
        mainPanel.add(exitButton);
        mainPanel.add(Box.createVerticalGlue()); // Üres tér alul
        mainPanel.setVisible(true);

        // Toplista panel
        JPanel toplistaPanel = new JPanel();
        toplistaPanel.setLayout(new BoxLayout(toplistaPanel, BoxLayout.Y_AXIS));
        toplistaPanel.setBackground(new Color(42, 42, 47));
        kontener.setBackground(new Color(67, 63, 77));
        //Lista output rendezes
        JLabel toplistaLabel = new JLabel("Toplista");
        toplistaLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        toplistaLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Vízszintes középre igazítás
        toplistaLabel.setVerticalAlignment(SwingConstants.CENTER);    // Függőleges középre igazítás
        JButton visszaButton = new JButton("Vissza");
        visszaButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        toplistaPanel.add(toplistaLabel);
        toplistaPanel.add(Box.createVerticalStrut(30));
        //toplistaPanel.add(visszaButton);
        toplistaPanel.setVisible(false);

        //kontenerbe adas
        kontener.add(mainPanel);
        kontener.add(toplistaPanel);
        frame.setContentPane(kontener);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String feherNev=JOptionPane.showInputDialog(null,"1. játékos neve");
                String feketeNev=JOptionPane.showInputDialog(null,"2. játékos neve");
                if(feherNev==null || feketeNev==null || feherNev.isBlank() || feketeNev.isBlank() || feherNev.isEmpty() || feketeNev.isEmpty() || feherNev.equals(feketeNev))
                {
                    JOptionPane.showMessageDialog(null,"Hibás nevek");
                    return;
                }
                Jatekos seged = new Jatekos("",0);
                frame.setVisible(false);
                try {
                    Tabla t = new Tabla(() -> { // Callback: amikor a Tabla véget ér
                        frame.setVisible(true);
                        if(!seged.nev.equals("")){
                            eredmenyKezeles(seged.nev,feherNev,feketeNev);
                        }

                    },feherNev,feketeNev,seged);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

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
                        JOptionPane.showMessageDialog(null,"Hibás file");
                        System.err.println(ex);
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
                        JOptionPane.showMessageDialog(null,"Hibás file");
                        System.err.println(ex);
                    }
                }


            }

        });


        toplistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainPanel.setVisible(false);
                toplistaPanel.add(visszaButton);
                toplista.sort(Comparator.comparingInt(Jatekos::getPont).reversed());
                String[] oszlopok = {"Név", "Pontok"};
                Object[][] tableData = new Object[toplista.size()][2];
                for (int i = 0; i < toplista.size(); i++) {
                    tableData[i][0] = toplista.get(i).nev;  // Név
                    tableData[i][1] = toplista.get(i).getPont();  // Pontok
                }
                DefaultTableModel tableModel = new DefaultTableModel(tableData, oszlopok) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; //nem lehet editelni a rekordokat
                    }
                };
                JTable table = new JTable(tableModel);
                table.setBackground(new Color(51, 51, 51));
                table.setForeground(Color.WHITE);
                table.setGridColor(new Color(102, 102, 102)); // rácsvonalak színe
                table.setFont(new Font("Arial", Font.PLAIN, 14));
                table.setRowHeight(25);
                table.setCellSelectionEnabled(false);

                // Görgetősáv és szülő komponens színezése
                JScrollPane scroll = new JScrollPane(table);
                scroll.getViewport().setBackground(new Color(42, 42, 47)); // Görgetősáv nézeti háttér
                scroll.setBorder(BorderFactory.createEmptyBorder()); // Görgetősáv határainak eltüntetése
                scroll.setBackground(new Color(42, 42, 47));
                toplistaPanel.add(scroll);
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
                toplistaPanel.removeAll();
                mainPanel.setVisible(true);
            }
        });


    }

    private void handleStartButton(ActionListener e)
    {
        String feherNev=JOptionPane.showInputDialog(null,"1. játékos neve");
        String feketeNev=JOptionPane.showInputDialog(null,"2. játékos neve");
        if(feherNev==null || feketeNev==null || feherNev.isBlank() || feketeNev.isBlank() || feherNev.isEmpty() || feketeNev.isEmpty() || feherNev.equals(feketeNev))
        {
            JOptionPane.showMessageDialog(null,"Hibás nevek");
            return;
        }
        Jatekos seged = new Jatekos("",0);
        frame.setVisible(false);
        try {
            Tabla t = new Tabla(() -> { // Callback: amikor a Tabla véget ér
                frame.setVisible(true);
                if(!seged.nev.equals("")){
                    eredmenyKezeles(seged.nev,feherNev,feketeNev);
                }

            },feherNev,feketeNev,seged);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Létrehoz egy új gombot, ki dekorálja majd visszaadja ezt a buttont
     * @param szoveg A szöveg ami megjelenik a buttonon
     * @return egy designolt button
     */
    private JButton ButtonDesign(String szoveg)
    {
        JButton btn = new JButton(szoveg);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(42, 42, 47));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(200,100));
        return btn;
    }
}


