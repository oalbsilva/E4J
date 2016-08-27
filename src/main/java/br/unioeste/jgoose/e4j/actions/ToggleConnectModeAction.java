/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.handler.mxConnectionHandler;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class ToggleConnectModeAction extends AbstractAction
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 12 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 13 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 14 */       mxConnectionHandler handler = graphComponent.getConnectionHandler();
/* 15 */       handler.setHandleEnabled(!handler.isHandleEnabled());
/*    */     }
/*    */   }
/*    */ }


