package info.gianlucacosta.zephyros.swing.graphics;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

/**
 * Image-related functions
 */
public interface Images {
    /**
     * Returns an Image from a URL, with an unchecked exception in case of errors
     *
     * @param url The source url
     * @return The requested Image
     */
    static Image readFromURL(URL url) {
        try {
            return ImageIO.read(url);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
