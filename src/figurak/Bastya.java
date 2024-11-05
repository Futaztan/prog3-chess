package figurak;

import javax.swing.*;

public class Bastya extends Figura {


    public Bastya(boolean fekete)
    {
        isFekete=fekete;
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-bastya.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-bastya.png"));
    }
    @Override
    public void lepes(int hova) {
        //TODO
    }
}
