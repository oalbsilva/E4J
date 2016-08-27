/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxUtils;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.Color;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ import javax.swing.JColorChooser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColorAction
/*    */   extends AbstractAction
/*    */ {
/*    */   protected String name;
/*    */   protected String key;
/*    */   
/*    */   public ColorAction(String name, String key)
/*    */   {
/* 22 */     this.name = name;
/* 23 */     this.key = key;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 28 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 29 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 30 */       mxGraph graph = graphComponent.getGraph();
/* 31 */       if (!graph.isSelectionEmpty()) {
/* 32 */         Color newColor = JColorChooser.showDialog(graphComponent, this.name, null);
/* 33 */         if (newColor != null) {
/* 34 */           graph.setCellStyles(this.key, mxUtils.hexString(newColor));
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }
