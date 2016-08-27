/*    */ package br.unioeste.jgoose.e4j.shape;
/*    */ 
/*    */ import com.mxgraph.canvas.mxGraphics2DCanvas;
/*    */ import com.mxgraph.model.mxCell;
/*    */ import com.mxgraph.shape.mxStencilShape;
/*    */ import com.mxgraph.view.mxCellState;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Stroke;
/*    */ import org.w3c.dom.Document;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActorShape
/*    */   extends mxStencilShape
/*    */ {
/*    */   public ActorShape() {}
/*    */   
/*    */   public ActorShape(Document document)
/*    */   {
/* 25 */     super(document);
/*    */   }
/*    */   
/*    */   public ActorShape(String shapeXml) {
/* 29 */     super(shapeXml);
/*    */   }
/*    */   
/*    */   public void paintNode(mxGraphics2DCanvas canvas, mxCellState state, mxStencilShape.svgShape shape, double widthRatio, double heightRatio)
/*    */   {
/* 34 */     Object c = state.getCell();
/* 35 */     if ((c instanceof mxCell)) {
/* 36 */       mxCell cell = (mxCell)c;
/*    */       
/* 38 */       if ((cell.getChildCount() > 0) && (!cell.isCollapsed())) {
/* 39 */         Stroke dashed = new BasicStroke(1.0F, 0, 0, 10.0F, new float[] { 10.0F }, 0.0F);
/*    */         
/*    */ 
/*    */ 
/* 43 */         canvas.getGraphics().setColor(Color.GRAY);
/* 44 */         canvas.getGraphics().setStroke(dashed);
/*    */         
/* 46 */         int w = (int)state.getWidth();
/* 47 */         int h = (int)state.getHeight();
/* 48 */         canvas.getGraphics().drawOval(0, 0, w, h);
/*    */         
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 55 */         return;
/*    */       }
/*    */     }
/* 58 */     super.paintNode(canvas, state, shape, widthRatio, heightRatio);
/*    */   }
/*    */   
/*    */   public void paintShape(mxGraphics2DCanvas canvas, mxCellState state)
/*    */   {
/* 63 */     super.paintShape(canvas, state);
/*    */   }
/*    */ }
