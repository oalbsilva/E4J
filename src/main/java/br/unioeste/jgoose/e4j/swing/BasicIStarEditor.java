/*    */ package br.unioeste.jgoose.e4j.swing;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.CustomGraph;
/*    */ import br.unioeste.jgoose.e4j.swing.listeners.SelectedEdgeChangeEventListener;
/*    */ import br.unioeste.jgoose.e4j.swing.palettes.ActorAssociationsPalette;
/*    */ import br.unioeste.jgoose.e4j.swing.palettes.ActorsPalette;
/*    */ import br.unioeste.jgoose.e4j.swing.palettes.ContributionLinksPalette;
/*    */ import br.unioeste.jgoose.e4j.swing.palettes.DependencyElementsPalette;
/*    */ import br.unioeste.jgoose.e4j.swing.palettes.RelationshipLinksPalette;
/*    */ import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEventSource;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.net.URL;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.Locale;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BasicIStarEditor
/*    */   extends BasicGraphEditor
/*    */ {
/*    */   private static final long serialVersionUID = -4601740824088314699L;
/* 27 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 33 */   public static final NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt_BR"));
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 38 */   public static URL url = null;
/*    */   
/*    */   public BasicIStarEditor(JFrame frame)
/*    */   {
/* 42 */     this("JGOOSE - Editor i*", new CustomGraphComponent(new CustomGraph()));
/* 43 */     super.setFrame(frame);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BasicIStarEditor(String appTitle, mxGraphComponent component)
/*    */   {
/* 52 */     super(appTitle, component);
/*    */     
/* 54 */     this.graphComponent.setEnterStopsCellEditing(true);
/* 55 */     mxGraph graph = this.graphComponent.getGraph();
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 61 */     EditorPalette actorsPalette = new ActorsPalette(this.libraryPane);
/* 62 */     EditorPalette actorAssosiationsPalette = new ActorAssociationsPalette(this.libraryPane);
/* 63 */     EditorPalette dependencyElementsPalette = new DependencyElementsPalette(this.libraryPane);
/* 64 */     EditorPalette relationshipLinksPalette = new RelationshipLinksPalette(this.libraryPane);
/* 65 */     EditorPalette contributionLinksPalette = new ContributionLinksPalette(this.libraryPane);
/*    */     
/*    */ 
/* 68 */     actorsPalette.addListener("select", (mxEventSource.mxIEventListener) new SelectedEdgeChangeEventListener(graph));
/* 69 */     actorAssosiationsPalette.addListener("select", (mxEventSource.mxIEventListener) new SelectedEdgeChangeEventListener(graph));
/* 70 */     dependencyElementsPalette.addListener("select", (mxEventSource.mxIEventListener) new SelectedEdgeChangeEventListener(graph));
/* 71 */     relationshipLinksPalette.addListener("select", (mxEventSource.mxIEventListener) new SelectedEdgeChangeEventListener(graph));
/* 72 */     contributionLinksPalette.addListener("select", (mxEventSource.mxIEventListener) new SelectedEdgeChangeEventListener(graph));
/*    */     
/* 74 */     Object item = relationshipLinksPalette.getComponent(0);
/* 75 */     relationshipLinksPalette.setSelectionEntry((JLabel)item, null);
/*    */   }
/*    */   
/*    */   public void exit()
/*    */   {
/* 80 */     if (this.frame != null) {
/* 81 */       ((EditorJFrame)this.frame).exit();
/*    */     }
/*    */     else {
/* 84 */       CONSOLE.debug("editorJFrame is null");
/*    */     }
/*    */   }
/*    */ }

