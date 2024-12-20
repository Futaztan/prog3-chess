package sakktabla;

import figurak.Figura;

import javax.swing.*;

/**
 * A sakktábla mátrixban használt Mezők, eltárolja hogy melyik sorban és oszlopban van illetve hogy milyen figura áll rajta
 */
public class Mezo extends JButton
{
    private Figura figura;
    public int sor;
    public int oszlop;

    public Mezo(Figura f,int s,int o)
    {
        sor=s;
        oszlop=o;
        figura = f;
        if(f!=null){
            this.setIcon(f.getIkon());

        }

    }
    public void setFigura(Figura figura) {

        this.figura = figura;
        if(figura!=null){
            this.setIcon(figura.getIkon());
        }
        else this.setIcon(null);
    }

    public Figura getFigura() {
        return figura;
    }
}
