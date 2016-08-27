/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import br.unioeste.jgoose.e4j.swing.menu.GraphPopupMenu;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import java.awt.Point;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GraphControlMenuMouseAdapter
/*    */   extends MouseAdapter
/*    */ {
/*    */   private BasicGraphEditor basicGraphEditor;
/*    */   private mxGraphComponent graphComponent;
/*    */   
/*    */   public GraphControlMenuMouseAdapter(BasicGraphEditor basicGraphEditor, mxGraphComponent graphComponent)
/*    */   {
/* 22 */     this.basicGraphEditor = basicGraphEditor;
/* 23 */     this.graphComponent = graphComponent;
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
/* 35 */       Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), this.graphComponent);
/*    */       
/* 37 */       GraphPopupMenu menu = new GraphPopupMenu(this.basicGraphEditor);
/* 38 */       menu.show(this.graphComponent, pt.x, pt.y);
/*    */       
/* 40 */       e.consume();
/*    */     }
/*    */   }
/*    */ }

