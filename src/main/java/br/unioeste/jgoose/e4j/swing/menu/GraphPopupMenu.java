/*    */ package br.unioeste.jgoose.e4j.swing.menu;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.actions.HistoryAction;
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import br.unioeste.jgoose.e4j.swing.menubar.FormatJMenu;
/*    */ import br.unioeste.jgoose.e4j.swing.menubar.ShapeJMenu;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import javax.swing.JMenuItem;
/*    */ import javax.swing.JPopupMenu;
/*    */ import javax.swing.TransferHandler;
/*    */ 
/*    */ public class GraphPopupMenu extends JPopupMenu
/*    */ {
/*    */   private static final long serialVersionUID = -3132749140550242191L;
/*    */   
/*    */   public GraphPopupMenu(BasicGraphEditor editor)
/*    */   {
/* 21 */     boolean selected = !editor.getGraphComponent().getGraph().isSelectionEmpty();
/*    */     
/*    */ 
/* 24 */     if ((editor instanceof br.unioeste.jgoose.e4j.swing.BasicIStarEditor)) {
/* 25 */       add(editor.bind(mxResources.get("addBoundary", "Add Boundary"), new br.unioeste.jgoose.e4j.actions.AddBoundary()));
/* 26 */       addSeparator();
/*    */     }
/*    */     
/* 29 */     add(editor.bind(mxResources.get("undo"), new HistoryAction(true), "/com/mxgraph/examples/swing/images/undo.gif"));
/*    */     
/*    */ 
/* 32 */     addSeparator();
/*    */     
/* 34 */     add(editor
/* 35 */       .bind(mxResources.get("cut"), 
/* 36 */       TransferHandler.getCutAction(), "/com/mxgraph/examples/swing/images/cut.gif"))
/*    */       
/* 38 */       .setEnabled(selected);
/* 39 */     add(editor
/* 40 */       .bind(mxResources.get("copy"), 
/* 41 */       TransferHandler.getCopyAction(), "/com/mxgraph/examples/swing/images/copy.gif"))
/*    */       
/* 43 */       .setEnabled(selected);
/* 44 */     add(editor.bind(mxResources.get("paste"), 
/* 45 */       TransferHandler.getPasteAction(), "/com/mxgraph/examples/swing/images/paste.gif"));
/*    */     
/*    */ 
/* 48 */     addSeparator();
/*    */     
/* 50 */     add(editor
/* 51 */       .bind(mxResources.get("delete"), 
/* 52 */       mxGraphActions.getDeleteAction(), "/com/mxgraph/examples/swing/images/delete.gif"))
/*    */       
/* 54 */       .setEnabled(selected);
/*    */     
/* 56 */     addSeparator();
/*    */     
/*    */ 
/* 59 */     add(new FormatJMenu(editor));
/*    */     
/*    */ 
/* 62 */     add(new ShapeJMenu(editor));
/*    */     
/* 64 */     addSeparator();
/* 65 */     add(editor.bind(mxResources.get("edit"), 
/* 66 */       mxGraphActions.getEditAction())).setEnabled(selected);
/*    */     
/* 68 */     addSeparator();
/* 69 */     add(editor.bind(mxResources.get("selectVertices"), 
/* 70 */       mxGraphActions.getSelectVerticesAction()));
/* 71 */     add(editor.bind(mxResources.get("selectEdges"), 
/* 72 */       mxGraphActions.getSelectEdgesAction()));
/*    */     
/* 74 */     addSeparator();
/* 75 */     add(editor.bind(mxResources.get("selectAll"), 
/* 76 */       mxGraphActions.getSelectAllAction()));
/*    */     
/* 78 */     addSeparator();
/*    */   }
/*    */ }
