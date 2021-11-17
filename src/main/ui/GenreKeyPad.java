package ui;

import model.GameGenre;
import model.PlayStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.java.swing.ui.CommonUI.createLabel;
import static model.GameGenre.*;
import static ui.GameCatalogueUI.*;

public class GenreKeyPad extends JPanel implements ActionListener {
    private JButton[] keys;
    private JLabel label;
    private GameGenre genreInput;

    public GenreKeyPad(String genreQuestion) {
        label = new JLabel(genreQuestion);
        label.setForeground(FONT_COLOUR);
        label.setFont(TITLE_FONT);

        JPanel genrePanel = new JPanel();
        genrePanel.setLayout(new FlowLayout());
        addButtons(genrePanel);
        add(genrePanel, BorderLayout.CENTER);
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(label);
        hbox.add(Box.createHorizontalGlue());
        add(hbox, BorderLayout.SOUTH);
    }

    // EFFECTS: adds button to game genre keypad
    private void addButtons(JPanel p) {
        keys = new JButton[7];

        keys[1] = new JButton("FPS");
        keys[1].addActionListener(this::actionPerformed);
        p.add(keys[1]);

        keys[2] = new JButton("RPG");
        keys[2].addActionListener(this::actionPerformed);
        p.add(keys[2]);

        keys[3] = new JButton("ADVENTURE");
        keys[3].addActionListener(this::actionPerformed);
        p.add(keys[3]);

        keys[4] = new JButton("PUZZLE");
        keys[4].addActionListener(this::actionPerformed);
        p.add(keys[4]);

        keys[5] = new JButton("HORROR");
        keys[5].addActionListener(this::actionPerformed);
        p.add(keys[5]);

        keys[6] = new JButton("SIMULATION");
        keys[6].addActionListener(this::actionPerformed);
        p.add(keys[6]);

        keys[7] = new JButton("PLATFORMER");
        keys[7].addActionListener(this::actionPerformed);
        p.add(keys[7]);
    }

    /**
     * A listener for key events.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();

        if (src.getText().equals("FPS")) {
            genreInput = FPS;
        } else if (src.getText().equals("RPG")) {
            genreInput = RPG;
        } else if (src.getText().equals("ADVENTURE")) {
            genreInput = ADVENTURE;
        } else if (src.getText().equals("PUZZLE")) {
            genreInput = PUZZLE;
        } else if (src.getText().equals("HORROR")) {
            genreInput = HORROR;
        } else if (src.getText().equals("SIMULATION")) {
            genreInput = SIMULATION;
        } else if (src.getText().equals("PLATFORMER")) {
            genreInput = PLATFORMER;
        }
    }
}
