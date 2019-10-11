import java.util.LinkedList;
import java.util.Scanner;

public class Problem3 {
	
	public State initialState() {
		State s = new State(3,3,0,0,Side.START);
		return s;
	}

	public ActionSet actions(State s) {
		ActionSet a = new ActionSet();
		a.actions.add(new Action(1,0));
		a.actions.add(new Action(0,1));
		a.actions.add(new Action(2,0));
		a.actions.add(new Action(0,2));
		a.actions.add(new Action(1,1));
		return a;
	}

	public State result(State s, Action a) {
		int sMis,sCan,eMis,eCan;
		if(s.side==Side.START){
			sMis = s.start.mis - a.mis;
			sCan =  s.start.can - a.can;
			eMis = s.end.mis + a.mis;
			eCan =  s.end.can + a.can;
		}
		else{
			sMis = s.start.mis + a.mis;
			sCan =  s.start.can + a.can;
			eMis = s.end.mis - a.mis;
			eCan =  s.end.can - a.can;
		}
		if(((sMis<sCan || eMis<eCan)&& sMis!=0 && eMis!=0 )|| sMis<0 || eMis<0 || sCan<0 || eCan<0)
			return null;
		else{
			if(s.side==Side.START)
				return new State(sMis,sCan,eMis,eCan,Side.END);
			else
				return new State(sMis,sCan,eMis,eCan,Side.START);
			}
	}

	public boolean goalTest(State s) {
		if(s.end.can==3&&
			s.end.mis==3&&
			s.start.can==0&&
			s.start.mis==0)
			return true;
		else
			return false;
			
	}

	public int actionCost(State s, Action a) {
		return 1;
	}
	
	public int pathCost(LinkedList<Action> p) {
		return p.size();
	}
}
