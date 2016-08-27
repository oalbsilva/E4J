/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.swing.mxGraphOutline;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import com.mxgraph.view.mxGraphView;
/*    */ import java.awt.event.MouseWheelEvent;
/*    */ import java.awt.event.MouseWheelListener;
/*    */ 
/*    */ public class ZoomWheelTracker
/*    */   implements MouseWheelListener
/*    */ {
/*    */   private BasicGraphEditor basicGraphEditor;
/*    */   private mxGraphComponent graphComponent;
/*    */   
/*    */   public ZoomWheelTracker(BasicGraphEditor basicGraphEditor, mxGraphComponent graphComponent)
/*    */   {
/* 20 */     this.basicGraphEditor = basicGraphEditor;
/* 21 */     this.graphComponent = graphComponent;
/*    */   }
/*    */   
/*    */   public void mouseWheelMoved(MouseWheelEvent e)
/*    */   {
/* 26 */     if (((e.getSource() instanceof mxGraphOutline)) || (e.isControlDown())) {
/* 27 */       if (e.getWheelRotation() < 0) {
/* 28 */         this.graphComponent.zoomIn();
/*    */       } else {
/* 30 */         this.graphComponent.zoomOut();
/*    */       }
/*    */       
/* 33 */       this.basicGraphEditor.status(mxResources.get("scale") + ": " + 
/* 34 */         (int)(100.0D * this.graphComponent.getGraph().getView().getScale()) + "%");
/*    */     }
/*    */   }
/*    */ }

