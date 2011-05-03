package org.badass.snake.brain;

import se.citerus.crazysnake.*;

public class PositionUtils {
	public static int distance(Position p1, Position p2) {
		return Math.abs(p2.getX() - p1.getX()) + Math.abs(p2.getY() - p1.getY());
	}
}
