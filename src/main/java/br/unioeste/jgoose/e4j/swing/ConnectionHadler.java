/*    */ package br.unioeste.jgoose.e4j.swing;
/*    */ 
/*    */ import com.mxgraph.model.mxGeometry;
/*    */ import com.mxgraph.model.mxIGraphModel;
/*    */ import com.mxgraph.swing.handler.mxConnectionHandler;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxPoint;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.MouseEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConnectionHadler
/*    */   extends mxConnectionHandler
/*    */ {
/*    */   public ConnectionHadler(mxGraphComponent graphComponent)
/*    */   {
/* 18 */     super(graphComponent);
/*    */   }
/*    */   
/*    */   public Object createTargetVertex(MouseEvent e, Object source)
/*    */   {
/* 23 */     mxGraph graph = this.graphComponent.getGraph();
/* 24 */     Object clone = graph.cloneCells(new Object[] { source }, false)[0];
/* 25 */     mxIGraphModel model = graph.getModel();
/* 26 */     mxGeometry geo = model.getGeometry(clone);
/*    */     
/* 28 */     if (geo != null) {
/* 29 */       mxPoint point = this.graphComponent.getPointForEvent(e);
/* 30 */       geo.setX(graph.snap(point.getX() - geo.getWidth() / 2.0D));
/* 31 */       geo.setY(graph.snap(point.getY() - geo.getHeight() / 2.0D));
/*    */     }
/*    */     
/* 34 */     return clone;
/*    */   }
/*    */ }
