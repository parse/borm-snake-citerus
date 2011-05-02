package se.citerus.crazysnake;

import java.io.Serializable;

/**
 * A position with x and y coordinate on the board.
 */
@SuppressWarnings({"RedundantIfStatement"})
public class Position implements Serializable {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate.
     *
     * @return the x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate.
     *
     * @return the y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the {@code Position} of a one square offset with respect to the
     * specified {@code Direction}.
     *
     * @param direction the offset {@code Direction}.
     * @return the {@code Position} of a one square offset with respect to the
     *         specified {@code Direction}.
     */
    public Position offset(Direction direction) {
        return offset(direction, 1);
    }

    /**
     * Returns the {@code Position} of a square offset with specified length
     * with respect to the specified {@code Direction}.
     *
     * @param direction the offset {@code Direction}.
     * @param length    the offset length.
     * @return the {@code Position} of a square offset with specified length
     *         with respect to the specified {@code Direction}.
     */
    public Position offset(Direction direction, int length) {
        switch (direction) {
            case EAST:
                return new Position(x + length, y);
            case NORTH:
                return new Position(x, y - length);
            case SOUTH:
                return new Position(x, y + length);
            case WEST:
                return new Position(x - length, y);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

}
