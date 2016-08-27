package br.unioeste.jgoose.e4j;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxEdgeStyle.mxEdgeStyleFunction;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandre Luiz de Borba Silva - albsilva at outlook.com
 */
class CurveGraphView extends mxGraphView {

    public CurveGraphView(mxGraph graph) {
        super(graph);
    }

    /* Only override this if you want the label to automatically position itself on the control point */
    @Override
    public mxPoint getPoint(mxCellState state, mxGeometry geometry) {
        double x = state.getCenterX();
        double y = state.getCenterY();

        if (state.getAbsolutePointCount() == 3) {
            mxPoint mid = state.getAbsolutePoint(1);
            x = mid.getX();
            y = mid.getY();
        }
        return new mxPoint(x, y);
    }

//    /* Makes sure that the full path of the curve is included in the bounding box */ 
    @Override
    public mxRectangle updateBoundingBox(mxCellState state) {
        List<mxPoint> points = state.getAbsolutePoints();
        mxRectangle bounds = super.updateBoundingBox(state);
        Object style = state.getStyle().get("edgeStyle");
        if (CurvedEdgeStyle.KEY.equals(style) && points != null && points.size() == 3) {
            Rectangle pathBounds = CurvedShape.createPath(state.getAbsolutePoints()).getBounds();
            Rectangle union = bounds.getRectangle().union(pathBounds);
            bounds = new mxRectangle(union);
            state.setBoundingBox(bounds);
        }
        return bounds;
    }

}
