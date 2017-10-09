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
