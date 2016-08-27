/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import java.awt.event.ActionEvent;
/*    */ 
/*    */ public class HistoryAction extends javax.swing.AbstractAction
/*    */ {
/*    */   protected boolean undo;
/*    */   
/*    */   public HistoryAction(boolean undo)
/*    */   {
/* 12 */     this.undo = undo;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 17 */     BasicGraphEditor editor = EditorActions.getEditor(e);
/* 18 */     if (editor != null) {
/* 19 */       if (this.undo) {
/* 20 */         editor.getUndoManager().undo();
/*    */       } else {
/* 22 */         editor.getUndoManager().redo();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

