package teszt;

public class Jatekos {
    public String nev;
    public int pont;
    public Jatekos(String n, int p)
    {
        nev=n;
        pont=p;
    }

    @Override
    public String toString() {
        return nev + " " + pont;
    }

    public int getPont() {
        return pont;
    }
}
