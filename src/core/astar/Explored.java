package core.astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import core.problem.State;
import g03.problem.npuzzle.*;


//The set that remembers every expanded node
public class Explored {

	public void insert(State state){
		sm.put(state.hashString(),state);
	}


	public boolean contains(State state) {
		return sm.containsKey(state.hashString());
	}

	public void clear(){
		sm.clear();
	}

	public int mapSize(){
		return sm.size();
	}

	HashMap<Long,State> sm = new HashMap();

}