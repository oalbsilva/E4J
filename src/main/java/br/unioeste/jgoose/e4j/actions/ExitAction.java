/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class ExitAction
/*    */   extends AbstractAction
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 12 */     BasicGraphEditor editor = EditorActions.getEditor(e);
/*    */     
/* 14 */     if (editor != null) {
/* 15 */       editor.exit();
/*    */     }
/*    */   }
/*    */ }
