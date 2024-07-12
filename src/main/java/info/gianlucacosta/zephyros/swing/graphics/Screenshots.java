package info.gianlucacosta.zephyros.swing.graphics;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Performs different kinds of screenshots
 */
public interface Screenshots {
    static void saveComponentAsJpeg(
            JComponent component,
            Path outputPath
    ) {
        saveComponentAsImage(
                component,
                outputPath,
                "jpg"
        );
    }


    static void saveComponentAsPng(
            JComponent component,
            Path outputPath
    ) {
        saveComponentAsImage(
                component,
                outputPath,
                "png"
        );
    }


    static void saveComponentAsImage(
            JComponent component,
            Path outputPath,
            String formatName
    ) {
        try {
            BufferedImage capturedImage =
                    new BufferedImage(
                            component.getWidth(),
                            component.getHeight(),
                            BufferedImage.TYPE_INT_RGB
                    );

            component.printAll(capturedImage.getGraphics());


            ImageIO.write(
                    capturedImage,
                    formatName,
                    outputPath.toFile()
            );
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
