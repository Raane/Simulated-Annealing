package generalSimulatedAnnealing;

public class SimulatedAnnealing {
	
	private final Node P;
	private final double T_MAX, DT;
	private Node bestFoundNode = null;
	
	public SimulatedAnnealing(Node P) {
		this.P = P;
		this.bestFoundNode = P;
		this.T_MAX = P.gettMax();
		this.DT = P.getDt();
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
		if(PMax.getObjectiveFunction()>=PMax.getTarget()) {
			System.out.println("Found an optimal solution! Search depth: " + (int)((P.gettMax()-T)/P.getDt()));
			return PMax;								// If the node match the target we have found a solution and can return it
		}
		if(PMax.getObjectiveFunction()>bestFoundNode.getObjectiveFunction())  {
			bestFoundNode = PMax;	// If this node is the best until now we save it
		}
		if(T<0) {
			System.out.println("Temperature hit 0 without finding and optimal solution. F(Target)=" + bestFoundNode.getTarget() + " My best solution was is F(P)=" + bestFoundNode.getObjectiveFunction());
			return bestFoundNode;																// If T<0 we end the search and return the best we have found until now
		}
		if(x>p) {
			System.out.println("Exploit, T=" + T);
			return solve(PMax, T-DT);																// Exploition
		} else {
			System.out.println("Explore, T=" + T);
			return solve(P.getRandomNeighbour(), T-DT);												// Exploration
		}
	}
}
