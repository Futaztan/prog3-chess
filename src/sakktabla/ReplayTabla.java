package sakktabla;

import teszt.Jatekos;

public class ReplayTabla  extends Tabla{
    public ReplayTabla(Runnable onGameOverCallback, String feher, String fekete, Jatekos nyertes) {
        super(onGameOverCallback, feher, fekete, nyertes);
    }
}
