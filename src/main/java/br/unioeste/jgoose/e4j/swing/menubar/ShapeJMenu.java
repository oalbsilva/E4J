/*    */ package br.unioeste.jgoose.e4j.swing.menubar;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.actions.AlignCellsAction;
/*    */ import br.unioeste.jgoose.e4j.actions.AutosizeAction;
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import javax.swing.JMenu;
/*    */ 
/*    */ public class ShapeJMenu
/*    */   extends JMenu
/*    */ {
/*    */   public ShapeJMenu(BasicGraphEditor editor)
/*    */   {
/* 15 */     super("Shape");
/*    */     
/* 17 */     add(editor.bind(mxResources.get("home"), mxGraphActions.getHomeAction(), "/com/mxgraph/examples/swing/images/house.gif"));
/*    */     
/* 19 */     addSeparator();
/*    */     
/* 21 */     add(editor.bind(mxResources.get("exitGroup"), mxGraphActions.getExitGroupAction(), "/com/mxgraph/examples/swing/images/up.gif"));
/* 22 */     add(editor.bind(mxResources.get("enterGroup"), mxGraphActions.getEnterGroupAction(), "/com/mxgraph/examples/swing/images/down.gif"));
/*    */     
/*    */ 
/* 25 */     addSeparator();
/*    */     
/* 27 */     add(editor.bind(mxResources.get("group"), mxGraphActions.getGroupAction(), "/com/mxgraph/examples/swing/images/group.gif"));
/* 28 */     add(editor.bind(mxResources.get("ungroup"), mxGraphActions.getUngroupAction(), "/com/mxgraph/examples/swing/images/ungroup.gif"));
/*    */     
/*    */ 
/* 31 */     addSeparator();
/*    */     
/* 33 */     add(editor.bind(mxResources.get("removeFromGroup"), mxGraphActions.getRemoveFromParentAction()));
/*    */     
/* 35 */     add(editor.bind(mxResources.get("updateGroupBounds"), mxGraphActions.getUpdateGroupBoundsAction()));
/*    */     
/* 37 */     addSeparator();
/*    */     
/* 39 */     add(editor.bind(mxResources.get("collapse"), mxGraphActions.getCollapseAction(), "/com/mxgraph/examples/swing/images/collapse.gif"));
/*    */     
/* 41 */     add(editor.bind(mxResources.get("expand"), mxGraphActions.getExpandAction(), "/com/mxgraph/examples/swing/images/expand.gif"));
/*    */     
/* 43 */     addSeparator();
/*    */     
/* 45 */     add(editor.bind(mxResources.get("toBack"), mxGraphActions.getToBackAction(), "/com/mxgraph/examples/swing/images/toback.gif"));
/* 46 */     add(editor.bind(mxResources.get("toFront"), mxGraphActions.getToFrontAction(), "/com/mxgraph/examples/swing/images/tofront.gif"));
/*    */     
/*    */ 
/* 49 */     addSeparator();
/*    */     
/* 51 */     JMenu submenu = (JMenu)add(new JMenu(mxResources.get("align")));
/*    */     
/* 53 */     submenu.add(editor.bind(mxResources.get("left"), new AlignCellsAction("left"), "/com/mxgraph/examples/swing/images/alignleft.gif"));
/*    */     
/* 55 */     submenu.add(editor.bind(mxResources.get("center"), new AlignCellsAction("center"), "/com/mxgraph/examples/swing/images/aligncenter.gif"));
/*    */     
/* 57 */     submenu.add(editor.bind(mxResources.get("right"), new AlignCellsAction("right"), "/com/mxgraph/examples/swing/images/alignright.gif"));
/*    */     
/*    */ 
/* 60 */     submenu.addSeparator();
/*    */     
/* 62 */     submenu.add(editor.bind(mxResources.get("top"), new AlignCellsAction("top"), "/com/mxgraph/examples/swing/images/aligntop.gif"));
/*    */     
/* 64 */     submenu.add(editor.bind(mxResources.get("middle"), new AlignCellsAction("middle"), "/com/mxgraph/examples/swing/images/alignmiddle.gif"));
/*    */     
/* 66 */     submenu.add(editor.bind(mxResources.get("bottom"), new AlignCellsAction("bottom"), "/com/mxgraph/examples/swing/images/alignbottom.gif"));
/*    */     
/*    */ 
/* 69 */     addSeparator();
/*    */     
/* 71 */     add(editor.bind(mxResources.get("autosize"), new AutosizeAction()));
/*    */   }
/*    */ }

