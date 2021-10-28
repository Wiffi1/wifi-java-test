package animals.sort;

import java.util.Comparator;

// Die Klasse kann für das Wortern von AnimmalObbjeken verwenet werden
// damit werden andere Reihenfogen unterstützt als die Standarsortierreihenfolge
public class SortiereNachGewicht implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
//        int ret = o1.getWeight() - o2.getWeight();
        // oder:
        int ret = Integer.compare(o1.getWeight(), o2.getWeight());
        System.out.printf("compare %d (%s) und %d (%s): %d\n",
                o1.getWeight(), o1.getName(), o2.getWeight(), o2.getName(), ret );
        return ret;
    }
}
