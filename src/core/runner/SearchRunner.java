package core.runner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import core.astar.IDAStar;
import core.astar.Node;
import core.problem.Problem;
import g03.problem.blocks.*;
import g03.problem.npuzzle.NpAction;
import g03.problem.npuzzle.NpProblem;
import g03.problem.npuzzle.NpState;

public class SearchRunner {
	public static Integer[] target4 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
	public static Integer[] target3 = {1,2,3,4,5,6,7,8,0};
	public static Integer[] target5 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0};

	public SearchRunner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
//		System.out.println("choose: ");
//		System.out.println("b: block-move | n: n-puzzle ");
//		Scanner scan = new Scanner(System.in);
//		String n = scan.nextLine();
//		if(n.equals("b"))
//			testB();
//		else if(n.equals("n"))
			runNp();
	}

	public static void runNp(){
		double[] mb =  {0, 0.03, 0.03, 0.3, 3.6, 3.6, 12, 75, 299};
		Integer[] st = {49,  31,   31,  52,  51,  50, 56, 57,  61};
		Integer[] init = {};
		System.out.println("1: 6 4 7 8 5 0 3 2 1\n2: 8 6 7 2 5 4 3 0 1\n0: 2 15 3 7 13 10 1 5 8 12 9 14 11 4 0 6\n3: 8 13 0 6 1 15 9 14 3 4 5 11 7 2 10 12\n4: 2 9 5 11 8 3 4 14 7 10 1 12 0 15 6 13\n5: 12 1 5 6 2 11 7 9 14 10 0 4 15 3 13 8\n6: 4 7 0 9 12 10 11 8 14 6 15 1 2 5 3 13\n7: 12 10 3 2 0 7 14 9 1 15 5 6 8 4 13 11\n8: 4 6 15 13 12 9 10 2 8 0 7 3 14 5 1 11\n");
		Integer[] init1 = {6, 4, 7, 8, 5, 0, 3, 2, 1};
		Integer[] init2 = {8, 6, 7, 2, 5, 4, 3, 0 ,1};
		Integer[] init0 = {2, 15, 3, 7, 13, 10, 1, 5, 8, 12, 9, 14, 11, 4, 0, 6};
		Integer[] init3 = {8, 13, 0, 6, 1, 15, 9, 14, 3, 4, 5, 11, 7, 2, 10, 12};
		Integer[] init4 = {2, 9 ,5 ,11 ,8 ,3 ,4 ,14 ,7 ,10 ,1 ,12 ,0 ,15 ,6 ,13};
		Integer[] init5 = {12, 1, 5, 6, 2, 11, 7, 9, 14, 10, 0, 4, 15, 3, 13, 8};
		Integer[] init6 = {4, 7, 0, 9, 12, 10, 11, 8, 14, 6, 15, 1, 2, 5, 3, 13};
		Integer[] init7 = {12, 10, 3, 2, 0, 7, 14, 9, 1, 15, 5, 6, 8, 4, 13, 11};
		Integer[] init8 = {4, 6, 15, 13, 12, 9, 10, 2, 8, 0, 7, 3, 14, 5, 1, 11};
		System.out.print("choose : ");
		//System.out.print("Please enter size (3/4/5): ");
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		while(m!=-1) {
			int n = 3;
			switch (m) {
				case 0:
					n = 4;
					init = init0;
					break;
				case 1:
					n = 3;
					init = init1;
					break;
				case 2:
					n = 3;
					init = init2;
					break;
				case 3:
					n = 4;
					init = init3;
					break;
				case 4:
					n = 4;
					init = init4;
					break;
				case 5:
					n = 4;
					init = init5;
					break;
				case 6:
					n = 4;
					init = init6;
					break;
				case 7:
					n = 4;
					init = init7;
					break;
				case 8:
					n = 4;
					init = init8;
					break;
			}
			//int n = scan.nextInt();
			NpState target = new NpState();
			switch (n) {
				case 3:
					init_z3();
					target = new NpState(target3);
					break;
				case 4:
					init_z4();
					target = new NpState(target4);
					break;
				case 5:
					init_z5();
					target = new NpState(target5);
					break;
			}
			System.out.print("Enter the whole board (" + n * n + ") : ");

//		for(int i=0; i<n*n; i++){
//			init[i] = scan.nextInt();
//		}

			System.out.println("Searching ...");
			NpProblem np = new NpProblem(new NpState(init), target);
			if (!np.solvable()) {
				System.out.println("not solvable !");
				return;
			}
			IDAStar a = new IDAStar(np);
			long startTime = System.nanoTime();
			Node node = a.Search();

			long endTime = System.nanoTime();
			long duration = (endTime - startTime);

			int steps = 0;
			node.draw();
			node.getAction().draw();
			while (node.getParent() != null) {
				node = node.getParent();
				node.draw();
				steps++;
				if (node.getAction() != null)
					node.getAction().draw();
			}
			System.out.println("Takes " + steps + " steps.");
			System.out.println("Search time : " + duration / 1000000000 + "." + String.format("%09d", duration % 1000000000) + " seconds.   (" + mb[m] + "s," + st[m] + ")");
			System.out.print("choose : ");
			m = scan.nextInt();
		}
	}

	public static void init_z4(){
		NpState.init_zobrist(NpState.table_15, 4);
	}
	public static void init_z3(){
		NpState.init_zobrist(NpState.table_8, 3);
	}
	public static void init_z5(){
		NpState.init_zobrist(NpState.table_24, 5);
	}

	public static void testB() {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int size;
		System.out.println("");
		System.out.println("please enter the number of size: ");
		size = reader.nextInt();
		System.out.println("Happy AIing!");
		System.out.println("JUMP (n + 1)/2 !");
		Long[][] zobrist = new Long[3][80];
		BState.init_zobrist(zobrist, size);
		BState.table = zobrist;
		int[] s =new int[size*2+1];
		int[] s1 =new int[size*2+1];

		for(int i = 0;i < size;i ++)
			s[i] = 2;//b
		for(int i = size;i < 2*size;i ++)
			s[i] = 1;
		s[2 * size] = 0;
		BState firstStat = new BState(s,size);


		for(int i = 0;i < size;i ++)
			s1[i] = 1;
		for(int i = size;i < 2*size;i ++)
			s1[i] = 2;
		s1[2 * size] = 0;
		BState goal = new BState(s1,size);

		Problem b = new BProblem(firstStat, goal);
		IDAStar a = new IDAStar(b);
		long startTime = System.currentTimeMillis();
		Node node = a.Search();
		long endTime = System.currentTimeMillis();
		node.getState();
		node.draw();
		node.getAction().draw();
		while (node.getParent() != null) {
			node = node.getParent();
			node.draw();
			if (node.getAction() != null)
				node.getAction().draw();
		}
		System.out.println("Search time " + (endTime - startTime)/1000.000 + " seconds.");
	}

}
