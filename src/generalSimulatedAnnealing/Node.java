package generalSimulatedAnnealing;

import java.util.ArrayList;
import java.util.Comparator;

public interface Node {

	public int compareTo(Node arg0); // It's simple to find the best child when I can compare them :D

	public double getObjectiveFunction(); // This is the objective function of the nodes

	public Node getBestNeighbour();

	public double getTarget();

	public Node getRandomNeighbour();
	
	public double getDt();
	
	public double gettMax();
}
