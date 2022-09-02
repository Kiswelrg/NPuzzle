package g03.problem.blocks;
import core.problem.*;
public class BAction extends Action {
	public int s;//��������λ��
	public int e;//�ո�λ��
	public BAction(int s,int e){
		super();
		this.s = s;
		this.e = e;
	}
	public int cost(){
		return 1;
	}
	@Override
	public int getBlank(){
		return -1;
	}
	@Override
	public int getTarget(){
		return -1;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}


	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("[" + (s + 1) + " -> " + (e + 1) + "]" );	
		System.out.println();
	}
	public BAction(){		
	}

	
}
