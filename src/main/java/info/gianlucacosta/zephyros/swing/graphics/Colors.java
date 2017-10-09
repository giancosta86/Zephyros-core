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
