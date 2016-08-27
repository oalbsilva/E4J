package br.unioeste.jgoose.e4j;

import static br.unioeste.jgoose.e4j.swing.BasicIStarEditor.numberFormat;
import br.unioeste.jgoose.util.IStarUtils;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;
import com.mxgraph.view.mxStyleRegistry;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

/**
 * A graph that creates new edges from a given template edge.
 *
 * @author Leonardo
 */
public class CustomGraph extends mxGraph {

    private static final Logger LOG = Logger.getLogger("console");
    /**
     * Holds the edge to be used as a template for inserting new edges.
     */
    protected Object edgeTemplate;

    public CustomGraph() {
        super();
        mxGraphics2DCanvas.putShape(CurvedShape.KEY, new CurvedShape());
        mxStyleRegistry.putValue(CurvedEdgeStyle.KEY, new CurvedEdgeStyle());
        setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical;");
        setAllowDanglingEdges(false);
        setEdgeLabelsMovable(false);
        getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_SHAPE, CurvedShape.KEY);
        getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_EDGE, CurvedEdgeStyle.KEY);

        // set default edge: dependency
        Element element = IStarUtils.createDepndency();
        mxGeometry geom = new mxGeometry(0, 0, 80, 80);
        Object cell = new mxCell(element, geom, "straight;endArrow=dependency;noLabel=1;shape=curvedEdge;edgeStyle=curvedEdgeStyle");

        ((mxCell) cell).setEdge(true);
        setEdgeTemplate(cell);

    }

    /**
     * Sets the edge template to be used to inserting edges.
     *
     * @param template
     */
    public void setEdgeTemplate(Object template) {
        edgeTemplate = template;
    }

    /**
     * Overrides the method to use the currently selected edge template for new
     * edges.
     *
     * @param parent Cell that specifies the parent of the new edge.
     * @param id Optional string that defines the Id of the new edge.
     * @param value Object to be used as the user object.
     * @param source Cell that defines the source of the edge.
     * @param target Cell that defines the target of the edge.
     * @param style Optional string that defines the cell style.
     * @return Returns the new edge to be inserted.
     */
    @Override
    public Object createEdge(Object parent, String id, Object value,
            Object source, Object target, String style) {

        if (edgeTemplate != null) {
            mxCell edge = (mxCell) cloneCells(new Object[]{edgeTemplate})[0];

            if (edge == null) {
                return null;
            }

            edge.setId(id);
            return edge;
        }
        return super.createEdge(parent, id, value, source, target, style);
    }

//    @Override
//    public boolean isValidDropTarget(Object cell, Object[] cells) {
//        mxCell target = (mxCell)cell;
//        mxCell source = (mxCell)cells[0];        
//        if(!target.isCollapsed() && source.getAttribute("type").matches("position|role|actor|agent")){
//            return false;
//        }
//        return super.isValidDropTarget(cell, cells);
//    }
    /**
     *
     * @param edge
     * @param source
     * @param target
     * @return null == valid. "" == not valid.
     */
    @Override
    public String getEdgeValidationError(Object edge, Object source, Object target) {
        String result = super.getEdgeValidationError(edge, source, target);
//        boolean result = super.isEdgeValid(edge, source, target);
        //@TODO: validation edges! when need the edge informations.
//        LOG.debug("Validating edge by errors output.");
        if (!(source instanceof mxCell)) {
            LOG.debug("source is not mxCell.");
            return result;
        }

        if (!(target instanceof mxCell)) {
//            LOG.debug("target is not mxCell.");
            return result;
        }

        if (!(edge instanceof mxCell)) {
            LOG.debug("edget is not mxCell.");
            return result;
        }

        mxCell cellSource = (mxCell) source;
        mxCell cellTarget = (mxCell) target;
        mxCell cellEdge = (mxCell) edge;

        String sourceType = cellSource.getAttribute("type");
        String targetType = cellTarget.getAttribute("type");
        String edgeTag = ((Element) cellEdge.getValue()).getTagName();
        String edgeType = ((Element) cellEdge.getValue()).getAttribute("type");
        switch (edgeTag) {
            case "actorLink":
                switch (edgeType) {
                    case "is_a":
                        if (!(targetType.matches("actor|agent|role|position") && sourceType.matches("actor|agent|role|position") && sourceType.compareTo(targetType) == 0)) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    case "is_part_of":
                        if (!(targetType.matches("actor|agent|role|position") && sourceType.matches("actor|agent|role|position") && sourceType.compareTo(targetType) == 0)) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    case "instance_of":
                        if (!(targetType.matches("agent") && sourceType.matches("agent"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    case "covers":
                        if (!(sourceType.matches("position") && targetType.matches("role"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    case "plays":
                        if (!(sourceType.matches("agent") && targetType.matches("role"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    case "occupies":
                        if (!(sourceType.matches("agent") && targetType.matches("position"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    case "association":
                        if (!(sourceType.matches("actor_usecase") && targetType.matches("usecase"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    case "generalization":
                        if (!(sourceType.matches("actor_usecase") && targetType.matches("actor_usecase"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    default:
                        LOG.debug("Edge "
                                + "'" + edgeType + "'"
                                + " "
                                + "are notimplemented yet.");
                        break;
                }
                break;
            case "dependency":
                if ((sourceType.matches("softgoal")) && targetType.matches("goal")) {
                    String error = "Edge '"
                            + "" + edgeTag
                            + "' between "
                            + "" + sourceType
                            + " and "
                            + "" + targetType
                            + " is invalid."
                            //                            + " [Guideline 4.3.4.1] Softgoal Dependency should not be met directly by a Goal."
                            + "";
                    return error;
                }
                if ((sourceType.matches("actor|agent|role|position")) && targetType.matches("actor|agent|role|position")) {
                    String error = "Edge '"
                            + "" + edgeTag
                            + "' between "
                            + "" + sourceType
                            + " and "
                            + "" + targetType
                            + " is invalid.";
                    return error;
                }

                break;
            case "ielementLink":
                switch (edgeType) {
                    case "decomposition":
                        if (!(targetType.matches("task") && sourceType.matches("goal|softgoal|task|resource"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType
                                    + " and "
                                    + "" + targetType
                                    + " is invalid."
                                    + "\n"
                                    + "The target must be a 'task' and "
                                    + "the source must be a dependum "
                                    + "(goal, task, resource or softgoal).";
                            LOG.debug(error);
                            return error;
                        }
                        break;
                    case "meansend":
                        if (!(sourceType.matches("task") && targetType.matches("goal|resource|task")) && !(sourceType.matches("goal") && targetType.matches("goal"))) {
                            String error = "Edge '"
                                    + "" + edgeTag
                                    + "' between "
                                    + "" + sourceType
                                    + " and "
                                    + "" + targetType
                                    + " is invalid.";
//                            error += "[Guideline 5.3.1] Means-Ends are only used to link a Task to a Goal.";
//                            LOG.debug(error);
                            return error;
                        }
                        break;
                    case "include":
                        if (!(sourceType.matches("usecase") && targetType.matches("usecase"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    case "extend":
                        if (!(sourceType.matches("usecase") && targetType.matches("usecase"))) {
                            String error = "Edge '"
                                    + "" + edgeType
                                    + "' between "
                                    + "" + sourceType + " (source) "
                                    + " and "
                                    + "" + targetType + " (target) "
                                    + " is invalid.";
                            return error;
                        }
                        break;
                    default:
                        LOG.debug("Edge "
                                + "'" + edgeType + "'"
                                + " "
                                + "are notimplemented yet.");
                        break;
                    case "contribution":
                        if (!(sourceType.matches("task|softgoal") && targetType.matches("softgoal"))) {
                            String error = "Edge '"
                                    + "" + edgeTag
                                    + "' between "
                                    + "" + sourceType
                                    + " and "
                                    + "" + targetType
                                    + " is invalid.";
                            return error;
                        }
                        break;
                }
                break;
            default:
                LOG.debug("Edge " + edgeTag + " is not filtred yet.");
                break;
        }

//        if (isDependum(sourceType) && !edgeType.matches("decomposition")) {
//            int outCount = mxGraphModel.getDirectedEdgeCount(model, cellSource, true);
//            if (outCount > 1) {
//                return "Dependum must have only one outcoming edge.";
//            }
//        }
        return result;
    }

    @Override
    protected mxGraphView createGraphView() {
        return new CurveGraphView(this);
    }

    /**
     * Override to convert specific IStarMLValue object.
     *
     * @param c
     * @return
     */
    @Override
    public String convertValueToString(Object c) {
        String result = null;
        if (c instanceof mxCell) {
            mxCell cell = (mxCell) c;
            Object v = cell.getValue();
            if (v instanceof Element) {
                Element value = (Element) v;
                result = value.getAttribute("label");
            } else {
                result = super.convertValueToString(c);
            }
        }
        return result;
    }

    @Override
    public void cellLabelChanged(Object cellChanged, Object newValue, boolean autoSize) {
        if (cellChanged instanceof mxCell && newValue != null) {
            mxCell cell = (mxCell) cellChanged;
            Object v = cell.getValue();
            if (v instanceof Element) {
                Element value = (Element) v;
                String label = newValue.toString();
                value.setAttribute("label", label);
                //se for um grupo, quando ele estiver Collapse e for editado, tbm editar o shape atras...
                if (cell.getChildCount() > 0) {
                    super.cellLabelChanged(cell.getChildAt(0), newValue, autoSize);
                }
                newValue = v;
            }
        }
        super.cellLabelChanged(cellChanged, newValue, autoSize);
    }

    @Override
    public boolean isCellEditable(Object cell) {
        boolean result = super.isCellEditable(cell);

        mxCell edge = (mxCell) cell;
        if (edge.isEdge()) {
            result = false;
        }
        return result;
    }

    /**
     * Prints out some useful information about the cell in the tooltip.
     *
     * @param cell
     * @return
     */
    @Override
    public String getToolTipForCell(Object cell) {
        String tip = "<html>";
        mxGeometry geo = getModel().getGeometry(cell);
        mxCellState state = getView().getState(cell);

        if (getModel().isEdge(cell)) {
            tip += "points={";

            if (geo != null) {
                List<mxPoint> points = geo.getPoints();

                if (points != null) {
                    Iterator<mxPoint> it = points.iterator();

                    while (it.hasNext()) {
                        mxPoint point = it.next();
                        tip += "[x=" + numberFormat.format(point.getX())
                                + ",y=" + numberFormat.format(point.getY())
                                + "],";
                    }

                    tip = tip.substring(0, tip.length() - 1);
                }
            }

            tip += "}<br>";
            tip += "absPoints={";

            if (state != null) {

                for (int i = 0; i < state.getAbsolutePointCount(); i++) {
                    mxPoint point = state.getAbsolutePoint(i);
                    tip += "[x=" + numberFormat.format(point.getX())
                            + ",y=" + numberFormat.format(point.getY())
                            + "],";
                }

                tip = tip.substring(0, tip.length() - 1);
            }

            tip += "}";
        } else {
            tip += "geo=[";

            if (geo != null) {
                tip += "x=" + numberFormat.format(geo.getX()) + ",y="
                        + numberFormat.format(geo.getY()) + ",width="
                        + numberFormat.format(geo.getWidth()) + ",height="
                        + numberFormat.format(geo.getHeight());
            }

            tip += "]<br>";
            tip += "state=[";

            if (state != null) {
                tip += "x=" + numberFormat.format(state.getX()) + ",y="
                        + numberFormat.format(state.getY()) + ",width="
                        + numberFormat.format(state.getWidth())
                        + ",height="
                        + numberFormat.format(state.getHeight());
            }

            tip += "]";
        }

        mxPoint trans = getView().getTranslate();

        tip += "<br>scale=" + numberFormat.format(getView().getScale())
                + ", translate=[x=" + numberFormat.format(trans.getX())
                + ",y=" + numberFormat.format(trans.getY()) + "]";
        tip += "</html>";

        return tip;
    }
}
