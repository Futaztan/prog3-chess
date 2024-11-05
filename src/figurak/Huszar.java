package figurak;

import javax.swing.*;

public class Huszar extends Figura {


    public Huszar(boolean fekete)
    {
        isFekete=fekete;
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-huszar.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-huszar.png"));
    }
    @Override
    public void lepes(int hova) {
        //TODO
    }
}
