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

package info.gianlucacosta.zephyros.swing.dialogs;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

/**
 * Splash screen to show while your application is loading.
 * <p>
 * It is undecorated, partially transparent, centered in the screen
 * and includes the app image as well as an indeterminate progress bar.
 */
public class SplashScreen extends JFrame {
    /**
     * @param windowIcon   The image (usually 32x32) for the taskbar icon
     * @param productName  The application's name
     * @param productImage The logo (usually 512x512) shown in the window
     */
    public SplashScreen(
            Image windowIcon,
            String productName,
            Image productImage
    ) {
        setIconImage(windowIcon);

        setTitle(productName);

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        setLayout(new BorderLayout());

        add(
                new JLabel(
                        new ImageIcon(
                                productImage
                        )
                ),
                BorderLayout.CENTER
        );

        add(
                new JProgressBar() {
                    {
                        setIndeterminate(true);
                        setPreferredSize(
                                new Dimension(
                                        0,
                                        32
                                )
                        );
                    }
                },

                BorderLayout.SOUTH
        );

        pack();

        setLocationRelativeTo(null);
    }
}
