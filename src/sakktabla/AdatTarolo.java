package sakktabla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AdatTarolo implements Serializable {
    private List<Lepes> lepesek = new ArrayList<Lepes>();
    private boolean feketeJon;
    private String fehernev;
    private String feketenev;
    private boolean isVege;

    public AdatTarolo(List<Lepes> l, boolean fjon,String feher, String fekete,boolean vege)
    {
        lepesek=l;
        feketeJon=fjon;
        fehernev=feher;
        feketenev=fekete;
        isVege =vege;
    }

    public String getFehernev() {
        return fehernev;
    }

    public String getFeketenev() {
        return feketenev;
    }

    public boolean isFeketeJon() {
        return feketeJon;
    }

    public List<Lepes> getLepesek() {
        return lepesek;
    }


}
