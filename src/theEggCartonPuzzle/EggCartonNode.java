package theEggCartonPuzzle;

import java.util.ArrayList;

import generalSimulatedAnnealing.Node;

public class EggCartonNode implements Node{

	private final int K=2, M=5, N=5;
	
	public static EggCartonNode getP() {
		return new EggCartonNode();
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getObjectiveFunction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Node> getNeighbours() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getBestNeighbour() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTarget() {
		// TODO Auto-generated method stub
		return Math.min(M,N)*K; // This is the optimal solution of the problem
	}

}
