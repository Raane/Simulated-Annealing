package generalSimulatedAnnealing;

public class SimulatedAnnealing {
	
	private final Node P;
	private final double T_MAX, DT;
	private double t;
	
	public SimulatedAnnealing(Node P, double T_MAX, double DT) {
		this.P = P;
		this.T_MAX = T_MAX;
		this.DT = DT;
	}
	
	public Node solve() {
		return solve(P,T_MAX);
	}

	private Node solve(Node p2, double t_MAX2) {
		return null;
	}
	
	
}
