package theEggCartonPuzzle;

import java.util.ArrayList;
import java.util.Collections;

import generalSimulatedAnnealing.Node;

public class EggCartonNode implements Node, Comparable<EggCartonNode>{

	private final int K=2, M=5, N=5, COMPARE_ROUNDING = 6;
	private ArrayList<EggCartonNode> neighbours = null;
	private EggCartonNode bestNode = null;
	private Double objectiveFunction = null; 
	
	private ArrayList<EggCartonNode> getNeighbours() {
		if(neighbours==null) {
			//TODO Find neighbours
		}
		return neighbours;
	}
	
	private double calculateObjectiveFunction() {
		return 0;
		// TODO make the objective function
	}	
	
	public static EggCartonNode getP() {
		return new EggCartonNode();
	}
	
	@Override
	public double getObjectiveFunction() {
		if(objectiveFunction==null) {
			calculateObjectiveFunction();
		}
		return (double)(objectiveFunction);
	}

	@Override
	public Node getBestNeighbour() {
		if(bestNode==null) {
			bestNode = Collections.max(getNeighbours());  
		}
		return bestNode;
	}

	@Override
	public double getTarget() {
		return Math.min(M,N)*K; // This is the optimal solution of the problem
	}

	@Override
	public Node getRandomNeighbour() {
		return getNeighbours().get((int)(Math.random()*getNeighbours().size()));
	}

	@Override
	public int compareTo(EggCartonNode otherEggCartonNode) {
		return (int)((this.getObjectiveFunction() - otherEggCartonNode.getObjectiveFunction())*Math.pow(10, COMPARE_ROUNDING));
	}

	@Override
	public int compareTo(Node otherNode) {
		return compareTo((EggCartonNode)(otherNode));
	}
}
