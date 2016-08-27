/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.swing.handler.mxConnectionHandler;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JCheckBoxMenuItem;
/*    */ 
/*    */ public class ToggleCreateTargetItem extends JCheckBoxMenuItem
/*    */ {
/*    */   public ToggleCreateTargetItem(final BasicGraphEditor editor, String name)
/*    */   {
/* 14 */     super(name);
/* 15 */     setSelected(true);
/* 16 */     addActionListener(new ActionListener()
/*    */     {
/*    */       public void actionPerformed(ActionEvent e) {
/* 19 */         mxGraphComponent graphComponent = editor.getGraphComponent();
/* 20 */         if (graphComponent != null) {
/* 21 */           mxConnectionHandler handler = graphComponent.getConnectionHandler();
/* 22 */           handler.setCreateTarget(!handler.isCreateTarget());
/* 23 */           ToggleCreateTargetItem.this.setSelected(handler.isCreateTarget());
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */ }

