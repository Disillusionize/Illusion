package org.example.illusion.core;

import javax.swing.*;

public class Main {
    private static final String MESSAGE =
            "This is an Ornithe mod, not an application.\n\n" +
                    "If you don't know what Ornithe is, or don't know how to install it,\n" +
                    "please visit: https://ornithemc.net/";

    private static final String TITLE = "Improper Installation Method";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        JOptionPane.showMessageDialog(
                null,
                MESSAGE,
                TITLE,
                JOptionPane.INFORMATION_MESSAGE
        );

        System.exit(0);
    }
}

