/*    */ package br.unioeste.jgoose.e4j.swing.menu;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphOutline;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JCheckBoxMenuItem;
/*    */ import javax.swing.JPopupMenu;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OutlinePopupMenu
/*    */   extends JPopupMenu
/*    */ {
/*    */   public OutlinePopupMenu(mxGraphOutline graphOutline)
/*    */   {
/* 17 */     JCheckBoxMenuItem itemMagnifyPage = new MagnifyPageMenuItem(graphOutline);
/* 18 */     JCheckBoxMenuItem itemShowLabel = new ShowLabelsMenuItem(graphOutline);
/*    */     
/* 20 */     add(itemMagnifyPage);
/* 21 */     add(itemShowLabel);
/*    */   }
/*    */   
/*    */   private class MagnifyPageMenuItem extends JCheckBoxMenuItem
/*    */   {
/*    */     MagnifyPageMenuItem(final mxGraphOutline graphOutline) {
/* 27 */       super();
/* 28 */       setSelected(graphOutline.isFitPage());
/*    */       
/* 30 */       addActionListener(new ActionListener()
/*    */       {
/*    */         public void actionPerformed(ActionEvent e) {
/* 33 */           graphOutline.setFitPage(!graphOutline.isFitPage());
/* 34 */           graphOutline.repaint();
/*    */         }
/*    */       });
/*    */     }
/*    */   }
/*    */   
/*    */   private class ShowLabelsMenuItem extends JCheckBoxMenuItem
/*    */   {
/*    */     ShowLabelsMenuItem(final mxGraphOutline graphOutline) {
/* 43 */       setSelected(graphOutline.isDrawLabels());
/* 44 */       addActionListener(new ActionListener()
/*    */       {
/*    */         public void actionPerformed(ActionEvent e) {
/* 47 */           graphOutline.setDrawLabels(!graphOutline.isDrawLabels());
/* 48 */           graphOutline.repaint();
/*    */         }
/*    */       });
/*    */     }
/*    */   }
/*    */   
/*    */   private class BufferingMenuItem extends JCheckBoxMenuItem
/*    */   {
/*    */     BufferingMenuItem(final mxGraphOutline graphOutline) {
/* 57 */       setSelected(graphOutline.isTripleBuffered());
/* 58 */       addActionListener(new ActionListener()
/*    */       {
/*    */         public void actionPerformed(ActionEvent e) {
/* 61 */           graphOutline.setTripleBuffered(!graphOutline.isTripleBuffered());
/* 62 */           graphOutline.repaint();
/*    */         }
/*    */       });
/*    */     }
/*    */   }
/*    */ }

