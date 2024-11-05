import figurak.Figura;

import javax.swing.*;

public class Tile extends JButton
{
    private Figura figura;

    Tile(Figura f)
    {
        figura = f;
        if(f!=null){
            this.setIcon(f.getIkon());

        }

    }
    public void setFigura(Figura figura) {
        this.figura = figura;
    }

    public Figura getFigura() {
        return figura;
    }
}
