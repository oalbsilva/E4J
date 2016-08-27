package br.unioeste.jgoose.e4j.actions;

import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
import com.mxgraph.swing.mxGraphOutline;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

@SuppressWarnings(value = "serial")
public class ToggleOutlineItem extends JCheckBoxMenuItem {

    public ToggleOutlineItem(final BasicGraphEditor editor, String name) {
        super(name);
        setSelected(true);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final mxGraphOutline outline = editor.getGraphOutline();
                outline.setVisible(!outline.isVisible());
                outline.revalidate();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (outline.getParent() instanceof JSplitPane) {
                            if (outline.isVisible()) {
                                ((JSplitPane) outline.getParent()).setDividerLocation(editor.getHeight() - 300);
                                ((JSplitPane) outline.getParent()).setDividerSize(6);
                            } else {
                                ((JSplitPane) outline.getParent()).setDividerSize(0);
                            }
                        }
                    }
                });
            }
        });
    }
}
