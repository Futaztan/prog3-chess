package figurak;

import javax.swing.*;

public class Kiraly extends Figura {


    public Kiraly(boolean fekete)
    {
        isFekete=fekete;
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-kiraly.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-kiraly.png"));
    }
    @Override
    public void lepes(int hova) {
        //TODO
    }
}
