package Project;

import java.util.Comparator;

public class OrganismComparator implements Comparator<Organism> {
    @Override
    public int compare(Organism o1, Organism o2) {
        if (o1.getInitiative() > o2.getInitiative()) {
            return -1;
        }

        if (o2.getInitiative() < o2.getInitiative()) {
            return 1;
        }
        return 0;
    }
}
