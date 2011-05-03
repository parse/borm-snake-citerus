package org.badass.snake.brain;

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
	
	public Movement plan(Position target, GameState state) {
		path.clear();
		this.state = state;
		this.target = target;
		rePlan(snake.getHeadPosition(), snake.getDirection());
		return path.peek();
	}
	
	private void rePlan(Position currentPos, Direction currentDir) {
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
			} else {
				if ( toTarget.getY() < 0 && isSafeTurn(currentPos, currentDir, Movement.FORWARD) ) nextMove = Movement.FORWARD;
				else if ( isSafeTurn(currentPos, currentDir, Movement.LEFT) ) nextMove = Movement.LEFT;
				else nextMove = Movement.RIGHT;
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
	}
	
	private boolean isSafeTurn(Position pos, Direction heading, Movement turn) {
		Position square = pos.offset(heading.turn(turn));
		return state.getSquare(square).isUnoccupied();
	}
}
