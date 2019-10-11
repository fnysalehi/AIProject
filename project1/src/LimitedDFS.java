import java.util.LinkedList;

public class LimitedDFS {
	Problem1 p;
	public Node rootNode;
	public int visitedNodes=0,expandedNodes=0;
	public static int maxCapacity=0;
	public int depth=0;
	public int limit=8;
	static boolean javab = false;
	public static LinkedList<Node> f,e;
	Node ans;
	long startTime, endTime;
	static long totalTime;
	
	public LimitedDFS(){
		p = new Problem1();
		rootNode = new Node(null);
		f=new LinkedList<Node>();
		e=new LinkedList<Node>();
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
		Node child = new Node(null);
		for(int a=0;a<actionSet.actions.size();a++){
			visitedNodes++;
			child.setState(p.result(n.s, actionSet.actions.get(a)));
			child.addPath(n, actionSet.actions.get(a));
			if(p.goalTest(child.s)){
				ans = child;
				return true;
			}
			else{
				if(!eContains(child) && !fContains(child)){
				
					f.addLast(child);
				}
				child = new Node(null);
			}
		}
		return false;
	}
	
	//BFS traversal of a tree is performed by the bfs() function
	public void dfs()
	{
		setRootNode();
		f.add(rootNode);
		visitedNodes++;
		if(p.goalTest(rootNode.s)){
			javab = true;
			ans.setState(rootNode.s);
			return;
		}
		
		while(!f.isEmpty())
		{
			capacity();
			Node n=(Node)f.removeLast();
			e.addLast(n);
			if(n.path.size()<limit){
				if(expandChild(n)){
					javab = true;
					endTime = System.nanoTime();
					totalTime = endTime - startTime;
					return;
				}
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
		for(Node m : f){
			if(n.s.sequal(m.s))
				return true;
		}
		return false;
	}

	
	private void capacity(){
		if(f.size()+e.size()>maxCapacity)
			maxCapacity = f.size()+e.size();
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
		LimitedDFS dfs = new LimitedDFS();
		dfs.dfs();
		if(javab){
			System.out.println("Limited DFS : ");
			System.out.println("Visited Nodes: "+dfs.visitedNodes);
			System.out.println("Expanded Nodes: "+dfs.expandedNodes);
			dfs.printNode(dfs.ans);
			System.out.println("Path Cost: "+dfs.p.pathCost(dfs.ans.path));
			System.out.println("Total Time: "+totalTime);
			System.out.println("Max Capacity for e and f: "+maxCapacity);
			System.out.println("Max Capacity for path in nodes: "+dfs.ans.path.size());
		}
		else
			System.out.println("cannot find answer");
	}
}