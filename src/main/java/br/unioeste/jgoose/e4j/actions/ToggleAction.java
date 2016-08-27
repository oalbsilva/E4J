/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ToggleAction
/*    */   extends AbstractAction
/*    */ {
/*    */   protected String key;
/*    */   protected boolean defaultValue;
/*    */   
/*    */   public ToggleAction(String key)
/*    */   {
/* 18 */     this(key, false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public ToggleAction(String key, boolean defaultValue)
/*    */   {
/* 26 */     this.key = key;
/* 27 */     this.defaultValue = defaultValue;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 31 */     mxGraph graph = mxGraphActions.getGraph(e);
/* 32 */     if (graph != null) {
/* 33 */       graph.toggleCellStyles(this.key, this.defaultValue);
/*    */     }
/*    */   }
/*    */ }

