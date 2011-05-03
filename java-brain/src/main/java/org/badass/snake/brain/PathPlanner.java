package org.badass.snake.brain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

import se.citerus.crazysnake.*;

public class PathPlanner {
	private Snake snake = null;
	private Stack<Movement> path = null;
	private Position target = null;
	private GameState state = null;
	
	public PathPlanner(Snake snake) {
		this.snake = snake;
		this.path = new Stack<Movement>();
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
	
	public Movement plan(Snake from, Position target, GameState state) {
		
		Direction dir = from.getDirection();
		Position pos = from.getHeadPosition();
		
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
		
		if ( choices.size() > 0 )
			return choices.get(0).move;
		else
			return Movement.FORWARD;
	}
	
	
	/*private void rePlan(Position currentPos, Direction currentDir) {
		if ( currentPos.equals(target) )
			return;
		
		// Check movement alternatives;
		Position toTarget = new Position(target.getX() - currentPos.getX(), target.getY() - currentPos.getY());
		int distX = Math.abs(toTarget.getX()), distY = Math.abs(toTarget.getY());
		
		Movement nextMove = null;

		switch (currentDir) {
		case NORTH:
			if ( distX > distY ) {
				if ( toTarget.getX() > 0 && isSafeTurn(currentPos, currentDir, Movement.RIGHT) ) nextMove = Movement.RIGHT;
				else if ( isSafeTurn(currentPos, currentDir, Movement.LEFT) ) nextMove = Movement.LEFT;
				else nextMove = Movement.FORWARD;
			} else {
				if ( toTarget.getY() > 0 && isSafeTurn(currentPos, currentDir, Movement.RIGHT) ) nextMove = Movement.RIGHT;
				else if ( toTarget.getY() < 0 && isSafeTurn(currentPos, currentDir, Movement.FORWARD) ) nextMove = Movement.FORWARD;
				else nextMove = Movement.LEFT;
			}
			break;
			
		case EAST:
			if ( distX > distY ) {
				if ( toTarget.getX() > 0 && isSafeTurn(currentPos, currentDir, Movement.FORWARD) ) nextMove = Movement.FORWARD;
				else if ( isSafeTurn(currentPos, currentDir, Movement.LEFT) ) nextMove = Movement.LEFT;
				else nextMove = Movement.RIGHT;
			} else {
				if ( toTarget.getY() > 0 && isSafeTurn(currentPos, currentDir, Movement.LEFT) ) nextMove = Movement.LEFT;
				else if ( isSafeTurn(currentPos, currentDir, Movement.RIGHT) ) nextMove = Movement.RIGHT;
				else nextMove = Movement.FORWARD;
			}
			break;
			
		case SOUTH:
			if ( distX > distY ) {
				if ( toTarget.getX() > 0 && isSafeTurn(currentPos, currentDir, Movement.LEFT) ) nextMove = Movement.LEFT;
				else if ( isSafeTurn(currentPos, currentDir, Movement.RIGHT) ) nextMove = Movement.RIGHT;
				else nextMove = Movement.FORWARD;
			} else {
				if ( toTarget.getY() > 0 && isSafeTurn(currentPos, currentDir, Movement.FORWARD) ) nextMove = Movement.FORWARD;
				else if ( isSafeTurn(currentPos, currentDir, Movement.LEFT) ) nextMove = Movement.LEFT;
				else nextMove = Movement.RIGHT;
			}
			break;
		case WEST:
			if ( distX > distY ) {
				if ( toTarget.getX() > 0 && isSafeTurn(currentPos, currentDir, Movement.LEFT) ) nextMove = Movement.LEFT;
				else if ( isSafeTurn(currentPos, currentDir, Movement.RIGHT) ) nextMove = Movement.RIGHT;
				else nextMove = Movement.FORWARD;
			} else {
				if ( toTarget.getY() > 0 && isSafeTurn(currentPos, currentDir, Movement.LEFT) ) nextMove = Movement.LEFT;
				else if ( isSafeTurn(currentPos, currentDir, Movement.RIGHT) ) nextMove = Movement.RIGHT;
				else nextMove = Movement.FORWARD;
			}
			
			break;
		}
		
		if ( nextMove == null ) return;
		Direction newDir = currentDir.turn(nextMove);
		
		path.push(nextMove);
		rePlan(currentPos.offset(newDir), newDir);
	}*/
	
	private Movement headingAsMovement(int head) {
		switch (head) {
		case 0: return Movement.LEFT;
		case 1: return Movement.FORWARD;
		case 2: return Movement.RIGHT;
		}
		return null;
	}
	
	private boolean isSafeTurn(Position pos, Direction heading, Movement turn) {
		Position p = pos.offset(heading.turn(turn), 1);
		if ( p.getX() <= 0 || p.getX() >= state.getNoColumns() || p.getY() <= 0 || p.getY() >= state.getNoRows() ) return false;
		return state.getSquare(p).isUnoccupied();
	}
}
