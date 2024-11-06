package figurak;

import sakktabla.*;
import javax.swing.*;

public class Huszar extends Figura {


    public Huszar(boolean fekete,int sor,int oszlop )
    {
        super(fekete, sor, oszlop);

        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-huszar.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-huszar.png"));
    }
    @Override
    public boolean lepes(Mezo mezo) {
        //TODO
        return false;
    }
}
