/*    */ package br.unioeste.jgoose.e4j.swing.menubar;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.actions.HistoryAction;
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import javax.swing.JMenu;
/*    */ import javax.swing.TransferHandler;
/*    */ 
/*    */ public class EditJMenu extends JMenu
/*    */ {
/*    */   public EditJMenu(BasicGraphEditor editor)
/*    */   {
/* 14 */     super("Edit");
/* 15 */     add(editor.bind(mxResources.get("undo"), new HistoryAction(true), "/com/mxgraph/examples/swing/images/undo.gif"));
/* 16 */     add(editor.bind(mxResources.get("redo"), new HistoryAction(false), "/com/mxgraph/examples/swing/images/redo.gif"));
/*    */     
/* 18 */     addSeparator();
/* 19 */     add(editor.bind(mxResources.get("cut"), TransferHandler.getCutAction(), "/com/mxgraph/examples/swing/images/cut.gif"));
/* 20 */     add(editor.bind(mxResources.get("copy"), TransferHandler.getCopyAction(), "/com/mxgraph/examples/swing/images/copy.gif"));
/* 21 */     add(editor.bind(mxResources.get("paste"), TransferHandler.getPasteAction(), "/com/mxgraph/examples/swing/images/paste.gif"));
/*    */     
/* 23 */     addSeparator();
/* 24 */     add(editor.bind(mxResources.get("delete"), mxGraphActions.getDeleteAction(), "/com/mxgraph/examples/swing/images/delete.gif"));
/*    */     
/* 26 */     addSeparator();
/* 27 */     add(editor.bind(mxResources.get("selectAll"), mxGraphActions.getSelectAllAction()));
/* 28 */     add(editor.bind(mxResources.get("selectNone"), mxGraphActions.getSelectNoneAction()));
/*    */     
/* 30 */     addSeparator();
/*    */     
/* 32 */     add(editor.bind(mxResources.get("edit"), mxGraphActions.getEditAction()));
/*    */   }
/*    */ }

