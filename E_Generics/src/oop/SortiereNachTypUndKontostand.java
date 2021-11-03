package oop;

import java.util.Comparator;

public class SortiereNachTypUndKontostand implements Comparator<Bankkonto> {
    @Override
    public int compare(Bankkonto o1, Bankkonto o2) {
        int ret = o1.getKontotyp().compareTo(o2.getKontotyp());
        if (ret == 0) {
            ret = Double.compare(o2.getKontostand(), o1.getKontostand());
        }
        return ret;
    }
}
