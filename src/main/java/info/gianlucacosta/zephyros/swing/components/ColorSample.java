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
