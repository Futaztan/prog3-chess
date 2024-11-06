package figurak;
import sakktabla.Mezo;

import javax.swing.*;

public abstract class Figura {
protected boolean isFekete;
protected ImageIcon ikon;
protected int sor;
protected int oszlop ;
protected Mezo m;

public Figura(boolean fekete,int sor,int oszlop )
{
    isFekete=fekete;
    this.sor=sor;
    this.oszlop=oszlop;
}

public abstract boolean lepes(Mezo mezo);
public void kiralyCheck()
{

}

    public ImageIcon getIkon() {
        return ikon;
    }

    public boolean isFekete() {
        return isFekete;
    }
}
