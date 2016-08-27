package br.unioeste.jgoose.e4j.actions;

import br.unioeste.jgoose.e4j.filters.DefaultFileFilter;
import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
import br.unioeste.jgoose.istarml.ActorLinkTag;
import br.unioeste.jgoose.istarml.ActorTag;
import br.unioeste.jgoose.istarml.BoundaryTag;
import br.unioeste.jgoose.istarml.DependeeTag;
import br.unioeste.jgoose.istarml.DependencyTag;
import br.unioeste.jgoose.istarml.DependerTag;
import br.unioeste.jgoose.istarml.DiagramTag;
import br.unioeste.jgoose.istarml.IElementLinkTag;
import br.unioeste.jgoose.istarml.IElementTag;
import br.unioeste.jgoose.istarml.IStarMLTag;
import br.unioeste.jgoose.util.IStarUtils;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.w3c.dom.Element;

/**
 *
 * @author Leonardo Merlin - leonardo.merlin at unioeste.br
 */
public class ImportIStarMLAction extends AbstractAction {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger("console");

    protected String lastDir;
    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;
    private mxGraphComponent component;
    private mxGraph graph;
    private mxIGraphModel model;
    private HashMap<String, mxCell> elements;

    private IStarMLTag istarmlTag;
    private DiagramTag diagramTag;

    private int initx;
    private int inity;

    @Override
    public void actionPerformed(ActionEvent e) {

        BasicGraphEditor editor = EditorActions.getEditor(e);
        if (editor != null) {
            if (!editor.isModified() || JOptionPane.showConfirmDialog(editor, mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
                mxGraph graph = editor.getGraphComponent().getGraph();
                if (graph != null) {
                    String wd = (lastDir != null) ? lastDir : System.getProperty("user.dir");
                    JFileChooser fc = new JFileChooser(wd);
                    // Adds file filter for supported file format
                    DefaultFileFilter defaultFilter = new DefaultFileFilter(".istarml", mxResources.get("allSupportedFormats") + " (.istarml)") {
                        public boolean accept(File file) {
                            String lcase = file.getName().toLowerCase();
                            return super.accept(file);
                        }
                    };
                    fc.addChoosableFileFilter(defaultFilter);
                    fc.addChoosableFileFilter(new DefaultFileFilter(".istarml", "IstarML " + mxResources.get("file") + " (.istarml)"));

                    fc.setFileFilter(defaultFilter);
                    int rc = fc.showDialog(null, mxResources.get("openFile"));
                    if (rc == JFileChooser.APPROVE_OPTION) {
                        lastDir = fc.getSelectedFile().getParent();
                        try {
                            resetEditor(editor);
                            initJAXB();
                            istarmlTag = (IStarMLTag) jaxbUnmarshaller.unmarshal(new InputStreamReader(new FileInputStream(fc.getSelectedFile().getAbsolutePath()), "ISO-8859-1"));

                            Object eventSource = e.getSource();
                            elements = new HashMap<>();

                            this.component = (mxGraphComponent) eventSource;
                            this.graph = component.getGraph();
                            this.model = graph.getModel();

                            initx = 50;
                            inity = 50;
                            model.beginUpdate();

                            initImport();

                            model.endUpdate();

                            System.out.println("Passou");
                        } catch (JAXBException ex) {
                            LOG.error(ex);
                        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
                            Logger.getLogger(ImportIStarMLAction.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    private void initImport() {
        diagramTag = istarmlTag.getDiagrams();
        System.out.println("Atores");
        Iterator it = diagramTag.getActors().iterator();
        //mapeia atores
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof ActorTag) {
                ActorTag actor = (ActorTag) next;
                Element value = null;
                String style = "";
                switch (actor.getType()) {
                    case "role":
                        style = "shape=Role";
                        value = IStarUtils.createRole();
                        break;
                    case "agent":
                        style = "shape=Agent";
                        value = IStarUtils.createAgent();
                        break;
                    case "actor":
                        style = "shape=Actor";
                        value = IStarUtils.createActor();
                        break;
                    case "position":
                        style = "shape=Position";
                        value = IStarUtils.createPosition();
                        break;
                    default:
                        LOG.debug(actor.getType() + " - Tipo de Ator não encontrado!!!");
                        break;
                }
                value.setAttribute("label", actor.getName());
                mxGeometry geo = new mxGeometry(initx, inity, 80, 80);
                mxCell cell = new mxCell(value, geo, style);
                cell.setVertex(true);
                elements.put(actor.getId(), cell);
                graph.addCell(cell);
                initBoundary(cell, actor.getBoundary());

                initx += 20;
                inity += 20;
            }
        }

        it = diagramTag.getActors().iterator();
        //mapeia elementos
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof IElementTag) {
                addIElement((mxCell) graph.getDefaultParent(), (IElementTag) next);
            }
        }

        //mapeia linksAtores
        it = diagramTag.getActors().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof ActorTag) {
                ActorTag actor = (ActorTag) next;
                Iterator itAux = actor.getLinks().iterator();
                while (itAux.hasNext()) {
                    ActorLinkTag actorLink = (ActorLinkTag) itAux.next();
                    Element value = null;
                    switch (actorLink.getType()) {
                        case "plays":
                            value = IStarUtils.createPLAYS();
                            break;
                        case "instance_of":
                            value = IStarUtils.createINSTANCE_OF();
                            break;
                        case "occupies":
                            value = IStarUtils.createOCCUPIES();
                            break;
                        case "covers":
                            value = IStarUtils.createCOVERS();
                            break;
                        case "is_part_of":
                            value = IStarUtils.createIS_PART_OF();
                            break;
                        case "is_a":
                            value = IStarUtils.createIS_A();
                            break;
                        default:
                            LOG.debug(actorLink.getType() + " - Tipo de ActorLink não encontrado!!!");
                            break;
                    }
                    mxGeometry geometry = new mxGeometry(0, 0, 80, 80);
                    geometry.setTerminalPoint(new mxPoint(0, 80), true);
                    geometry.setTerminalPoint(new mxPoint(80, 0), false);
                    geometry.setRelative(true);
                    mxCell cell = new mxCell(value, geometry, "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle");
                    cell.setEdge(true);
                    graph.addEdge(cell, (mxCell) graph.getDefaultParent(), elements.get(actor.getId()), elements.get(actorLink.getAref()), null);
                }
            }
        }
    }

    public void initBoundary(mxCell actorRoot, BoundaryTag boundary) {
        if (boundary.getElements().size() > 0) { //realmente existe um boundary
            //adiciona fronteira
            mxGeometry geo = new mxGeometry(0, 0.5, 80,
                    80);
            // Because the origin is at upper left corner, need to translate to
            // position the center of port correctly
            geo.setOffset(new mxPoint(-40, -40));
            geo.setRelative(true);
            mxCell port = new mxCell(actorRoot.getAttribute("label"), geo,
                    actorRoot.getStyle());
            port.setVertex(true);
            //nao é de fato um port!!!!!!!!
            port.setConnectable(false);
            graph.addCell(port, actorRoot);
            ////
            //send to back! 
            actorRoot.setStyle(actorRoot.getStyle() + ";noLabel=1");
            actorRoot.getGeometry().setX(actorRoot.getGeometry().getX() + 100);
            actorRoot.getGeometry().setY(actorRoot.getGeometry().getY() + 100);
            //mapeando
            Iterator it = boundary.getElements().iterator();
            while (it.hasNext()) {
                addIElement(actorRoot, (IElementTag) it.next());
            }
        }
    }

    public mxCell addIElement(mxCell actorRoot, IElementTag iElement) {
        if (elements.containsKey(iElement.getId())) {
            return elements.get(iElement.getId());
        }
        Element value = null;
        mxGeometry geo;
        String style = "";
        switch (iElement.getType()) {
            case "task":
                value = IStarUtils.createTask();
                style = "shape=Task";
                break;
            case "goal":
                value = IStarUtils.createGoal();
                style = "shape=Goal";
                break;
            case "softgoal":
                value = IStarUtils.createSoftGoal();
                style = "shape=Softgoal";
                break;
            case "resource":
                value = IStarUtils.createResource();
                style = "shape=Resource";
                break;
            default:
                LOG.debug(iElement.getType() + " - Tipo de Elemento não encontrado!!!");
                break;
        }

        value.setAttribute("label", iElement.getName());
        geo = new mxGeometry(initx, inity, 100, 60);
        mxCell cell = new mxCell(value, geo, style);
        cell.setVertex(true);
        elements.put(iElement.getId(), cell);
        graph.addCell(cell, actorRoot);
        initx += 20;
        inity += 20;

        Iterator it = iElement.getLinks().iterator();
        while (it.hasNext()) {
            addIElementLink(actorRoot, cell, (IElementLinkTag) it.next());
        }

        DependencyTag dependency = iElement.getDependency();
        if (dependency != null) {
            Iterator itDependee = dependency.getDependee().iterator();
            Iterator itDepender = dependency.getDepender().iterator();
            DependeeTag dependee = (DependeeTag) itDependee.next();
            DependerTag depender = (DependerTag) itDepender.next();

            Element edge = IStarUtils.createDepndency();

            mxGeometry geometry = new mxGeometry(0, 0, 80, 80);
            geometry.setTerminalPoint(new mxPoint(0, 80), true);
            geometry.setTerminalPoint(new mxPoint(80, 0), false);
            geometry.setRelative(true);
            style = "straight;endArrow=dependency;noLabel=1;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
            mxCell edgeCellDepender = new mxCell(value, geometry, style);
            mxCell edgeCellDependee = new mxCell(value, geometry, style);
            edgeCellDepender.setEdge(true);
            edgeCellDependee.setEdge(true);
            String idDepender = depender.getAref() != null ? depender.getAref() : depender.getIref();
            String idDependee = dependee.getAref() != null ? dependee.getAref() : dependee.getIref();
            mxCell dependerCell = elements.get(idDepender);
            mxCell dependeeCell = elements.get(idDependee);
            graph.addEdge(edgeCellDepender, actorRoot, dependerCell, cell, null);
            graph.addEdge(edgeCellDependee, actorRoot, cell, dependeeCell, null);
        }

        return cell;
    }

    public void addIElementLink(mxCell actorRoot, mxCell lastElement, IElementLinkTag iElementLink) {
        Element value = null;
        String style = "";
        switch (iElementLink.getType()) {
            case "meansend":
                value = IStarUtils.createMeansEnd();
                style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                break;
            case "decomposition":
                value = IStarUtils.createDecomposition();
                style = "straight;endArrow=decomposition;noLabel=1";
                break;
            case "contribution":
                switch (iElementLink.getValue()) {
                    case "some+":
                        value = IStarUtils.createContributionSomePlus();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                    case "some-":
                        value = IStarUtils.createContributionSomeMinus();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                    case "hurt":
                        value = IStarUtils.createContributionHurt();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                    case "unknown":
                        value = IStarUtils.createContributionUnknown();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                    case "and":
                        value = IStarUtils.createContributionAnd();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                    case "help":
                        value = IStarUtils.createContributionHelp();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                    case "or":
                        value = IStarUtils.createContributionOr();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                    case "make":
                        value = IStarUtils.createContributionMake();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                    case "break":
                        value = IStarUtils.createContributionBreak();
                        style = "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle";
                        break;
                }
                break;
        }

        mxGeometry geometry = new mxGeometry(0, 0, 80, 80);
        geometry.setTerminalPoint(new mxPoint(0, 80), true);
        geometry.setTerminalPoint(new mxPoint(80, 0), false);
        geometry.setRelative(true);

        Iterator it = iElementLink.getElements().iterator();
        while (it.hasNext()) {
            IElementTag next = (IElementTag) it.next();
            mxCell cell = new mxCell(value, geometry, style);
            cell.setEdge(true);
            if ("decomposition".equals(iElementLink.getType())) {
                graph.addEdge(cell, actorRoot, addIElement(actorRoot, next), lastElement, null);
            } else {
                graph.addEdge(cell, actorRoot, lastElement, addIElement(actorRoot, next), null);
            }
        }
    }

    private void loadXml() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            istarmlTag = (IStarMLTag) jaxbUnmarshaller.unmarshal(new InputStreamReader(new FileInputStream("testeagain.istarml"), "ISO-8859-1"));
        } catch (JAXBException ex) {
            LOG.error(ex);
        }
    }

    private void initJAXB() {
        try {
            //todo change encoding to ISO-8859-1
            jaxbContext = JAXBContext.newInstance(
                    br.unioeste.jgoose.istarml.ActorLinkTag.class,
                    br.unioeste.jgoose.istarml.ActorTag.class,
                    br.unioeste.jgoose.istarml.BoundaryTag.class,
                    br.unioeste.jgoose.istarml.DependeeTag.class,
                    br.unioeste.jgoose.istarml.DependencyTag.class,
                    br.unioeste.jgoose.istarml.DependerTag.class,
                    br.unioeste.jgoose.istarml.DiagramTag.class,
                    br.unioeste.jgoose.istarml.GraphicTag.class,
                    br.unioeste.jgoose.istarml.IElementLinkTag.class,
                    br.unioeste.jgoose.istarml.IElementTag.class,
                    br.unioeste.jgoose.istarml.IStarMLTag.class);

            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
            LOG.error(ex);
        }
    }

    protected void resetEditor(BasicGraphEditor editor) {
        mxGraph graph = editor.getGraphComponent().getGraph();
        // Check modified flag and display save dialog
        mxCell root = new mxCell();
        root.insert(new mxCell());
        graph.getModel().setRoot(root);                //////////

        /////////
        editor.setModified(false);
        editor.setCurrentFile(null);
        editor.getGraphComponent().zoomAndCenter();
    }
}
