/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class ScaleAction extends AbstractAction
/*    */ {
/*    */   protected double scale;
/*    */   
/*    */   public ScaleAction(double scale)
/*    */   {
/* 14 */     this.scale = scale;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 19 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 20 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 21 */       double scale = this.scale;
/* 22 */       if (scale == 0.0D) {
/* 23 */         String value = (String)javax.swing.JOptionPane.showInputDialog(graphComponent, mxResources.get("value"), mxResources.get("scale") + " (%)", -1, null, null, "");
/* 24 */         if (value != null) {
/* 25 */           scale = Double.parseDouble(value.replace("%", "")) / 100.0D;
/*    */         }
/*    */       }
/* 28 */       if (scale > 0.0D) {
/* 29 */         graphComponent.zoomTo(scale, graphComponent.isCenterZoom());
/*    */       }
/*    */     }
/*    */   }
/*    */ }
