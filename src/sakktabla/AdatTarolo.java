package sakktabla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AdatTarolo implements Serializable {
    private List<Lepes> lepesek = new ArrayList<Lepes>();
    private boolean feketeJon;

    public AdatTarolo(List<Lepes> l, boolean fjon)
    {
        lepesek=l;
        feketeJon=fjon;
    }
}
