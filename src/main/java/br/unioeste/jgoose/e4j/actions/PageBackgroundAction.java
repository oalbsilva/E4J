/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import java.awt.Color;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ import javax.swing.JColorChooser;
/*    */ 
/*    */ public class PageBackgroundAction
/*    */   extends AbstractAction
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 15 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 16 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 17 */       Color newColor = JColorChooser.showDialog(graphComponent, mxResources.get("pageBackground"), null);
/* 18 */       if (newColor != null) {
/* 19 */         graphComponent.setPageBackgroundColor(newColor);
/*    */       }
/*    */       
/* 22 */       graphComponent.repaint();
/*    */     }
/*    */   }
/*    */ }

