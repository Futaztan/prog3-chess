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


    /**
     * Megnézi hogy az adott király sakkban van e
     * @param matrix sakktábla mátrix
     * @return true ha sakk van
     */
    public boolean sakkCheck(Mezo[][] matrix)
    {
        Mezo kiralymezo = matrix[this.sor][this.oszlop];
        for(int sor = 0; sor<8;sor++)
        {
            for(int oszlop=0; oszlop<8;oszlop++)
            {
                if(matrix[sor][oszlop].getFigura()!=null &&
                        matrix[sor][oszlop].getFigura().isFekete!=this.isFekete &&
                        matrix[sor][oszlop].getFigura().lepesCheck(kiralymezo,matrix))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean lepes(Mezo mezo,Mezo[][] matrix) {

        if(lepesCheck(mezo,matrix))
        {
            mozgatas(mezo,matrix);
            return true;
        }
        return false;
    }
    @Override
    public boolean lepesCheck(Mezo mezo,Mezo[][] matrix)
    {
        boolean isSzabad=true;
        if(mezo.getFigura()!=null && mezo.getFigura().isFekete==this.isFekete)
            isSzabad=false;
        return isSzabad && isNegyzetLepes(mezo);
    }


    /**
     * Megnézi hogy a mező a király körüli négyzet van e
     * @param mezo a mező ahová lépni akar
     * @return true ha érvényes lépés
     */
    public boolean isNegyzetLepes(Mezo mezo)
    {
        return  Math.abs(mezo.oszlop-this.oszlop)<=1 && Math.abs(mezo.sor-this.sor)<=1;
    }
}
