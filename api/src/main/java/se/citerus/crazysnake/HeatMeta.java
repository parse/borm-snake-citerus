package se.citerus.crazysnake;

import java.awt.*;

/**
 * Describes information about the current heat. This information remains static throughout the
 * entire heat.
 */
public class HeatMeta {

    private final long thinkTimeInMs;
    private final int growFrequency;
    private final Dimension boardDimensions;

    public HeatMeta(long thinkTimeInMs, int growFrequency, Dimension boardDimensions) {
        this.thinkTimeInMs = thinkTimeInMs;
        this.growFrequency = growFrequency;
        this.boardDimensions = boardDimensions;
    }

    /**
     * Returns the maximum allowed response time for a call to getNextMove in Brain. A Brain
     * should take this into account when performing its calculation for its next move. If the
     * brain is too slow, the result from getNextMove will be ignored and the snake will move forward.
     *
     * @return Maximum allowed think time in ms.
     * @see Brain#getNextMove(GameState)
     */
    public long getThinkTimeInMs() {
        return thinkTimeInMs;
    }

    /**
     * Returns the number of turns between growth of a snake.
     * <p/>
     * The game is turn-based and all snakes has a static growth frequency. All snakes
     * will grow at the same time and will grow by extension (by size of one Square) onto
     * the tail.
     *
     * @return the growth frequency for snakes (in turns).
     */
    public int getGrowFrequency() {
        return growFrequency;
    }

    /**
     * Returns the size of the arena.
     *
     * @return a Dimension describing the size of the arena
     */
    public Dimension getBoardDimensions() {
        return boardDimensions;
    }

    @Override
    public String toString() {
        return "HeatMeta{" +
                "thinkTimeInMs=" + thinkTimeInMs +
                ", growFrequency=" + growFrequency +
                ", boardDimensions=" + boardDimensions +
                '}';
    }
}
