package figurak;

import javax.swing.*;
import sakktabla.*;
public class Kiraly extends Figura {


    public Kiraly(boolean fekete,int sor,int oszlop )
    {
        super(fekete, sor, oszlop);
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-kiraly.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-kiraly.png"));
    }
    @Override
    public boolean lepes(Mezo mezo) {
        //TODO
        return false;
    }
}
