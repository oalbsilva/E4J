/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JCheckBoxMenuItem;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ToggleGridItem
/*    */   extends JCheckBoxMenuItem
/*    */ {
/*    */   public ToggleGridItem(final BasicGraphEditor editor, String name)
/*    */   {
/* 17 */     super(name);
/* 18 */     setSelected(true);
/* 19 */     addActionListener(new ActionListener()
/*    */     {
/*    */       public void actionPerformed(ActionEvent e)
/*    */       {
/* 23 */         mxGraphComponent graphComponent = editor.getGraphComponent();
/* 24 */         mxGraph graph = graphComponent.getGraph();
/* 25 */         boolean enabled = !graph.isGridEnabled();
/* 26 */         graph.setGridEnabled(enabled);
/* 27 */         graphComponent.setGridVisible(enabled);
/* 28 */         graphComponent.repaint();
/* 29 */         ToggleGridItem.this.setSelected(enabled);
/*    */       }
/*    */     });
/*    */   }
/*    */ }

