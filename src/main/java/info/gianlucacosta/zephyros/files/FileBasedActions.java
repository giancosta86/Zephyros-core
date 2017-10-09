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
