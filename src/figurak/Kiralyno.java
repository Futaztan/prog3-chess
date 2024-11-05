package figurak;

import javax.swing.*;

public class Kiralyno extends Figura {


    public Kiralyno(boolean fekete)
    {
        isFekete=fekete;
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-kiralyno.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-kiralyno.png"));
    }
    @Override
    public void lepes(int hova) {
        //TODO
    }
}
