/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class ToggleDirtyAction
/*    */   extends AbstractAction
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 12 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 13 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 14 */       graphComponent.showDirtyRectangle = (!graphComponent.showDirtyRectangle);
/*    */     }
/*    */   }
/*    */ }


