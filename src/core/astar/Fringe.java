package core.astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import g03.problem.npuzzle.*;
import core.problem.State;


//The set of all leaf nodes available for expansion at any given point
public class Fringe {
	//
	public Node pop() {
		Node min = nodes.peek();
		nodes.remove(min);
		//hm.remove(min.getState().hashString());
		return min; //Fix me
	}


	//
	public void insert(Node node){
		//Fix me
		hm.put(node.getState().hashString(),node);
		nodes.add(node);
	}

	//
	public boolean contains(State state) {
		if(hm.containsKey(state.hashString()))
            return true;
		return false;
	}

	//
	public Node revisited(State state) {
		return hm.get(state.hashString());
	}

	//
	public boolean isEmpty() {
		return nodes.isEmpty(); //Fix me
	}

	//
	public void replace(Node from, Node to) {
		nodes.remove(from);
		nodes.add(to);
		//Fix me
		hm.remove(from.getState().hashString());
		hm.put(to.getState().hashString(),to);
	}


	public int nodeSize(){
		return this.nodes.size();
	}
	public int mapSize(){
		return this.hm.size();
	}

	public void clear(){
	    nodes.clear();
	    hm.clear();
    }

	//Data Structures for Fringe, implement it yourself.
	Comparator<Node> cmp = new Comparator<Node>()
	{
		public int compare(Node e1,Node e2)
		{
			int a = e1.evaluation();
			int b = e2.evaluation();
			return a-b;
		}
	};

	Queue<Node> nodes = new PriorityQueue<>(cmp);
	HashMap<Long,Node> hm = new HashMap();

}