package org.badass.snake.brain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

import se.citerus.crazysnake.*;

public class PathPlanner {
	public static final int MAX_DIST = 200;
	private Snake snake = null;
	
	private Stack<Movement> path = null;
	private Position target = null;
	
	private GameState state = null;
	
	public PathPlanner(Snake snake, GameState state) {
		this.snake = snake;
		this.state = state;
	}
	
	public Snake getSnake() {
		return snake;
	}
	
	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	
	class Choice {
		public Movement move;
		public int h;
		
		public Choice(Movement m, int h) {
			this.move = m;
			this.h = h;
		}
	}
	
	public Movement plan(Snake from, Position target) {
		this.state = state;
		Direction dir = from.getDirection();
		Position pos = from.getHeadPosition();
		
		ArrayList<Choice> choices = new ArrayList<Choice>();
		
		for ( int i = 0; i < 3; i++ ) {
			Movement move = headingAsMovement(i);
			Direction newDir = dir.turn(move);
			Position newPos = pos.offset(newDir);
			if ( state.getSquare(newPos).isUnoccupied() ) {
				choices.add(new Choice(move, walkDistance(newPos, newDir, target, 0)));
			}
		}
		
		Collections.sort(choices, new Comparator<Choice>() {
			public int compare(Choice o1, Choice o2) {
				return (int)Math.signum(o1.h - o2.h);
			}
		});
		
		if ( choices.size() > 0 )
			return choices.get(0).move;
		else
			return Movement.FORWARD;
	}
	
	public int walkDistance(Position pos, Direction dir, Position target) {
		return walkDistance(pos, dir, target, 0);
	}
	
	private int walkDistance(Position pos, Direction dir, Position target, int walked) {
		if ( pos.getX() == target.getX() && pos.getY() == target.getY() ) {
			//System.out.println("Walking dist to " + target + " is " + walked);
			return walked;
		}
		else if ( walked >= MAX_DIST ) {
			//System.out.println("Max path dist reached. Returning.");
			return walked;
		}
		
		//System.out.println("Walked: " + walked + ", curr = " + pos + ", target = " + target);
		
		ArrayList<Choice> choices = new ArrayList<Choice>();
		
		for ( int i = 0; i < 3; i++ ) {
			Movement move = headingAsMovement(i);
			Direction newDir = dir.turn(move);
			Position newPos = pos.offset(newDir);
			if ( state.getSquare(newPos).isUnoccupied() ) {
				choices.add(new Choice(move, PositionUtils.distance(newPos, target)));
			}
		}
		
		Collections.sort(choices, new Comparator<Choice>() {
			public int compare(Choice o1, Choice o2) {
				return (int)Math.signum(o1.h - o2.h);
			}
		});
		
		if ( choices.size() > 0 ) {
			Direction newDir = dir.turn(choices.get(0).move);
			Position newPos = pos.offset(newDir);
			return walkDistance(newPos, newDir, target, walked + 1);
		}
		else
			return MAX_DIST;
	}
	
	private ArrayList<Choice> naivePath() {
		return null;
	}
	
	public static Movement headingAsMovement(int head) {
		switch (head) {
		case 0: return Movement.LEFT;
		case 1: return Movement.FORWARD;
		case 2: return Movement.RIGHT;
		}
		return null;
	}
}
