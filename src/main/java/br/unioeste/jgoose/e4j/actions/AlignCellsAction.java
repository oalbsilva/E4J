/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AlignCellsAction
/*    */   extends AbstractAction
/*    */ {
/*    */   protected String align;
/*    */   
/*    */   public AlignCellsAction(String align)
/*    */   {
/* 18 */     this.align = align;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 23 */     mxGraph graph = mxGraphActions.getGraph(e);
/* 24 */     if ((graph != null) && (!graph.isSelectionEmpty())) {
/* 25 */       graph.alignCells(this.align);
/*    */     }
/*    */   }
/*    */ }
