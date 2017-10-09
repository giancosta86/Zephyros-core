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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Optional;

/**
 * General-purpose dialog for editing an object.
 *
 * @param <T> The object type
 */
public abstract class ObjectDialog<T> {
    protected final Optional<T> initialValueOption;

    private JDialog backingDialog;

    private JButton okButton;
    private JButton cancelButton;

    private Optional<T> confirmedValueOption =
            Optional.empty();

    /**
     * Creates an empty dialog, ready to create a brand-new object
     */
    public ObjectDialog() {
        initialValueOption = Optional.empty();
    }

    /**
     * Create as dialog with the data of an existing object,
     * whose a clone should be edited
     *
     * @param valueToedit The initial value in the dialog
     */
    public ObjectDialog(T valueToedit) {
        this.initialValueOption = Optional.of(valueToedit);

    }


    /**
     * Shows the dialog, returning only when it's confirmed and validated
     * or closed; validation errors should occur via RuntimeException's,
     * which are caught and shown via a dedicated warning dialog, without
     * closing the ObjectDialog.
     *
     * @return The object whose attributes are backed by the dialog.
     */
    public Optional<T> show() {
        backingDialog =
                new JDialog() {
                    {
                        getWindowIcon().ifPresent(this::setIconImage);

                        setTitle(
                                getDialogTitle(
                                        initialValueOption.isPresent()
                                )
                        );

                        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                        setModal(true);


                        setLayout(new BorderLayout());

                        add(
                                createContentPanel(),
                                BorderLayout.CENTER
                        );


                        add(
                                createButtonsPanel(),
                                BorderLayout.SOUTH
                        );

                        getRootPane().setDefaultButton(okButton);

                        getRootPane().registerKeyboardAction(
                                cancelButton.getActionListeners()[0],
                                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                JComponent.WHEN_IN_FOCUSED_WINDOW
                        );


                        pack();

                        setLocationRelativeTo(null);
                    }
                };

        initialValueOption.ifPresent(this::writeToGuiFields);

        backingDialog.setVisible(true);

        return confirmedValueOption;
    }


    /**
     * @param hasInitialValue true if the dialog was started to edit an existing
     *                        object
     * @return The dialog's title
     */
    protected abstract String getDialogTitle(boolean hasInitialValue);


    /**
     * @return The icon for the dialog
     */
    protected abstract Optional<Image> getWindowIcon();


    /**
     * @return The panel with the controls required to edit the object.
     * A good candidate might be LabeledInputPanel.
     */
    protected abstract JPanel createContentPanel();

    /**
     * Writes the properties of the initial object into the GUI controls
     *
     * @param initialValue The initial object
     */
    protected abstract void writeToGuiFields(T initialValue);

    /**
     * Reads the properties of the object and creates the return value
     * for the <i>show()</i> method.
     * <p>
     * Validation errors should be notified by throwing any RuntimeException
     * from within this method.
     *
     * @return The object returned by the dialog
     */
    protected abstract T readFromGuiFields();


    private JPanel createButtonsPanel() {
        JPanel buttonsPanel =
                new JPanel() {
                    {
                        setLayout(
                                new GridLayout(0, 2)
                        );

                        setBorder(
                                BorderFactory.createEmptyBorder(8, 8, 8, 8)
                        );
                    }
                };


        okButton =
                new JButton("OK") {
                    {
                        addActionListener(event -> {
                            try {
                                T confirmedValue =
                                        readFromGuiFields();

                                confirmedValueOption =
                                        Optional.of(confirmedValue);

                                backingDialog.dispose();
                            } catch (Exception ex) {
                                BasicDialogs.showException(ex);
                            }
                        });
                    }
                };

        buttonsPanel.add(okButton);


        cancelButton =
                new JButton("Cancel") {
                    {
                        addActionListener(event ->
                                backingDialog.dispose()
                        );
                    }
                };

        buttonsPanel.add(cancelButton);

        return buttonsPanel;
    }
}
