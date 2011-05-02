package se.citerus.crazysnake;

/**
 * Represents the incarnation of a Snake's soul (i.e the {@code Brain}). A Snake
 * is positioned in the arena as long as it is still alive. Its {@code Brain}
 * determines how the Snake moves.
 */
public interface Snake extends Comparable<Snake> {

    /**
     * Returns the current length of the {@code Snake} including, including the
     * head.
     *
     * @return the current length of the {@code Snake}.
     */
    public int getLength();

    /**
     * Returns the {@code Position} of the {@code Snake}'s head on the board.
     *
     * @return a {@code Position} representing the position of the {@code
     *         Square} containing the head of this {@code Snake}.
     */
    public Position getHeadPosition();

    /**
     * Returns the current {@code Direction} of the {@code Snake}.
     *
     * @return the current {@code Direction} of the {@code Snake}.
     */
    public Direction getDirection();

    /**
     * Returns the name of the {@code Snake}.
     *
     * @return the name of the {@code Snake}.
     */
    public String getName();

    /**
     * Returns the current score of the {@code Snake}.
     *
     * @return the current score of the {@code Snake}.
     */
    public int getScore();
}
