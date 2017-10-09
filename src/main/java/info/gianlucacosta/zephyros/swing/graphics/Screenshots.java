/*^
  ===========================================================================
  Zephyros - Core
  ===========================================================================
  Copyright (C) 2017 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

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
