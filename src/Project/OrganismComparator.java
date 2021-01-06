package Project;

import java.util.Comparator;

public class OrganismComparator implements Comparator<Organism> {
    @Override
    public int compare(Organism o1, Organism o2) {
        if (o1.getInitiative() > o2.getInitiative()) {
            return -1;
        }

        if (o1.getInitiative() < o2.getInitiative()) {
            return 1;
        }

        if (o1 == o2) {
            return 0;
        }

        return 1;
    }
}
