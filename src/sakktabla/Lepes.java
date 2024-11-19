package sakktabla;

import java.io.Serializable;

public class Lepes implements Serializable {

    private int honnanSor;
    private int hovaSor;
    private char honnanBetu;
    private char hovaBetu;
    private int honnanOszlop;
    private int hovaOszlop;
    //sakk formátumú lépéseket valósítja meg
    public Lepes(int o1, int s1, int o2, int s2)
    {
        honnanOszlop = o1;
        hovaOszlop = o2;
        honnanSor =s1+1;
        hovaSor = s2+1;
        honnanBetu = (char) ('a' + honnanOszlop);
        hovaBetu = (char) ('a' + hovaOszlop);

    }

    public String getHonnan()
    {
        return "" +honnanOszlop+honnanSor;
    }
    public String getHova()
    {
        return ""+hovaOszlop+hovaSor;
    }

    @Override
    public String toString() {
        return ""+honnanBetu + honnanSor + " " + hovaBetu + hovaSor;
    }
}
