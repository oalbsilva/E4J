/*    */ package br.unioeste.jgoose.e4j.swing.listeners;
/*    */ 
/*    */ import com.mxgraph.model.mxCell;
/*    */ import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
/*    */ import com.mxgraph.util.mxEventSource.mxIEventListener;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CollapsingExpandingListener
/*    */   implements mxEventSource.mxIEventListener
/*    */ {
/*    */   public void invoke(Object o, mxEventObject eo)
/*    */   {
/* 23 */     mxGraph graph = (mxGraph)o;
/* 24 */     mxCell cell = (mxCell)graph.getSelectionCell();
/*    */     
/* 26 */     if (((Boolean)eo.getProperty("collapse")).booleanValue()) {
/* 27 */       cell.setStyle(cell.getStyle().replaceAll(";noLabel=1", ";noLabel=0"));
/*    */     } else {
/* 29 */       cell.setStyle(cell.getStyle().replaceAll(";noLabel=0", ";noLabel=1"));
/*    */     }
/*    */   }
/*    */ }

