package info.gianlucacosta.zephyros.swing.components;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.function.Consumer;

/**
 * Panel whose controls are layed out in a GridBagLayout having 2 columns:
 * the left one for the labels and the right one for input controls.
 * <p>
 * To use it, just create it and alternately call <i>addLabel(label string)</i>
 * and <i>addInput(input component)</i>.
 */
public class LabeledInputPanel extends JPanel {
    private static final Insets GRID_INSETS =
            new Insets(10, 10, 10, 10);


    private final GridBagConstraints labelConstraints =
            new GridBagConstraints() {
                {
                    gridx = 0;
                    insets = GRID_INSETS;
                }
            };

    private final GridBagConstraints inputConstraints =
            new GridBagConstraints() {
                {
                    gridx = 1;
                    weightx = 1;
                    fill = 1;
                    insets = GRID_INSETS;
                }
            };


    public LabeledInputPanel() {
        setLayout(new GridBagLayout());
    }


    public void addLabel(String labelText) {
        add(
                new JLabel(labelText) {
                    {
                        setFont(
                                getFont()
                                        .deriveFont(Font.BOLD)
                        );
                    }
                },
                labelConstraints
        );
    }


    public void addInput(JComponent inputComponent) {
        add(
                inputComponent,
                inputConstraints
        );
    }


    /**
     * Adds the given input component to the dialog, altering a <i>clone</i>
     * of the internal constraints.
     *
     * @param inputComponent           The component to add
     * @param constraintsCloneConsumer The consumer used to alter the clone of the constraints
     */
    public void addInput(
            JComponent inputComponent,
            Consumer<GridBagConstraints> constraintsCloneConsumer
    ) {
        GridBagConstraints customConstraints =
                (GridBagConstraints) inputConstraints.clone();

        constraintsCloneConsumer.accept(customConstraints);

        add(
                inputComponent,
                customConstraints
        );
    }
}
