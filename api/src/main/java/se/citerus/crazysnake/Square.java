package se.citerus.crazysnake;

import java.util.List;

/**
 * A Square is a specific cell on the board with its content. A Square
 * containing more than one objects leads to a collision.
 *
 * If such collision includes the head of a {@code Snake} and another
 * hard object (such as a snake or a wall), that specific {@code Snake} dies.
 *
 * A Square can also contain fruit. When a snake collides with a fruit, the fruit
 * gets eaten and the snake scores a point in the game. All fruits are considered
 * equally important when it comes to scoring.
 */
public interface Square {

    /**
     * Returns true if the square does not contain a wall or a snake-part, otherwise false.
     *
     * @return true if the Square does not contain a wall or a snake-part, otherwise false.
     */
    boolean isUnoccupied();

    /**
     * Returns true if a collision is detected in the Square. More formally, if
     * Square contains more than one objects.
     *
     * @return true if a collision is detected in the Square, otherwise false.
     */
    boolean isCollision();

    /**
     * Returns true if a lethal collision is detected at the Square. That is, if
     * the Square contains more than one snake or a snake colliding with a wall.
     *
     * @return true if a lethal collision is detected in the Square.
     */
    boolean isLethalCollision();

    /**
     * Returns the content of the Square.
     *
     * @return the content of the Square.
     * @see SquareContent
     */
    List<SquareContent> getContent();

    /**
     * Tells whether this Square is occupied by a snake
     *
     * @return true if Square is occupied by a snake
     */
    boolean containsSnake();

    /**
     * Tells whether this Square is occupied by a fruit
     *
     * @return true if Square is occupied by a fruit
     */
    boolean containsFruit();

    /**
     * Tells whether this Square is occupied by a wall
     *
     * @return true if Square is occupied by a wall
     */
    boolean containsWall();

    /**
     * Tells whether this Square is occupied by a head
     *
     * @return true if Square is occupied by a head
     */
    boolean containsHead();

    /**
     * Tells whether this Square is occupied by a cherry
     *
     * @return true if Square is occupied by a cherry
     */
    boolean containsCherry();

    /**
     * Tells whether this Square is occupied by an apple
     *
     * @return true if Square is occupied by an apple
     */
    boolean containsApple();

    /**
     * Tells whether this Square is occupied by a strawberry
     *
     * @return true if Square is occupied by a strawberry
     */
    boolean containsStrawberry();

    /**
     * Return the direction of a Snake. Throws IllegalArgumentException if square doesn't
     * contain a head.
     *
     * @return The direction of the snake
     */
    Direction getSnakeDirection();
}
