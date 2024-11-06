package figurak;

import javax.swing.*;
import sakktabla.*;

public class Paraszt extends Figura {


    public Paraszt(boolean fekete,int sor,int oszlop)
    {
        super(fekete, sor, oszlop);
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-paraszt.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-paraszt.png"));
    }



    @Override
    public boolean lepes(Mezo mezo) {
        //TODO
        if(!isFekete)
        {
            if(mezo.oszlop==this.oszlop && mezo.sor==this.sor-1)
            {
                mezo.setFigura(this);
                this.sor=mezo.sor;
                this.oszlop=mezo.oszlop;
                return true;
            }
        }
        return false;

    }
}
