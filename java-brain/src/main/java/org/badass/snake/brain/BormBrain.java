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
	
	private StringBuffer memory = new StringBuffer();
	
	public String getName() {
		return "Team BORM";
	}

	public Movement getNextMove(GameState arg0) {
		Collection<Snake> snakes = arg0.snakes();
		List<Position> fruits = arg0.getFruitPositions();
		
		Position bestFruit = null;
		Movement bestMovement = null;
		int otherSnakes = 0;
		
		Snake ourSnake = arg0.getSnake( getName() );
		Position ourPosition = ourSnake.getHeadPosition();
				
		for ( Position pos : fruits) {
			for ( Snake snake : snakes ) {
				// Found snakes position is less than our own
				if ( PositionUtils.distance(snake.getHeadPosition(), pos) > PositionUtils.distance(ourPosition, pos) ) {
					bestFruit = pos;
					System.out.println( "--- DEBUG: Snake "+snake.getName()+" is further away, fruit at "+ pos.getX() +": " + pos.getY() );
				}		
			}	
		}
		
		PathPlanner planner = new PathPlanner(ourSnake);
		
		// Found a fruit, go for it!
		if (bestFruit != null) {
			System.out.println("--DEBUG: Going for fruit at "+bestFruit.getX()+":"+bestFruit.getY());
			bestMovement = planner.plan(bestFruit, arg0);
		} else {
			if ( arg0.getSquare(ourPosition.getX()+1, ourPosition.getY()).isUnoccupied() ) {
				// No trouble (x+1,y)
				Position newPosition = new Position(ourPosition.getX()+1, ourPosition.getY());
				bestMovement = planner.plan(newPosition, arg0);
			} else if ( arg0.getSquare(ourPosition.getX(), ourPosition.getY()+1).isUnoccupied() ) {
				// No trouble (x,y+1)
				Position newPosition = new Position(ourPosition.getX()+1, ourPosition.getY());
				bestMovement = planner.plan(newPosition, arg0);
			} else if ( arg0.getSquare(ourPosition.getX()-1, ourPosition.getY()).isUnoccupied() ) {
				// No trouble (x-1,y)
				Position newPosition = new Position(ourPosition.getX()-1, ourPosition.getY());
				bestMovement = planner.plan(newPosition, arg0);
			} else if ( arg0.getSquare(ourPosition.getX(), ourPosition.getY()-1).isUnoccupied() ) {
				// No trouble (x,y-1)
				Position newPosition = new Position(ourPosition.getX()-1, ourPosition.getY());
				bestMovement = planner.plan(newPosition, arg0);
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
