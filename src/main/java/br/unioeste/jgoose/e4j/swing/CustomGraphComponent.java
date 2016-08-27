package br.unioeste.jgoose.e4j.swing;

import br.unioeste.jgoose.e4j.swing.BasicIStarEditor;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.handler.mxCellMarker;
import com.mxgraph.swing.handler.mxConnectionHandler;
import com.mxgraph.swing.handler.mxGraphHandler;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.EventObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Leonardo
 */
public class CustomGraphComponent extends mxGraphComponent {

    /**
     *
     */
    private static final long serialVersionUID = -6833603133512882012L;

    /**
     *
     * @param graph
     */
    public CustomGraphComponent(mxGraph graph) {
        super(graph);

        // Sets switches typically used in an editor
//        setPageVisible(true);
        setGridVisible(true);
        setToolTips(true);
//        setZoomFactor(1.2);

        // create target?
        getConnectionHandler().setCreateTarget(false);
//        getConnectionHandler().setCreateTarget(true);

        // Loads the defalt stylesheet from an external file
        mxCodec codec = new mxCodec();
        URL defaultStyleResource = BasicIStarEditor.class.getResource("/com/mxgraph/examples/swing/resources/default-style.xml");
        Document doc = mxUtils.loadDocument(defaultStyleResource.toString());
        codec.decode(doc.getDocumentElement(), graph.getStylesheet());

        // Sets the background to white
        getViewport().setOpaque(true);
        getViewport().setBackground(Color.WHITE);

        // listener when fold cells. Set minimum size.
        getGraph().addListener(mxEvent.FOLD_CELLS, new mxEventSource.mxIEventListener() {
            @Override
            public void invoke(Object sender, mxEventObject evt) {
                Object[] cells = (Object[]) evt.getProperty("cells");

                for (Object c : cells) {
                    if (c instanceof mxCell) {
                        mxCell cell = (mxCell) c;

                        mxCellState state = CustomGraphComponent.this.getGraph().getView().getState(cell);
                        if (cell.isCollapsed()) {
//                            state.setLabel("actor-collapsed");
                            mxGeometry geom = cell.getGeometry();
                            double w = geom.getWidth();
                            double h = geom.getHeight();
                            if (w < 80) {
                                geom.setWidth(80);
                            }
                            if (h < 80) {
                                geom.setHeight(80);
                            }
                        }
//                        else {
//                            state.setLabel("actor-default");
//                            cell.getGeometry().setHeight(220);
//                            cell.getGeometry().setWidth(220);
//                        }
//                        System.out.println("state:" + state.getLabel());
                    }
                }
            }
        });
    }

    //Faz que todas as celulas dentro do limite, fiquem no limite.
    @Override
    public mxGraphHandler createGraphHandler() {
        return new mxGraphHandler(this) {
            @Override
            protected boolean shouldRemoveCellFromParent(Object parent, Object[] cells, MouseEvent e) {
                return false;
            }
        };
    }

    /**
     * Overrides drop behaviour to set the cell style if the target is not a
     * valid drop target and the cells are of the same type (eg. both vertices
     * or both edges).
     *
     * @param cells
     * @param dx
     * @param dy
     * @param target
     * @param location
     * @return
     */
    @Override
    public Object[] importCells(Object[] cells, double dx, double dy,
            Object target, Point location
    ) {

        //TODO: validate compatible types.
        // drop and change elements
//        if (target == null && cells.length == 1 && location != null) {
//            target = getCellAt(location.x, location.y);
//
//            if (target instanceof mxICell && cells[0] instanceof mxICell) {
//                mxICell targetCell = (mxICell) target;
//                mxICell dropCell = (mxICell) cells[0];
//
//                if (targetCell.isVertex() == dropCell.isVertex()
//                        || targetCell.isEdge() == dropCell.isEdge()) {
//                    mxIGraphModel model = graph.getModel();
//                    model.setStyle(target, model.getStyle(cells[0]));
//                    graph.setSelectionCell(target);
//
//                    return null;
//                }
//            }
//        }
        return super.importCells(cells, dx, dy, target, location);
    }

    @Override
    public String getEditingValue(Object c, EventObject trigger
    ) {
        if (c instanceof mxCell) {
            mxCell cell = ((mxCell) c);
            Object e = cell.getValue();

            if (e instanceof Element) {
                Element element = (Element) e;

                return element.getAttribute("label");

//                if (element.getTagName().equalsIgnoreCase("person")) {
//                    String firstName = element.getAttribute("firstName");
//                    String lastName = element.getAttribute("lastName");
//
//                    return firstName + " " + lastName;
//                }
            }
        }

        return super.getEditingValue(c, trigger);
    }

    @Override
    protected mxConnectionHandler createConnectionHandler() {
//        return super.createConnectionHandler();

        return new ConnectionHadler(this);
    }

}
