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

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Rectangle showing a color
 */
public class ColorSample extends JPanel {
    private Color color =
            Color.BLACK;

    /**
     * @param squareSize The side of the square
     */
    public ColorSample(int squareSize) {
        this(squareSize, squareSize);
    }


    public ColorSample(int width, int height) {
        this(
                new Dimension(
                        width,
                        height
                )
        );
    }


    public ColorSample(Dimension dimension) {
        setSize(dimension);
        setPreferredSize(dimension);
    }


    public Color getColor() {
        return color;
    }


    public void setColor(Color color) {
        this.color = color;

        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        g.setColor(Color.BLACK);

        g.drawRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1
        );
    }
}
