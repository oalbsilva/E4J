/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.model.mxIGraphModel;
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class AutosizeAction
/*    */   extends AbstractAction
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 14 */     mxGraph graph = mxGraphActions.getGraph(e);
/* 15 */     if ((graph != null) && (!graph.isSelectionEmpty())) {
/* 16 */       Object[] cells = graph.getSelectionCells();
/* 17 */       mxIGraphModel model = graph.getModel();
/* 18 */       model.beginUpdate();
/*    */       try {
/* 20 */         for (int i = 0; i < cells.length; i++) {
/* 21 */           graph.updateCellSize(cells[i]);
/*    */         }
/*    */       } finally {
/* 24 */         model.endUpdate();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


