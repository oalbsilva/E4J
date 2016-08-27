/*    */ package br.unioeste.jgoose.e4j;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import br.unioeste.jgoose.e4j.swing.EditorJFrame;
/*    */ import com.mxgraph.model.mxIGraphModel;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.HeadlessException;
/*    */ import java.io.IOException;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.UnsupportedLookAndFeelException;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class App
/*    */ {
/* 19 */   public static final Logger CONSOLE = Logger.getLogger("console");
/*    */   public mxGraph graph;
/*    */   mxIGraphModel model;
/*    */   
/*    */   public App(mxGraph graph)
/*    */   {
/* 25 */     this.graph = graph;
/* 26 */     this.model = graph.getModel();
/*    */   }
/*    */   
/*    */   public void begin() {
/* 30 */     this.model.beginUpdate();
/*    */   }
/*    */   
/*    */   public void end() {
/* 34 */     this.model.endUpdate();
/*    */   }
/*    */   
/*    */   public static void main(String[] args) throws HeadlessException, IOException
/*    */   {
/*    */     try {
/* 40 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*    */     } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException ex) {
/* 42 */       CONSOLE.error("LookAndFeel error", ex);
/*    */     }
/*    */     
/* 45 */     EditorJFrame editor = new EditorJFrame(true);
/* 46 */     editor.setDefaultCloseOperation(3);
/*    */     
/*    */ 
/* 49 */     BasicGraphEditor bge = editor.getEditor();
/*    */     
/* 51 */     editor.setVisible(true);
/*    */   }
/*    */ }

