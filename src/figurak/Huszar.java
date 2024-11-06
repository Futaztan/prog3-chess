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
        if(isBalLepes(mezo) || isJobbLepes(mezo) || isFelLepes(mezo) || isLeLepes(mezo)){
            mezo.setFigura(this);
            this.sor=mezo.sor;
            this.oszlop=mezo.oszlop;
            return true;
        }
        return false;
    }

    public boolean isBalLepes(Mezo mezo) {
        return (mezo.sor == this.sor + 1 || mezo.sor == this.sor - 1) &&
                mezo.oszlop == this.oszlop - 2;
    }

    public boolean isJobbLepes(Mezo mezo) {
        return (mezo.sor == this.sor + 1 || mezo.sor == this.sor - 1) &&
                mezo.oszlop == this.oszlop + 2;
    }

    public boolean isFelLepes(Mezo mezo) {
        return (mezo.oszlop == this.oszlop - 1 || mezo.oszlop == this.oszlop + 1) &&
                mezo.sor == this.sor - 2;

    }

    public boolean isLeLepes(Mezo mezo){
        return (mezo.oszlop == this.oszlop - 1 || mezo.oszlop == this.oszlop + 1) &&
                mezo.sor == this.sor + 2;
    }
}
