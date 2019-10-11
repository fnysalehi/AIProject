public class State{
	String name;
	public State(String name){
		this.name=name;
	}
	public void setState(State s){
		this.name=s.name;
	}
	public boolean sequal(State s2){
		if(this.name==s2.name)
			return true;
		else
			return false;
	}
	public void print(){
		System.out.print(this.name+" ");
	}
}