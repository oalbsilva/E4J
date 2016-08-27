/*    */ package br.unioeste.jgoose.e4j.swing;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.actions.HistoryAction;
/*    */ import br.unioeste.jgoose.e4j.actions.NewAction;
/*    */ import br.unioeste.jgoose.e4j.actions.OpenAction;
/*    */ import br.unioeste.jgoose.e4j.actions.SaveAction;
/*    */ import com.mxgraph.swing.handler.mxKeyboardHandler;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import javax.swing.ActionMap;
/*    */ import javax.swing.InputMap;
/*    */ import javax.swing.KeyStroke;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EditorKeyboardHandler
/*    */   extends mxKeyboardHandler
/*    */ {
/*    */   public EditorKeyboardHandler(mxGraphComponent graphComponent)
/*    */   {
/* 31 */     super(graphComponent);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected InputMap getInputMap(int condition)
/*    */   {
/* 42 */     InputMap map = super.getInputMap(condition);
/*    */     
/* 44 */     if ((condition == 0) && (map != null)) {
/* 45 */       map.put(KeyStroke.getKeyStroke("control S"), "save");
/* 46 */       map.put(KeyStroke.getKeyStroke("control shift S"), "saveAs");
/* 47 */       map.put(KeyStroke.getKeyStroke("control N"), "new");
/* 48 */       map.put(KeyStroke.getKeyStroke("control O"), "open");
/*    */       
/* 50 */       map.put(KeyStroke.getKeyStroke("control Z"), "undo");
/* 51 */       map.put(KeyStroke.getKeyStroke("control Y"), "redo");
/* 52 */       map.put(KeyStroke.getKeyStroke("control shift V"), "selectVertices");
/* 53 */       map.put(KeyStroke.getKeyStroke("control shift E"), "selectEdges");
/*    */     }
/*    */     
/* 56 */     return map;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected ActionMap createActionMap()
/*    */   {
/* 66 */     ActionMap map = super.createActionMap();
/*    */     
/* 68 */     map.put("save", new SaveAction(false));
/* 69 */     map.put("saveAs", new SaveAction(true));
/* 70 */     map.put("new", new NewAction());
/* 71 */     map.put("open", new OpenAction());
/* 72 */     map.put("undo", new HistoryAction(true));
/* 73 */     map.put("redo", new HistoryAction(false));
/* 74 */     map.put("selectVertices", mxGraphActions.getSelectVerticesAction());
/* 75 */     map.put("selectEdges", mxGraphActions.getSelectEdgesAction());
/*    */     
/* 77 */     return map;
/*    */   }
/*    */ }
