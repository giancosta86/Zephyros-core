package info.gianlucacosta.zephyros.swing.components;

import info.gianlucacosta.zephyros.swing.dialogs.BasicDialogs;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

/**
 * Shows a color and enables the user to change it
 */
public class ColorPicker extends JPanel {
    private final ColorSample colorSample;

    public ColorPicker(Dimension colorSampleSize) {
        setLayout(new FlowLayout() {
            {
                setHgap(10);
                setAlignment(FlowLayout.LEFT);
            }
        });


        colorSample =
                new ColorSample(colorSampleSize) {
                    {
                        addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                askForNewColor();
                            }
                        });
                    }
                };

        add(colorSample);


        add(new JButton("Select...") {
            {
                addActionListener(event ->
                        askForNewColor()
                );
            }
        });
    }


    private void askForNewColor() {
        Optional<Color> chosenColorOption =
                BasicDialogs.chooseColor(
                        "Choose color...",
                        colorSample.getColor()
                );

        chosenColorOption.ifPresent(colorSample::setColor);
    }


    public Color getColor() {
        return colorSample.getColor();
    }


    public void setColor(Color color) {
        colorSample.setColor(color);
    }
}
