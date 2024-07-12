package info.gianlucacosta.zephyros.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Utilities for actions based on files
 */
public interface FileBasedActions {

    /**
     * Executes the given action only if the given flag file does not exist:
     * in this case, it runs the action and creates the file.
     *
     * @param flagFile The flag file, which must be missing for the action to run
     * @param action   The action to execute
     */
    static void runOnce(Path flagFile, Runnable action) {
        if (!Files.exists(flagFile)) {
            action.run();

            try {
                Files.createFile(flagFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
