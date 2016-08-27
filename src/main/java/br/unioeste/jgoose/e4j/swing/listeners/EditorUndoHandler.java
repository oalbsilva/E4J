/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
/*    */ import com.mxgraph.util.mxEventSource.mxIEventListener;
/*    */ import com.mxgraph.util.mxUndoManager;
/*    */ import com.mxgraph.util.mxUndoableEdit;
/*    */ 
/*    */ 
/*    */ public class EditorUndoHandler
/*    */   implements mxEventSource.mxIEventListener
/*    */ {
/*    */   private final BasicGraphEditor editor;
/*    */   
/*    */   public EditorUndoHandler(BasicGraphEditor editor)
/*    */   {
/* 17 */     this.editor = editor;
/*    */   }
/*    */   
/*    */   public void invoke(Object source, mxEventObject evt)
/*    */   {
/* 22 */     this.editor.getUndoManager().undoableEditHappened(
/* 23 */       (mxUndoableEdit)evt.getProperty("edit"));
/*    */   }
/*    */ }

