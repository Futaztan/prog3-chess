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

    /**
     * Megnézi hogy érvényes e az adott lépés és ha igen akkor el is végzi a lépést
     * @param mezo a mezo ahova lépni akar a játékos
     * @param matrix a sakktábla mátrix
     * @return true ha oda a mezo mezőre lépett
     */
public abstract boolean lepes(Mezo mezo,Mezo[][] matrix);

    /**
     * Megnézi hogy érvényes e az adott lépés, de nem végzi el ha az
     * @param mezo a mezo ahova lépni akar a játékos
     * @param matrix a sakktábla mátrix
     * @return true ha érvényes lépés lenne
     */
public abstract boolean lepesCheck(Mezo mezo,Mezo[][] matrix);


    /**
     * Nem ellenőrizzük, hogy érvényes lépés lenne-e, szimplán mozgatjuk az adott figurát a megadott mezőre
     * @param mezo a mező ahová tesszük az adott figurát
     * @param matrix a sakktábla mátrix
     */
public void mozgatas(Mezo mezo, Mezo[][] matrix)
{
    matrix[this.sor][this.oszlop].setFigura(null);
    mezo.setFigura(this);
    this.sor=mezo.sor;
    this.oszlop=mezo.oszlop;
}


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
