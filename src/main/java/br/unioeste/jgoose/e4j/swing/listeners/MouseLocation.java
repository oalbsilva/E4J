/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MouseLocation
/*    */   implements MouseMotionListener
/*    */ {
/*    */   private final BasicGraphEditor editor;
/*    */   
/*    */   public MouseLocation(BasicGraphEditor editor)
/*    */   {
/* 17 */     this.editor = editor;
/*    */   }
/*    */   
/*    */   public void mouseDragged(MouseEvent e)
/*    */   {
/* 22 */     this.editor.status(e.getX() + ", " + e.getY());
/*    */   }
/*    */   
/*    */   public void mouseMoved(MouseEvent e)
/*    */   {
/* 27 */     mouseDragged(e);
/*    */   }
/*    */ }

