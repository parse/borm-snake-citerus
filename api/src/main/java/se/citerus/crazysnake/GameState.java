package se.citerus.crazysnake;

import java.io.Serializable;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Represents the current state of the game.
 * <p/>
 * Note that a new GameState is provided to the brains for each turn.
 */
public class GameState implements Serializable {

    private final Square[][] arena;
    private final Map<String, Snake> snakes;
    private final long turn;
    private final long turnsUntilGrowth;
    private List<Position> fruitPositions;

    public GameState(final Square[][] arena, final Map<String, Snake> snakes, long turn, long turnUntilGrowth) {
        this.arena = arena;
        this.snakes = snakes;
        this.turn = turn;
        this.turnsUntilGrowth = turnUntilGrowth;
        fruitPositions = extractFruitPositions(arena);
    }

    /**
     * Returns the turn which this GameState represents.
     *
     * @return the turn which this GameState represents.
     */
    public long getTurn() {
        return turn;
    }

    /**
     * Returns the number of turns until the snakes will grow by one square in
     * length. 0 indicates that the snakes will grow this turn. More formally, 0
     * indicates that the end of the tails will remain on the same square after
     * this turns movement.
     *
     * @return the number of turns until the snakes will grow by one square.
     */
    public long getTurnsUntilGrowth() {
        return turnsUntilGrowth;
    }

    /**
     * Returns the Snake with the given id. The id is equal to the name of the Snake.
     *
     * @param id the id for the Snake.
     * @return the Snake with the given id, null if no such Snake exists.
     */
    public Snake getSnake(String id) {
        return snakes.get(id);
    }

    /**
     * Returns the Square at the given (x,y) position.
     * <p/>
     * If the coordinate (either x- or y) is below 0, 0 will be used. Likewise,
     * if the coordinate is larger than the size of the board in that direction,
     * size-1 will be used.
     * <p/>
     * For example, given a board of size 10-10, asking for a Square on position (0, 11)
     * will return the Square for position (0, 9). Asking for a Square on position (-1, 5)
     * will return the Square for position (0, 5).
     *
     * @param x the x-position
     * @param y the y-position
     * @return the Square at the given position
     */
    public Square getSquare(int x, int y) {
        int safeX = min(max(x, 0), getNoColumns() - 1);
        int safeY = min(max(y, 0), getNoRows() - 1);
        return arena[safeX][safeY];
    }

    /**
     * @return the number of columns (x-wise) of the game board.
     */
    public int getNoColumns() {
        return arena.length;
    }

    /**
     * @return the number of rows (y-dimension) of the game board.
     */
    public int getNoRows() {
        return arena[0].length;
    }

    /**
     * @return a Collection of all living snakes currently in the game.
     */
    public Collection<Snake> snakes() {
        return Collections.unmodifiableCollection(snakes.values());
    }

    /**
     * @return the number of living snakes in the game
     */
    public int getNoSnakes() {
        return snakes.size();
    }

    /**
     * Returns a Square for the given Position. This method follows the same rules for boundaries as
     * {@code getSquare(int, int)}}
     *
     * @param position the Position
     * @return the Square at the given Position
     * @see #getSquare(int, int)
     */
    public Square getSquare(Position position) {
        return getSquare(position.getX(), position.getY());
    }

    /**
     * @return a list containing the current positions of all fruits in the game
     */
    public List<Position> getFruitPositions() {
        return fruitPositions;
    }

    /**
     * Calculate where on the game board there are fruits.
     *
     * @param arena the game arena
     * @return a list of Position objects where fruit can be found.
     */
    private List<Position> extractFruitPositions(Square[][] arena) {
        int width = arena.length;
        int height = arena[0].length;
        List<Position> fruitPositions = new ArrayList<Position>();
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                if (arena[x][y].containsFruit())
                    fruitPositions.add(new Position(x, y));

        return Collections.unmodifiableList(fruitPositions);
    }
}
