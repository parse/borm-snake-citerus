 
package org.badass.snake.brain;
import se.citerus.crazysnake.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
		ArrayList<Position> fruits = new ArrayList<Position>(arg0.getFruitPositions());
		
		Snake ourSnake = arg0.getSnake( getName() );
		final Position ourPosition = ourSnake.getHeadPosition();
		final Direction ourDir = ourSnake.getDirection();

		final PathPlanner planner = new PathPlanner(ourSnake, arg0);
		Position bestFruit = null;
		
		final HashMap<Position, Float> fruitDensities = new HashMap<Position, Float>(fruits.size());
		
		for ( Position fruit : fruits ) {
			float d = 0;
			for ( Position other : fruits ) {
				if ( !fruit.equals(other) ) {
					float distance = (float)PositionUtils.distance(fruit, other);
					d += (100.0f / distance);
				}
			}
			fruitDensities.put(fruit, d / fruits.size());
		}
		
		Collections.sort(fruits, new Comparator<Position>() {
			public int compare(Position o1, Position o2) {
				float dist1 = planner.walkDistance(ourPosition, ourDir, o1),
					dist2 = planner.walkDistance(ourPosition, ourDir, o2);
				return (int)Math.signum((dist1 - fruitDensities.get(o1)) 
										- (dist2 - fruitDensities.get(o2)));  
			}
		});
		
		int fruitIx = 0;
		if ( fruits.size() > 0 && fruitIx < fruits.size() ) {
			for ( Snake otherSnake : snakes ) {
				bestFruit = fruits.get(fruitIx);
				if ( planner.walkDistance(otherSnake.getHeadPosition(), otherSnake.getDirection(), bestFruit) < planner.walkDistance(ourPosition, ourDir, bestFruit) )
					fruitIx++;
			}
			
			if ( bestFruit != null )
				return planner.plan(ourSnake, bestFruit);
		}
		
		System.out.println("Borm-idling.");
		return planner.plan(ourSnake, ourPosition);
	}

	@Override
	public void init(Set<String> arg0, HeatMeta arg1) {
		// TODO Auto-generated method stub
	}
}
