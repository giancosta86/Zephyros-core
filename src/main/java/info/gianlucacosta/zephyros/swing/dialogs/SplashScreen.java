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
