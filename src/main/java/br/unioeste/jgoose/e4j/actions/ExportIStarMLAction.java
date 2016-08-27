package br.unioeste.jgoose.e4j.actions;

import br.unioeste.jgoose.e4j.filters.DefaultFileFilter;
import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
import br.unioeste.jgoose.istarml.ActorLinkTag;
import br.unioeste.jgoose.istarml.ActorTag;
import br.unioeste.jgoose.istarml.DependeeTag;
import br.unioeste.jgoose.istarml.DependencyTag;
import br.unioeste.jgoose.istarml.DependerTag;
import br.unioeste.jgoose.istarml.DiagramTag;
import br.unioeste.jgoose.istarml.IElementLinkTag;
import br.unioeste.jgoose.istarml.IElementTag;
import br.unioeste.jgoose.istarml.IStarMLConstants;
import br.unioeste.jgoose.istarml.IStarMLTag;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

@SuppressWarnings(value = "serial")
public class ExportIStarMLAction extends AbstractAction {

    private static Logger LOG = Logger.getLogger("console");
    //
    private mxGraphComponent component;
    private mxGraph graph;
    private mxIGraphModel model;
    //
    private IStarMLTag istarmlTag;
    private DiagramTag diagramTag;
    //
    private Map<mxCell, Object> mapped;
    //
    protected boolean showDialog = true;
    protected String lastDir = null;
    //
    private JAXBContext jaxbContext;
    private Marshaller jaxbMarshaller;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();

        if (!(eventSource instanceof mxGraphComponent)) {
            LOG.debug("Event source is not a mxGraphComponent instance.");
            return;
        }

        // init with memory leak free.
        if (this.mapped == null) {
            this.mapped = new HashMap<>();
        } else {
            this.mapped.clear();
        }

        this.component = (mxGraphComponent) eventSource;
        this.graph = component.getGraph();
        this.model = graph.getModel();

        LOG.debug("Generate IStarML.");

        // is created in a only one diagram
        diagramTag = new DiagramTag("", "Exported from E4J");

        // get references to all cells
        Object[] cells = this.selectAll();

        //<editor-fold desc="step 1: map all vertex" defaultstate="collapsed">
        for (Object c : cells) {
            mxCell cell = (mxCell) c;

            if (cell.isVertex()) {
                Object result = this.convertVertex(cell, (mxCell) model.getRoot());
            }
        }
        //</editor-fold>

        //<editor-fold desc="step 2: map all edges" defaultstate="collapsed">
        for (Object c : cells) {
            mxCell cell = (mxCell) c;

            if (cell.isEdge()) {
                Object result = this.convertEdge(cell, (mxCell) model.getRoot());
            } else {
                int children = cell.getChildCount();
                if (children > 0) {
                    for (int i = children - 1; i >= 0; i--) {
                        mxCell child = (mxCell) cell.getChildAt(i);
                        String t = child.getAttribute("type");
                        if (t == null) {
                            LOG.debug("children removed.");
                            cell.remove(child);
                        } else {
                            if (child.isEdge()) {
                                Object resultt = convertEdge(child, cell);
                                if (resultt != null) {
                                    Object parent = mapped.get(cell);
                                    if (parent instanceof ActorTag) {
                                        ActorTag parentActor = (ActorTag) parent;

                                        if (resultt instanceof ActorTag) {
                                            parentActor.getBoundary().getActors().add((ActorTag) resultt);
                                        }

                                        if (resultt instanceof IElementTag) {
                                            parentActor.getBoundary().getElements().add((IElementTag) resultt);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //</editor-fold>
        istarmlTag = new IStarMLTag(diagramTag);

        //<editor-fold desc="export to file" defaultstate="collapsed">
        initJAXB();

        exportToFile(istarmlTag, e);
        printXml(istarmlTag);

        //</editor-fold>
        // clear references after exported file.
        this.mapped.clear();

        LOG.debug("Generate IStarML finished.");
    }

    private Object[] selectAll() {

        graph.clearSelection();
        graph.selectAll();
        Object[] cells = graph.getSelectionCells();
        graph.clearSelection();

        LOG.debug("Total root cells founded: " + cells.length);
        return cells;
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

            
            jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, true);
        } catch (JAXBException ex) {
            LOG.error(ex);
        }
    }

    private Object convertVertex(mxCell cell, mxCell parent) {
        Object result = null;

        if (!cell.isVertex()) {
            LOG.debug("Cell is not a vertex.");
            return null;
        }

        Element element = null;
        Object v = cell.getValue();
        if (v instanceof Element) {
            element = (Element) v;
        } else {
            LOG.debug("Value of cell is not a Element type.");
            return null;
        }

        String id = cell.getId();
        String tag = element.getTagName();
        String type = element.getAttribute("type");
        String label = element.getAttribute("label").replaceAll("\n", " ");

        if (tag == null || type == null) {
            LOG.debug("tagname and type is null.");
            return null;
        }

        // switch only vertex nodes
        switch (tag) {
            case IStarMLConstants.TAG_ACTOR:
                //<editor-fold desc="tag actor" defaultstate="collapsed">
                LOG.debug("converting actor: " + type);
                ActorTag actor = new ActorTag();
                actor.setId(id);
                actor.setName(label);
                actor.setType(type);

                this.mapped.put(cell, actor);
                this.diagramTag.getActors().add(actor);
                result = actor;

                int children = cell.getChildCount();
                if (children > 0) {
                    //<editor-fold desc="convert children" defaultstate="collapsed">
                    for (int i = children - 1; i >= 0; i--) {
                        mxCell child = (mxCell) cell.getChildAt(i);
                        String t = child.getAttribute("type");
                        if (t == null) {
                            LOG.debug("children removed.");
                            cell.remove(child);
                        } else {
                            if (child.isVertex()) {
                                Object resultt = convertVertex(child, cell);
                                //<editor-fold desc="add to boundary list of this actor" defaultstate="collapsed">
                                if (resultt != null) {
                                    Object parentt = mapped.get(cell);
                                    if (parentt instanceof ActorTag) {
                                        ActorTag parentActor = (ActorTag) parentt;

                                        if (resultt instanceof ActorTag) {
                                            ActorTag resultActor = (ActorTag) resultt;
                                            if (!mapped.containsValue(resultActor)) {
                                                parentActor.getBoundary().getActors().add((ActorTag) resultt);
                                            }
                                        }
//
                                        if (resultt instanceof IElementTag) {
                                            IElementTag resultElement = (IElementTag) resultt;

                                            if (!mapped.containsValue(resultElement)) {
                                                LOG.debug("actor not contain ielement: " + resultElement.getId());
                                                parentActor.getBoundary().getElements().add((IElementTag) resultt);
                                            }
                                        }
                                    }
                                }
                                //</editor-fold>
                            }
                        }
                    }
                    //</editor-fold>
                }
                //</editor-fold>
                break;
            case IStarMLConstants.TAG_IELEMENT:
                //<editor-fold desc="tag element" defaultstate="collapsed">
                LOG.debug("converting ielement: " + type);
                IElementTag ielement = new IElementTag();
                ielement.setId(id);
                ielement.setName(label);
                ielement.setType(type); //goal, task, resource, softgoal
                if (cell.getParent().isVertex()
                        && ((mxCell) cell.getParent()).getAttribute("type").matches("actor|agent|role|position")) {
//                    this.diagramTag.getIntentionalElements().add(ielement);
                    if (!mapped.containsValue(ielement)) {
                        LOG.debug("actor not contain ielement: " + ielement.getId());
                        ActorTag mappedParent = (ActorTag) mapped.get(((mxCell) cell.getParent()));
                        ;
                        mappedParent.getBoundary().getElements().add(ielement);
                    }
                } else {
                    this.diagramTag.getIntentionalElements().add(ielement);
                }
                this.mapped.put(cell, ielement);

                result = ielement;
                break;
            //</editor-fold>
            default:
                LOG.debug("case for "
                        + "'" + tag + "'"
                        + " tag is not supported. It`s a vertex?");
                break;
        }

        return result;
    }

    private Object convertEdge(mxCell cell, mxCell parent) {
        Object result = null;

        if (!cell.isEdge()) {
            LOG.debug("Cell is not a edge.");
            return null;
        }

        Element element = null;
        Object v = cell.getValue();
        if (v instanceof Element) {
            element = (Element) v;
        } else {
            LOG.debug("Value of cell is not a Element type.");
            return null;
        }

        String id = cell.getId();
        String tag = element.getTagName();
        String type = element.getAttribute("type");
        String label = element.getAttribute("label");
        if (tag == null || type == null) {
            LOG.debug("tagname and type is null.");
            return null;
        }

        // 1) get elements of this edge
        mxCell source = (mxCell) cell.getSource();
        mxCell target = (mxCell) cell.getTarget();

        // 2) get mapped elements to create a link between then
        Object mappedSource = mapped.get(source);
        if (mappedSource == null) {
            LOG.error("mapped source dont founded.");
        }
        Object mappedTarget = mapped.get(target);
        if (mappedTarget == null) {
            LOG.error("mapped target dont founded.");
        }

        // switch only edge nodes
        switch (tag) {
            case IStarMLConstants.TAG_ACTOR_LINK:
                LOG.debug("ActorLink edge: " + type);

                ActorLinkTag actorLink = new ActorLinkTag();
                actorLink.setType(type); // is_a, is_part_of, ...

                if ((mappedSource != null && mappedSource instanceof ActorTag)
                        && (mappedTarget != null && mappedTarget instanceof ActorTag)) {
                    ActorTag sourceActor = ((ActorTag) mappedSource);
                    ActorTag targetActor = ((ActorTag) mappedTarget);
                    actorLink.setAref(targetActor.getId());
                    sourceActor.getLinks().add(actorLink);
                } else {
                    LOG.error("ActorLink is connected with who?");
                }

                this.mapped.put(cell, actorLink);
                result = actorLink;
                break;
            case IStarMLConstants.TAG_IELEMENT_LINK:
                LOG.debug("IElementLink edge: " + type);

                IElementLinkTag ielementLink = new IElementLinkTag();
                ielementLink.setType(type);
                if(type.equals("contribution")){
                    ielementLink.setValue(element.getAttribute("value"));
                }

                if ((mappedSource != null && mappedSource instanceof IElementTag)
                        && (mappedTarget != null && mappedTarget instanceof IElementTag)) {

                    IElementTag sourceIElement = ((IElementTag) mappedSource);
                    IElementTag targetIElement = ((IElementTag) mappedTarget);

                    if (type.matches("decomposition")) {
                        //is inverted
                        sourceIElement = ((IElementTag) mappedTarget);
                        targetIElement = ((IElementTag) mappedSource);
                    }

                    // Check if a IElementLink already exist (same type)
                    // TODO: check same value (and, or)
                    boolean alreadyExist = false;
                    for (IElementLinkTag link : sourceIElement.getLinks()) {
                        if (link.getType().equals(type)) {
                            link.getElements().add(targetIElement);
                            alreadyExist = true;
                        }
                    }
                    if (!alreadyExist) {
                        ielementLink.getElements().add(targetIElement);
                        sourceIElement.getLinks().add(ielementLink);
                    }

                    Object mappedParent = mapped.get(parent);
                    if (mappedParent != null && mappedParent instanceof ActorTag) {
                        ActorTag mappedActorParent = (ActorTag) mappedParent;
                        mappedActorParent.getBoundary().getElements().remove(targetIElement);
                    }
                } else {
                    LOG.error("IElementLink is connected with who?");
                }

                this.mapped.put(cell, ielementLink);
                result = ielementLink;
                break;
            case IStarMLConstants.TAG_DEPENDENCY:
                LOG.debug("Mapping dependency Link.");

                // has mxCell: source, target
                boolean sourceHasParent = (source.getParent().getValue() != null);
                boolean targetHasParent = (target.getParent().getValue() != null);

                String sourceType = source.getAttribute("type");
                String targetType = target.getAttribute("type");
                boolean sourceIsActor = sourceType.matches("actor|agent|role|position");
                boolean targetIsDependum = targetType.matches("goal|softgoal|task|resource");
                boolean sourceIsDependum = sourceType.matches("goal|softgoal|task|resource");
                boolean targetIsActor = targetType.matches("actor|agent|role|position");

                LOG.debug("source is a "
                        + "(" + sourceType + ")"
                        + " and target is a "
                        + "(" + targetType + ")");

                if (sourceHasParent || targetHasParent) {
                    LOG.debug("Mapping dependency in a SR case.");
                    // has only depender > dependum > "dependum" case.
                    // case 1: depender > dependum
                    // case 2: dependum > dependum

                    if (sourceIsActor && targetIsDependum) {
                        // case 1: depender > dependum
                        IElementTag dependum = (IElementTag) mappedTarget;
                        DependencyTag dependencyLink = dependum.getDependency();
                        if (dependencyLink == null) {
                            dependencyLink = new DependencyTag();
                        }

                        DependerTag depender = new DependerTag();
                        depender.setAref(((ActorTag) mappedSource).getId());
                        dependencyLink.getDepender().add(depender);
                        dependum.setDependency(dependencyLink);

                        this.mapped.put(cell, dependencyLink);
                        result = dependencyLink;
                    } else if (sourceIsDependum && targetIsDependum) {
                        // case 2: dependum > dependum
                        IElementTag dependum = (IElementTag) mappedSource;
                        DependencyTag dependencyLink = dependum.getDependency();
                        if (dependencyLink == null) {
                            dependencyLink = new DependencyTag();
                        }

                        DependeeTag dependee = new DependeeTag();
                        dependee.setIref(((IElementTag) mappedTarget).getId());
                        dependencyLink.getDependee().add(dependee);
                        dependum.setDependency(dependencyLink);

                        this.mapped.put(cell, dependencyLink);
                        result = dependencyLink;
                    }

                } else {
                    LOG.debug("Mapping dependency in a SD case.");
                    // has only depender > dependum > dependee cases.
                    // case 1: depender > dependum
                    // case 2: dependum > dependee
                    if (sourceIsActor && targetIsDependum) {
                        // case 1: depender > dependum
                        IElementTag dependum = (IElementTag) mappedTarget;
                        DependencyTag dependencyLink = dependum.getDependency();
                        if (dependencyLink == null) {
                            dependencyLink = new DependencyTag();
                        }

                        DependerTag depender = new DependerTag();
                        depender.setAref(((ActorTag) mappedSource).getId());
                        dependencyLink.getDepender().add(depender);
                        dependum.setDependency(dependencyLink);

                        this.mapped.put(cell, dependencyLink);
                        result = dependencyLink;
                    } else if (sourceIsDependum && targetIsActor) {
                        // case 2: dependum > dependee
                        IElementTag dependum = (IElementTag) mappedSource;
                        DependencyTag dependencyLink = dependum.getDependency();
                        if (dependencyLink == null) {
                            dependencyLink = new DependencyTag();
                        }

                        DependeeTag dependee = new DependeeTag();
                        dependee.setAref(((ActorTag) mappedTarget).getId());
                        dependencyLink.getDependee().add(dependee);
                        dependum.setDependency(dependencyLink);

                        this.mapped.put(cell, dependencyLink);
                        result = dependencyLink;
                    } else {
                        LOG.error("dependeny between who?");
                    }
                }
                break;
            default:
                LOG.debug("case for "
                        + "'" + tag + "'"
                        + " tag is not supported. It`s a vertex?");
                break;
        }

        return result;
    }

    private void exportToFile(IStarMLTag istarmlTag, ActionEvent e) {
        BasicGraphEditor editor = EditorActions.getEditor(e);

        FileFilter selectedFilter = null;
        FileFilter istarmlFileFilter = new DefaultFileFilter(".istarml", "iStarML " + mxResources.get("file") + " (.istarml)");

        String filename = null;
        boolean dialogShown = false;

        if (showDialog) {
            String wd;
            if (lastDir != null) {
                wd = lastDir;
            } else if (editor.getCurrentFile() != null) {
                wd = editor.getCurrentFile().getParent();
            } else {
                wd = System.getProperty("user.dir");
            }
            JFileChooser fc = new JFileChooser(wd);
            // Set iStarML file filter
            fc.setFileFilter(istarmlFileFilter);
            int rc = fc.showDialog(null, mxResources.get("save"));
            dialogShown = true;
            if (rc != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                lastDir = fc.getSelectedFile().getParent();
            }
            filename = fc.getSelectedFile().getAbsolutePath();
            selectedFilter = fc.getFileFilter();
            if (selectedFilter instanceof DefaultFileFilter) {
                String ext = ((DefaultFileFilter) selectedFilter).getExtension();
                if (!filename.toLowerCase().endsWith(ext)) {
                    filename += ext;
                }
            }
            if (new File(filename).exists() && JOptionPane.showConfirmDialog(this.component, mxResources.get("overwriteExistingFile")) != JOptionPane.YES_OPTION) {
                return;
            }
        } else {
            filename = editor.getCurrentFile().getAbsolutePath();
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(filename);
        } catch (Throwable ex) {
            LOG.error(ex);
            JOptionPane.showMessageDialog(this.component, ex.toString(), mxResources.get("error"), JOptionPane.ERROR_MESSAGE);
        }

        try {
            jaxbMarshaller.marshal(istarmlTag, fw);
        } catch (JAXBException ex) {
            java.util.logging.Logger.getLogger(ExportIStarMLAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printXml(Object obj) {
        try {
            jaxbMarshaller.marshal(obj, System.out);
        } catch (JAXBException ex) {
            java.util.logging.Logger.getLogger(ExportIStarMLAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
