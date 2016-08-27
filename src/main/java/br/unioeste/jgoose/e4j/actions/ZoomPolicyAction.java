package br.unioeste.jgoose.e4j.actions;

import com.mxgraph.swing.mxGraphComponent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

@SuppressWarnings(value = "serial")
public class ZoomPolicyAction extends AbstractAction {
    protected int zoomPolicy;

    public ZoomPolicyAction(int zoomPolicy) {
        this.zoomPolicy = zoomPolicy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof mxGraphComponent) {
            mxGraphComponent graphComponent = (mxGraphComponent) e.getSource();
            graphComponent.setPageVisible(true);
            graphComponent.setZoomPolicy(zoomPolicy);
        }
    }

}
