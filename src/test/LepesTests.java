package test;

import figurak.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sakktabla.Mezo;
import static org.junit.jupiter.api.Assertions.*;

public class LepesTests {
    static Mezo[][] matrix = new Mezo[8][8];
    static Bastya bastya;
    static Futo futo;
    static Huszar huszar;
    static Kiraly kiraly;
    static Kiralyno kiralyno;
    static Paraszt paraszt;

    @BeforeAll
    public static void tablaSetup()
    {
        for(int row=0; row<4;row++)
        {
            for(int col =0; col<8; col++)
            {
                matrix[row][col] = new Mezo(null,row,col);
            }

        }
        matrix[4][0] = new Mezo(new Bastya(false,4,0),4,0);
        matrix[4][1] = new Mezo(new Futo(false,4,1),4,1);
        matrix[4][2] = new Mezo(new Huszar(false,4,2),4,2);
        matrix[4][3] = new Mezo(new Kiraly(false,4,3),4,3);
        matrix[4][4] = new Mezo(new Kiralyno(false,4,4),4,4);
        matrix[4][5] = new Mezo(new Paraszt(false,4,5),4,5);
        matrix[4][6] = new Mezo(null,4,6);
        matrix[4][7] = new Mezo(null,4,7);
        bastya = (Bastya) matrix[4][0].getFigura();
        futo = (Futo) matrix[4][1].getFigura();
        huszar = (Huszar) matrix[4][2].getFigura();
        kiraly = (Kiraly) matrix[4][3].getFigura();
        kiralyno = (Kiralyno) matrix[4][4].getFigura();
        paraszt = (Paraszt) matrix[4][5].getFigura();

        for(int row=5; row<8;row++)
        {
            for(int col =0; col<8; col++)
            {
                matrix[row][col] = new Mezo(null,row,col);
            }

        }

    }

//    Az összes tipusú figura a 4. sorba helyezkedik el, a következő sorrendben:
//    BÁSTYA-FUTÓ-HUSZÁR-KIRÁLY-KIRÁLYNŐ-PARASZT-X-X (X= üres mező)



    @Test
    public void bastyaLepesTest()
    {
        Mezo hova = matrix[0][0];
        assertTrue(bastya.lepesCheck(hova,matrix));
        hova = matrix[7][0];
        assertTrue(bastya.lepesCheck(hova,matrix));
        hova = matrix[4][7];
        assertFalse(bastya.lepesCheck(hova,matrix));
        hova = matrix[6][4];
        assertFalse(bastya.lepesCheck(hova,matrix));
    }

    @Test
    public void futoLepesTest()
    {
        Mezo hova = matrix[3][0];
        assertTrue(futo.lepesCheck(hova,matrix));
        hova = matrix[3][1];
        assertFalse(futo.lepesCheck(hova,matrix));
        hova = matrix[3][2];
        assertTrue(futo.lepesCheck(hova,matrix));

        hova = matrix[5][0];
        assertTrue(futo.lepesCheck(hova,matrix));
        hova = matrix[5][1];
        assertFalse(futo.lepesCheck(hova,matrix));
        hova = matrix[5][2];
        assertTrue(futo.lepesCheck(hova,matrix));
    }

    @Test
    public void huszarLepesTest()
    {
        Mezo hova = matrix[3][0];
        assertTrue(huszar.lepesCheck(hova,matrix));
        hova = matrix[4][0];
        assertFalse(huszar.lepesCheck(hova,matrix));
        hova = matrix[5][0];
        assertTrue(huszar.lepesCheck(hova,matrix));

        hova = matrix[2][1];
        assertTrue(huszar.lepesCheck(hova,matrix));
        hova = matrix[2][2];
        assertFalse(huszar.lepesCheck(hova,matrix));
        hova = matrix[2][3];
        assertTrue(huszar.lepesCheck(hova,matrix));

    }

    @Test
    public void kiralyLepesTest()
    {
        Mezo hova = matrix[3][3];
        assertTrue(kiraly.lepesCheck(hova,matrix));
        hova = matrix[5][2];
        assertTrue(kiraly.lepesCheck(hova,matrix));
        hova = matrix[4][2];
        assertFalse(kiraly.lepesCheck(hova,matrix)); //false mert áll ott egy másik fekete figura
    }

    @Test
    public void kiralynoLepesTest()
    {
        Mezo hova = matrix[4][4];
        assertTrue(kiralyno.lepesCheck(hova,matrix));
        hova = matrix[7][4];
        assertTrue(kiralyno.lepesCheck(hova,matrix));
        hova = matrix[4][7];
        assertFalse(kiralyno.lepesCheck(hova,matrix));

        hova = matrix[3][3];
        assertTrue(kiralyno.lepesCheck(hova,matrix));
        hova = matrix[3][4];
        assertTrue(kiralyno.lepesCheck(hova,matrix));
        hova = matrix[3][5];
        assertTrue(kiralyno.lepesCheck(hova,matrix));

        hova = matrix[5][3];
        assertTrue(kiralyno.lepesCheck(hova,matrix));
        hova = matrix[5][4];
        assertTrue(kiralyno.lepesCheck(hova,matrix));
        hova = matrix[5][5];
        assertTrue(kiralyno.lepesCheck(hova,matrix));

    }

    @Test
    public void parasztLepesTest()
    {
        Mezo hova = matrix[3][5];
        assertTrue(paraszt.lepesCheck(hova,matrix));
        hova = matrix[0][5];
        assertFalse(paraszt.lepesCheck(hova,matrix));
    }
}