package br.unioeste.jgoose.e4j.actions;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

@SuppressWarnings(value = "serial")
public class GenerateUseCaseAction extends AbstractAction {

    private static Logger LOG = Logger.getLogger("console");
    //
    private Map<mxCell, Element> elements = new HashMap<>();
    private Map<mxCell, Element> links = new HashMap<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        LOG.debug("Generate Use Case init. TESTANDOOOOOO!!!!");
        elements.clear();
        links.clear();
        Object source = e.getSource();

        if (source instanceof mxGraphComponent) {
            mxGraphComponent component = (mxGraphComponent) source;
            mxGraph graph = component.getGraph();
            mxIGraphModel model = graph.getModel();

            // test with selected elements only
            Object[] cells = graph.getSelectionCells();
            boolean selectionForced = false;
            if (cells.length == 0) {
                LOG.debug("No one cell is selected. Selecting All...");
                //get all cells in the graph
                //TODO: change this! its dont select all.
                // Only the first level is selected
                graph.selectAll();
                cells = graph.getSelectionCells();
                selectionForced = true;
//                model.get
            }

            LOG.debug("Total root cells founded: " + cells.length);
            for (Object c : cells) {
                mxCell cell = (mxCell) c;
//                this.debugStateInfo(cell, model);
//                this.debugGraphInfo(cell, model);

                Object value = cell.getValue();
                if (value instanceof Element) {
                    Element element = (Element) value;
                    this.computeElement(element, cell);
                } else {
                    LOG.debug("value of cell is not a element type.");
                    this.debugStateInfo(cell, model);
//                    this.debugGraphInfo(cell, model);
                }
            }

            LOG.debug("total elements: " + elements.size());
            LOG.debug("total links: " + links.size());

            if (selectionForced) {
                graph.clearSelection();
            }
        }

        LOG.debug("Generate Use Case finished.");
    }

    private void debugGraphInfo(mxCell cell, mxIGraphModel model) {

        String id = cell.getId();
        LOG.debug("id: " + id);

        int children = model.getChildCount(cell);
        LOG.debug("children: " + children);
        int edges = model.getEdgeCount(cell);
        LOG.debug("edges: " + edges);

        mxGeometry geometry = model.getGeometry(cell);
        LOG.debug("position: " + geometry.getCenterX() + ", " + geometry.getCenterY());

        String tag = ((Element) cell.getValue()).getTagName();
        LOG.debug("tag: " + tag);

        String type = cell.getAttribute("type");
        LOG.debug("type: " + type);

        String label = cell.getAttribute("label");
        LOG.debug("label: " + label);

        LOG.debug("element: " + ((Element) cell.getValue()).toString());
    }

    private void debugStateInfo(mxCell cell, mxIGraphModel model) {
        boolean isCollapsed = model.isCollapsed(cell);
        boolean isConnectable = model.isConnectable(cell);
        boolean isEdge = model.isEdge(cell);
        boolean isVertex = model.isVertex(cell);
        boolean isVisible = model.isVisible(cell);

        LOG.debug("Cell: ["
                + " " + (isVertex ? "" : "!") + "isVertex"
                + " " + (isEdge ? "" : "!") + "isEdge"
                + " " + (isCollapsed ? "" : "!") + "isCollapsed"
                + " " + (isConnectable ? "" : "!") + "isConnectable"
                + " " + (isVisible ? "" : "!") + "isVisible"
                + "]");
    }

    private void computeElement(Element element, mxCell cell) {
        String tagName = element.getTagName();
        String type = element.getAttribute("type");
        System.out.println("Type: "+type);
        if (tagName == null || type == null) {
            return;
        }
        switch (tagName) {
            case "actor":
                //actor, agent, role, position,
                int children = cell.getChildCount();
                if (children > 0) {
                    for (int i = children - 1; i >= 0; i--) {
                        mxCell child = (mxCell) cell.getChildAt(i);
                        String t = child.getAttribute("type");
                        if (t == null) {
                            LOG.debug("children removed.");
                            cell.remove(child);
                        } else {
                            computeElement((Element) child.getValue(), child);
                        }
                    }
                }
                switch (type) {
                    case "actor":
//                        break;
                    case "agent":
//                        break;
                    case "role":
//                        break;
                    case "position":
                        elements.put(cell, element);
                        break;
                    default:
                        LOG.debug("case for "
                                + "'" + type
                                + "' actor type is not implemented yet.");
                        break;
                }
                break;
            case "ielement":
                //goal, resource, task, softgoal
                switch (type) {
                    case "goal":
                    case "resource":
                    case "task":
                    case "softgoal":
                        elements.put(cell, element);
                        break;
                    default:
                        LOG.debug("case for "
                                + "'" + type
                                + "' ielement type is not implemented yet.");
                        break;
                }
                break;
            case "actorLink":
                LOG.debug("actorLink");
                this.debugEdge(cell);

                switch (type) {
                    case "is_a":
                    case "is_part_of":
                    case "instance_of":
                    case "plays":
                    case "occupies":
                    case "covers":
                        links.put(cell, element);
                        break;
                    default:
                        LOG.debug("case for "
                                + "'" + type
                                + "' iactorLink type is not implemented yet.");
                        break;
                }
                break;
            case "ielementLink":
                LOG.debug("ielementLink");
                this.debugEdge(cell);
                switch (type) {
                    case "descomposition":
                    case "meansend":        
                    case "contribution":
                        //contribution > break, hurt ...
                        LOG.debug(type + " entrou aqui");
                        links.put(cell, element);
                        break;
                    default:
                        LOG.debug("case for "
                                + "'" + type
                                + "' ielementLink type is not implemented yet. FUNCIONAAAA >>>");
                        break;
                }
                break;
            case "dependency":
                //dependency dont have type
                // all dependency elements must have been mapped.
                LOG.debug("dependency");
                this.debugEdge(cell);

                links.put(cell, element);
                break;
            default:
                LOG.debug("case for "
                        + "'" + tagName
                        + "' tagname is not implemented yet.");
                break;
        }
    }

    private void debugEdge(mxCell cell) {
        if (!cell.isEdge()) {
            LOG.error("this cell is not a edge! How?");
        } else {
            mxICell source = cell.getSource();
            LOG.debug("source: " + ((Element) source.getValue()).getAttribute("type"));

            mxICell target = cell.getTarget();
            LOG.debug("target: " + ((Element) target.getValue()).getAttribute("type"));
        }
    }
}
