/*     */ package br.unioeste.jgoose.e4j.swing;
/*     */ 
/*     */ import br.unioeste.jgoose.e4j.CustomGraph;
/*     */ import br.unioeste.jgoose.e4j.swing.listeners.SelectedEdgeChangeEventListener;
/*     */ import br.unioeste.jgoose.e4j.swing.palettes.UseCasesDiagramPalette;
/*     */ import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEventSource;
/*     */ import com.mxgraph.view.mxGraph;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicUseCasesEditor
/*     */   extends BasicGraphEditor
/*     */ {
/*     */   private static final long serialVersionUID = -4601740824088314699L;
/*  24 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  30 */   public static final NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt_BR"));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  35 */   public static URL url = null;
/*     */   
/*     */   public BasicUseCasesEditor(JFrame frame) throws IOException
/*     */   {
/*  39 */     this("JGOOSE - Editor Use Cases", new CustomGraphComponent(new CustomGraph()));
/*  40 */     super.setFrame(frame);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void generateDiagram()
/*     */     throws IOException
/*     */   {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BasicUseCasesEditor(String appTitle, mxGraphComponent component)
/*     */   {
/* 179 */     super(appTitle, component);
/*     */     
/* 181 */     this.graphComponent.setEnterStopsCellEditing(true);
/* 182 */     mxGraph graph = this.graphComponent.getGraph();
/*     */     
/*     */ 
/*     */ 
/* 186 */     EditorPalette useCasesDiagramPalette = new UseCasesDiagramPalette(this.libraryPane);
/* 187 */     useCasesDiagramPalette.addListener("select", (mxEventSource.mxIEventListener) new SelectedEdgeChangeEventListener(graph));
/*     */     
/* 189 */     Object item = useCasesDiagramPalette.getComponent(2);
/* 190 */     useCasesDiagramPalette.setSelectionEntry((JLabel)item, null);
/*     */   }
/*     */   
/*     */   public void exit()
/*     */   {
/* 195 */     if (this.frame != null) {
/* 196 */       ((EditorJFrame)this.frame).exit();
/*     */     }
/*     */     else {
/* 199 */       CONSOLE.debug("editorJFrame is null");
/*     */     }
/*     */   }
/*     */ }
