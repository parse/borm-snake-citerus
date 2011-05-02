package se.citerus.crazysnake;

/**
 * Represents some content within a {@code Square} on the board.
 */
public interface SquareContent {

    /**
     * Returns the type of the content.
     *
     * @return the type of the content.
     * @see SquareContentType
     */
    SquareContentType getContentType();

    /**
     * If this content represents a part of a {@code Snake}, this method can be
     * used to retrieve that {@code Snake}.
     *
     * @return if this content represents a {@code Snake}, this method returns
     *         that {@code Snake}. Otherwise null.
     */
    Snake getSnake();

}
