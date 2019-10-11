import java.util.LinkedList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class UniformCost {
	Problem1 p;
	public Node rootNode;
	public int visitedNodes=0,expandedNodes=0;
	public static int maxCapacity=0;
	static boolean javab = false;
	public static LinkedList<Node> e;
	Comparator<Node> comparator;
	PriorityQueue<Node> queue;
	Node ans;
	long startTime, endTime;
	static long totalTime;

	public UniformCost(){
		p = new Problem1();
		rootNode = new Node(null);
		e=new LinkedList<Node>();
		comparator = new PathComparator();
		queue = new PriorityQueue<Node>(1000,comparator);
		ans = new Node(null);
	}
	public void setRootNode()
	{
		rootNode.setState(p.initialState());
	}

	public class PathComparator implements Comparator<Node>
	{
	    @Override
	    public int compare(Node x, Node y)
	    {
	        if (p.pathCost(x.path) < p.pathCost(y.path))
	        {
	            return -1;
	        }
	        if (p.pathCost(x.path) > p.pathCost(y.path))
	        {
	            return 1;
	        }
	        return 0;
	    }
	}
	
	public boolean expandChild(Node n)
	{
		expandedNodes++;
		ActionSet actionSet = p.actions(n.s);
		for(int a=0;a<actionSet.actions.size();a++){
			if(p.result(n.s, actionSet.actions.get(a))!= null){
				visitedNodes++;
				Node child = new Node(null);
				child.setState(p.result(n.s, actionSet.actions.get(a)));
				child.addPath(n, actionSet.actions.get(a));
				if(p.goalTest(child.s)){
					ans = child;
					return true;
				}
				else{
					if(fContains(child)){
						for(Node m : queue){
							if(child.s.sequal(m.s))
								if(p.pathCost(child.path)<p.pathCost(m.path)){
									m.path.clear();
									m.path.addAll(child.path);
								}
						}
									
								
					}
					else if(!eContains(child) && !fContains(child)){
						queue.offer(child);

					}
					
				}
			}
		}
		return false;
	}

	
	//BFS traversal of a tree is performed by the bfs() function
	public void uf()
	{
		 //to measure solution time
		startTime = System.nanoTime();
		setRootNode();
		queue.add(rootNode);
		visitedNodes++;
		if(p.goalTest(rootNode.s)){
			ans.setState(rootNode.s);
			return;
		}
		
		while(!queue.isEmpty())
		{
			capacity();
			Node n=(Node)queue.poll();
			e.addLast(n);
			if(expandChild(n)){
				javab = true;
				endTime = System.nanoTime();
				totalTime = endTime - startTime;
				return;
			}
		}
	}
	
	private boolean eContains(Node n)
	{
		for(Node m : e){
			if(n.s.sequal(m.s))
				return true;
		}
		return false;
	}
	
	private boolean fContains(Node n)
	{
		for(Node m : queue){
			if(n.s.sequal(m.s))
				return true;
		}
		return false;
	}
	private void capacity(){
		if(e.size()+queue.size()>maxCapacity)
			maxCapacity = e.size()+e.size()+queue.size();
	}
	
	private void printNode(Node n){
		System.out.print("Best Path: ");
		State prev= rootNode.s;
		rootNode.s.print();
		for(int i=0;i<n.path.size();i++){
			p.result(prev, n.path.get(i)).print();
			prev = p.result(prev, n.path.get(i));
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		UniformCost uf = new UniformCost();
		uf.uf();
		if(javab){
			System.out.println("Uniform Cost : ");
			System.out.println("Visited Nodes: "+uf.visitedNodes);
			System.out.println("Expanded Nodes: "+uf.expandedNodes);
			uf.printNode(uf.ans);
			System.out.println("Path Cost: "+uf.p.pathCost(uf.ans.path));
			System.out.println("Total Time: "+totalTime);
			System.out.println("Max Capacity for e and f: "+maxCapacity);
			System.out.println("Max Capacity for path in nodes: "+uf.ans.path.size());
		}
		else
			System.out.println("cannot find answer");
	}
}
