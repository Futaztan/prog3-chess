package sakktabla;

import figurak.*;
import fomenu.AdatTarolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayTabla  extends Tabla{

    public ReplayTabla(Runnable onGameOverCallback, AdatTarolo adat) {
        super(onGameOverCallback, adat);
        replayUI();
    }

    public void replayUI()
    {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);


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
                matrix[i][j].setBorderPainted(false);
                matrix[i][j].setContentAreaFilled(true);

                if ((i + j) % 2 == 0) {
                    matrix[i][j].setBackground(Color.WHITE); // Világos mező
                } else {
                    matrix[i][j].setBackground(Color.LIGHT_GRAY); // Sötét mező
                }

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
        ///////////////////////////
        JButton exitButton = new JButton("Kilépés");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitToMenu();
            }
        });

        JButton elozoButton = new JButton("Előző lépés");
        elozoButton.setEnabled(false);
        JButton kovetkezoButton = new JButton("Következő lépés");

        final int[] index = {0};
        kovetkezoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elozoButton.setEnabled(true);
                Lepes lepes = lepesek.get(index[0]);

                int honnanSor = Character.getNumericValue(lepes.getHonnan().charAt(0));
                int honnanOszlop = Character.getNumericValue(lepes.getHonnan().charAt(1));
                int hovaSor = Character.getNumericValue(lepes.getHova().charAt(0));
                int hovaOszlop = Character.getNumericValue(lepes.getHova().charAt(1));
                Mezo hova = matrix[hovaSor][hovaOszlop];
                Mezo honnan =  matrix[honnanSor][honnanOszlop];
                honnan.getFigura().mozgatas(hova,matrix);
                honnan.setFigura(null);
                index[0]++;
                if(index[0] == lepesek.size()){
                    kovetkezoButton.setEnabled(false);
                }

            }
        });

        elozoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                kovetkezoButton.setEnabled(true);
                index[0]--;

                Lepes lepes = lepesek.get(index[0]);
                int honnanSor = Character.getNumericValue(lepes.getHova().charAt(0));
                int honnanOszlop = Character.getNumericValue(lepes.getHova().charAt(1));
                int hovaSor = Character.getNumericValue(lepes.getHonnan().charAt(0));
                int hovaOszlop = Character.getNumericValue(lepes.getHonnan().charAt(1));
                Mezo hova = matrix[hovaSor][hovaOszlop];
                Mezo honnan =  matrix[honnanSor][honnanOszlop];
                honnan.getFigura().mozgatas(hova,matrix);
                honnan.setFigura(null);

                if(index[0]==0){
                    elozoButton.setEnabled(false);
                }
            }
        });

        panel.add(elozoButton);
        panel.add(kovetkezoButton);
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(exitButton);
        frame.add(panel);
        frame.setVisible(true);


    }
}
