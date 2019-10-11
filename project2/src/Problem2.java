import java.util.LinkedList;
import java.util.Scanner;

public class Problem2 {
	
	public State initialState() {
		State s = new State();
		Scanner in = new Scanner(System.in);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				s.pos[i][j]=in.nextInt();
				if(s.pos[i][j]==0)
					s.setPos((3*i)+(j));
			}
		}
		in.close();
		return s;
	}

	public ActionSet actions(State s) {
		ActionSet a = new ActionSet();
		switch(s.zeroPosition){
			case 0:
				a.actions.add(new Action(Direction.right));
				a.actions.add(new Action(Direction.down));
				break;
			case 1:
				a.actions.add(new Action(Direction.left));
				a.actions.add(new Action(Direction.right));
				a.actions.add(new Action(Direction.down));
				break;
			case 2:
				a.actions.add(new Action(Direction.left));
				a.actions.add(new Action(Direction.down));
				break;
			case 3:
				a.actions.add(new Action(Direction.right));
				a.actions.add(new Action(Direction.up));
				a.actions.add(new Action(Direction.down));
				break;
			case 4:
				a.actions.add(new Action(Direction.left));
				a.actions.add(new Action(Direction.right));
				a.actions.add(new Action(Direction.up));
				a.actions.add(new Action(Direction.down));
				break;
			case 5:
				a.actions.add(new Action(Direction.left));
				a.actions.add(new Action(Direction.up));
				a.actions.add(new Action(Direction.down));
				break;
			case 6:
				a.actions.add(new Action(Direction.right));
				a.actions.add(new Action(Direction.up));
				break;
			case 7:
				a.actions.add(new Action(Direction.left));
				a.actions.add(new Action(Direction.right));
				a.actions.add(new Action(Direction.up));
				break;
			case 8:
				a.actions.add(new Action(Direction.left));
				a.actions.add(new Action(Direction.up));
				break;	
		}
		return a;
	}

	public State result(State s, Action a) {
		State news = new State();
		int i=0,j=0;
		for(int b=0;b<3;b++)
			for(int c=0;c<3;c++)
				news.pos[b][c] = s.pos[b][c];
		switch(a.dir){
		case left:
			news.zeroPosition = s.zeroPosition-1;
			i = news.zeroPosition/3;
			j = news.zeroPosition%3;
			news.pos[i][j+1] = news.pos[i][j];
			news.pos[i][j] = 0;
			break;
		case right:
			news.zeroPosition = s.zeroPosition+1;
			i = news.zeroPosition/3;
			j = news.zeroPosition%3;
			news.pos[i][j-1] = news.pos[i][j];
			news.pos[i][j] = 0;
			break;
		case up:
			news.zeroPosition = s.zeroPosition-3;
			i = news.zeroPosition/3;
			j = news.zeroPosition%3;
			news.pos[i+1][j] = news.pos[i][j];
			news.pos[i][j] = 0;
			break;
		case down:
			news.zeroPosition = s.zeroPosition+3;
			i = news.zeroPosition/3;
			j = news.zeroPosition%3;
			news.pos[i-1][j] = news.pos[i][j];
			news.pos[i][j] = 0;
			break;
		}
		return news;
	}

	public boolean goalTest(State s) {
		boolean flag=true;
		if(s.zeroPosition!=0)
			return false;
		else{
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++)
					if(s.pos[i][j]!=(3*i)+(j))
						flag = false;
			}
			return flag;
		}
	}
	
	public State goal() {
		State s = new State();
		s.setPos(0);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++)
				s.pos[i][j]=(3*i)+(j);
		}
		return s;
	}

	public int actionCost(State s, Action a) {
		return 1;
	}
	
	public int pathCost(LinkedList<Action> p) {
		return p.size();
	}
	
	public int Heuristic(State s) {
		int h =0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++)
				if(s.pos[i][j]!=(3*i)+(j))
					h++;
		}
		return h;
	}
}
