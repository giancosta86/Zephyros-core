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
