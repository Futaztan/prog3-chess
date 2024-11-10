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

        if(isAtloLepes(mezo,matrix))
        {
            mezo.setFigura(this);
            this.sor=mezo.sor;
            this.oszlop=mezo.oszlop;
            return true;
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

                return Math.abs(this.sor - mezo.sor) <= hatarJobbFel;


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
                return Math.abs(this.sor - mezo.sor) <= hatarJobbLe;

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
                return Math.abs(this.sor - mezo.sor) <= hatarBalFel;

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
                return Math.abs(this.sor - mezo.sor) <= hatarBalLe;


        }

        return false;
    }


}
