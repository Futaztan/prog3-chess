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

        if(lepesCheck(mezo,matrix)){
            matrix[this.sor][this.oszlop].setFigura(null);
            mezo.setFigura(this);
            this.sor=mezo.sor;
            this.oszlop=mezo.oszlop;
            return true;
        }
        return false;
    }
    @Override
    public boolean lepesCheck(Mezo mezo,Mezo[][] matrix)
    {
        return isOszlopSorLepes(mezo,matrix);
    }

    public boolean isOszlopSorLepes(Mezo mezo,Mezo[][] matrix){

        String mezopozicio;
        if(mezo.sor==this.sor){
            if(mezo.oszlop>this.oszlop) mezopozicio="jobb";
            else mezopozicio="bal";
        }
        else if(mezo.oszlop==this.oszlop){
            if(mezo.sor>this.sor) mezopozicio="le";
            else mezopozicio="fel";
        }
        else return false;

        Figura blockingFigura;
        switch (mezopozicio)
        {
            case "jobb":
                int hataroszlopJobb=7;
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
                return mezo.oszlop <=hataroszlopJobb;


            case "bal":
                int hataroszlopBal=0;
                for(int oszlop=this.oszlop-1;oszlop>=0;oszlop--)
                {
                    blockingFigura = matrix[this.sor][oszlop].getFigura();
                    if(blockingFigura!=null)
                    {
                        if(blockingFigura.isFekete== this.isFekete)
                            hataroszlopBal = oszlop+1;
                        else hataroszlopBal = oszlop;
                        break;
                    }
                }
                return mezo.oszlop>=hataroszlopBal;


            case "fel":
                int hatarsorFel=0;
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
                return mezo.sor >= hatarsorFel;


            case "le":
                int hatarsorLe=7;
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
                return hatarsorLe >= mezo.sor;

        }
        return false;
    }

}
