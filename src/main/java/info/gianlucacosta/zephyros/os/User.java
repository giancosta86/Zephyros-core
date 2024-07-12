package info.gianlucacosta.zephyros.os;

import info.gianlucacosta.zephyros.swing.dialogs.BasicDialogs;

import java.awt.Desktop;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * General-purpose user-related functions
 */
public interface User {
    /**
     * Tries to retrieve the user's home directory
     *
     * @return The user's home directory, if available
     */
    static Optional<Path> getHomeDirectory() {
        return Optional
                .ofNullable(
                        System.getProperty("user.home")
                )
                .map(Paths::get);
    }


    /**
     * Tries to open the browser at the given URL, showing a warning message
     * if it's not available.
     * <p>
     * Exceptions (such as malformed URI) are thrown.
     *
     * @param url The URL to show in the browser
     */
    static void openBrowser(String url) {
        Desktop desktop =
                Desktop.getDesktop();

        if (desktop != null) {
            try {
                desktop.browse(
                        new URI(url)
                );
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            BasicDialogs.showWarning("Cannot detect the web browser for your OS.");
        }
    }
}
