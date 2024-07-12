package info.gianlucacosta.zephyros.swing.graphics;

import org.junit.Test;

import java.awt.Color;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ColorsTest {
    @Test
    public void encodeShouldPreserveTheColor() {
        Color referenceColor =
                new Color(197, 53, 181);

        String encodedColor =
                Colors.encode(referenceColor);

        Color retrievedColor =
                Color.decode(encodedColor);

        assertThat(
                retrievedColor,
                equalTo(referenceColor)
        );
    }


    @Test(expected = IllegalArgumentException.class)
    public void transparentColorsShouldNotBeAccepted() {
        Color transparentColor =
                new Color(128, 128, 128, 128);

        Colors.encode(transparentColor);
    }
}

