import java.util.LinkedList;

public class Problem1 {
	
	public State initialState(){
		State s = new State("Arad");
		return s;
		
	}
	
	public ActionSet actions(State s){
		ActionSet a = new ActionSet();
		switch(s.name){
		case "Oradea":
			a.actions.add(new Action(0,71));
			a.actions.add(new Action(1,151));
			break;
		case "Zerind":
			a.actions.add(new Action(0,71));
			a.actions.add(new Action(2,75));
			break;
		case "Arad":
			a.actions.add(new Action(2,75));
			a.actions.add(new Action(3,118));
			a.actions.add(new Action(4,140));
			break;
		case "Timisoara":
			a.actions.add(new Action(5,111));
			a.actions.add(new Action(3,118));
			break;
		case "Lugoj":
			a.actions.add(new Action(5,111));
			a.actions.add(new Action(6,70));
			break;
		case "Mehadia":
			a.actions.add(new Action(6,70));
			a.actions.add(new Action(7,75));
			break;
		case "Dobreta":
			a.actions.add(new Action(7,75));
			a.actions.add(new Action(8,120));
			break;
		case "Sibiu":
			a.actions.add(new Action(10,80));
			a.actions.add(new Action(9,99));
			a.actions.add(new Action(4,140));
			a.actions.add(new Action(1,151));
			break;
		case "Rimnicu Vilcea":
			a.actions.add(new Action(10,80));
			a.actions.add(new Action(11,97));
			a.actions.add(new Action(12,146));
			break;
		case "Craiova":
			a.actions.add(new Action(8,120));
			a.actions.add(new Action(13,138));
			a.actions.add(new Action(12,146));
			break;
		case "Fagaras":
			a.actions.add(new Action(9,99));
			a.actions.add(new Action(14,211));
			break;
		case "Pitesti":
			a.actions.add(new Action(11,97));
			a.actions.add(new Action(15,101));
			a.actions.add(new Action(13,138));
			break;
		case "Bucharest":
			a.actions.add(new Action(17,85));
			a.actions.add(new Action(16,90));
			a.actions.add(new Action(15,101));
			a.actions.add(new Action(14,211));
			break;
		case "Giurgiu":
			a.actions.add(new Action(16,90));
			break;
		case "Urziceni":
			a.actions.add(new Action(17,85));
			a.actions.add(new Action(18,98));
			a.actions.add(new Action(19,142));
			break;
		case "Hirsova":
			a.actions.add(new Action(20,86));
			a.actions.add(new Action(18,98));
			break;
		case "Eforie":
			a.actions.add(new Action(20,86));
			break;
		case "Vaslui":
			a.actions.add(new Action(21,92));
			a.actions.add(new Action(19,142));
			break;
		case "Iasi":
			a.actions.add(new Action(22,87));
			a.actions.add(new Action(21,92));
			break;
		case "Neamt":
			a.actions.add(new Action(22,87));
			break;
		}
		return a;
		
	}
	
	public State result(State s,Action a){
		switch(s.name){
		case "Oradea":
			if(a.id==0)
				return (new State("Zerind")); 
			else
				return (new State("Sibiu"));
		case "Zerind":
			if(a.id==0)
				return (new State("Oradea")); 
			else
				return (new State("Arad"));
		case "Arad":
			if(a.id==2)
				return (new State("Zerind")); 
			else if(a.id==3)
				return (new State("Timisoara"));
			else
				return (new State("Sibiu"));
		case "Timisoara":
			if(a.id==5)
				return (new State("Lugoj")); 
			else
				return (new State("Arad"));
		case "Lugoj":
			if(a.id==5)
				return (new State("Timisoara")); 
			else
				return (new State("Mehadia"));
		case "Mehadia":
			if(a.id==6)
				return (new State("Lugoj")); 
			else
				return (new State("Dobreta"));
		case "Dobreta":
			if(a.id==7)
				return (new State("Mehadia")); 
			else
				return (new State("Craiova"));
		case "Sibiu":
			if(a.id==10)
				return (new State("Rimnicu Vilcea"));
			else if(a.id==9)
				return (new State("Fagaras")); 
			else if(a.id==4)
				return (new State("Arad")); 
			else
				return (new State("Oradea"));
		case "Rimnicu Vilcea":
			if(a.id==10)
				return (new State("Sibiu")); 
			else if(a.id==11)
				return (new State("Pitesti")); 
			else
				return (new State("Craiova"));
		case "Craiova":
			if(a.id==8)
				return (new State("Dobreta")); 
			else if(a.id==12)
				return (new State("Rimnicu Vilcea")); 
			else
				return (new State("Pitesti"));
		case "Fagaras":
			if(a.id==9)
				return (new State("Sibiu")); 
			else
				return (new State("Bucharest"));
		case "Pitesti":
			if(a.id==11)
				return (new State("Rimnicu Vilcea")); 
			else if(a.id==13)
				return (new State("Craiova")); 
			else
				return (new State("Bucharest"));
		case "Bucharest":
			if(a.id==14)
				return (new State("Fagaras")); 
			else if(a.id==15)
				return (new State("Pitesti")); 
			else if(a.id==16)
				return (new State("Giurgiu")); 
			else
				return (new State("Urziceni"));
		case "Giurgiu":
			return (new State("Bucharest"));
		case "Urziceni":
			if(a.id==17)
				return (new State("Bucharest")); 
			else if(a.id==18)
				return (new State("Hirsova")); 
			else
				return (new State("Vaslui"));
		case "Hirsova":
			if(a.id==18)
				return (new State("Urziceni")); 
			else
				return (new State("Eforie"));
		case "Eforie":
			return (new State("Hirsova"));
		case "Vaslui":
			if(a.id==19)
				return (new State("Urziceni")); 
			else
				return (new State("Iasi"));
		case "Iasi":
			if(a.id==21)
				return (new State("Vaslui")); 
			else
				return (new State("Neamt"));
		case "Neamt":
			return (new State("Iasi"));
		}
		
		return null;
	}
	
	public boolean goalTest(State s){
		if(s.name =="Vaslui")
			return true;
		else
			return false;
	}
	
	public int actionCost(State s,Action a){
		return a.weight;
	}
	
	public int pathCost(LinkedList<Action> p){
		int cost = 0;
		for(Action action : p){
			cost += action.weight;
		}
		return cost;
	}
	
	
	
	public int Heuristic(State s){
		switch(s.name){
		case "Oradea":
			return 579; 
		case "Zerind":
			return 573;
		case "Arad":
			return 565;
		case "Timisoara":
			return 528;
		case "Lugoj":
			return 443;
		case "Mehadia":
			return 440;
		case "Dobreta":
			return 441;
		case "Sibiu":
			return 452;
		case "Rimnicu Vilcea":
			return 392;
		case "Craiova":
			return 368;
		case "Fagaras":
			return 375;
		case "Pitesti":
			return 299;
		case "Bucharest":
			return 199;
		case "Giurgiu":
			return 276;
		case "Urziceni":
			return 130;
		case "Hirsova":
			return 140;
		case "Eforie":
			return 190;
		case "Vaslui":
			return 0;
		case "Iasi":
			return 85;
		case "Neamt":
			return 145;
		}
		
		return 0;
	}
}


