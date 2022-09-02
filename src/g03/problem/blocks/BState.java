package g03.problem.blocks;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import core.problem.State;
public class BState extends State {
	private static int SIZE;//��ɫ��������
	private int state[] = new int[SIZE*2+1];	//��ǰ״̬��1��Black��-1��White��0��Empty	
	public static Long[][] table = new Long[3][80];
	
	public BState(int []s,int size)
    {
    	state = s;
    	SIZE = size;
    }
    public BState()
    {
       
    }
    
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub

		if(this.hashString().equals(((BState)obj).hashString()))
			return true;
		return false;
		
	}
public static int getSIZE() {
		return SIZE;
	}

	public static void setSIZE(int sIZE) {
		SIZE = sIZE;
	}

	public int[] getState() {
		return state;
	}

	public void setState(int[] state) {
		this.state = state;
	}
	@Override
	public void draw()
	{
	 
		for (int i = 0;i < this.state.length;i++){
			if (this.state[i] == 1)
				System.out.print(" |W| ");
			else if (this.state[i] == 2)
				System.out.print(" |B| ");
			else
				System.out.print(" |E| ");
		}
		System.out.println();

	}
	public static void init_zobrist(Long[][] tab, int size) {
		Random random = ThreadLocalRandom.current();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < size * 2 + 1; j++) {
				Long r;; //Means 64 bit
				r = random.nextLong();
				//zobrist.table[i][j] = Base64.encodeBase64String(r);
				tab[i][j] = r;
			}
		}
	}

	@Override
	public Long hashString() {
		// TODO Auto-generated method stub
		Long h ,c;
		Long[][] temp = BState.table;
		int i;
		h = temp[this.state[0]][0];
		for(i=0; i< 2* BState.SIZE+1; i++){
				//byte[] b = h.getBytes(Charset.forName("UTF-8"));
			 c = temp[this.state[i]][i];
			 h = h^c;
		}
		return h;
	}


}
