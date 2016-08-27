/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.analysis.mxDistanceCostFunction;
/*    */ import com.mxgraph.analysis.mxGraphAnalysis;
/*    */ import com.mxgraph.model.mxIGraphModel;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class SelectSpanningTreeAction extends AbstractAction
/*    */ {
/*    */   protected boolean directed;
/*    */   
/*    */   public SelectSpanningTreeAction(boolean directed)
/*    */   {
/* 17 */     this.directed = directed;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 22 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 23 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 24 */       mxGraph graph = graphComponent.getGraph();
/* 25 */       mxIGraphModel model = graph.getModel();
/* 26 */       Object parent = graph.getDefaultParent();
/* 27 */       Object[] cells = graph.getSelectionCells();
/* 28 */       for (int i = 0; i < cells.length; i++) {
/* 29 */         if (model.getChildCount(cells[i]) > 0) {
/* 30 */           parent = cells[i];
/* 31 */           break;
/*    */         }
/*    */       }
/* 34 */       Object[] v = graph.getChildVertices(parent);
/* 35 */       Object[] mst = mxGraphAnalysis.getInstance().getMinimumSpanningTree(graph, v, new mxDistanceCostFunction(), this.directed);
/* 36 */       graph.setSelectionCells(mst);
/*    */     }
/*    */   }
/*    */ }


