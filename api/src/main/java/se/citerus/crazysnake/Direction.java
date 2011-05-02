package se.citerus.crazysnake;

/**
 * Represents a compass direction in the world of snakes.
 */
public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    /**
     * For a given direction and a relative movement, generates a new Direction.
     * <p/>
     * For example, if we are moving NORTH and turn RIGHT the generated Direction is EAST.
     *
     * @param movement the turn movement relative to the current direction
     * @return the new direction
     */
    public Direction turn(Movement movement) {
        switch (movement) {
            case FORWARD:
                return this;
            case LEFT:
                switch (this) {
                    case EAST:
                        return NORTH;
                    case NORTH:
                        return WEST;
                    case WEST:
                        return SOUTH;
                    case SOUTH:
                        return EAST;
                }
            case RIGHT:
                switch (this) {
                    case EAST:
                        return SOUTH;
                    case SOUTH:
                        return WEST;
                    case WEST:
                        return NORTH;
                    case NORTH:
                        return EAST;
                }
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Generates a Movement from a Direction relative to another Direction.
     * the Movement that should be used
     * <p/>
     * <p/>
     * For example, if we are moving NORTH and decide that we want to go EAST, this method will return RIGHT.
     * <p/>
     * NOTE: A Movement can never be used to reverse the movement of a snake. For example,
     * if we are moving NORTH and decide that we want to go SOUTH, there is no Movement that
     * can take us there, since snakes can only turn RIGHT, LEFT or move FORWARD. In these cases,
     * this method will return FORWARD.
     *
     * @param currentDirection the current Direction
     * @param newDirection the desired direction
     * @return a Movement that takes us from the current to the desired Direction, if possible.
     */
    public static Movement newMovement(Direction currentDirection, Direction newDirection) {
        if (currentDirection == newDirection) {
            return Movement.FORWARD;
        }

        switch (currentDirection) {
            case EAST:
                switch (newDirection) {
                    case NORTH:
                        return Movement.LEFT;
                    case SOUTH:
                        return Movement.RIGHT;
                    default:
                        return Movement.FORWARD;
                }
            case NORTH:
                switch (newDirection) {
                    case EAST:
                        return Movement.RIGHT;
                    case WEST:
                        return Movement.LEFT;
                    default:
                        return Movement.FORWARD;
                }
            case WEST:
                switch (newDirection) {
                    case NORTH:
                        return Movement.RIGHT;
                    case SOUTH:
                        return Movement.LEFT;
                    default:
                        return Movement.FORWARD;
                }
            case SOUTH:
                switch (newDirection) {
                    case EAST:
                        return Movement.LEFT;
                    case WEST:
                        return Movement.RIGHT;
                    default:
                        return Movement.FORWARD;
                }
        }

        return null;
    }
}
