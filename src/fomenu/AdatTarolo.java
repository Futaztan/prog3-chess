package fomenu;

import sakktabla.Lepes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A mentéshez szükséges adatokat menti le
 * Meccs betöltés és a visszanézés is ezt használja
 */
public class AdatTarolo implements Serializable {
    private List<Lepes> lepesek = new ArrayList<Lepes>();
    private boolean feketeJon;
    private String fehernev;
    private String feketenev;


    public AdatTarolo(List<Lepes> l, boolean fjon,String feher, String fekete,boolean vege)
    {
        lepesek=l;
        feketeJon=fjon;
        fehernev=feher;
        feketenev=fekete;

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
