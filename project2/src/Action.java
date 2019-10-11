
public class Action{
		Direction dir;
		public Action(Direction dir){
			this.dir = dir;
		}
		public Action inverse(){
			switch(this.dir){
			case right:
				return new Action(Direction.left);
			case left:
				return new Action(Direction.right);
			case up:
				return new Action(Direction.down);
			case down:
				return new Action(Direction.up);
			}
			return null;
		}
	}