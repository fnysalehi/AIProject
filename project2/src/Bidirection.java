import java.util.LinkedList;

public class Bidirection {
	Problem2 p = new Problem2();
	public Node rootNodeStart,rootNodeEnd;
	public int visitedNodes=0,expandedNodes=0;
	public static int maxCapacity=0;
	static boolean javab = false;
	public static LinkedList<Node> f1,f2,e1,e2;
	Node ans1,ans2;
	long startTime, endTime;
	static long totalTime;

	public Bidirection(){
		p = new Problem2();
		rootNodeStart = new Node(null);
		rootNodeEnd = new Node(null);
		f1=new LinkedList<Node>();
		f2=new LinkedList<Node>();
		e1=new LinkedList<Node>();
		e2=new LinkedList<Node>();
		ans1 = new Node(null);
		ans2 = new Node(null);
	}
	public void setRootNodeStart()
	{
		rootNodeStart.setState(p.initialState());
	}
	public void setRootNodeEnd()
	{
		rootNodeEnd.setState(p.goal());
	}
	
	public boolean expandChild(Node n,int num)
	{
		expandedNodes++;
		ActionSet actionSet = p.actions(n.s);
		for(int a=0;a<actionSet.actions.size();a++){
			if(p.result(n.s, actionSet.actions.get(a))!= null){
				visitedNodes++;
				Node child = new Node(null);
				child.setState(p.result(n.s, actionSet.actions.get(a)));
				child.addPath(n, actionSet.actions.get(a));
				if(num==1){
					if(contains2(child)){
						ans1 = child;
						ans2 = containsNode2(child);
						return true;
					}
					else{
						if(!contains1(child)){
							f1.addLast(child);
						}
					}
				}
				else{
					if(contains1(child)){
						ans2 = child;
						ans1 = containsNode1(child);
						return true;
					}
					else{
						if(!contains2(child)){
							f2.addLast(child);
						}
					}
				}
			}
		}
		return false;
	}
	
	public void bs()
	{
		setRootNodeStart();
		setRootNodeEnd();
		f1.addFirst(rootNodeStart);
		f2.addFirst(rootNodeEnd);
		visitedNodes++;
		if(p.goalTest(rootNodeStart.s)){
			ans1.setState(rootNodeStart.s);
			ans2.setState(rootNodeEnd.s);
			return;
		}
		
		while(!f1.isEmpty()&&!f2.isEmpty())
		{
			capacity();
			Node n=(Node)f1.removeFirst();
			Node m=(Node)f2.removeFirst();
			if(n.s.sequal(m.s)){
				ans1 = n;
				ans2 = m;
			}	
			e1.addLast(n);
			e2.addLast(m);
			if(expandChild(n,1)){
				javab = true;
				endTime = System.nanoTime();
				totalTime = endTime - startTime;
				return;
			}
			if(expandChild(m,2)){
				javab = true;
				endTime = System.nanoTime();
				totalTime = endTime - startTime;
				return;
			}
		}
	}
	
	private boolean contains1(Node n)
	{
		for(Node m : e1){
			if(n.s.sequal(m.s))
				return true;
		}
		for(Node m : f1){
			if(n.s.sequal(m.s))
				return true;
		}
		return false;
	}
	
	private boolean contains2(Node n)
	{
		for(Node m : e2){
			if(n.s.sequal(m.s))
				return true;
		}
		for(Node m : f2){
			if(n.s.sequal(m.s))
				return true;
		}
		return false;
	}
	
	private Node containsNode1(Node n)
	{
		for(Node m : e1){
			if(n.s.sequal(m.s))
				return m;
		}
		for(Node m : f1){
			if(n.s.sequal(m.s))
				return m;
		}
		return null;
	}
	
	private Node containsNode2(Node n)
	{
		for(Node m : e2){
			if(n.s.sequal(m.s))
				return m;
		}
		for(Node m : f2){
			if(n.s.sequal(m.s))
				return m;
		}
		return null;
	}
	private void capacity(){
		if(e1.size()+f1.size()+e2.size()+f2.size()>maxCapacity)
			maxCapacity = e1.size()+f1.size()+e2.size()+f2.size();
	}
	private void printNode(Node n1,Node n2)
	{
		System.out.println("Best Path: ");
		State prev= rootNodeStart.s;
		rootNodeStart.s.print();
		System.out.println("");
		for(int i=0;i<n1.path.size();i++){
			p.result(prev, n1.path.get(i)).print();
			prev = p.result(prev, n1.path.get(i));
			System.out.println("");
		}
		System.out.println("");
		for(int i=n2.path.size()-1;i>-1;i--){
			p.result(prev, n2.path.get(i).inverse()).print();
			prev = p.result(prev, n2.path.get(i).inverse());
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		Bidirection bs = new Bidirection();
		int cost=0;
		bs.bs();
		if(javab){
			System.out.println("Bidirection : ");
			System.out.println("Visited Nodes: "+bs.visitedNodes);
			System.out.println("Expanded Nodes: "+bs.expandedNodes);
			bs.printNode(bs.ans1,bs.ans2);
			cost = bs.p.pathCost(bs.ans1.path)+bs.p.pathCost(bs.ans2.path);
			System.out.println("Path Cost: "+cost);
			System.out.println("Total Time: "+totalTime);
			System.out.println("Max Capacity for e and f: "+maxCapacity);
			System.out.println("Max Capacity for path in nodes: "+(bs.ans2.path.size()+bs.ans1.path.size()));		}
		else
			System.out.println("cannot find answer");
	}
}
