import java.util.LinkedList;

public class DFS {
	Problem2 p;
	public Node rootNode;
	public int visitedNodes=0,expandedNodes=0;
	public static int maxCapacity=0;
	static boolean javab = false;
	public static LinkedList<Node> f,e;
	Node ans;
	long startTime, endTime;
	static long totalTime;

	public DFS(){
		p = new Problem2();
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
					if(!eContains(child) && !fContains(child)){
						f.addLast(child);
					}
				}
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
			ans.setState(rootNode.s);
			return;
		}
		
		while(!f.isEmpty())
		{
			capacity();
			Node n=(Node)f.removeLast();
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
		for(Node m : f){
			if(n.s.sequal(m.s))
				return true;
		}
		return false;
	}
	private void capacity(){
		if(e.size()+f.size()>maxCapacity)
			maxCapacity = e.size()+f.size();
	}
	
	//Utility methods for printing the node's name
	private void printNode(Node n){
		System.out.println("Best Path: ");
		State prev= rootNode.s;
		rootNode.s.print();
		System.out.println("");
		for(int i=0;i<n.path.size();i++){
			p.result(prev, n.path.get(i)).print();
			prev = p.result(prev, n.path.get(i));
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		DFS dfs = new DFS();
		dfs.dfs();
		if(javab){
			System.out.println("DFS : ");
			System.out.println("Visited Nodes: "+dfs.visitedNodes);
			System.out.println("Expanded Nodes: "+dfs.expandedNodes);
			dfs.printNode(dfs.ans);
			System.out.println("Path Cost: "+dfs.p.pathCost(dfs.ans.path));
			System.out.println("Total Time: "+totalTime);
			System.out.println("Max Capacity for e and f: "+maxCapacity);
			System.out.println("Max Capacity for path in nodes: "+dfs.ans.path.size());		}
		else
			System.out.println("cannot find answer");
	}
}
