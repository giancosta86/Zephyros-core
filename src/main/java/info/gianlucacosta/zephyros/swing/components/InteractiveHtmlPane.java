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
