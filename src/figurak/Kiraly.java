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
    public boolean lepes(Mezo mezo,Mezo[][] matrix) {

        if(isNegyzetLepes(mezo))
        {
            mezo.setFigura(this);
            this.sor=mezo.sor;
            this.oszlop=mezo.oszlop;
            return true;
        }
        return false;
    }

    public boolean isNegyzetLepes(Mezo mezo)
    {
        return Math.abs(mezo.oszlop-this.oszlop)<=1 && Math.abs(mezo.sor-this.sor)<=1;
    }
}
