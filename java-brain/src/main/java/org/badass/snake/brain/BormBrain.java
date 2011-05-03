package org.badass.snake.brain;

import se.citerus.crazysnake.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.citerus.crazysnake.Brain;
import se.citerus.crazysnake.GameState;
import se.citerus.crazysnake.HeatMeta;
import se.citerus.crazysnake.Movement;
import se.citerus.crazysnake.Snake;
import se.citerus.crazysnake.Position;

public class BormBrain implements Brain {
	
	public String getName() {
		return "Team BORM";
	}

	public Movement getNextMove(GameState arg0) {
		Collection<Snake> snakes = arg0.snakes();
		List<Position> fruits = arg0.getFruitPositions();
		
		Position bestFruit = null;
		Movement bestMovement = null;
		
		Snake ourSnake = null;
		Position ourPosition = null;
		
		for ( Snake snake : snakes ) {
			if (snake.getName() == "Team BORM") {
				ourSnake = snake;
				ourPosition = snake.getHeadPosition();
			}
		}
		
		Square ourSquare = arg0.getSquare(ourPosition);
		
		for ( Position pos : fruits) {
			for ( Snake snake : snakes ) {
				// Found snakes position is less than our own
				if (PositionUtils.distance(snake.getHeadPosition(), pos) > PositionUtils.distance(ourPosition, pos)) {
					bestFruit = pos;
				}
			}	
		}
		
		PathPlanner planner = new PathPlanner(ourSnake);
		
		// Found a fruit, go for it!
		if (bestFruit != null) {
			bestMovement = planner.plan(bestFruit, arg0);
		} else {
			if ( !arg0.getSquare(ourPosition.getX()+1, ourPosition.getY()).isCollision() ) {
				// No trouble (x+1,y)
				Position newPosition = new Position(ourPosition.getX()+1, ourPosition.getY());
				bestMovement = planner.plan(newPosition);
			} else if ( !arg0.getSquare(ourPosition.getX(), ourPosition.getY()+1).isCollision() ) {
				// No trouble (x,y+1)
				Position newPosition = new Position(ourPosition.getX()+1, ourPosition.getY());
				bestMovement = planner.plan(newPosition);
			} else if ( !arg0.getSquare(ourPosition.getX()-1, ourPosition.getY()).isCollision() ) {
				// No trouble (x-1,y)
				Position newPosition = new Position(ourPosition.getX()-1, ourPosition.getY());
				bestMovement = planner.plan(newPosition);
			} else if ( !arg0.getSquare(ourPosition.getX(), ourPosition.getY()-1).isCollision() ) {
				// No trouble (x,y-1)
				Position newPosition = new Position(ourPosition.getX()-1, ourPosition.getY());
				bestMovement = planner.plan(newPosition);
			}
					
			/*
			for (int x = ourPosition.getX()-10; x < ourPosition.getX()+10; x++) {
				for (int y = ourPosition.getY()-10; Y < ourPosition.getY()+10; x++) {
					
				}
			}
			*/
		}
		
		return bestMovement;
	}

	@Override
	public void init(Set<String> arg0, HeatMeta arg1) {
		// TODO Auto-generated method stub
		
	}
}
