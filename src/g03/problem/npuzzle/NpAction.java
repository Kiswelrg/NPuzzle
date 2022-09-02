package g03.problem.npuzzle;

import core.problem.Action;

public class NpAction extends Action {
	
	public NpAction(int m, int c) {
		super();
		this.blank = m;
		this.target = c;
	}


	public int cost(){
		return 1;
	}

	@Override
	public int getBlank() {
		return blank;
	}

	public void setBlank(int m) {
		this.blank = m;
	}

	@Override
	public int getTarget() {
		return target;
	}

	public void setTarget(int c) {
		this.target = c;
	}

	private int blank;
	private int target;

	@Override
	public void draw() {
		// TODO Auto-generated method stub

		System.out.println( "[" + (target+1) + " -> " + (blank+1) + "]" );
	}
	
}