/*     */ package br.unioeste.jgoose.e4j.swing.menubar;
/*     */ 
/*     */ import br.unioeste.jgoose.e4j.actions.BackgroundAction;
/*     */ import br.unioeste.jgoose.e4j.actions.BackgroundImageAction;
/*     */ import br.unioeste.jgoose.e4j.actions.GridColorAction;
/*     */ import br.unioeste.jgoose.e4j.actions.GridStyleAction;
/*     */ import br.unioeste.jgoose.e4j.actions.PageBackgroundAction;
/*     */ import br.unioeste.jgoose.e4j.actions.PromptPropertyAction;
/*     */ import br.unioeste.jgoose.e4j.actions.SelectShortestPathAction;
/*     */ import br.unioeste.jgoose.e4j.actions.SelectSpanningTreeAction;
/*     */ import br.unioeste.jgoose.e4j.actions.StylesheetAction;
/*     */ import br.unioeste.jgoose.e4j.actions.ToggleOutlineItem;
/*     */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*     */ import com.mxgraph.swing.mxGraphComponent;
/*     */ import com.mxgraph.util.mxResources;
/*     */ import com.mxgraph.view.mxGraph;
/*     */ import javax.swing.JMenu;
/*     */ 
/*     */ public class DiagramJMenu
/*     */   extends JMenu
/*     */ {
/*     */   public DiagramJMenu(BasicGraphEditor editor)
/*     */   {
/*  24 */     super("Model");
/*  25 */     mxGraphComponent graphComponent = editor.getGraphComponent();
/*  26 */     mxGraph graph = graphComponent.getGraph();
/*     */     
/*     */ 
/*  29 */     add(new ToggleOutlineItem(editor, mxResources.get("outline")));
/*     */     
/*  31 */     addSeparator();
/*     */     
/*  33 */     JMenu submenu = (JMenu)add(new JMenu(mxResources.get("background")));
/*     */     
/*  35 */     submenu.add(editor.bind(mxResources.get("backgroundColor"), new BackgroundAction()));
/*  36 */     submenu.add(editor.bind(mxResources.get("backgroundImage"), new BackgroundImageAction()));
/*     */     
/*  38 */     submenu.addSeparator();
/*     */     
/*  40 */     submenu.add(editor.bind(mxResources.get("pageBackground"), new PageBackgroundAction()));
/*     */     
/*  42 */     submenu = (JMenu)add(new JMenu(mxResources.get("grid")));
/*     */     
/*  44 */     submenu.add(editor.bind(mxResources.get("gridSize"), new PromptPropertyAction(graph, "Grid Size", "GridSize")));
/*  45 */     submenu.add(editor.bind(mxResources.get("gridColor"), new GridColorAction()));
/*     */     
/*  47 */     submenu.addSeparator();
/*     */     
/*  49 */     submenu.add(editor.bind(mxResources.get("dashed"), new GridStyleAction(3)));
/*  50 */     submenu.add(editor.bind(mxResources.get("dot"), new GridStyleAction(0)));
/*  51 */     submenu.add(editor.bind(mxResources.get("line"), new GridStyleAction(2)));
/*  52 */     submenu.add(editor.bind(mxResources.get("cross"), new GridStyleAction(1)));
/*     */     
/*  54 */     addSeparator();
/*     */     
/*  56 */     submenu = (JMenu)add(new JMenu(mxResources.get("layout")));
/*     */     
/*  58 */     submenu.add(editor.graphLayout("verticalHierarchical", true));
/*  59 */     submenu.add(editor.graphLayout("horizontalHierarchical", true));
/*     */     
/*  61 */     submenu.addSeparator();
/*     */     
/*  63 */     submenu.add(editor.graphLayout("verticalPartition", false));
/*  64 */     submenu.add(editor.graphLayout("horizontalPartition", false));
/*     */     
/*  66 */     submenu.addSeparator();
/*     */     
/*  68 */     submenu.add(editor.graphLayout("verticalStack", false));
/*  69 */     submenu.add(editor.graphLayout("horizontalStack", false));
/*     */     
/*  71 */     submenu.addSeparator();
/*     */     
/*  73 */     submenu.add(editor.graphLayout("verticalTree", true));
/*  74 */     submenu.add(editor.graphLayout("horizontalTree", true));
/*     */     
/*  76 */     submenu.addSeparator();
/*     */     
/*  78 */     submenu.add(editor.graphLayout("placeEdgeLabels", false));
/*  79 */     submenu.add(editor.graphLayout("parallelEdges", false));
/*     */     
/*  81 */     submenu.addSeparator();
/*     */     
/*  83 */     submenu.add(editor.graphLayout("organicLayout", true));
/*  84 */     submenu.add(editor.graphLayout("circleLayout", true));
/*     */     
/*  86 */     submenu = (JMenu)add(new JMenu(mxResources.get("selection")));
/*     */     
/*  88 */     submenu.add(editor.bind(mxResources.get("selectPath"), new SelectShortestPathAction(false)));
/*  89 */     submenu.add(editor.bind(mxResources.get("selectDirectedPath"), new SelectShortestPathAction(true)));
/*     */     
/*  91 */     submenu.addSeparator();
/*     */     
/*  93 */     submenu.add(editor.bind(mxResources.get("selectTree"), new SelectSpanningTreeAction(false)));
/*  94 */     submenu.add(editor.bind(mxResources.get("selectDirectedTree"), new SelectSpanningTreeAction(true)));
/*     */     
/*  96 */     addSeparator();
/*     */     
/*  98 */     submenu = (JMenu)add(new JMenu(mxResources.get("stylesheet")));
/*     */     
/* 100 */     submenu.add(editor.bind(mxResources.get("basicStyle"), new StylesheetAction("/com/mxgraph/examples/swing/resources/basic-style.xml")));
/*     */     
/* 102 */     submenu.add(editor.bind(mxResources.get("defaultStyle"), new StylesheetAction("/com/mxgraph/examples/swing/resources/default-style.xml")));
/*     */   }
/*     */ }


