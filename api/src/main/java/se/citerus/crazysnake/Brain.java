package se.citerus.crazysnake;

import java.util.Set;

/**
 * The brain of the snake.
 * <p/>
 * Implementing classes should provide the following:
 * <ol>
 * <li> A static name via getName()</li>
 * <li> Its next movement via getNextMove(), based on the current GameState. getNextMove() must finish within a given
 * time span or the results will be ignored and the snake will continue its next move forward.</li>
 * </ol>
 * init() is called once when the game starts. This can be used to keep track of other participants in the game and
 * to get some metadata about the current game.
 */
public interface Brain {

    /**
     * Will be called once by the game engine before the game is started.
     *
     * @param participants All participants in this heat.
     * @param meta         Meta data.
     */
    void init(Set<String> participants, HeatMeta meta);

    /**
     * @param state Game state.
     * @return The snake's desired next move.
     */
    Movement getNextMove(GameState state);

    /**
     * @return Name, for display purposes only.
     */
    String getName();
}
