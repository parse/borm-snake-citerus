package org.badass.snake.brain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import se.citerus.crazysnake.Direction;
import se.citerus.crazysnake.GameState;
import se.citerus.crazysnake.Movement;
import se.citerus.crazysnake.Position;
import se.citerus.crazysnake.Snake;

public class MadBormBrain extends BormBrain {
	@Override
	public String getName() {
		return "Mad-borm";
	}
	
	@Override
	public Movement getNextMove(GameState state) {
		final Snake snake = state.getSnake(getName());
		final Position ourPosition = snake.getHeadPosition();
		final Direction ourDir = snake.getDirection();
		final PathPlanner planner = new PathPlanner(snake, state);
		
		ArrayList<Position> fruits = new ArrayList<Position>(state.getFruitPositions());
		
		Collections.sort(fruits, new Comparator<Position>() {
			public int compare(Position o1, Position o2) {
				float dist1 = PositionUtils.distance(ourPosition, o1),
					dist2 = PositionUtils.distance(ourPosition, o2);
				return (int)Math.signum((dist1) - (dist2));  
			}
		});
		
		if ( fruits.size() > 0 )
			return planner.plan(snake, fruits.get(0));
		else
			return planner.plan(snake, new Position(25, 25));
	}
}
