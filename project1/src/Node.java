import java.util.LinkedList;

public class Node{
	State s;
	LinkedList<Action> path;
	public Node(State s)
	{
		this.s = s;
		this.path = new LinkedList<Action>();
	}
	public void setState(State s)
	{
		this.s = s;
	}
	public void addPath(Node n ,Action a){
		this.path.addAll(n.path);
		this.path.add(a);
	}
}
