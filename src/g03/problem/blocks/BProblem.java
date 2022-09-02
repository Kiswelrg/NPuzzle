package g03.problem.blocks;
import java.util.ArrayList;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;
import g03.problem.blocks.*;
public class BProblem extends Problem{
	
    public BProblem()
    {
    	
    }
    public BProblem(BState firstStat,BState goal)
    {
    	super(firstStat, goal);
    }
	@Override
	public boolean solvable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public State result(State parent, Action action) {
		// TODO Auto-generated method stub
		int n=0;
		n = ((BState)parent).getSIZE();
		int []s = new int[2 * n + 1];
		int []s1 = ((BState)parent).getState();
		int i;
		for(i=0;i<2*n+1;i++)
		{
			s[i] = s1[i];
		}
		s[((BAction)action).getE()] = s[((BAction)action).getS()];
		s[((BAction)action).getS()] = 0;
		int size = ((BState)parent).getSIZE();
		BState chi = new BState(s,size);
		return chi;
	}

	@Override
	public int stepCost(State parent, Action action) {
		// TODO Auto-generated method stub
	    BAction ac = (BAction) action;
	    int s = ac.getS();
	    int e = ac.getE();
	    int len = Math.abs(s-e);
	    if(len > 1)
	    	len --;
		return len;
	}

	@Override
	public int heuristic(State state) {
		// TODO Auto-generated method stub
		BState sta = (BState)state;
		int [] s = sta.getState();
		int sum = 0,cnt = 0;
		for(int i = s.length - 1;i >= 0;i --)
		{
			if(s[i] == 1) sum ++;
			if(s[i] == 2) cnt += sum;
		}
		return cnt;
	}

	@Override
	public ArrayList<Action> Actions(State state) {
		ArrayList<Action> actions = new ArrayList<>();
		// TODO Auto-generated method stub
		int empty = 0;
		int i,j;
		int l,r;
		int counter = 0;
		BState st = (BState)state;
		int []s = st.getState();
		for (i = 0;i < s.length;i++){
			if (s[i] == 0)
			{
				empty = i;	
				break;
			}
		}

		int n = 3;
		l = empty - n;
		if(l < 0)
			l = 0;
		r = empty + n;
		if(r >= s.length)
			r = s.length - 1;
		BAction ac = new BAction();
		for (j = l;j <= r;j++){
			if (j != empty)
			{
				BAction action = new BAction(j,empty);
				actions.add(action);
			    
			}
		}
		return actions;
	}

	@Override
	public void drawWorld() {
		// TODO Auto-generated method stub
		
	}
  
	@Override
	public void simulateResult(State parent, Action action) {
		// TODO Auto-generated method stub
		State child = result(parent, action);
	}
}
