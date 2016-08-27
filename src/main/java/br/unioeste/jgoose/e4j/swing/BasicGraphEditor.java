package br.unioeste.jgoose.e4j.swing;

import br.unioeste.jgoose.e4j.swing.listeners.BoundaryRestrictionListener;
import br.unioeste.jgoose.e4j.swing.listeners.CollapsingExpandingListener;
import br.unioeste.jgoose.e4j.swing.listeners.EditorChangeTracker;
import br.unioeste.jgoose.e4j.swing.listeners.GraphControlMenuMouseAdapter;
import br.unioeste.jgoose.e4j.swing.listeners.MouseLocation;
import br.unioeste.jgoose.e4j.swing.listeners.OutlineMenuMouseAdapter;
import br.unioeste.jgoose.e4j.swing.listeners.EditorUndoHandler;
import br.unioeste.jgoose.e4j.swing.listeners.ZoomWheelTracker;
import br.unioeste.jgoose.e4j.swing.toolbars.EditorToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxEdgeLabelLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.mxPartitionLayout;
import com.mxgraph.layout.mxStackLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUndoManager;
import com.mxgraph.util.mxUndoableEdit;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxUndoableEdit.mxUndoableChange;
import com.mxgraph.view.mxGraph;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.log4j.Logger;

public class BasicGraphEditor extends JPanel {

    private static final long serialVersionUID = -6561623072112577140L;
    private static final Logger console = Logger.getLogger("console");

    /**
     * Adds resources for i18n
     */
    static {
        try {
            mxResources.add("com/mxgraph/examples/swing/resources/editor");
        } catch (Exception e) {
            console.error(e);
        }
    }
    //    
    protected JFrame frame = null;
    protected mxGraphComponent graphComponent;
    protected mxGraphOutline graphOutline;
    protected JTabbedPane libraryPane;
    protected mxUndoManager undoManager;
    protected String appTitle;
    protected JLabel statusBar;
    protected File currentFile;
    /**
     * Flag indicating whether the current graph has been modified
     */
    protected boolean modified = false;
    protected mxRubberband rubberband;
    protected mxKeyboardHandler keyboardHandler;
    protected mxIEventListener undoHandler;
    protected mxIEventListener modelChangeTracker;

    public BasicGraphEditor(String appTitle, mxGraphComponent component) {
        // Stores and updates the frame title
        this.appTitle = appTitle;

        // Stores a reference to the graph and creates the command history
        graphComponent = component;
        final mxGraph graph = graphComponent.getGraph();
        undoManager = new mxUndoManager();

        // Do not change the scale and translation after files have been loaded
        graph.setResetViewOnRootChange(false);

        // Updates the modified flag if the graph model changes
        modelChangeTracker = (mxIEventListener) new EditorChangeTracker(this);
        graph.getModel().addListener(mxEvent.CHANGE, modelChangeTracker);

        // Adds the command history to the model and view
        undoHandler = (mxIEventListener) new EditorUndoHandler(this);
        graph.getModel().addListener(mxEvent.UNDO, undoHandler);
        graph.getView().addListener(mxEvent.UNDO, undoHandler);

        // Keeps the selection in sync with the command history
        mxIEventListener undoManagerHandler = new mxIEventListener() {
            @Override
            public void invoke(Object source, mxEventObject evt) {
                List<mxUndoableChange> changes = ((mxUndoableEdit) evt
                        .getProperty("edit")).getChanges();
                graph.setSelectionCells(graph
                        .getSelectionCellsForChanges(changes));
            }
        };

        //listener de Collapsing/Expanding Cell
        graph.addListener(mxEvent.CELLS_FOLDED, (mxIEventListener) new CollapsingExpandingListener());
        //listener de Boundary Restriction
        graph.addListener(mxEvent.CELLS_ADDED, (mxIEventListener) new BoundaryRestrictionListener());
        
        undoManager.addListener(mxEvent.UNDO, undoManagerHandler);
        undoManager.addListener(mxEvent.REDO, undoManagerHandler);

        // Creates the graph outline component
        graphOutline = new mxGraphOutline(graphComponent);

        // Creates the library pane that contains the tabs with the palettes
        libraryPane = new JTabbedPane();

        // Creates the inner split pane that contains the library with the
        // palettes and the graph outline on the left side of the window
        JSplitPane inner = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                libraryPane, graphOutline);
        inner.setDividerLocation(320);
        inner.setResizeWeight(1);
        inner.setDividerSize(6);
        inner.setBorder(null);

        // Creates the outer split pane that contains the inner split pane and
        // the graph component on the right side of the window
        JSplitPane outer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inner,
                graphComponent);
        outer.setOneTouchExpandable(true);
        outer.setDividerLocation(200);
        outer.setDividerSize(6);
        outer.setBorder(null);

        // Creates the status bar
        statusBar = new JLabel(mxResources.get("ready"));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));

        // Display some useful information about repaint events
        installRepaintListener();

        // Puts everything together
        setLayout(new BorderLayout());
        add(outer, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        installToolBar();

        // Installs rubberband selection and handling for some special
        // keystrokes such as F2, Control-C, -V, X, A etc.
        installHandlers();

        installListeners();
        updateTitle();
    }

    protected final void installToolBar() {
        add(new EditorToolBar(this, JToolBar.HORIZONTAL), BorderLayout.NORTH);
    }

    protected final void installHandlers() {
        rubberband = new mxRubberband(graphComponent);
        keyboardHandler = new EditorKeyboardHandler(graphComponent);
    }

    protected final void installRepaintListener() {
        graphComponent.getGraph().addListener(mxEvent.REPAINT,
                new mxIEventListener() {
                    @Override
                    public void invoke(Object source, mxEventObject evt) {
                        String buffer = (graphComponent.getTripleBuffer() != null) ? ""
                        : " (unbuffered)";
                        mxRectangle dirty = (mxRectangle) evt
                        .getProperty("region");

                        if (dirty == null) {
                            status("Repaint all" + buffer);
                        } else {
                            status("Repaint: x=" + (int) (dirty.getX()) + " y="
                                    + (int) (dirty.getY()) + " w="
                                    + (int) (dirty.getWidth()) + " h="
                                    + (int) (dirty.getHeight()) + buffer);
                        }
                    }
                });
    }

    public EditorPalette insertPalette(String title) {
        final EditorPalette palette = new EditorPalette();
        final JScrollPane scrollPane = new JScrollPane(palette);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        libraryPane.add(title, scrollPane);

        // Updates the widths of the palettes if the container size changes
        libraryPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = scrollPane.getWidth()
                        - scrollPane.getVerticalScrollBar().getWidth();
                palette.setPreferredWidth(w);
            }
        });

        return palette;
    }

    protected final void installListeners() {
        // Installs mouse wheel listener for zooming
        MouseWheelListener wheelTracker = new ZoomWheelTracker(
                this, graphComponent);

        // Handles mouse wheel events in the outline and graph component
        graphOutline.addMouseWheelListener(wheelTracker);
        graphComponent.addMouseWheelListener(wheelTracker);

        // Installs the popup menu in the outline
        OutlineMenuMouseAdapter omma = new OutlineMenuMouseAdapter(
                graphComponent, graphOutline);
        graphOutline.addMouseListener(omma);

        // Installs the popup menu in the graph component
        GraphControlMenuMouseAdapter gcmma = new GraphControlMenuMouseAdapter(
                this, graphComponent);
        graphComponent.getGraphControl().addMouseListener(gcmma);

        // Install the point control.
        //TODO: test with getView() component.
//        DoubleClickOnEdgeMouseListener dblClickEdge = new DoubleClickOnEdgeMouseListener(graphComponent);
//        graphComponent.getGraphControl().addMouseListener(dblClickEdge);
        // Installs a mouse motion listener to display the mouse location
        MouseLocation ml = new MouseLocation(this);
        graphComponent.getGraphControl().addMouseMotionListener(ml);
    }

    public void setCurrentFile(File file) {
        File oldValue = currentFile;
        currentFile = file;

        firePropertyChange("currentFile", oldValue, file);

        if (oldValue != file) {
            updateTitle();
        }
    }

    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * Set current graph has (un)modified and update the title of window.
     *
     * @param modified
     */
    public void setModified(boolean modified) {
        boolean oldValue = this.modified;
        this.modified = modified;

        firePropertyChange("modified", oldValue, modified);

        if (oldValue != modified) {
            updateTitle();
        }
    }

    /**
     *
     * @return whether or not the current graph has been modified
     */
    public boolean isModified() {
        return modified;
    }

    public mxGraphComponent getGraphComponent() {
        return graphComponent;
    }

    public mxGraphOutline getGraphOutline() {
        return graphOutline;
    }

    public JTabbedPane getLibraryPane() {
        return libraryPane;
    }

    public mxUndoManager getUndoManager() {
        return undoManager;
    }

    /**
     *
     * @param name
     * @param action
     * @return a new Action bound to the specified string name
     */
    public Action bind(String name, final Action action) {
        return bind(name, action, null);
    }

    /**
     *
     * @param name
     * @param action
     * @param iconUrl
     * @return a new Action bound to the specified string name and icon
     */
    @SuppressWarnings("serial")
    public Action bind(String name, final Action action, String iconUrl) {
        AbstractAction newAction = new AbstractAction(name, (iconUrl != null) ? new ImageIcon(
                BasicGraphEditor.class.getResource(iconUrl)) : null) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        action.actionPerformed(new ActionEvent(getGraphComponent(), e
                                        .getID(), e.getActionCommand()));
                    }
                };

        newAction.putValue(Action.SHORT_DESCRIPTION, action.getValue(Action.SHORT_DESCRIPTION));

        return newAction;
    }

    /**
     *
     * Show <code>msg</code> in the statusbar.
     *
     * @param msg
     */
    public void status(String msg) {
        statusBar.setText(msg);
    }

    public final void updateTitle() {
        if (this.frame != null) {
            String title = (currentFile != null) ? currentFile
                    .getAbsolutePath() : mxResources.get("newDiagram");

            if (modified) {
                title += "*";
            }

            this.frame.setTitle(title + " - " + appTitle);
        }
    }

    public void about() {
        if (this.frame != null) {
            EditorAboutJDialog about = new EditorAboutJDialog(frame);
            about.setModal(true);

            // Centers inside the application frame
            int x = frame.getX() + (frame.getWidth() - about.getWidth()) / 2;
            int y = frame.getY() + (frame.getHeight() - about.getHeight()) / 2;
            about.setLocation(x, y);

            // Shows the modal dialog and waits
            about.setVisible(true);
        }
    }

    public void exit() {
        if (frame != null) {
            frame.dispose();
        } else {
            console.debug("editorJFrame is null");
        }
    }

    public void setLookAndFeel(String clazz) {
        if (frame != null) {
            try {
                UIManager.setLookAndFeel(clazz);
                SwingUtilities.updateComponentTreeUI(frame);

                // Needs to assign the key bindings again
                keyboardHandler = new EditorKeyboardHandler(graphComponent);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
            }
        }
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame createFrame(JMenuBar menuBar) {

        if (this.frame == null) {
            this.frame = new JFrame();
            frame.getContentPane().add(this);
//            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setJMenuBar(menuBar);
            frame.setSize(870, 640);
        }

        // Updates the frame title
        updateTitle();

        return frame;
    }

    /**
     * Creates an action that executes the specified layout.
     *
     * @param key Key to be used for getting the label from mxResources and also
     * to create the layout instance for the commercial graph editor example.
     * @param animate
     * @return an action that executes the specified layout
     */
    @SuppressWarnings("serial")
    public Action graphLayout(final String key, boolean animate) {
        final mxIGraphLayout layout = createLayout(key, animate);

        if (layout != null) {
            return new AbstractAction(mxResources.get(key)) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    final mxGraph graph = graphComponent.getGraph();
                    Object cell = graph.getSelectionCell();

                    if (cell == null
                            || graph.getModel().getChildCount(cell) == 0) {
                        cell = graph.getDefaultParent();
                    }

                    graph.getModel().beginUpdate();
                    try {
                        long t0 = System.currentTimeMillis();
                        layout.execute(cell);
                        status("Layout: " + (System.currentTimeMillis() - t0)
                                + " ms");
                    } finally {
                        mxMorphing morph = new mxMorphing(graphComponent, 20,
                                1.2, 20);

                        morph.addListener(mxEvent.DONE, new mxIEventListener() {
                            @Override
                            public void invoke(Object sender, mxEventObject evt) {
                                graph.getModel().endUpdate();
                            }
                        });

                        morph.startAnimation();
                    }

                }
            };
        } else {
            return new AbstractAction(mxResources.get(key)) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(graphComponent,
                            mxResources.get("noLayout"));
                }
            };
        }
    }

    /**
     * Creates a layout instance for the given identifier.
     *
     * @param ident
     * @param animate
     * @return
     */
    protected mxIGraphLayout createLayout(String ident, boolean animate) {
        mxIGraphLayout layout = null;

        if (ident != null) {
            mxGraph graph = graphComponent.getGraph();

            if (ident.equals("verticalHierarchical")) {
                layout = new mxHierarchicalLayout(graph);
            } else if (ident.equals("horizontalHierarchical")) {
                layout = new mxHierarchicalLayout(graph, JLabel.WEST);
            } else if (ident.equals("verticalTree")) {
                layout = new mxCompactTreeLayout(graph, false);
            } else if (ident.equals("horizontalTree")) {
                layout = new mxCompactTreeLayout(graph, true);
            } else if (ident.equals("parallelEdges")) {
                layout = new mxParallelEdgeLayout(graph);
            } else if (ident.equals("placeEdgeLabels")) {
                layout = new mxEdgeLabelLayout(graph);
            } else if (ident.equals("organicLayout")) {
                layout = new mxOrganicLayout(graph);
            }
            if (ident.equals("verticalPartition")) {
                layout = new mxPartitionLayout(graph, false) {
                    /**
                     * Overrides the empty implementation to return the size of
                     * the graph control.
                     */
                    public mxRectangle getContainerSize() {
                        return graphComponent.getLayoutAreaSize();
                    }
                };
            } else if (ident.equals("horizontalPartition")) {
                layout = new mxPartitionLayout(graph, true) {
                    /**
                     * Overrides the empty implementation to return the size of
                     * the graph control.
                     */
                    public mxRectangle getContainerSize() {
                        return graphComponent.getLayoutAreaSize();
                    }
                };
            } else if (ident.equals("verticalStack")) {
                layout = new mxStackLayout(graph, false) {
                    /**
                     * Overrides the empty implementation to return the size of
                     * the graph control.
                     */
                    public mxRectangle getContainerSize() {
                        return graphComponent.getLayoutAreaSize();
                    }
                };
            } else if (ident.equals("horizontalStack")) {
                layout = new mxStackLayout(graph, true) {
                    /**
                     * Overrides the empty implementation to return the size of
                     * the graph control.
                     */
                    public mxRectangle getContainerSize() {
                        return graphComponent.getLayoutAreaSize();
                    }
                };
            } else if (ident.equals("circleLayout")) {
                layout = new mxCircleLayout(graph);
            }
        }

        return layout;
    }
}
