package generalSimulatedAnnealing;

public interface Node extends Comparable{

	public int compareTo(Object arg0); // It's simple to find the best child when I can compare them :D

	public double getObjectiveFunction(); // This is the objective function of the nodes
	
}
