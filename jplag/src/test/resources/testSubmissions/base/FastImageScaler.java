package carcassonne.util;

import java.awt.Image;

/**
 * Utility class for scaling down quadratic images extremely fast. This class enable faster scaling than
 * {@link Image#getScaledInstance(int, int, int)} (5x faster with {@link Image#SCALE_FAST} and 10x faster with
 * {@link Image#SCALE_SMOOTH}). Additionally, the resulting images look better as images generated with
 * {@link Image#SCALE_FAST}. This class is heavily based on an article by Dr. Franz Graf.
 * @see <a href=" https://www.locked.de/fast-image-scaling-in-java/">locked.de/fast-image-scaling-in-java</a>
 * @author Timur Saglam
 */
public final class FastImageScaler {

    private FastImageScaler() {
        // private constructor ensures non-instantiability!
    }

    /**
     * Scales down a quadratic images to a image with a given size.
     * @param image is the original image.
     * @param targetSize is the desired edge length of the scaled image.
     * @return the scaled image.
     */
    public static Image scaleDown(Image image, int targetSize) {
        return image; // scaleDown needs to be implemented!
    }
}