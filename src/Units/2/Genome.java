import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Genome implements Comparable<Genome> {

    protected MyLinkedList<Character> genes = new MyLinkedList<>();
    private MyLinkedList<Character> target;
    public String name = "CHRISTOPHER PAUL MARRIOTT";
    public List<Character> Characters = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\'');

    public Genome() {
        genes = new MyLinkedList<>();
        this.target = new MyLinkedList<>();
        for (char c : name.toCharArray()) {
            this.target.add(c);
        }
    }

    public Genome(Genome genome) {
        this.target = genome.target;
        for (int i = 0; i < genome.genes.size(); i++) {
            genes.add(genome.genes.get(i));
        }
    }

    public void mutate(double mutationRate) {
        Random rand = new Random();
        Character c = Characters.get(rand.nextInt(Characters.size()));
        int index = rand.nextInt(genes.size() + 1);

        // Adds random char to random location
        if (rand.nextDouble() < mutationRate) {
            if (genes.size() == 0) {
                genes.add(c);
            } else {
                genes.add(c, index);
            }
        }

        // delete random char
        if (rand.nextDouble() < mutationRate && genes.size() >= 1) {
            genes.remove(index);
        }

        // run through the list and replace each char with a random char
        for (int i = 0; i < genes.size(); i++) {
            if (rand.nextDouble() < mutationRate) {
                genes.set(i, c);
            }
        }
    }

    public void crossover(Genome parent) {
        Random rand = new Random();
        MyLinkedList<Character> newGenes = new MyLinkedList<>();

        int max = Math.max(genes.size(), parent.genes.size());

        for (int i = 0; i < max; i++) {
            if (genes.iterator().hasNext() && i < genes.size()) {
                newGenes.add(genes.get(i));
            } else if (i < parent.genes.size()) {
                newGenes.add(parent.genes.get(i));
            }
        }
    }

    public int fitness() {
        int fit = (Math.abs(target.size() - genes.size()) * 2);
        int min = Math.min(target.size(), genes.size());
        for (int i = 0; i < min; i++) {
            if (!(genes.get(i).equals(target.get(i)))) {
                fit++;
            }
        }
        return (-1 * fit);
    }

    public int compareTo(Genome other) {
        return Integer.compare(this.fitness(), other.fitness());
    }

    public String toString() {
        return genes.toString();
    }
}
