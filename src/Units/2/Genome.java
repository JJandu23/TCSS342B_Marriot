import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Genome implements Comparable<Genome> {

    protected MyLinkedList<Character> genes = new MyLinkedList<>();
    private final MyLinkedList<Character> target;
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

        // Add a random character to the genes
        if (rand.nextDouble() < mutationRate) {
            if (genes.size() == 0) {
                genes.add(c);
            } else {
                genes.add(c, index);
            }
        }

        // Delete random char
        if (rand.nextDouble() < mutationRate && genes.size() >= 1) {
            genes.remove(index);
        }

        // Run through list and replace random char with random char
        for (int i = 0; i < genes.size(); i++) {
            if (rand.nextDouble() < mutationRate) {
                genes.set(i, c);
            }
        }
    }

    // updates its genes to be the child of its genes and the other genome's genes
    public void crossover(Genome parent) {
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

    // returns the fitness of the genome
    public int fitness() {
        int l = (Math.abs(target.size() - genes.size()) * 2);
        int min = Math.min(target.size(), genes.size());
        for (int i = 0; i < min; i++) {
            if (!(genes.get(i).equals(target.get(i)))) {
                l++;
            }
        }
        return (-1 * l);
    }

    public int compareTo(Genome other) {
        return this.fitness() - other.fitness();
    }

    public String toString() {
        return genes.toString();
    }
}
