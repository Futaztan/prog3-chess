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

        if(isBasicLepes(mezo) || isDuplaLepes(mezo) || isLeutesLepes(mezo))
        {
            mezo.setFigura(this);
            this.sor=mezo.sor;
            this.oszlop=mezo.oszlop;
            return true;
        }
        return false;

    }

    public boolean isBasicLepes(Mezo mezo)
    {
        if(!this.isFekete){
            return mezo.getFigura()==null &&
                    mezo.oszlop==this.oszlop &&
                    mezo.sor==this.sor-1;
        }
        else return mezo.getFigura()==null &&
                mezo.oszlop==this.oszlop &&
                mezo.sor==this.sor+1;

    }
    public boolean isDuplaLepes(Mezo mezo)
    {
        if(!this.isFekete){
            return mezo.getFigura() == null &&
                    mezo.oszlop == this.oszlop &&
                    this.sor == 6 && mezo.sor == this.sor - 2;
        }
        else return mezo.getFigura()==null &&
                mezo.oszlop==this.oszlop &&
                this.sor==1 &&
                mezo.sor==this.sor+2;
    }

    public boolean isLeutesLepes(Mezo mezo)
    {
        if(!this.isFekete){
            return mezo.getFigura() != null &&
                    mezo.getFigura().isFekete &&
                    (mezo.oszlop == this.oszlop - 1 || mezo.oszlop == this.oszlop + 1) &&
                    mezo.sor == this.sor - 1;
        }
        else return mezo.getFigura() != null &&
                !mezo.getFigura().isFekete &&
                (mezo.oszlop == this.oszlop - 1 || mezo.oszlop == this.oszlop + 1) &&
                mezo.sor == this.sor + 1;
    }
}
