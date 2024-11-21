import fomenu.Menu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        JFrame mainmenu = new JFrame();
//        mainmenu.setContentPane(new teszt.Menu().panel1);
//        mainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainmenu.pack();
//        mainmenu.setVisible(true);

        try {
            Menu m = new Menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //t.inic();
    }
}