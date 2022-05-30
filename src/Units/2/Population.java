import java.util.Random;

public class Population {

    public MyLinkedList<Genome> population;
    public Genome mostFit;
    private int size;
    private double mutationRate;

    public Population() {
        this.size = 100;
        this.mutationRate = 0.05;
        this.population = new MyLinkedList<>();
        for (int i = 0; i < size; i++) {
            this.population.add(new Genome());
        }
        this.mostFit = population.iterator().next();
    }

    public void nextGeneration() {
        Random rand = new Random();
        Genome clone;
        Genome other;
        for (int i = 0; i < 50; i++) {
            int j = rand.nextInt(50) + 50;
            clone = new Genome(population.get(j));
            if (rand.nextInt(2) == 1) {
                other = new Genome(population.get(j));
                clone.crossover(other);
            }
            clone.mutate(mutationRate);
            population.set(i, new Genome(clone));
        }
        population.sort();
        // checks if the most fit genome is the same as the previous most fit genome
        this.mostFit = population.get(this.size - 1);
    }

    public String toString() {
        return "(\"" + mostFit.toString() + "\", " + mostFit.fitness() + ")";
    }
}
