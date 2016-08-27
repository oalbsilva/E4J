/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.util.mxConstants;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.Component;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PromptValueAction
/*    */   extends AbstractAction
/*    */ {
/*    */   protected String key;
/*    */   protected String message;
/*    */   
/*    */   public PromptValueAction(String key, String message)
/*    */   {
/* 24 */     this.key = key;
/* 25 */     this.message = message;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 30 */     if ((e.getSource() instanceof Component)) {
/* 31 */       mxGraph graph = mxGraphActions.getGraph(e);
/* 32 */       if ((graph != null) && (!graph.isSelectionEmpty())) {
/* 33 */         String value = (String)JOptionPane.showInputDialog((Component)e.getSource(), mxResources.get("value"), this.message, -1, null, null, "");
/* 34 */         if (value != null) {
/* 35 */           if (value.equals(mxConstants.NONE)) {
/* 36 */             value = null;
/*    */           }
/* 38 */           graph.setCellStyles(this.key, value);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }
