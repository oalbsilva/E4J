package br.unioeste.jgoose.e4j.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;

public class EditorAboutJDialog extends JDialog {

    private static final long serialVersionUID = -3378029138434324390L;

    /**
     *
     * @param owner parent
     */
    public EditorAboutJDialog(Frame owner) {
        super(owner);
        setTitle("About E4J");
        setLayout(new BorderLayout());

        // Creates the gradient panel
        JPanel panel = new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = -5062895855016210947L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Paint gradient background
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, Color.WHITE, getWidth(),
                        0, getBackground()));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
                .createMatteBorder(0, 0, 1, 0, Color.GRAY), BorderFactory
                .createEmptyBorder(8, 8, 12, 8)));

        // Adds title
        JLabel titleLabel = new JLabel("E4J - EDITOR FOR JGOOSE");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        titleLabel.setOpaque(false);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Adds optional subtitle
        JLabel subtitleLabel = new JLabel(
//                "For more information visit http://www.mxgraph.com/");
                "Making your Job easier with i-star diagrams.");
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(4, 18, 0, 0));
        subtitleLabel.setOpaque(false);
        panel.add(subtitleLabel, BorderLayout.CENTER);

        getContentPane().add(panel, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        content.add(new JLabel("E4J - Editor for JGOOSE"));
        content.add(new JLabel(" "));
        content.add(new JLabel("Author: Leonardo Pereira Merlin"));
        content.add(new JLabel("Author: Alexandre Luiz de Borba Silva"));
        content.add(new JLabel(" "));

        content.add(new JLabel("Using mxGraph Version " + mxGraph.VERSION));
        content.add(new JLabel("Copyright (C) 2009 by JGraph Ltd."));
        content.add(new JLabel("All rights reserved."));
        content.add(new JLabel(" "));

//        content.add(new JLabel("Using JGOOSE version 2013."));
//        content.add(new JLabel(" "));

        try {
            content.add(new JLabel("Operating System Name: "
                    + System.getProperty("os.name")));
            content.add(new JLabel("Operating System Version: "
                    + System.getProperty("os.version")));
            content.add(new JLabel(" "));

            content.add(new JLabel("Java Vendor: "
                    + System.getProperty("java.vendor", "undefined")));
            content.add(new JLabel("Java Version: "
                    + System.getProperty("java.version", "undefined")));
            content.add(new JLabel(" "));

            content.add(new JLabel("Total Memory: "
                    + Runtime.getRuntime().totalMemory()));
            content.add(new JLabel("Free Memory: "
                    + Runtime.getRuntime().freeMemory()));
        } catch (Exception e) {
            // ignore
        }

        getContentPane().add(content, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
                .createMatteBorder(1, 0, 0, 0, Color.GRAY), BorderFactory
                .createEmptyBorder(16, 8, 8, 8)));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Adds OK button to close window
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        buttonPanel.add(closeButton);

        // Sets default button for enter key
        getRootPane().setDefaultButton(closeButton);

        setResizable(false);
        setSize(400, 400);
    }

    /**
     * Overrides {@link JDialog#createRootPane()} to return a root pane that
     * hides the window when the user presses the ESCAPE key.O
     * @return 
     */
    @Override
    protected JRootPane createRootPane() {
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        JRootPane rootPane = new JRootPane();
        rootPane.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        }, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
}
