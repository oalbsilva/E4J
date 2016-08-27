package br.unioeste.jgoose.e4j.actions;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxResources;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

@SuppressWarnings(value = "serial")
public class WarningAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof mxGraphComponent) {
            mxGraphComponent graphComponent = (mxGraphComponent) e.getSource();
            Object[] cells = graphComponent.getGraph().getSelectionCells();
            if (cells != null && cells.length > 0) {
                String warning = JOptionPane.showInputDialog(mxResources.get("enterWarningMessage"));
                for (int i = 0; i < cells.length; i++) {
                    graphComponent.setCellWarning(cells[i], warning);
                }
            } else {
                JOptionPane.showMessageDialog(graphComponent, mxResources.get("noCellSelected"));
            }
        }
    }
}
