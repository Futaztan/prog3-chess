import figurak.*;

import javax.swing.*;
import java.awt.*;

public class Tabla {
    private Tile[][] matrix = new Tile[8][8];
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

        matrix[0][0] = new Tile(fekete_bastya);
        matrix[0][1] = new Tile(fekete_huszar);
        matrix[0][2] = new Tile(fekete_futo);
        matrix[0][3] = new Tile(fekete_kiralyno);
        matrix[0][4] = new Tile(fekete_kiraly);
        matrix[0][5] = new Tile(fekete_futo);
        matrix[0][6] = new Tile(fekete_huszar);
        matrix[0][7] = new Tile(fekete_bastya);


        Icon icon;
        for (int row=1;row<7;row++)
        {
            for(int col=0; col<8;col++)
            {
                //icon = new ImageIcon("feher-huszar.png");
                if(row==1){
                    matrix[row][col]= new Tile(new Paraszt(true));
                }
                else if(row==6){
                    matrix[row][col]= new Tile(new Paraszt(false));
                }
                else  matrix[row][col] = new Tile(null);
            }
        }

        matrix[7][0] = new Tile(feher_bastya);
        matrix[7][1] = new Tile(feher_huszar);
        matrix[7][2] = new Tile(feher_futo);
        matrix[7][3] = new Tile(feher_kiralyno);
        matrix[7][4] = new Tile(feher_kiraly);
        matrix[7][5] = new Tile(feher_futo);
        matrix[7][6] = new Tile(feher_huszar);
        matrix[7][7] = new Tile(feher_bastya);

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
                panel.add(matrix[i][j]);
            }
        }





        frame.add(panel);

        // Keret megjelenítése
        frame.setVisible(true);
        //frame.pack();
        // Panel létrehozása az elemekhez

    }

}
