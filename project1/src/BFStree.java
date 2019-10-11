import java.util.LinkedList;

public class BFStree {
	Problem1 p;
	public Node rootNode;
	public int visitedNodes=0,expandedNodes=0;
	public static int maxCapacity=0;
	static boolean javab = false;
	public static LinkedList<Node> q;
	Node ans;
	long startTime, endTime;
	static long totalTime;

	public BFStree(){
		p = new Problem1();
		rootNode = new Node(null);
		q=new LinkedList<Node>();
		ans = new Node(null);
	}
	
	public void setRootNode()
	{
		rootNode.setState(p.initialState());
	}
	
	public boolean expandChild(Node n)
	{
		expandedNodes++;
		ActionSet actionSet = p.actions(n.s);
		for(int a=0;a<actionSet.actions.size();a++){
			Node child = new Node(null);
			visitedNodes++;
			child.setState(p.result(n.s, actionSet.actions.get(a)));
			child.addPath(n, actionSet.actions.get(a));
			if(p.goalTest(child.s)){
				ans = child;
				return true;
			}
			else{
				q.add(q.size(), child);
			}
		}
		return false;
	}
	
	public void bfs()
	{
		setRootNode();
		q.addFirst(rootNode);
		visitedNodes++;
		if(p.goalTest(rootNode.s)){
			ans.setState(rootNode.s);
			return;
		}
		
		while(!q.isEmpty())
		{
			capacity();
			Node n=(Node)q.removeFirst();
			if(expandChild(n)){
				javab = true;
				endTime = System.nanoTime();
				totalTime = endTime - startTime;
				return;
			}
		}
	}
	
	private void capacity(){
		if(q.size()>maxCapacity)
			maxCapacity = q.size();
	}
	
	private void printNode2(Node n){
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
		BFStree bfs = new BFStree();
		bfs.bfs();
		if(javab){
			System.out.println("BFS tree : ");
			System.out.println("Visited Nodes: "+bfs.visitedNodes);
			System.out.println("Expanded Nodes: "+bfs.expandedNodes);
			bfs.printNode2(bfs.ans);
			System.out.println("Path Cost: "+bfs.p.pathCost(bfs.ans.path));
			System.out.println("Total Time: "+totalTime);
			System.out.println("Max Capacity for e and f: "+maxCapacity);
			System.out.println("Max Capacity for path in nodes: "+bfs.ans.path.size());
		}
		else
			System.out.println("cannot find answer");
	}
}
