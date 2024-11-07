package figurak;

import sakktabla.*;

import javax.swing.*;

public class Bastya extends Figura {


    public Bastya(boolean fekete,int sor,int oszlop)
    {
        super(fekete, sor, oszlop);
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-bastya.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-bastya.png"));
    }
    @Override
    public boolean lepes(Mezo mezo,Mezo[][] matrix) {

        if(isOszlopSorLepes(mezo,matrix)){
            mezo.setFigura(this);
            this.sor=mezo.sor;
            this.oszlop=mezo.oszlop;
            return true;
        }
        return false;
    }

    public boolean isOszlopSorLepes(Mezo mezo,Mezo[][] matrix){
        if((mezo.sor == this.sor || mezo.oszlop == this.oszlop) ==false) return false;
        int hatarsorLe=7;
        int hatarsorFel=0;
        int hataroszlopJobb=7;
        int hataroszlopBal=0;
        Figura blockingFigura;
        for(int sor=this.sor+1;sor<8;sor++)
        {
            blockingFigura = matrix[sor][this.oszlop].getFigura();
            if(blockingFigura!=null)
            {
                if(blockingFigura.isFekete == this.isFekete)
                    hatarsorLe=sor-1;
                else hatarsorLe=sor;
                break;
            }

        }
        for(int sor=this.sor-1; sor>=0; sor--)
        {
            blockingFigura = matrix[sor][this.oszlop].getFigura();
            if(blockingFigura!=null)
            {
                if(blockingFigura.isFekete == this.isFekete)
                    hatarsorFel=sor+1;
                else hatarsorFel=sor;
                break;
            }
        }

        for(int oszlop=this.oszlop+1; oszlop<8;oszlop++)
        {
            blockingFigura = matrix[this.sor][oszlop].getFigura();
            if(blockingFigura!=null)
            {
                if(blockingFigura.isFekete== this.isFekete)
                    hataroszlopJobb = oszlop-1;
                else hataroszlopJobb = oszlop;
                break;
            }
        }

        for(int oszlop=this.oszlop-1;oszlop>=0;oszlop--)
        {
            blockingFigura = matrix[this.sor][oszlop].getFigura();
            if(blockingFigura!=null)
            {
                if(blockingFigura.isFekete== this.isFekete)
                    hataroszlopJobb = oszlop+1;
                else hataroszlopJobb = oszlop;
                break;
            }
        }

        return hatarsorLe >= mezo.sor && mezo.sor >= hatarsorFel &&
                hataroszlopBal <= mezo.oszlop && mezo.oszlop <=hataroszlopJobb;
    }

}
