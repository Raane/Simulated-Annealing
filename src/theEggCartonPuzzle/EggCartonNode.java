package theEggCartonPuzzle;

import java.util.ArrayList;
import java.util.Collections;

import generalSimulatedAnnealing.Node;
import generalSimulatedAnnealing.SimulatedAnnealing;

public class EggCartonNode implements Node, Comparable<EggCartonNode>{

	private final int K=2, M=5, N=5, COMPARE_ROUNDING = 6;
	private ArrayList<EggCartonNode> neighbours = new ArrayList<EggCartonNode>();
	private EggCartonNode bestNode = null;
	private Double objectiveFunction = null;
	private boolean[][] eggCarton = new boolean[M][N];
	
	private static final int STEPS = 100; 				// This limits how many steps the program is willying to search.
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

	public EggCartonNode(boolean[][] eggCarton) {
		this.eggCarton = eggCarton;
	}

	private ArrayList<EggCartonNode> getNeighbours() {
		if(neighbours.size()==0) { // If the neighbours is not generated yet, we must do so.
			
			// I generate each new neighbour by changing the state of one cell in the carton
			for(int n=0;n<N;n++) {
				for(int m=0;m<M;m++) {
					
					//Make a copy of the eggCarton
					boolean[][] newEggCarton = new boolean[M][N];
					for(int nn=0;nn<N;nn++) {
						for(int mm=0;mm<M;mm++) {
							newEggCarton[mm][nn] = eggCarton[mm][nn];
						}
					}
					
					// Change the state of one cell
					newEggCarton[m][n] = !eggCarton[m][n];
					neighbours.add(new EggCartonNode(newEggCarton));
				}
			}
		}
		return neighbours;
	}
	
	private double calculateObjectiveFunction() { // This calculates the F(P), where P=this
		
		/*
		 * 
		 * This works by counting the eggs and giving a value based on the number of eggs.  
		 * It also counts the eggs in each column, row and diagonal and reduces the score 
		 * based on the number of extra eggs found in the carton.
		 * 
		 */
		
		int numberOfEggs = 0;
		for(int n=0;n<N;n++) for(int m=0;m<M;m++) if(eggCarton[m][n])numberOfEggs++; // Counts the eggs
		
		int numberOfErrors = 0;
		//Horizontal error count
		for(int n=0;n<N;n++) {
			int numberOfEggsInRow = 0;
			for(int m=0;m<M;m++) {
				if(eggCarton[m][n])numberOfEggsInRow++;
			}
			if(numberOfEggsInRow>K) numberOfErrors += numberOfEggsInRow-K;
		}
		//Vertical error count
		for(int m=0;m<N;m++) {
			int numberOfEggsInCol = 0;
			for(int n=0;n<M;n++) {
				if(eggCarton[n][n])numberOfEggsInCol++;
			}
			if(numberOfEggsInCol>K) numberOfErrors += numberOfEggsInCol-K;
		}
		// Diagonal error count
		for(int m=0-N;m<M+N;m++) {
			for(int i=0;i<2;i++) {
				int numberOfEggsInDiag = 0;
				for(int n=0;n<N;n++) {
					if( n>=0 && n<N && m>=0 && m<M ) {
						if(eggCarton[m][n])numberOfEggsInDiag++;
					}
				}
				if(numberOfEggsInDiag>K)numberOfErrors+=numberOfEggsInDiag-K;
			}
		}
		
		double errorReductionCoefficient = 0.5;		// This determine how much the score is reduces from one error.
		double wrongNumberOfEggsCoefficient = 1;	// This determine how much the score is reduces from each missing or extra egg.
		
		return 1 - (Math.abs(numberOfEggs-getTarget())*wrongNumberOfEggsCoefficient+numberOfErrors*errorReductionCoefficient);
	}	
	
	public static EggCartonNode getP() { // This generates a startingpoint for SA
		return new EggCartonNode();
	}
	
	@Override
	public double getObjectiveFunction() {
		if(objectiveFunction==null) {
			this.objectiveFunction = calculateObjectiveFunction();
		}
		return (double)(objectiveFunction);
	}

	@Override
	public Node getBestNeighbour() {
		ArrayList<EggCartonNode> neighbours = getNeighbours();
		if(bestNode==null && neighbours.size()!=0) {
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
	
	@Override
	public String toString() {
		String carton = "+ ";
		for(int m=0;m<M;m++) carton += "- ";
		carton += "+" + System.getProperty("line.separator");
		for(int n=0;n<N;n++) {
			carton += "| ";
			for(int m=0;m<M;m++) carton+= (eggCarton[m][n])?"O ":"' ";
			carton += "|" + System.getProperty("line.separator");
		}
		carton += "+ ";
		for(int m=0;m<M;m++) carton += "- ";
		carton += "+";
		return carton;
	}
	
	public static void main(String[] args) {
		SimulatedAnnealing sa = new SimulatedAnnealing(getP(), T_MAX, DT);
		System.out.println(sa.solve());
	}
}
