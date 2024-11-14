package figurak;
import sakktabla.Mezo;

import javax.swing.*;

public abstract class Figura {
protected boolean isFekete;
protected ImageIcon ikon;
protected int sor;
protected int oszlop ;


public Figura(boolean fekete,int sor,int oszlop )
{
    isFekete=fekete;
    this.sor=sor;
    this.oszlop=oszlop;
}
//matrix a táblamátrix
    //mezo az a mezo ahova lépni akar
public abstract boolean lepes(Mezo mezo,Mezo[][] matrix);
//tud-e lépni az adott mezőre a figura
public abstract boolean lepesCheck(Mezo mezo,Mezo[][] matrix);


    public ImageIcon getIkon() {
        return ikon;
    }

    public boolean isFekete() {
        return isFekete;
    }

    public int getOszlop() {
        return oszlop;
    }

    public int getSor() {
        return sor;
    }

    public void setOszlop(int oszlop) {
        this.oszlop = oszlop;
    }

    public void setSor(int sor) {
        this.sor = sor;
    }
}
