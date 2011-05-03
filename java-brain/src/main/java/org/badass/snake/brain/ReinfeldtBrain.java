package org.badass.snake.brain;

import se.citerus.crazysnake.Movement;

import static se.citerus.crazysnake.Movement.RIGHT;

/**
 * Inherently right-drifting.
 */
public class ReinfeldtBrain extends PoliticalBrain {

    @Override
    public Movement getDodgeProblemsAheadDirection() {
        return RIGHT;
    }
}
