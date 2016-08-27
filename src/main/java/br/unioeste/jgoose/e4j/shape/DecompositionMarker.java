package br.unioeste.jgoose.e4j.shape;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxIMarker;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraphView;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

/**
 *
 * @author Leonardo Merlin - leonardo.merlin at unioeste.br
 */
public class DecompositionMarker implements mxIMarker {

    private mxPoint getRelativePoint(mxPoint absolutePoint, mxCellState state) {
        mxGraphView view = state.getView();
        return view.getRelativePoint(state, absolutePoint.getX(), absolutePoint.getY());
    }

    @Override
    public mxPoint paintMarker(mxGraphics2DCanvas canvas, mxCellState state, String type, mxPoint pe, double nx, double ny, double size, boolean source) {
        // drawDependency '+' - adapted from OpenOME source.
        mxCell cell = (mxCell) state.getCell();
        mxPoint pointA;
        mxPoint pointB;

        List<mxPoint> statePoints = state.getAbsolutePoints();
        if (statePoints == null || statePoints.isEmpty()) {
            pointA = cell.getGeometry().getSourcePoint();
            pointB = cell.getGeometry().getTargetPoint();
        } else {
            pointA = statePoints.get(0);
            pointB = statePoints.get(statePoints.size() - 1);
        }
//        mxPoint midPoint = state.getAbsoluteOffset();
        double x = pointA.getX() + 2*( pointB.getX() - pointA.getX()) / 3;
        double y = pointA.getY() + 2*( pointB.getY() - pointA.getY()) / 3;
        mxPoint midPoint = new mxPoint(x, y);

        // calculate the angle between the two points
        int angle = (int) (calcAngle(pointB, pointA));

        List<mxPoint> points = cell.getGeometry().getPoints();
//        if (points != null) {
//            int countPoints = points.size();
//            if (countPoints == 1) {
//                midPoint = points.get(0);
//                angle = (int) (calcAngle(pointB, midPoint));
//            } else if (countPoints % 2 != 0) {
//                midPoint = points.get(countPoints / 2 + 1);
//                angle = (int) (calcAngle(points.get(countPoints / 2 + 1), points.get(countPoints / 2)));
//            } else if (countPoints > 0) {
//                mxPoint midA, midB;
//                midA = points.get(countPoints / 2 - 1);
//                midB = points.get(countPoints / 2);
//                angle = (int) (calcAngle(midB, midA));
//
//                x = midA.getX() + 2*( midB.getX() - midA.getX()) / 3;
//                y = midA.getY() + 2*( midB.getY() - midA.getY()) / 3;
//                midPoint = new mxPoint(x, y);
//            }
//        }
        
        // approx middle point to the end point
        

        angle = (angle - 90) * (-1);
        double lineAngle = angle + 90;
        int sizeOfD = (int) (2 * size);

        // convert the rotation angle from degrees to radians
        double line_Angle_Radians = Math.toRadians(lineAngle);
        mxPoint linePoint0 = new mxPoint((midPoint.getX() + (Math.sin(line_Angle_Radians) * sizeOfD)),
                (int) (midPoint.getY() + (Math.cos(line_Angle_Radians) * sizeOfD)));

        mxPoint linePoint1 = new mxPoint((midPoint.getX() - (Math.sin(line_Angle_Radians) * sizeOfD)),
                (int) (midPoint.getY() - (Math.cos(line_Angle_Radians) * sizeOfD)));

        Graphics2D g = canvas.getGraphics();
        Stroke oldStroke = g.getStroke();
        Stroke stroke = new BasicStroke(2.5f);
        g.setStroke(stroke);
        
        g.drawLine((int) linePoint0.getX(), (int) linePoint0.getY(), (int) linePoint1.getX(), (int) linePoint1.getY());
        g.setStroke(oldStroke);

        return new mxPoint(-nx / 2, -ny / 2);
    }

    /**
     * Calculate the angle (in degrees) between two points. Reference:
     * http://www.uk-dave.com/bytes/java/angle_between_points.html
     *
     * @param p the first point
     * @param q the second point
     * @return the angle (in degrees) between point p and point q
     */
    public static double calcAngle(mxPoint p, mxPoint q) {

        double x1 = p.getX();
        double y1 = p.getY();
        double x2 = q.getX();
        double y2 = q.getY();

        double dx = x2 - x1;
        double dy = y2 - y1;
        double angle = 0.0d;

        // Calculate angle
        if (dx == 0.0) {
            if (dy == 0.0) {
                angle = 0.0;
            } else if (dy > 0.0) {
                angle = Math.PI / 2.0;
            } else {
                angle = Math.PI * 3.0 / 2.0;
            }
        } else if (dy == 0.0) {
            if (dx > 0.0) {
                angle = 0.0;
            } else {
                angle = Math.PI;
            }
        } else {
            if (dx < 0.0) {
                angle = Math.atan(dy / dx) + Math.PI;
            } else if (dy < 0.0) {
                angle = Math.atan(dy / dx) + (2 * Math.PI);
            } else {
                angle = Math.atan(dy / dx);
            }
        }

        // Convert to degrees
        angle = angle * 180 / Math.PI;

        // Return
        return angle;
    }
}
