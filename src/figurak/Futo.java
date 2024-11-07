package figurak;
import sakktabla.*;
import javax.swing.*;

public class Futo extends Figura {


    public Futo(boolean fekete,int sor,int oszlop )
    {
        super(fekete, sor, oszlop);
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-futo.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-futo.png"));
    }
    @Override
    public boolean lepes(Mezo mezo, Mezo[][] matrix) {
        //TODO
        return false;
    }
}
