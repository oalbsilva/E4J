/*    */ package br.unioeste.jgoose.e4j.swing.menubar;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.actions.ExitAction;
/*    */ import br.unioeste.jgoose.e4j.actions.ExportIStarMLAction;
/*    */ import br.unioeste.jgoose.e4j.actions.ImporStencilAction;
/*    */ import br.unioeste.jgoose.e4j.actions.ImportIStarMLAction;
/*    */ import br.unioeste.jgoose.e4j.actions.NewAction;
/*    */ import br.unioeste.jgoose.e4j.actions.OpenAction;
/*    */ import br.unioeste.jgoose.e4j.actions.PageSetupAction;
/*    */ import br.unioeste.jgoose.e4j.actions.PrintAction;
/*    */ import br.unioeste.jgoose.e4j.actions.SaveAction;
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import br.unioeste.jgoose.e4j.swing.BasicIStarEditor;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import javax.swing.JMenu;
/*    */ 
/*    */ public class FileJMenu
/*    */   extends JMenu
/*    */ {
/*    */   public FileJMenu(BasicGraphEditor editor)
/*    */   {
/* 22 */     super("File");
/* 23 */     add(editor.bind(mxResources.get("new"), new NewAction(), "/com/mxgraph/examples/swing/images/new.gif"));
/* 24 */     add(editor.bind(mxResources.get("openFile"), new OpenAction(), "/com/mxgraph/examples/swing/images/open.gif"));
/* 25 */     addSeparator();
/* 26 */     add(editor.bind(mxResources.get("importStencil"), new ImporStencilAction(), "/com/mxgraph/examples/swing/images/open.gif"));
/* 27 */     if ((editor instanceof BasicIStarEditor)) {
/* 28 */       String label = mxResources.get("exportIStarML", "Export iStarML");
/* 29 */       add(editor.bind(label, new ExportIStarMLAction()));
/* 30 */       String label2 = mxResources.get("importIStarML", "Import iStarML");
/* 31 */       add(editor.bind(label2, new ImportIStarMLAction()));
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 36 */     addSeparator();
/* 37 */     add(editor.bind(mxResources.get("save"), new SaveAction(false), "/com/mxgraph/examples/swing/images/save.gif"));
/* 38 */     add(editor.bind(mxResources.get("saveAs"), new SaveAction(true), "/com/mxgraph/examples/swing/images/saveas.gif"));
/*    */     
/* 40 */     addSeparator();
/* 41 */     add(editor.bind(mxResources.get("pageSetup"), new PageSetupAction(), "/com/mxgraph/examples/swing/images/pagesetup.gif"));
/* 42 */     add(editor.bind(mxResources.get("print"), new PrintAction(), "/com/mxgraph/examples/swing/images/print.gif"));
/*    */     
/* 44 */     addSeparator();
/* 45 */     add(editor.bind(mxResources.get("exit"), new ExitAction()));
/*    */   }
/*    */ }

