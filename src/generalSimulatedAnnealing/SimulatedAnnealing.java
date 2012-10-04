package generalSimulatedAnnealing;

public class SimulatedAnnealing {
	
	private final Node P;
	private final double T_MAX, DT;
	
	public SimulatedAnnealing(Node P, double T_MAX, double DT) {
		this.P = P;
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
		if(x>p) {
			return solve(PMax, T-DT);
		} else {
			return solve(P.getRandomNeighbour(), T-DT);
		}
	}
}