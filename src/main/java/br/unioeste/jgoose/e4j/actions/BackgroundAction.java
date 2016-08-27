package br.unioeste.jgoose.e4j.actions;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxResources;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JColorChooser;

@SuppressWarnings(value = "serial")
public class BackgroundAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof mxGraphComponent) {
            mxGraphComponent graphComponent = (mxGraphComponent) e.getSource();
            Color newColor = JColorChooser.showDialog(graphComponent, mxResources.get("background"), null);
            if (newColor != null) {
                graphComponent.getViewport().setOpaque(true);
                graphComponent.getViewport().setBackground(newColor);
            }
            // Forces a repaint of the outline
            graphComponent.getGraph().repaint();
        }
    }
}
