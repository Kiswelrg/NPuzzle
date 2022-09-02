package g03.problem.npuzzle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;

public class NpProblem extends Problem {

		public NpProblem() {

		}

		public NpProblem(NpState initialState, NpState goal) {
			super();
			this.initialState = initialState;
			this.goal = goal;
		}

		@Override
		public boolean solvable(){
			NpState state = this.getInitialState();
			int x = state.getZero();
			int inver;
			int n = state.getSize();
			if(n%2 != 0){
				inver = state.getInversions();
				//System.out.println(inver);
			}
			else{
				int row = 1;
				while(x > n-1){
					row++;
					x -= n;
				}
				inver = row + state.getInversions();
				//System.out.println(row + " + " + state.getInversions());
			}

			if(inver % 2 == 0)
				return true;
			else
				return false;
		}


		//The resulting state from parent through action.
		@Override
		public NpState result(State parent, Action action){
			int temp;
			int size = goal.getSize();
			//Integer[] board = ((NpState)parent).getBoard();
			Integer[] board = new Integer[size*size];
			for(int i=0; i< size*size; i++)	board[i] = ((NpState)parent).getBoard()[i];

			temp = board[((NpAction)action).getTarget()];
			board[((NpAction)action).getTarget()] = board[((NpAction)action).getBlank()];
			board[((NpAction)action).getBlank()] = temp;
			Long next = NpState.nextHash(((NpState)(parent)),((NpAction)(action)));
			return new NpState(board,next);
		}

		//The cost of the path from parent through action to its successors.
		@Override
		public int stepCost(State parent, Action action) {
			return  1;
		}

		//estimated cost of the cheapest path from the state to a goal state
		@Override
		public int heuristic(State state) {
			int hee = 0;
			int size = ((NpState)state).getSize();
			Integer[] board = ((NpState)state).getBoard();
			for(int i=0; i<size*size; i++){
				if(board[i] != 0){
					hee += (Math.abs(i/size - (board[i]-1)/size) + Math.abs(i%size - (board[i]-1)%size ));
				}

			}
			return hee;
		}

		//all the possible actions from current state.
		@Override
		public ArrayList<Action> Actions(State state) {
			ArrayList<Action> nexts = new ArrayList<Action>();
			Integer[] board = ((NpState)state).getBoard();
			int x = Arrays.asList(board).indexOf(0);
			int n = ((NpState)state).getSize();
			if( (x - n) > -1 ) {
				NpAction action = new NpAction(x,x-n);
				nexts.add(action);
			}
			if( (x - 1)%n != (n-1) && x > 0) {
				NpAction action = new NpAction(x,x-1);
				nexts.add(action);
			}
			if( (x + 1)%n != 0 ){
				NpAction action = new NpAction(x,x+1);
				nexts.add(action);
			}
			if( ((x+n) < n*n) ){
				NpAction action = new NpAction(x,x+n);
				nexts.add(action);
			}
			return nexts;
		}

		//test if the state is a goal.
		public boolean goalTest(State state) {
			if( Arrays.equals(goal.getBoard(),((NpState)state).getBoard()) && goal.getSize() == ((NpState) state).getSize())
				return true;
			return false;
		}

		public NpState getInitialState() {
			return initialState;
		}

		public void setInitialState(NpState initialState) {
			this.initialState = initialState;
		}

		//描画出该问题的World State，最好能对细节进行渲染
		@Override
		public void drawWorld(){

		}

		//描画从parent状态经Action的变化过程
		@Override
		public void simulateResult(State parent, Action action){

		}

		private NpState initialState;
		private NpState goal;

		public NpState getGoal() {
			return goal;
		}

		public void setGoal(NpState goal) {
			this.goal = goal;
		}

}
