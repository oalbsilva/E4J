package br.unioeste.jgoose.e4j.actions;

import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
import br.unioeste.jgoose.e4j.swing.EditorRuler;
import com.mxgraph.swing.mxGraphComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;

@SuppressWarnings(value = "serial")
public class ToggleRulersItem extends JCheckBoxMenuItem {

    public ToggleRulersItem(final BasicGraphEditor editor, String name) {
        super(name);
        setSelected(editor.getGraphComponent().getColumnHeader() != null);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mxGraphComponent graphComponent = editor.getGraphComponent();
                if (graphComponent.getColumnHeader() != null) {
                    graphComponent.setColumnHeader(null);
                    graphComponent.setRowHeader(null);
                } else {
                    graphComponent.setColumnHeaderView(new EditorRuler(graphComponent, EditorRuler.ORIENTATION_HORIZONTAL));
                    graphComponent.setRowHeaderView(new EditorRuler(graphComponent, EditorRuler.ORIENTATION_VERTICAL));
                }
            }
        });
    }
}
