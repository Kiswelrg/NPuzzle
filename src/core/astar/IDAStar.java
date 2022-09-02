package core.astar;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;
import g03.problem.npuzzle.NpAction;
import g03.problem.npuzzle.NpProblem;
import g03.problem.npuzzle.NpState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class IDAStar {
	int cutoff;
	Node goal;
	//Fringe fringe = new Fringe();

	public IDAStar(Problem problem) {
		super();
		this.problem = problem;
	}
	
	public Node childNode(Node parent, Action action) {
		State state = problem.result(parent.getState(), action);
		int pathCost = parent.getPathCost() + problem.stepCost(parent.getState(), action);
		int heuristic = problem.heuristic(state);												//待优化
		return new Node(state, parent, action, pathCost, heuristic);
	}
	
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public int ida(Node node){

		if( node.evaluation() > cutoff ) return node.evaluation();
		if( problem.goalTest(node.getState())){
			goal = node;
			return 0;
		}

		Node child;
		int fmin=99;

		for (Action action : problem.Actions(node.getState())) {
			child = childNode(node, action);
			if( action.getTarget() == node.getAction().getBlank() ) continue;
			int t = ida(child);
			if( t == 0) return 0;
			if( t < fmin) fmin = t;

		}
		return fmin;
	}

	public Node Search()
	{
		State headstate = problem.getInitialState();
		Node head = new Node(headstate,null, new NpAction(-1,-1), 0, problem.heuristic(headstate));

		for(cutoff = head.getHeuristic() ; ; )
		{

			System.out.println(cutoff + " !");
			int t = ida(head);
			if(t == 0) return goal;
			if(t == 99) return null;
			cutoff = t;

		}


	}
	
	//用动画展示问题的解路径
	public void solution(Node node)
	{
		// Fix me
		// 调用Problem的drawWorld方法，和simulateResult方法
	}
	
	private Problem problem;
}
