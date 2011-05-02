package se.citerus.crazysnake;

/**
 * Describes what can types of content that exist on a Square.
 */
public enum SquareContentType {

    SNAKE_HEAD(true), SNAKE_TAIL(true), BORDER(true), CHERRY(false), APPLE(false), STRAWBERRY(false);

    private final boolean collisionIsLethal;

    private SquareContentType(boolean hardObject) {
        this.collisionIsLethal = hardObject;
    }

    public boolean isHard() {
        return collisionIsLethal;
    }
}
