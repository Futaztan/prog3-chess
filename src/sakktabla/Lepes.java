package sakktabla;

public class Lepes {

    private int honnanSor;
    private int hovaSor;
    private char honnanBetu;
    private char hovaBetu;

    public Lepes(int o1, int s1, int o2, int s2)
    {
        honnanSor =s1+1;
        hovaSor = s2+1;
        honnanBetu = (char) ('a' + o1);
        hovaBetu = (char) ('a' + o2);
    }

    @Override
    public String toString() {
        return ""+honnanBetu + honnanSor + " " + hovaBetu + hovaSor;
    }
}
