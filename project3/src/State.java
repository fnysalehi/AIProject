
public class State{
	Shore start;
	Shore end;
	Side side;
	public State(int sMis,int sCan,int eMis,int eCan,Side side){
		start = new Shore(sMis,sCan);
		end = new Shore(eMis,eCan);
		this.side = side;
	}
	public void setSide(Side side)
	{
		this.side = side;
	}
	public void setStart(int mis,int can)
	{
		start.setNum(mis, can);
	}
	public void setEnd(int mis,int can)
	{
		end.setNum(mis, can);
	}
	public boolean sequal(State s2){
		if(this.start.can==s2.start.can&&
				this.start.mis==s2.start.mis&&
				this.end.can==s2.end.can&&
				this.end.mis==s2.end.mis&&
				this.side.equals(s2.side))
			return true;
		else
			return false;
	}
	public void print(){
		System.out.println(this.side);
		System.out.println("Start:   missionary: "+this.start.mis+" cannibal: "+this.start.can);
		System.out.println("End:     missionary: "+this.end.mis+" cannibal: "+this.end.can);
		//System.out.println("");
			
	}
}
enum Side{
	END,
	START
}
class Shore{
	int mis;
	int can;
	public Shore(int mis,int can){
		this.can = can;
		this.mis = mis;
	}
	public void setNum(int mis,int can){
		this.can = can;
		this.mis = mis;
	}
}