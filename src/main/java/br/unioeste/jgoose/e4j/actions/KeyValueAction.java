/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyValueAction
/*    */   extends AbstractAction
/*    */ {
/*    */   protected String key;
/*    */   protected String value;
/*    */   
/*    */   public KeyValueAction(String key)
/*    */   {
/* 18 */     this(key, null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public KeyValueAction(String key, String value)
/*    */   {
/* 26 */     this.key = key;
/* 27 */     this.value = value;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 32 */     mxGraph graph = mxGraphActions.getGraph(e);
/* 33 */     if ((graph != null) && (!graph.isSelectionEmpty())) {
/* 34 */       graph.setCellStyles(this.key, this.value);
/*    */     }
/*    */   }
/*    */ }
