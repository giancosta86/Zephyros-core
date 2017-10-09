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

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;

/**
 * General-purpose dialogs
 */
public final class BasicDialogs {
    private static Optional<File> latestChosenDirectoryOption =
            Optional.empty();


    private BasicDialogs() {
    }


    public static void showInfo(String message) {
        showBasicMessage(message, JOptionPane.INFORMATION_MESSAGE);
    }


    public static void showWarning(String message) {
        showBasicMessage(message, JOptionPane.WARNING_MESSAGE);
    }


    public static void showError(String message) {
        showBasicMessage(message, JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Shows a warning message by suitably formatting the given exception,
     * <i>in addition to printing its stack trace to stderr</i>.
     *
     * @param ex The exception to show
     */
    public static void showException(Exception ex) {
        String message =
                getThrowableMessage(ex);

        showWarning(message);

        ex.printStackTrace(System.err);
    }


    private static String getThrowableMessage(Throwable throwable) {
        if (!throwable.getLocalizedMessage().isEmpty()) {
            return throwable.getLocalizedMessage();
        }

        if (throwable.getCause() != null) {
            return getThrowableMessage(throwable.getCause());
        }

        if (throwable.getSuppressed().length > 0) {
            return getThrowableMessage(throwable.getSuppressed()[0]);
        }

        return throwable.getClass().getSimpleName();
    }


    private static void showBasicMessage(String message, int kind) {
        //Adding a label enables support for HTML formatting
        JLabel messageLabel =
                new JLabel(message);

        JOptionPane.showMessageDialog(
                null,
                messageLabel,
                "Message",
                kind
        );
    }


    /**
     * Dialog for choosing a file to open, adding the given file filter to the file dialog and remembering the
     * directory of the chosen file - which will be used by subsequent file dialogs.
     * <p>
     * In addition to this, if the file name filter is selected but the file does not have its extension,
     * the extension is automatically appended.
     *
     * @param title      The dialog title
     * @param fileFilter The file filter
     * @return The path of the file to open
     */
    public static Optional<Path> openFile(String title, FileNameExtensionFilter fileFilter) {
        return chooseFile(
                title,
                fileFilter,
                fileChooser -> fileChooser.showOpenDialog(null),
                true
        );
    }


    /**
     * Dialog for choosing a file to save, adding the given file filter to the file dialog and remembering the
     * directory of the chosen file - which will be used by subsequent file dialogs.
     * <p>
     * In addition to this, if the file name filter is selected but the file does not have its extension,
     * the extension is automatically appended.
     *
     * @param title      The dialog title
     * @param fileFilter The file filter
     * @return The path of the file to save
     */
    public static Optional<Path> saveFile(String title, FileNameExtensionFilter fileFilter) {
        return chooseFile(
                title,
                fileFilter,
                jFileChooser -> jFileChooser.showSaveDialog(null),
                false
        );
    }


    private static Optional<Path> chooseFile(
            String title,
            FileNameExtensionFilter fileFilter,
            Function<JFileChooser, Integer> fileSelectionFunction,
            boolean requireFileExistence
    ) {
        JFileChooser fileChooser =
                new JFileChooser();

        fileChooser.setDialogTitle(title);

        fileChooser.setFileFilter(fileFilter);

        latestChosenDirectoryOption.ifPresent(fileChooser::setCurrentDirectory);

        int fileSelectionOutcome =
                fileSelectionFunction.apply(fileChooser);

        if (fileSelectionOutcome == JFileChooser.APPROVE_OPTION) {
            File selectedFile =
                    fileChooser.getSelectedFile();

            File actualSelectedFile;

            if (fileChooser.getFileFilter() == fileFilter) {
                String mainExtension =
                        fileFilter.getExtensions()[0];

                actualSelectedFile =
                        selectedFile.getName().endsWith("." + mainExtension) ?
                                selectedFile
                                :
                                new File(selectedFile.getPath() + "." + mainExtension);
            } else {
                actualSelectedFile =
                        selectedFile;
            }

            latestChosenDirectoryOption =
                    Optional.of(actualSelectedFile.getParentFile());


            Path actualSelectedPath =
                    actualSelectedFile.toPath();

            if (!requireFileExistence || Files.exists(actualSelectedPath)) {
                return Optional.of(actualSelectedPath);
            } else {
                BasicDialogs.showWarning(
                        String.format(
                                "Cannot select inexisting path: '%s'",
                                actualSelectedPath
                        )
                );

                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }


    /**
     * Dialog for choosing a color.
     *
     * @param title        The dialog title
     * @param initialColor The initial color shown in the dialog
     * @return The chosen color
     */
    public static Optional<Color> chooseColor(String title, Color initialColor) {
        return Optional.ofNullable(
                JColorChooser.showDialog(
                        null,
                        title,
                        initialColor
                )
        );
    }


    /**
     * Asks the user for a string value.
     *
     * @param prompt       The prompt
     * @param initialValue The initial string shown in the dialog
     * @return The string input by the user
     */
    public static Optional<String> askForString(
            String prompt,
            String initialValue
    ) {
        String selectedValue =
                JOptionPane.showInputDialog(
                        null,
                        prompt,
                        initialValue
                );

        return Optional.ofNullable(selectedValue);
    }
}
