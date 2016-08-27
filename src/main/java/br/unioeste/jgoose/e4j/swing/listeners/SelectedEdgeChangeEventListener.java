/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.CustomGraph;
/*    */ import com.mxgraph.model.mxIGraphModel;
/*    */ import com.mxgraph.swing.util.mxGraphTransferable;
/*    */ import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
/*    */ import com.mxgraph.util.mxEventSource.mxIEventListener;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SelectedEdgeChangeEventListener
/*    */   implements mxEventSource.mxIEventListener
/*    */ {
/* 23 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   private final mxGraph graph;
/*    */   
/*    */   public SelectedEdgeChangeEventListener(mxGraph graph) {
/* 27 */     this.graph = graph;
/*    */   }
/*    */   
/*    */ 
/*    */   public void invoke(Object sender, mxEventObject evt)
/*    */   {
/* 33 */     Object possiblyTransferable = evt.getProperty("transferable");
/*    */     
/* 35 */     if ((possiblyTransferable instanceof mxGraphTransferable)) {
/* 36 */       CONSOLE.debug("the element is transferable (is a edge!?)");
/* 37 */       mxGraphTransferable transferable = (mxGraphTransferable)possiblyTransferable;
/* 38 */       Object cell = transferable.getCells()[0];
/*    */       
/*    */ 
/* 41 */       if (this.graph.getModel().isEdge(cell))
/*    */       {
/* 43 */         ((CustomGraph)this.graph).setEdgeTemplate(cell);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

