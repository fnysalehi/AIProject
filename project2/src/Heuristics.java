import java.util.Comparator;

public class Heuristics implements Comparator<Node> {


	@Override
	public int compare(Node x, Node y) {
		
		long gestimate1 = (new Problem2()).pathCost(x.path), gestimate2 =  (new Problem2()).pathCost(y.path); //get path cost so far
		long hestimate1 = (new Problem2()).Heuristic(x.s),hestimate2 = (new Problem2()).Heuristic(y.s);
		long estimate1 = hestimate1 + gestimate1;
		long estimate2 = hestimate2 + gestimate2;
		if (estimate1 > estimate2){
			return 1;
		}
		else if (estimate1 < estimate2){
			return -1;
		}
		else{
			return 0; //do nothing if the mode is invalid
		}
	}

}