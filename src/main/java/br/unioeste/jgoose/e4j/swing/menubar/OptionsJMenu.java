/*     */ package br.unioeste.jgoose.e4j.swing.menubar;
/*     */ 
/*     */ import br.unioeste.jgoose.e4j.actions.PromptPropertyAction;
/*     */ import br.unioeste.jgoose.e4j.actions.ToggleConnectModeAction;
/*     */ import br.unioeste.jgoose.e4j.actions.ToggleCreateTargetItem;
/*     */ import br.unioeste.jgoose.e4j.actions.ToggleDirtyAction;
/*     */ import br.unioeste.jgoose.e4j.actions.TogglePropertyItem;
/*     */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*     */ import com.mxgraph.swing.mxGraphComponent;
/*     */ import com.mxgraph.util.mxResources;
/*     */ import com.mxgraph.view.mxGraph;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JMenu;
/*     */ 
/*     */ public class OptionsJMenu extends JMenu
/*     */ {
/*     */   public OptionsJMenu(BasicGraphEditor editor)
/*     */   {
/*  20 */     super("Options");
/*     */     
/*  22 */     final mxGraphComponent graphComponent = editor.getGraphComponent();
/*  23 */     mxGraph graph = graphComponent.getGraph();
/*     */     
/*  25 */     JMenu submenu = (JMenu)add(new JMenu(mxResources.get("display")));
/*  26 */     submenu.add(new TogglePropertyItem(graphComponent, mxResources.get("buffering"), "TripleBuffered", true));
/*     */     
/*  28 */     submenu.add(new TogglePropertyItem(graphComponent, mxResources.get("preferPageSize"), "PreferPageSize", true, new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  31 */         graphComponent.zoomAndCenter();
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  38 */     }));
/*  39 */     submenu.addSeparator();
/*     */     
/*  41 */     submenu.add(editor.bind(mxResources.get("tolerance"), new PromptPropertyAction(graphComponent, "Tolerance")));
/*     */     
/*  43 */     submenu.add(editor.bind(mxResources.get("dirty"), new ToggleDirtyAction()));
/*     */     
/*  45 */     submenu = (JMenu)add(new JMenu(mxResources.get("zoom")));
/*     */     
/*  47 */     submenu.add(new TogglePropertyItem(graphComponent, mxResources.get("centerZoom"), "CenterZoom", true));
/*  48 */     submenu.add(new TogglePropertyItem(graphComponent, mxResources.get("zoomToSelection"), "KeepSelectionVisibleOnZoom", true));
/*     */     
/*  50 */     submenu.addSeparator();
/*     */     
/*  52 */     submenu.add(new TogglePropertyItem(graphComponent, mxResources.get("centerPage"), "CenterPage", true, new ActionListener()
/*     */     {
/*     */ 
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/*  57 */         if ((graphComponent.isPageVisible()) && (graphComponent.isCenterPage())) {
/*  58 */           graphComponent.zoomAndCenter();
/*     */         }
/*     */         
/*     */       }
/*  62 */     }));
/*  63 */     addSeparator();
/*     */     
/*  65 */     submenu = (JMenu)add(new JMenu(mxResources.get("dragAndDrop")));
/*     */     
/*  67 */     submenu.add(new TogglePropertyItem(graphComponent, mxResources.get("dragEnabled"), "DragEnabled"));
/*  68 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("dropEnabled"), "DropEnabled"));
/*     */     
/*  70 */     submenu.addSeparator();
/*     */     
/*  72 */     submenu.add(new TogglePropertyItem(graphComponent.getGraphHandler(), mxResources.get("imagePreview"), "ImagePreview"));
/*     */     
/*  74 */     submenu = (JMenu)add(new JMenu(mxResources.get("labels")));
/*     */     
/*  76 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("htmlLabels"), "HtmlLabels", true));
/*  77 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("showLabels"), "LabelsVisible", true));
/*     */     
/*  79 */     submenu.addSeparator();
/*     */     
/*  81 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("moveEdgeLabels"), "EdgeLabelsMovable"));
/*  82 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("moveVertexLabels"), "VertexLabelsMovable"));
/*     */     
/*  84 */     submenu.addSeparator();
/*     */     
/*  86 */     submenu.add(new TogglePropertyItem(graphComponent, mxResources.get("handleReturn"), "EnterStopsCellEditing"));
/*     */     
/*  88 */     addSeparator();
/*     */     
/*  90 */     submenu = (JMenu)add(new JMenu(mxResources.get("connections")));
/*     */     
/*  92 */     submenu.add(new TogglePropertyItem(graphComponent, mxResources.get("connectable"), "Connectable"));
/*  93 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("connectableEdges"), "ConnectableEdges"));
/*     */     
/*  95 */     submenu.addSeparator();
/*     */     
/*  97 */     submenu.add(new ToggleCreateTargetItem(editor, mxResources.get("createTarget")));
/*  98 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("disconnectOnMove"), "DisconnectOnMove"));
/*     */     
/* 100 */     submenu.addSeparator();
/*     */     
/* 102 */     submenu.add(editor.bind(mxResources.get("connectMode"), new ToggleConnectModeAction()));
/*     */     
/* 104 */     submenu = (JMenu)add(new JMenu(mxResources.get("validation")));
/*     */     
/* 106 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("allowDanglingEdges"), "AllowDanglingEdges"));
/* 107 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("cloneInvalidEdges"), "CloneInvalidEdges"));
/*     */     
/* 109 */     submenu.addSeparator();
/*     */     
/* 111 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("allowLoops"), "AllowLoops"));
/* 112 */     submenu.add(new TogglePropertyItem(graph, mxResources.get("multigraph"), "Multigraph"));
/*     */   }
/*     */ }

