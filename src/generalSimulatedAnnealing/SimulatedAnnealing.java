package generalSimulatedAnnealing;

public class SimulatedAnnealing {
	
	private final Node P;
	private final double T_MAX, DT;
	private Node bestFoundNode = null;
	
	public SimulatedAnnealing(Node P, double T_MAX, double DT) {
		this.P = P;
		this.bestFoundNode = P;
		this.T_MAX = T_MAX;
		this.DT = DT;
	}
	
	public Node solve() {
		return solve(P,T_MAX);
	}

	private Node solve(Node P, double T) {
		double fp = P.getObjectiveFunction();
		if(fp>=P.getTarget()) {
			return P;
		}
		Node PMax = P.getBestNeighbour();
		double q = (PMax.getObjectiveFunction()-fp)/2;
		double p = Math.min(1,Math.exp(-q/T));
		double x = Math.random();
		if(PMax.getObjectiveFunction()>=PMax.getTarget()) return PMax;								// If the node match the target we have found a solution and can return it
		if(PMax.getObjectiveFunction()>bestFoundNode.getObjectiveFunction()) bestFoundNode = PMax;	// If this node is the best until now we save it
		if(T<0) return bestFoundNode;																// If T<0 we end the search and return the best we have found until now
		if(x>p) {
			return solve(PMax, T-DT);																// Exploition
		} else {
			return solve(P.getRandomNeighbour(), T-DT);												// Exploration
		}
	}
}
