/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import java.awt.event.ActionEvent;
/*    */ 
/*    */ public class GridStyleAction extends javax.swing.AbstractAction
/*    */ {
/*    */   protected int style;
/*    */   
/*    */   public GridStyleAction(int style)
/*    */   {
/* 12 */     this.style = style;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 17 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 18 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 19 */       graphComponent.setGridStyle(this.style);
/* 20 */       graphComponent.repaint();
/*    */     }
/*    */   }
/*    */ }
