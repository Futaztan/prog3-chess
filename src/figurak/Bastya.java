package figurak;

import sakktabla.*;

import javax.swing.*;

public class Bastya extends Figura {


    public Bastya(boolean fekete,int sor,int oszlop)
    {
        super(fekete, sor, oszlop);
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-bastya.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-bastya.png"));
    }
    @Override
    public boolean lepes(Mezo mezo) {
        //TODO
        return false;
    }
}
