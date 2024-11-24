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
    public boolean lepes(Mezo mezo,Mezo[][] matrix) {

        if(lepesCheck(mezo, matrix))
        {
            mozgatas(mezo,matrix);
            return true;
        }
        return false;

    }

    @Override
    public boolean lepesCheck(Mezo mezo, Mezo[][] matrix) {
        return isBasicLepes(mezo) || isDuplaLepes(mezo) || isLeutesLepes(mezo);
    }


    /**
     * A szimpla egyet előre lépés ellenőrzi
     * @param mezo a mező ahová lépni akar
     * @return true ha érvényes lépés
     */
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

    /**
     * A 2-t előre lépést ellenőrzi, ha még a kezdő helyén van
     * @param mezo a mező ahová lépni akar
     * @return true ha érvényes a lépés
     */
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


    /**
     * Az átlós lépést ellenőrzi, ami csak akkor lesz érvényes ha leüt közben egy ellenséges bábut
     * @param mezo a mező ahová lépni akar
     * @return true ha érvényes a lépés
     */
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
