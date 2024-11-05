package figurak;

import javax.swing.*;

public class Paraszt extends Figura {


    public Paraszt(boolean fekete)
    {
        isFekete=fekete;
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-paraszt.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-paraszt.png"));
    }
    @Override
    public void lepes(int hova) {
        //TODO
    }
}
