package br.unioeste.jgoose.e4j;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxEdgeStyle;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Alexandre Luiz de Borba Silva - albsilva at outlook.com
 */
class CurvedEdgeStyle implements mxEdgeStyle.mxEdgeStyleFunction {

    public static final String KEY = "curvedEdgeStyle";

    @Override
    public void apply(mxCellState state, mxCellState source, mxCellState target, List<mxPoint> points, List<mxPoint> result) {
        mxPoint pt = (points != null && points.size() > 0) ? points.get(0) : null;
        if (source != null && target != null) {
            double x = 0;
            double y = 0;
            if (pt != null) {
                mxCell sourceCell = (mxCell) source.getCell();
                mxCell stateCell = (mxCell) state.getCell();
                //se estiver em um grupo, tem que recalcular os valores para não perder o ponto central.                
//                if (((mxCell) state.getView().getGraph().getDefaultParent() != sourceCell.getParent())) {                    
//                    mxCell parent = (mxCell) sourceCell.getParent();
//                    pt.setX(parent.getGeometry().getX() + pt.getX());
//                    pt.setY(parent.getGeometry().getY() + pt.getY());
//
//                }
                mxPoint origem = state.getOrigin();
                x = pt.getX() + origem.getX();
                y = pt.getY() + origem.getY();
                result.add(new mxPoint(x, y));
            } else {
                x = (target.getCenterX() + source.getCenterX()) / 2;
                y = (target.getCenterY() + source.getCenterY()) / 2;
                mxPoint point = new mxPoint(x, y);
                result.add(point);
            }
        }
    }

}
