/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.io.mxCodec;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxUtils;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.net.URL;
/*    */ import org.w3c.dom.Document;
/*    */ 
/*    */ public class StylesheetAction extends javax.swing.AbstractAction
/*    */ {
/*    */   protected String stylesheet;
/*    */   
/*    */   public StylesheetAction(String stylesheet)
/*    */   {
/* 17 */     this.stylesheet = stylesheet;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 22 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 23 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 24 */       mxGraph graph = graphComponent.getGraph();
/* 25 */       mxCodec codec = new mxCodec();
/* 26 */       Document doc = mxUtils.loadDocument(EditorActions.class.getResource(this.stylesheet).toString());
/* 27 */       if (doc != null) {
/* 28 */         codec.decode(doc.getDocumentElement(), graph.getStylesheet());
/* 29 */         graph.refresh();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

