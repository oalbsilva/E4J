/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.model.mxCell;
/*    */ import com.mxgraph.model.mxGeometry;
/*    */ import com.mxgraph.model.mxIGraphModel;
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.util.mxPoint;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AddBoundary
/*    */   extends AbstractAction
/*    */ {
/* 18 */   final int PORT_DIAMETER = 80;
/*    */   
/* 20 */   final int PORT_RADIUS = 40;
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 24 */     mxGraph graph = mxGraphActions.getGraph(e);
/* 25 */     if ((graph != null) && (!graph.isSelectionEmpty())) {
/* 26 */       mxCell cell = (mxCell)graph.getSelectionCell();
/* 27 */       boolean isActor = cell.getAttribute("type").matches("actor|agent");
/* 28 */       boolean hasChild = cell.getChildCount() > 0;
/*    */       
/* 30 */       if ((cell.isVertex()) && (!hasChild) && (isActor)) {
/* 31 */         mxIGraphModel model = graph.getModel();
/*    */         
/*    */ 
/* 34 */         model.beginUpdate();
/*    */         
/*    */ 
/*    */ 
/* 38 */         mxGeometry geo = new mxGeometry(0.0D, 0.5D, 80.0D, 80.0D);
/*    */         
/*    */ 
/*    */ 
/* 42 */         geo.setOffset(new mxPoint(-40.0D, -40.0D));
/* 43 */         geo.setRelative(true);
/*    */         
/*    */ 
/* 46 */         String style = "";
/* 47 */         switch (cell.getAttribute("type")) {
/*    */         case "actor": 
/* 49 */           style = "shape=Actor";
/* 50 */           break;
/*    */         case "agent": 
/* 52 */           style = "shape=Agent";
/* 53 */           break;
/*    */         case "role": 
/* 55 */           style = "shape=Role";
/* 56 */           break;
/*    */         case "position": 
/* 58 */           style = "shape=Position";
/*    */         }
/*    */         
/*    */         
/*    */ 
/* 63 */         mxCell port = new mxCell(cell.getAttribute("label"), geo, style);
/*    */         
/* 65 */         port.setVertex(true);
/*    */         
/* 67 */         port.setConnectable(false);
/* 68 */         graph.addCell(port, cell);
/*    */         
/*    */ 
/* 71 */         cell.setStyle(cell.getStyle() + ";noLabel=1");
/* 72 */         graph.cellsOrdered(new Object[] { cell }, true);
/*    */         
/* 74 */         model.endUpdate();
/*    */       }
/*    */     }
/*    */   }
/*    */ }
