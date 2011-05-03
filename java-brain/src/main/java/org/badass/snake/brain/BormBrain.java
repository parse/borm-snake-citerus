 
package org.badass.snake.brain;
import se.citerus.crazysnake.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
		ArrayList<Position> fruits = new ArrayList<Position>(arg0.getFruitPositions());
		
		Snake ourSnake = arg0.getSnake( getName() );
		final Position ourPosition = ourSnake.getHeadPosition();

		PathPlanner planner = new PathPlanner(ourSnake);
		
		Collections.sort(fruits, new Comparator<Position>() {
			public int compare(Position o1, Position o2) {
				int dist1 = PositionUtils.distance(ourPosition, o1),
					dist2 = PositionUtils.distance(ourPosition, o2);
				return (int)Math.signum(dist1 - dist2);  
			}
		});
		
		for ( Position pos : fruits) {
			for ( Snake otherSnake : snakes ) {
				// Found snakes position is less than our own
				if ( PositionUtils.distance(otherSnake.getHeadPosition(), pos) > PositionUtils.distance(ourPosition, pos) ) {
					System.out.println( "--- DEBUG: Snake "+otherSnake.getName()+" is further away, fruit at "+ pos.getX() +": " + pos.getY() );
					return planner.plan(ourSnake, pos, arg0);
				}		
			}	
		}
		
		return planner.plan(ourSnake, ourPosition, arg0);
	}

	@Override
	public void init(Set<String> arg0, HeatMeta arg1) {
		// TODO Auto-generated method stub
	}
}
