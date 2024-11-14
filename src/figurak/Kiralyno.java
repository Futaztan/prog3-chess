package figurak;

import javax.swing.*;
import  sakktabla.*;

public class Kiralyno extends Figura {


    public Kiralyno(boolean fekete,int sor,int oszlop )
    {
        super(fekete, sor, oszlop);
        if(isFekete) {
            ikon= new ImageIcon(this.getClass().getResource("/ikonok/fekete-kiralyno.png"));
        }
        else ikon = new ImageIcon(this.getClass().getResource("/ikonok/feher-kiralyno.png"));
    }
    @Override
    public boolean lepes(Mezo mezo,Mezo[][] matrix) {
        if(lepesCheck(mezo, matrix))
        {
            matrix[this.sor][this.oszlop].setFigura(null);
            mezo.setFigura(this);
            this.sor=mezo.sor;
            this.oszlop=mezo.oszlop;
            return true;
        }
        return false;
    }

    @Override
    public boolean lepesCheck(Mezo mezo, Mezo[][] matrix) {
        return isAtloLepes(mezo, matrix) || isOszlopSorLepes(mezo,matrix);
    }

    public boolean isOszlopSorLepes(Mezo mezo, Mezo[][] matrix){

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


    public boolean isAtloLepes(Mezo mezo,Mezo[][] matrix)
    {
        int hatarJobbLe=7;
        int hatarJobbFel=7;
        int hatarBalLe=7;
        int hatarBalFel=7;
        int kulonb = mezo.oszlop - this.oszlop;
        String mezopozicio;
        if(mezo.sor == this.sor+kulonb || mezo.sor ==this.sor-kulonb)
        {
            if(mezo.sor<this.sor)
            {
                if(mezo.oszlop>this.oszlop) mezopozicio="jobbfel";
                else mezopozicio="balfel";
            }
            else if(mezo.oszlop>this.oszlop) mezopozicio="jobble";
            else mezopozicio="balle";
        }
        else return false;

        int sorkulonb = 1;
        Figura blockingFigura;
        switch (mezopozicio) {
            case "jobbfel":
                for (int oszlop = this.oszlop + 1; oszlop <= mezo.oszlop; oszlop++) {
                    // if(this.sor+sorkulonb>7) break;
                    blockingFigura = matrix[this.sor - sorkulonb][oszlop].getFigura();
                    if (blockingFigura != null) {
                        if (blockingFigura.isFekete == this.isFekete) {
                            hatarJobbFel = sorkulonb - 1;
                        } else hatarJobbFel = sorkulonb;
                        break;
                    }
                    sorkulonb++;
                }

                if (Math.abs(this.sor - mezo.sor) <= hatarJobbFel) return true;

                break;
            case "jobble":
                for (int oszlop = this.oszlop + 1; oszlop <= mezo.oszlop; oszlop++) {
                    if(this.sor+sorkulonb>7) break;
                    blockingFigura = matrix[this.sor + sorkulonb][oszlop].getFigura();
                    if (blockingFigura != null) {
                        if (blockingFigura.isFekete == this.isFekete) {
                            hatarJobbLe = sorkulonb - 1;
                        } else hatarJobbLe = sorkulonb;
                        break;
                    }
                    sorkulonb++;

                }
                if (Math.abs(this.sor - mezo.sor) <= hatarJobbLe) return true;
                break;
            case "balfel":

                for (int oszlop = this.oszlop - 1; oszlop >= mezo.oszlop; oszlop--) {
                    // if(this.sor+sorkulonb>7) break;
                    blockingFigura = matrix[this.sor - sorkulonb][oszlop].getFigura();
                    if (blockingFigura != null) {
                        if (blockingFigura.isFekete == this.isFekete) {
                            hatarBalFel = sorkulonb - 1;
                        } else hatarBalFel = sorkulonb;
                        break;
                    }
                    sorkulonb++;
                }
                if (Math.abs(this.sor - mezo.sor) <= hatarBalFel) return true;
                break;
            case "balle":

                for (int oszlop = this.oszlop - 1; oszlop >= mezo.oszlop; oszlop--) {
                    if(this.sor+sorkulonb>7) break;
                    blockingFigura = matrix[this.sor + sorkulonb][oszlop].getFigura();
                    if (blockingFigura != null) {
                        if (blockingFigura.isFekete == this.isFekete) {
                            hatarBalLe = sorkulonb - 1;
                        } else hatarBalLe = sorkulonb;
                        break;
                    }
                    sorkulonb++;
                }
                if (Math.abs(this.sor - mezo.sor) <= hatarBalLe) return true;
                break;

        }

        return false;
    }

}
