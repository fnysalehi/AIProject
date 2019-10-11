
public class State{
		int[][] pos;
		int zeroPosition;
		public State(){
			this.pos = new int[3][3];
		}
		public void setPos(int position)
		{
			this.zeroPosition = position;
		}	
		public boolean sequal(State s2){
			boolean flag=true;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++)
					if(this.pos[i][j] != s2.pos[i][j])
						flag = false;
			}
		
			return flag;
		}
		public void print(){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++)
					System.out.print(pos[i][j]+" ");
					//System.out.print(" haha ");
				System.out.println("");
			}
			
		}
	}