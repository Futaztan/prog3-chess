package figurak;

import javax.swing.*;

public class Futo extends Figura {


    public Futo(boolean fekete)
    {
        isFekete=fekete;
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-futo.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-futo.png"));
    }
    @Override
    public void lepes(int hova) {
        //TODO
    }
}
