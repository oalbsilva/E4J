/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.menu.OutlinePopupMenu;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.swing.mxGraphOutline;
/*    */ import java.awt.Point;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JPopupMenu;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OutlineMenuMouseAdapter
/*    */   extends MouseAdapter
/*    */ {
/*    */   private mxGraphComponent graphComponent;
/*    */   private mxGraphOutline graphOutline;
/*    */   
/*    */   public OutlineMenuMouseAdapter(mxGraphComponent graphComponent, mxGraphOutline graphOutline)
/*    */   {
/* 22 */     this.graphComponent = graphComponent;
/* 23 */     this.graphOutline = graphOutline;
/*    */   }
/*    */   
/*    */ 
/*    */   public void mousePressed(MouseEvent e)
/*    */   {
/* 29 */     mouseReleased(e);
/*    */   }
/*    */   
/*    */   public void mouseReleased(MouseEvent e)
/*    */   {
/* 34 */     if (e.isPopupTrigger()) {
/* 35 */       Point pt = SwingUtilities.convertPoint(e
/* 36 */         .getComponent(), e
/* 37 */         .getPoint(), this.graphComponent);
/*    */       
/*    */ 
/* 40 */       JPopupMenu menu = new OutlinePopupMenu(this.graphOutline);
/* 41 */       menu.show(this.graphComponent, pt.x, pt.y);
/*    */       
/* 43 */       e.consume();
/*    */     }
/*    */   }
/*    */ }

