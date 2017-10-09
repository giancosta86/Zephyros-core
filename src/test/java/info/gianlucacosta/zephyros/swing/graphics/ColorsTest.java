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

