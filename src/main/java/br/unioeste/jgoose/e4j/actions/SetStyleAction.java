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
/*    */ public class SetStyleAction
/*    */   extends AbstractAction
/*    */ {
/*    */   protected String value;
/*    */   
/*    */   public SetStyleAction(String value)
/*    */   {
/* 18 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 27 */     mxGraph graph = mxGraphActions.getGraph(e);
/* 28 */     if ((graph != null) && (!graph.isSelectionEmpty())) {
/* 29 */       graph.setCellStyle(this.value);
/*    */     }
/*    */   }
/*    */ }
