package info.gianlucacosta.zephyros.swing.graphics;

import java.awt.Color;

/**
 * Functions handling colors
 */
public interface Colors {
    /**
     * Encodes the given <b>opaque</b> color to the web format
     * compatible with Color.decode
     *
     * @param color The opaque color to encode
     * @return a hexadecimal string starting with #
     */
    static String encode(Color color) {
        if (color.getAlpha() == 255) {
            return String.format(
                    "#%02X%02X%02X",
                    color.getRed(),
                    color.getGreen(),
                    color.getBlue()
            );
        } else {
            throw new IllegalArgumentException("Only opaque colors are supported");
        }
    }
}
