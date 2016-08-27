/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import com.mxgraph.model.mxCell;
/*    */ import com.mxgraph.model.mxGeometry;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxPoint;
/*    */ import java.awt.Point;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.geom.Line2D;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DoubleClickOnEdgeMouseListener
/*    */   extends MouseAdapter
/*    */ {
/*    */   private mxGraphComponent graphComponent;
/*    */   
/*    */   public DoubleClickOnEdgeMouseListener(mxGraphComponent graphComponent)
/*    */   {
/* 28 */     this.graphComponent = graphComponent;
/*    */   }
/*    */   
/*    */ 
/*    */   public void mouseClicked(MouseEvent e)
/*    */   {
/* 34 */     if (e.getClickCount() > 1) {
/* 35 */       Point pt = SwingUtilities.convertPoint(this.graphComponent, e
/*    */       
/* 37 */         .getPoint(), e
/* 38 */         .getComponent());
/*    */       
/* 40 */       Object cell = this.graphComponent.getCellAt(pt.x, pt.y);
/* 41 */       if ((cell != null) && ((cell instanceof mxCell))) {
/* 42 */         mxCell edge = (mxCell)cell;
/* 43 */         if (edge.isEdge())
/*    */         {
/* 45 */           mxGeometry geom = edge.getGeometry();
/*    */           
/* 47 */           if (geom != null) {
/* 48 */             mxPoint newPoint = new mxPoint(pt);
/* 49 */             addSortedPoint(newPoint, geom);
/*    */             
/* 51 */             this.graphComponent.refresh();
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private void addSortedPoint(mxPoint newPoint, mxGeometry geom) {
/* 59 */     List<mxPoint> allPoints = new ArrayList();
/* 60 */     List<mxPoint> geomPoints = geom.getPoints();
/* 61 */     if (geomPoints == null) {
/* 62 */       geomPoints = new ArrayList();
/* 63 */       geom.setPoints(geomPoints);
/*    */     }
/*    */     
/* 66 */     allPoints.add(geom.getSourcePoint());
/* 67 */     allPoints.addAll(geomPoints);
/* 68 */     allPoints.add(geom.getTargetPoint());
/*    */     
/*    */ 
/*    */ 
/* 72 */     double minDistance = Double.MAX_VALUE;
/* 73 */     int index = 0;
/* 74 */     int size = allPoints.size();
/* 75 */     if (size == 2) {
/* 76 */       geomPoints.add(newPoint);
/* 77 */       return;
/*    */     }
/* 79 */     for (int i = 0; i < size - 1; i++) {
/* 80 */       mxPoint a = (mxPoint)allPoints.get(i);
/* 81 */       mxPoint b = (mxPoint)allPoints.get(i + 1);
/*    */       
/* 83 */       double distance = distance(a, b, newPoint);
/* 84 */       if (distance < minDistance) {
/* 85 */         index = i;
/* 86 */         minDistance = distance;
/*    */       }
/*    */     }
/* 89 */     if (index == size - 1) {
/* 90 */       index++;
/*    */     }
/* 92 */     geomPoints.add(index, newPoint);
/*    */   }
/*    */   
/*    */   private double distance(mxPoint a, mxPoint b, mxPoint z) {
/* 96 */     return Line2D.ptLineDist(a.getX(), a.getY(), b.getX(), b.getY(), z.getX(), z.getY());
/*    */   }
/*    */ }

