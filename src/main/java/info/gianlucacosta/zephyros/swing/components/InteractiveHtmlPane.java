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

import info.gianlucacosta.zephyros.os.User;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import java.util.Objects;

/**
 * Read-only component rendering HTML and responding to clicks on hyperlinks -
 * by showing the related URL in the user's browser.
 */
public class InteractiveHtmlPane extends JEditorPane {
    /**
     * @param html The HTML content to render
     */
    public InteractiveHtmlPane(String html) {
        super("text/html", html);

        setEditable(false);

        addHyperlinkListener(this::handleHyperlink);
    }


    private void handleHyperlink(HyperlinkEvent event) {
        if (Objects.equals(
                HyperlinkEvent.EventType.ACTIVATED,
                event.getEventType()
        )) {
            User.openBrowser(
                    event.getURL().toString()
            );
        }
    }
}
