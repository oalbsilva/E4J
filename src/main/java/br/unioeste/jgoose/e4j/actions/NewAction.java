/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.model.mxCell;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ public class NewAction extends AbstractAction
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 16 */     BasicGraphEditor editor = EditorActions.getEditor(e);
/* 17 */     if ((editor != null) && (
/* 18 */       (!editor.isModified()) || (JOptionPane.showConfirmDialog(editor, mxResources.get("loseChanges")) == 0))) {
/* 19 */       mxGraph graph = editor.getGraphComponent().getGraph();
/*    */       
/* 21 */       mxCell root = new mxCell();
/* 22 */       root.insert(new mxCell());
/* 23 */       graph.getModel().setRoot(root);
/*    */       
/*    */ 
/*    */ 
/* 27 */       editor.setModified(false);
/* 28 */       editor.setCurrentFile(null);
/* 29 */       editor.getGraphComponent().zoomAndCenter();
/*    */     }
/*    */   }
/*    */ }

