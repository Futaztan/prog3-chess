import figurak.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tabla {
    private Mezo[][] matrix = new Mezo[8][8];
    private Kiraly fekete_kiraly = new Kiraly(true);
    private Kiraly feher_kiraly = new Kiraly(false);
    private Kiralyno feher_kiralyno = new Kiralyno(false    );
    private Kiralyno fekete_kiralyno = new Kiralyno(true);
    private Bastya fekete_bastya = new Bastya(true);
    private Bastya feher_bastya = new Bastya(false);
    private Futo fekete_futo = new Futo(true);
    private  Futo feher_futo = new Futo(false);
    private Huszar fekete_huszar = new Huszar(true);
    private  Huszar feher_huszar = new Huszar(false);
    private boolean feketeJon =false;
    private Figura selected;


    public void inic()
    {
        JFrame frame = new JFrame("Tabla");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null); // Ablak középre helyezése

        // Panel létrehozása az elemekhez
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,8,0,0));
        //TODO: BAL SAROKBÓL JOBBRA KEZDI FELÉPITENI

        matrix[0][0] = new Mezo(fekete_bastya,0,0);
        matrix[0][1] = new Mezo(fekete_huszar,0,1);
        matrix[0][2] = new Mezo(fekete_futo,0,2);
        matrix[0][3] = new Mezo(fekete_kiralyno,0,3);
        matrix[0][4] = new Mezo(fekete_kiraly,0,4);
        matrix[0][5] = new Mezo(fekete_futo,0,5);
        matrix[0][6] = new Mezo(fekete_huszar,0,6);
        matrix[0][7] = new Mezo(fekete_bastya,0,7);


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

        matrix[7][0] = new Mezo(feher_bastya,7,0);
        matrix[7][1] = new Mezo(feher_huszar,7,1);
        matrix[7][2] = new Mezo(feher_futo,7,2);
        matrix[7][3] = new Mezo(feher_kiralyno,7,3);
        matrix[7][4] = new Mezo(feher_kiraly,7,4);
        matrix[7][5] = new Mezo(feher_futo,7,5);
        matrix[7][6] = new Mezo(feher_huszar,7,6);
        matrix[7][7] = new Mezo(feher_bastya,7,7);

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
        frame.add(panel);
        frame.setVisible(true);


    }

    public void kattintas(Mezo mezo)
    {

        Figura f = mezo.getFigura();
        if(feketeJon && f.isFekete())
            selected = f;
        else if(!feketeJon && !f.isFekete())
            selected = f;
        else {
            f.lepes(mezo);
        }

    }



}
