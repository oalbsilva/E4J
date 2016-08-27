/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
/*    */ import com.mxgraph.util.mxEventSource.mxIEventListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EditorChangeTracker
/*    */   implements mxEventSource.mxIEventListener
/*    */ {
/*    */   private final BasicGraphEditor editor;
/*    */   
/*    */   public EditorChangeTracker(BasicGraphEditor editor)
/*    */   {
/* 16 */     this.editor = editor;
/*    */   }
/*    */   
/*    */   public void invoke(Object source, mxEventObject evt)
/*    */   {
/* 21 */     this.editor.setModified(true);
/*    */   }
/*    */ }

