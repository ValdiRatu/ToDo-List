package ui.gui;

import network.PokemonFacts;

import javax.swing.*;
import java.awt.*;

public class FunFactPanel extends JPanel {

    public FunFactPanel() {
        setLayout(new BorderLayout());

        JPanel information = new JPanel();
        information.setLayout(new BorderLayout());
        JTextArea abilities = new JTextArea();
        information.add(abilities);
        add(information, BorderLayout.CENTER);

        JPanel select = new JPanel();
        select.setLayout(new BoxLayout(select, BoxLayout.Y_AXIS));
        select.add(new JButton("test"));

        add(select, BorderLayout.EAST);

    }

}
