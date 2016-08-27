/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.util.mxUtils;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.Image;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ public class BackgroundImageAction extends AbstractAction
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 17 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 18 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 19 */       String value = (String)JOptionPane.showInputDialog(graphComponent, mxResources.get("backgroundImage"), "URL", -1, null, null, "http://www.callatecs.com/images/background2.JPG");
/* 20 */       if (value != null) {
/* 21 */         if (value.length() == 0) {
/* 22 */           graphComponent.setBackgroundImage(null);
/*    */         } else {
/* 24 */           Image background = mxUtils.loadImage(value);
/*    */           
/*    */ 
/* 27 */           if (background != null) {
/* 28 */             graphComponent.setBackgroundImage(new ImageIcon(background));
/*    */           }
/*    */         }
/*    */         
/* 32 */         graphComponent.getGraph().repaint();
/*    */       }
/*    */     }
/*    */   }
/*    */ }
