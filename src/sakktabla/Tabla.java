package sakktabla;

import figurak.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tabla {
    private Mezo[][] matrix = new Mezo[8][8];

    private boolean feketeJon =false;
    private Figura selected_figura;
    private Mezo selected_mezo;
    public JLabel kiiras= new JLabel();

    public void inic()
    {
        JFrame frame = new JFrame("sakktabla.Tabla");

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


        Icon icon;
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
                    matrix[i][j].setBackground(Color.GRAY); // Sötét mező
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

        panel.add(kiiras);
        frame.add(panel);
        frame.setVisible(true);
        kiiras.setText("FEHER JON");


    }

    public void kattintas(Mezo mezo)
    {
        if(mezo.getFigura()!=null)
        {
            Figura f = mezo.getFigura();
            if(feketeJon && f.isFekete()){
                selected_figura = f;
                selected_mezo=mezo;
            }

            else if(!feketeJon && !f.isFekete()){
                selected_figura = f;
                selected_mezo=mezo;
            }

        }
        if(selected_figura!=null && selected_figura.lepes(mezo)){
                //selected_mezo.setIcon(null);
                selected_mezo.setFigura(null);
                selected_figura=null;
                selected_mezo=null;
                feketeJon=!feketeJon;
                if(feketeJon) kiiras.setText("fekete jon");
                else kiiras.setText("feher jon");               //kövi kör
        }


    }



}
