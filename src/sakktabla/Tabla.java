package sakktabla;

import figurak.*;
import fomenu.AdatTarolo;
import fomenu.Jatekos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Tabla {
    protected JFrame frame = new JFrame("Sakktabla");
    protected Mezo[][] matrix = new Mezo[8][8];
    private boolean feketeJon =false;
    private Figura selected_figura;
    private Mezo selected_mezo;
    public JLabel kiJonLabel= new JLabel();
    public JLabel sakkVanLabel = new JLabel();
    public JLabel utolsolepesLabel = new JLabel();
    public JButton mentesButton;
    protected Kiraly feketeKiraly;
    protected Kiraly feherKiraly;
    protected List<Figura> feherek = new ArrayList<Figura>();
    protected List<Figura> feketek = new ArrayList<Figura>();
    private Runnable onGameOverCallback;
    private String feherNev;
    private String feketeNev;
    private Jatekos nyertes;
    protected List<Lepes> lepesek = new ArrayList<Lepes>();

    public Tabla(Runnable onGameOverCallback, String feher, String fekete, Jatekos nyertes)
    {
        feherNev=feher;
        feketeNev=fekete;
        this.nyertes=nyertes;
        this.onGameOverCallback = onGameOverCallback;
        jatekUI();

    }
    public Tabla(Runnable onGameOverCallback, AdatTarolo adat, Jatekos nyertes)
    {
        feherNev=adat.getFehernev();
        feketeNev=adat.getFeketenev();
        this.nyertes=nyertes;
        this.onGameOverCallback = onGameOverCallback;
        lepesek = adat.getLepesek();
        feketeJon = adat.isFeketeJon();
        jatekUI();
        mentesBetoltes();
    }
    public Tabla(Runnable onGameOverCallback, AdatTarolo adat)
    {
        feherNev=adat.getFehernev();
        feketeNev=adat.getFeketenev();
        this.onGameOverCallback = onGameOverCallback;
        lepesek = adat.getLepesek();
        feketeJon = adat.isFeketeJon();
    }

    public void mentesBetoltes()
    {
        for(Lepes lepes : lepesek)
        {
            int honnanSor = Character.getNumericValue(lepes.getHonnan().charAt(0));
            int honnanOszlop = Character.getNumericValue(lepes.getHonnan().charAt(1));
            int hovaSor = Character.getNumericValue(lepes.getHova().charAt(0));
            int hovaOszlop = Character.getNumericValue(lepes.getHova().charAt(1));
            Mezo hova = matrix[hovaSor][hovaOszlop];
            Mezo honnan =  matrix[honnanSor][honnanOszlop];
            honnan.getFigura().lepes(hova,matrix);
            honnan.setFigura(null);
        }
        if(feketeJon) kiJonLabel.setText(feketeNev + " jön");
        else kiJonLabel.setText(feherNev+ " jön");
    }

    public void jatekUI()
    {


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        // Panel létrehozása az elemekhez
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9,8,0,0));

        //BAL SAROKBÓL JOBBRA KEZDI FELÉPITENI
        matrix[0][0] = new Mezo(new Bastya(true,0,0),0,0);
        matrix[0][1] = new Mezo(new Huszar(true,0,1),0,1);
        matrix[0][2] = new Mezo(new Futo(true,0,2),0,2);
        matrix[0][3] = new Mezo(new Kiralyno(true,0,3),0,3);
        matrix[0][4] = new Mezo (new Kiraly(true,0,4),0,4);
        matrix[0][5] = new Mezo(new Futo(true,0,5),0,5);
        matrix[0][6] = new Mezo(new Huszar(true,0,6),0,6);
        matrix[0][7] = new Mezo(new Bastya(true,0,7),0,7);
        feketeKiraly = (Kiraly) matrix[0][4].getFigura();


        for (int row=1;row<7;row++)
        {
            for(int col=0; col<8;col++)
            {

                if(row==1){
                    matrix[row][col]= new Mezo(new Paraszt(true,row,col),row,col);
                }
                else if(row==6){
                    matrix[row][col]= new Mezo(new Paraszt(false,row,col),row,col);
                }
                else  matrix[row][col] = new Mezo(null,row,col);
            }
        }

        matrix[7][0] = new Mezo(new Bastya( false,7,0),7,0);
        matrix[7][1] = new Mezo(new Huszar( false,7,1),7,1);
        matrix[7][2] = new Mezo(new Futo(false,7,2),7,2);
        matrix[7][3] = new Mezo(new Kiralyno (false,7,3),7,3);
        matrix[7][4] = new Mezo (new Kiraly( false,7,4),7,4);
        matrix[7][5] = new Mezo(new Futo( false,7,5),7,5);
        matrix[7][6] = new Mezo(new Huszar( false,7,6),7,6);
        matrix[7][7] = new Mezo(new Bastya( false,7,7),7,7);
        feherKiraly = (Kiraly) matrix[7][4].getFigura();

        for(int i=0;i<8;i++)
        {
            for(int j=0; j<8;j++)
            {
                matrix[i][j].setFocusPainted(false);
                matrix[i][j].setBorderPainted(false); //nem jelöli a kattintott gombot
                matrix[i][j].setContentAreaFilled(true);
                if ((i + j) % 2 == 0) {
                    matrix[i][j].setBackground(Color.WHITE); // Világos mező
                } else {
                    matrix[i][j].setBackground(Color.LIGHT_GRAY); // Sötét mező
                }

                final int row =i;
                final int col =j;
                matrix[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        kattintas(matrix[row][col]);

                    }
                });
                panel.add(matrix[i][j]);
            }
        }
        for(int sor =0; sor<=1;sor++)
        {
            for(int oszlop=0; oszlop<8;oszlop++)
            {
                feketek.add(matrix[sor][oszlop].getFigura());
            }
        }
        for(int sor =6; sor<=7;sor++)
        {
            for(int oszlop=0; oszlop<8;oszlop++)
            {
                feherek.add(matrix[sor][oszlop].getFigura());
            }
        }

//////////////////////////////
        mentesButton = new JButton("Mentes");
        mentesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AdatTarolo adatok = new AdatTarolo(lepesek,feketeJon,feherNev,feketeNev,false);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                Date date = new Date();

                String filename = formatter.format(date)+".ser";
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
                    oos.writeObject(adatok);
                    oos.close();
                    JOptionPane.showMessageDialog(null,"Sikeres mentés");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        JButton exitButton = new JButton("Kilépés");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitToMenu();
            }
        });

        panel.add(mentesButton);
        panel.add(new JPanel()); //üres tér
        panel.add(kiJonLabel);
        panel.add(sakkVanLabel);
        panel.add(utolsolepesLabel);
        panel.add(new JPanel()); //üres tér
        panel.add(new JPanel()); //üres tér
        panel.add(exitButton);
        frame.add(panel);
        frame.setVisible(true);
        //sakkVanLabel.setText("NINCS SAKK");
        kiJonLabel.setText(feherNev + " jön");


    }



    public void jatekVege()
    {

        AdatTarolo adatok = new AdatTarolo(lepesek,feketeJon,feherNev,feketeNev,true);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();

        String filename = "R_"+formatter.format(date)+".ser2";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(adatok);
            oos.close();
            JOptionPane.showMessageDialog(null,"Sikeres mentés");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        exitToMenu();


    }

    public void exitToMenu()
    {
        if (onGameOverCallback != null) {
            onGameOverCallback.run(); // Callback meghívása
        }
        frame.dispose();
    }

    public void kattintas(Mezo mezo)
    {

        if(mezo.getFigura()!=null)
        {
            Figura f = mezo.getFigura();
            if(feketeJon && f.isFekete()){
                selected_figura = f;
                selected_mezo=mezo;
                return;
            }

            else if(!feketeJon && !f.isFekete()){
                selected_figura = f;
                selected_mezo=mezo;
                return;
            }

        }
        if(selected_figura!=null)
        {
           int eredetiSor = selected_figura.getSor();
           int eredetiOszlop = selected_figura.getOszlop();
           Icon eredetiIcon = mezo.getIcon();
           Figura eredetiFigura = mezo.getFigura();

           if(selected_figura.lepes(mezo,matrix))
           {
               if(feketeJon && feketeKiraly.sakkCheck(matrix))
               {
                   selected_figura.setSor(eredetiSor);
                   selected_figura.setOszlop(eredetiOszlop);
                   selected_mezo.setFigura(selected_figura);
                   mezo.setIcon(eredetiIcon);
                   mezo.setFigura(eredetiFigura);
                   sakkVanLabel.setText("HIBÁS LÉPÉS");
               }

               else if(!feketeJon && feherKiraly.sakkCheck(matrix))
               {
                   selected_figura.setSor(eredetiSor);
                   selected_figura.setOszlop(eredetiOszlop);
                   selected_mezo.setFigura(selected_figura);
                   mezo.setIcon(eredetiIcon);
                   mezo.setFigura(eredetiFigura);
                   sakkVanLabel.setText("HIBÁS LÉPÉS");
               }
               else{
                   sakkVanLabel.setText("");
                   sikeresLepes(eredetiFigura);
               }
           }
        }
    }

    public void sikeresLepes(Figura eredetiFigura)
    {

        lepesek.add(new Lepes(selected_mezo.oszlop,selected_mezo.sor, selected_figura.getOszlop(),selected_figura.getSor()));
        utolsolepesLabel.setText(lepesek.getLast().toString());

        //selected_mezo.setFigura(null);
        selected_figura=null;
        selected_mezo=null;
        feketeJon=!feketeJon;
        //kövi kör
        if(feketeJon)
        {
            feketek.remove(eredetiFigura);
            kiJonLabel.setText(feketeNev+ " jön");
            if(feketeKiraly.sakkCheck(matrix)){
                sakkVanLabel.setText("SAKK VAN");
                if(mattCheck(feketek,feketeKiraly))
                {
                    nyertes.nev=feherNev;
                    JOptionPane.showMessageDialog(null,feherNev+ " a nyertes!");
                    jatekVege();
                }
            }
        }
        else
        {
            feherek.remove(eredetiFigura);
            kiJonLabel.setText(feherNev+ " jön");
            if(feherKiraly.sakkCheck(matrix)){
                sakkVanLabel.setText("SAKK VAN");
                if(mattCheck(feherek,feherKiraly))
                {
                    nyertes.nev = feketeNev;
                    JOptionPane.showMessageDialog(null,feketeNev + " a nyertes!");
                    jatekVege();
                }
            }
        }
    }

    public void hibasLepes(int eredetiSor, int eredetiOszlop, Mezo mezo, Icon eredetiIcon, Figura eredetiFigura, Figura currentFigura)
    {
        currentFigura.setSor(eredetiSor);
        currentFigura.setOszlop(eredetiOszlop);
        matrix[eredetiSor][eredetiOszlop].setFigura(currentFigura);
        mezo.setIcon(eredetiIcon);
        mezo.setFigura(eredetiFigura);
        sakkVanLabel.setText("HIBAS LEPES");
    }
    public boolean mattCheck(List<Figura> csapat, Kiraly csapatKiraly)
    {
            for(Figura babu : csapat)           //megnézi hogy sakk matt van e
            {

                for(int i=0; i<8; i++)
                {
                    for(int j=0; j<8;j++)
                    {
                        Mezo mezo = matrix[i][j];
                        int eredetiSor = babu.getSor();
                        int eredetiOszlop = babu.getOszlop();
                        Icon eredetiIcon = mezo.getIcon();
                        Figura eredetiFigura = mezo.getFigura();
                        if(babu.lepes(mezo,matrix))
                        {
                            if(!csapatKiraly.sakkCheck(matrix))
                            {
                                babu.setSor(eredetiSor); //léptetés vissza állítása
                                babu.setOszlop(eredetiOszlop);
                                matrix[eredetiSor][eredetiOszlop].setFigura(babu);
                                mezo.setIcon(eredetiIcon);
                                mezo.setFigura(eredetiFigura);
                                return false;
                            }
                            babu.setSor(eredetiSor);
                            babu.setOszlop(eredetiOszlop);
                            matrix[eredetiSor][eredetiOszlop].setFigura(babu);
                            mezo.setIcon(eredetiIcon);
                            mezo.setFigura(eredetiFigura);
                        }

                    }
                }
            }
        return true;
    }
}
