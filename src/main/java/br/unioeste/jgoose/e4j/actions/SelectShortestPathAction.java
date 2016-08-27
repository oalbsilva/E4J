/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.analysis.mxDistanceCostFunction;
/*    */ import com.mxgraph.analysis.mxGraphAnalysis;
/*    */ import com.mxgraph.model.mxIGraphModel;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class SelectShortestPathAction extends AbstractAction
/*    */ {
/*    */   protected boolean directed;
/*    */   
/*    */   public SelectShortestPathAction(boolean directed)
/*    */   {
/* 18 */     this.directed = directed;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 22 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 23 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 24 */       mxGraph graph = graphComponent.getGraph();
/* 25 */       mxIGraphModel model = graph.getModel();
/* 26 */       Object source = null;
/* 27 */       Object target = null;
/* 28 */       Object[] cells = graph.getSelectionCells();
/* 29 */       for (int i = 0; i < cells.length; i++) {
/* 30 */         if (model.isVertex(cells[i])) {
/* 31 */           if (source == null) {
/* 32 */             source = cells[i];
/* 33 */           } else if (target == null) {
/* 34 */             target = cells[i];
/*    */           }
/*    */         }
/* 37 */         if ((source != null) && (target != null)) {
/*    */           break;
/*    */         }
/*    */       }
/* 41 */       if ((source != null) && (target != null)) {
/* 42 */         int steps = graph.getChildEdges(graph.getDefaultParent()).length;
/* 43 */         Object[] path = mxGraphAnalysis.getInstance().getShortestPath(graph, source, target, new mxDistanceCostFunction(), steps, this.directed);
/* 44 */         graph.setSelectionCells(path);
/*    */       } else {
/* 46 */         javax.swing.JOptionPane.showMessageDialog(graphComponent, mxResources.get("noSourceAndTargetSelected"));
/*    */       }
/*    */     }
/*    */   }
/*    */ }

