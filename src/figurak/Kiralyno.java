package figurak;

import javax.swing.*;
import  sakktabla.*;

public class Kiralyno extends Figura {


    public Kiralyno(boolean fekete,int sor,int oszlop )
    {
        super(fekete, sor, oszlop);
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-kiralyno.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-kiralyno.png"));
    }
    @Override
    public boolean lepes(Mezo mezo) {
        //TODO
        return false;
    }
}
