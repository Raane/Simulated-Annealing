package theEggCartonPuzzle;

import java.util.ArrayList;
import java.util.Collections;

import generalSimulatedAnnealing.Node;
import generalSimulatedAnnealing.SimulatedAnnealing;

public class EggCartonNode implements Node, Comparable<EggCartonNode>{

	private final int K=2, M=5, N=5, COMPARE_ROUNDING = 6;
	private ArrayList<EggCartonNode> neighbours = null;
	private EggCartonNode bestNode = null;
	private Double objectiveFunction = null;
	private boolean[][] eggCarton = new boolean[M][N];
	
	private static final int STEPS = 50000; 				// This limits how many steps the program is willying to search.
	private static final double T_MAX = 1, DT = T_MAX/STEPS;// This is the starting maximum temperature.
	
	public EggCartonNode() {
		for(int n=0;n<N;n++) {	
			boolean[] line = new boolean[M];
			int eggsPlaced = 0;
			while(eggsPlaced<K) {
				int candidate = (int)(Math.random()*M);
				if(!eggCarton[candidate][n]) {
					eggCarton[candidate][n] = true;
					eggsPlaced++;
				}
			}
		}
	}
	
	private ArrayList<EggCartonNode> getNeighbours() {
		if(neighbours==null) {
			//TODO Find neighbours
		}
		return neighbours;
	}
	
	private double calculateObjectiveFunction() { // This calculates the F(P), where P=this
		return 0;
		// TODO make the objective function
	}	
	
	public static EggCartonNode getP() { // This generates a startingpoint for SA
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
	
	public static void main(String[] args) {
		SimulatedAnnealing sa = new SimulatedAnnealing(getP(), T_MAX, DT);
		System.out.println(sa.solve());
	}
}
