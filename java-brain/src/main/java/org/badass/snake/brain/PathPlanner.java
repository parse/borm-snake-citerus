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
	//	return null;
	}
	
	private void rePlan(Position currentPos, Direction currentDir) {
		if ( currentPos.equals(target) )
			return;
		
		// Check movement alternatives;
		Position toTarget = new Position(target.getX() - currentPos.getX(), target.getY() - currentPos.getY());
		Direction nextMove = null;

		for ( int i = 0; i < 4; i++) {
			Direction dir = headingAsDirection(i);
			Position squarePos = currentPos.offset(dir);
			Square s = state.getSquare(squarePos.getX(), squarePos.getY());
			if ( s.isUnoccupied() ) {
				nextMove = dir;
			}
		}
		
		if ( nextMove == null ) return;
		
		path.push(Direction.newMovement(currentDir, nextMove));
		rePlan(currentPos.offset(nextMove), nextMove);
	}
	
	private Direction headingAsDirection(int heading) {
		switch (heading) {
		case 0: return Direction.NORTH;
		case 1: return Direction.EAST;
		case 2: return Direction.SOUTH;
		case 3: return Direction.WEST;
		}
		return null;
	}
}
