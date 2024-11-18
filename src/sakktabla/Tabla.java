package sakktabla;

import figurak.*;
import teszt.Jatekos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Tabla {
    private JFrame frame = new JFrame("Sakktabla");
    private Mezo[][] matrix = new Mezo[8][8];
    private boolean feketeJon =false;
    private Figura selected_figura;
    private Mezo selected_mezo;
    public JLabel kiJonLabel= new JLabel();
    public JLabel sakkVanLabel = new JLabel();
    private Kiraly feketeKiraly;
    private Kiraly feherKiraly;
    private List<Figura> feherek = new ArrayList<Figura>();
    private List<Figura> feketek = new ArrayList<Figura>();
    private Runnable onGameOverCallback;
    public String feherNev;
    public String feketeNev;
    public Jatekos nyertes;
    public Tabla(Runnable onGameOverCallback, String feher, String fekete, Jatekos nyertes)
    {
        feherNev=feher;
        feketeNev=fekete;
        this.nyertes=nyertes;
        this.onGameOverCallback = onGameOverCallback;
        setupUI();
    }


    public void setupUI()
    {


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null); // Ablak középre helyezése


        // Panel létrehozása az elemekhez
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9,8,0,0));
        //TODO: BAL SAROKBÓL JOBBRA KEZDI FELÉPITENI


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
                //icon = new ImageIcon("feher-huszar.png");
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
                matrix[i][j].setFocusPainted(false); // Fókusz keret kikapcsolása
                matrix[i][j].setBorderPainted(false); // Szegély kikapcsolása
                matrix[i][j].setContentAreaFilled(true); // Kitöltés megtartása, de nyomás effekt nélkül
                if ((i + j) % 2 == 0) {
                    matrix[i][j].setBackground(Color.WHITE); // Világos mező
                } else {
                    matrix[i][j].setBackground(Color.PINK); // Sötét mező
                }

                final int row =i;
                final int col =j;
                matrix[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       //matrix[row][col].setBackground(Color.red);
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

        panel.add(kiJonLabel);
        panel.add(sakkVanLabel);
        frame.add(panel);
        frame.setVisible(true);
        sakkVanLabel.setText("NINCS SAKK");
        kiJonLabel.setText(feherNev + " jön");


    }

    public void  jatekVege()
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
                   sakkVanLabel.setText("HIBAS LEPES");
               }
               //TODO NEM JO HA ELLEP VALAKI AKKOR SAKKBA RAKJA MAGAT elvileg jo?
               else if(!feketeJon && feherKiraly.sakkCheck(matrix))
               {
                   selected_figura.setSor(eredetiSor);
                   selected_figura.setOszlop(eredetiOszlop);
                   selected_mezo.setFigura(selected_figura);
                   mezo.setIcon(eredetiIcon);
                   mezo.setFigura(eredetiFigura);
                   sakkVanLabel.setText("HIBAS LEPES");
               }
               else{
                   sakkVanLabel.setText("NINCS SAKK");
                   //isSakkVan=false;
                   sikeresLepes(eredetiFigura);
               }
           }
        }
    }

    public void sikeresLepes(Figura eredetiFigura)
    {

        selected_mezo.setFigura(null);
        selected_figura=null;
        selected_mezo=null;
        feketeJon=!feketeJon;
        //kövi kör
        if(feketeJon)
        {
            feketek.remove(eredetiFigura);
            kiJonLabel.setText(feketeNev+ " jon");
            if(feketeKiraly.sakkCheck(matrix)){
                sakkVanLabel.setText("SAKK VAN");
                if(mattCheck(feketek,feketeKiraly))
                {
                    nyertes.nev=feherNev;
                    JOptionPane.showMessageDialog(null,"feher win");
                    jatekVege();
                }
            }
        }
        else
        {
            feherek.remove(eredetiFigura);
            kiJonLabel.setText(feherNev+ " jon");
            if(feherKiraly.sakkCheck(matrix)){
                sakkVanLabel.setText("SAKK VAN");
                if(mattCheck(feherek,feherKiraly))
                {
                    nyertes.nev = feketeNev;
                    JOptionPane.showMessageDialog(null,"fekete win");
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
            for(Figura babu : csapat)
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
                                babu.setSor(eredetiSor);
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
