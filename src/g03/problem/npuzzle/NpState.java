package g03.problem.npuzzle;

import core.problem.State;;import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class NpState extends State {
	public static Long[][] table_15 = new Long[16][16];
	public static Long[][] table_8 = new Long[9][9];
	public static Long[][] table_24 = new Long[25][25];
	public static List<Long> existedTables_15 = new ArrayList<Long>();
	public static List<Long> existedTables_8 = new ArrayList<Long>();
	public static List<Long> existedTables_24 = new ArrayList<Long>();
	public static List<Integer[]> existed_15 = new ArrayList<Integer[]>();
	public static List<Integer[]> existed_8 = new ArrayList<Integer[]>();
	public static List<Integer[]> existed_24 = new ArrayList<Integer[]>();

	public NpState(Integer[] m) {
		super();
		this.s = m;
		this.size = (int)Math.sqrt(m.length);
	}

	public NpState(Integer[] m, Long hashcode){
		super();
		this.s = m;
		this.size = (int)Math.sqrt(m.length);
		this.hashcode = hashcode;
	}
	public NpState(){
	}

	public static void init_zobrist(Long[][] tab, int n){
		Random random = ThreadLocalRandom.current();
		for(int i=0; i<n*n; i++){
			for(int j=0; j<n*n; j++){
				Long r;; //Means 64 bit
				r = random.nextLong();
				//zobrist.table[i][j] = Base64.encodeBase64String(r);
				tab[i][j] = r;
			}
		}
	}


	@Override
	public Long hashString(){
		if(hashcode == null)
			hashcode = NpState.hash(getBoard(),getSize());
		return hashcode;
	}

	public static Long hash(Integer[] board, int n){

		Long h = Long.valueOf(0);
		Long[][] temp;
		switch (n) {
			case 4:
				temp = NpState.table_15;
				break;
			case 3:
				temp = NpState.table_8;
				break;
			case 5:
				temp = NpState.table_24;
				break;
			default:
				temp = NpState.table_15;
		}
		for(int i=0; i< n * n; i++){
			if(board[i] != 0){
				int j = board[i];
				//byte[] b = h.getBytes(Charset.forName("UTF-8"));
				Long c = temp[i][j];
				h = h^c;
			}
		}
		return h;
	}

	//public static String nextHsh(Integer[] board, int n, int m, int index){
	public static Long nextHash(NpState state, NpAction action){
		int j = state.getSize();
		int n = action.getTarget();
		int m = action.getBlank();
		Integer[] board = state.getBoard();
		Long ptarget = Long.valueOf(0);
		Long rtarget = Long.valueOf(0);
		Long rsource = Long.valueOf(0);
		Long nsource = Long.valueOf(0);
		switch (j) {
			case 4:
				ptarget = NpState.table_15[n][board[n]];
				rtarget = NpState.table_15[n][board[m]];
				rsource = NpState.table_15[m][board[m]];
				nsource = NpState.table_15[m][board[n]];
				break;
			case 3:
				ptarget = NpState.table_8[n][board[n]];
				rtarget = NpState.table_8[n][board[m]];
				rsource = NpState.table_8[m][board[m]];
				nsource = NpState.table_8[m][board[n]];
				break;
			case 5:
				ptarget = NpState.table_24[n][board[n]];
				rtarget = NpState.table_24[n][board[m]];
				rsource = NpState.table_24[m][board[m]];
				nsource = NpState.table_24[m][board[n]];
				break;
		}
		Long origin = state.hashString();
		Long c = origin;
		c = c^ptarget;
		c = c^rtarget;
		c = c^rsource;
		c = c^nsource;
		return c;
	}

	public static boolean exists(Long h, int n){
		switch (n) {
			case 4:
				if (existedTables_15.contains(h))
					return true;
				else {
					existedTables_15.add(h);
					return false;
				}
			case 3:
				if (existedTables_8.contains(h))
					return true;
				else {
					existedTables_8.add(h);
					return false;
				}
			case 5:
				if (existedTables_24.contains(h))
					return true;
				else {
					existedTables_24.add(h);
					return false;
				}
			default:
				return false;
		}

	}



	public Integer[] getBoard() {
		return s;
	}
	public void setBoard(Integer[] m) {
		this.s = m;
	}

	public int getZero(){
		return Arrays.asList(this.getBoard()).indexOf(0);
	}

	public int getInversions(){
		int count = 0;
		int n = this.getSize();
		Integer[] board = this.getBoard();
		for(int i=0; i<n*n-1; i++){
			for(int j=i+1; j<n*n; j++){
				if(board[j] < board[i]) count++;
			}
		}
		return (count - this.getZero());
	}


	private Integer[] s;
	private int size;
	private Long hashcode;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static Integer[] shuffle(int n){
		Integer[] board = new Integer[n*n];
		for(int i=0; i<n*n; i++)
			board[i] = i;
		for(int i=0; i<board.length; i++){
			Random rand = new Random();
			int num = rand.nextInt(board.length);
			Integer temp;
			temp = board[i];
			board[i] = board[num];
			board[num] = temp;
		}
		return board;
	}

	public static void generate_n(int n, List<Integer[]> t, int num){
		Integer[] board = new Integer[n*n];
		for(int i=0; i<n*n; i++)
			board[i] = i;
		for(int i=0; i<num; i++){
			Integer[] cd = shuffle(n);
			NpState initState = new NpState(cd);
			NpState goal = new NpState(board);
			NpProblem npp = new NpProblem(initState, goal);
			if(npp.solvable()) {
				if (initState.exists(initState.hash(cd ,n), n))
					i--;
				else {
					t.add(cd);
					//initState.draw();
				}
			}else
				i--;
		}
	}

	@Override
	public void draw() {
//		// TODO Auto-generated method stub
		int n = this.getSize();
		Integer[] board = this.getBoard();
		for(int i=0; i<n; i++){
			System.out.print("+---");
		}
		System.out.println("+");
		for(int i=0; i<n*n; i++){
			if(board[i] != 0)
				System.out.print("|" + String.format("%2d", board[i]) + " ");
			else
				System.out.print("| # ");

			if((i + 1) % n == 0){
				System.out.println("|");
				for(int j=0; j<n; j++){
					System.out.print("+---");
				}
				System.out.println("+");
			}
		}
		System.out.println();
	}

}
