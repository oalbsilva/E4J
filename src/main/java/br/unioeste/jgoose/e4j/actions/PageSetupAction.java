/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.print.PageFormat;
/*    */ import java.awt.print.PrinterJob;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class PageSetupAction extends AbstractAction
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 13 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 14 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 15 */       PrinterJob pj = PrinterJob.getPrinterJob();
/* 16 */       PageFormat format = pj.pageDialog(graphComponent.getPageFormat());
/* 17 */       if (format != null) {
/* 18 */         graphComponent.setPageFormat(format);
/* 19 */         graphComponent.zoomAndCenter();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

