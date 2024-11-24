package sakktabla;

import java.io.Serializable;

/**
 * sakk formátumú lépéseket valósítja meg
 */
public class Lepes implements Serializable {

    private int honnanSor;
    private int hovaSor;
    private char honnanBetu;
    private char hovaBetu;
    private int honnanOszlop;
    private int hovaOszlop;

    public Lepes(int o1, int s1, int o2, int s2)
    {
        honnanOszlop = o1;
        hovaOszlop = o2;
        honnanSor =s1;
        hovaSor = s2;
        honnanBetu = (char) ('a' + honnanOszlop);
        hovaBetu = (char) ('a' + hovaOszlop);

    }

    public String getHonnan()
    {
        return "" +honnanSor+honnanOszlop;  //ezt vissza teszi a mátrixba
    }
    public String getHova()
    {
        return ""+hovaSor+hovaOszlop;
    }

    @Override
    public String toString() {
        return ""+honnanBetu + (honnanSor+1) + " " + hovaBetu + (hovaSor+1);
    }
}
