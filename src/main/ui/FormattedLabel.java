package ui;

import javax.swing.*;
import java.awt.*;

import static ui.GameCatalogueUI.FONT_COLOUR;

// Represents a label formatted to the application style
public class FormattedLabel extends JLabel {

    public FormattedLabel(String label, Font font) {
        super(label);
        setForeground(FONT_COLOUR);
        setFont(font);
    }
}
