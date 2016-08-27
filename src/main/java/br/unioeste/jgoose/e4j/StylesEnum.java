/*    */ package br.unioeste.jgoose.e4j;
/*    */ 
/*    */ import com.mxgraph.util.mxConstants;
/*    */ import com.mxgraph.util.mxUtils;
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum StylesEnum
/*    */ {
/* 13 */   OPACITY(new Object[] { mxConstants.STYLE_OPACITY, Double.valueOf(50.0D) }), 
/* 14 */   TEXT_OPACITY(new Object[] { mxConstants.STYLE_TEXT_OPACITY, Double.valueOf(50.0D) }), 
/* 15 */   OVERFLOW_1(new Object[] { mxConstants.STYLE_OVERFLOW, "visible" }), 
/* 16 */   OVERFLOW_2(new Object[] { mxConstants.STYLE_OVERFLOW, "hidden" }), 
/* 17 */   OVERFLOW_3(new Object[] { mxConstants.STYLE_OVERFLOW, "fill" }), 
/* 18 */   ROTATION(new Object[] { mxConstants.STYLE_ROTATION, Integer.valueOf(45) }), 
/* 19 */   FILLCOLOR(new Object[] { mxConstants.STYLE_FILLCOLOR, mxUtils.getHexColorString(Color.RED) }), 
/* 20 */   GRADIENTCOLOR(new Object[] { mxConstants.STYLE_GRADIENTCOLOR, mxUtils.getHexColorString(Color.BLUE) }), 
/* 21 */   GRADIENT_DIRECTION(new Object[] { mxConstants.STYLE_GRADIENT_DIRECTION, "east", mxConstants.STYLE_GRADIENTCOLOR, mxUtils.getHexColorString(Color.YELLOW) }), 
/* 22 */   STROKECOLOR(new Object[] { mxConstants.STYLE_STROKECOLOR, mxUtils.getHexColorString(Color.GREEN) }), 
/* 23 */   STROKEWIDTH(new Object[] { mxConstants.STYLE_STROKEWIDTH, Integer.valueOf(5) }), 
/* 24 */   ALIGN(new Object[] { mxConstants.STYLE_ALIGN, "left" }), 
/* 25 */   VERTICAL_ALIGN(new Object[] { mxConstants.STYLE_VERTICAL_ALIGN, "bottom" }), 
/* 26 */   LABEL_POSITION(new Object[] { mxConstants.STYLE_LABEL_POSITION, "left" }), 
/* 27 */   VERTICAL_LABEL_POSITION(new Object[] { mxConstants.STYLE_VERTICAL_LABEL_POSITION, "bottom" }), 
/* 28 */   GLASS(new Object[] { mxConstants.STYLE_GLASS, Integer.valueOf(1) }), 
/* 29 */   NOLABEL(new Object[] { mxConstants.STYLE_NOLABEL, Integer.valueOf(1) }), 
/* 30 */   LABEL_BACKGROUNDCOLOR(new Object[] { mxConstants.STYLE_LABEL_BACKGROUNDCOLOR, mxUtils.getHexColorString(Color.CYAN) }), 
/* 31 */   LABEL_BORDERCOLOR(new Object[] { mxConstants.STYLE_LABEL_BORDERCOLOR, mxUtils.getHexColorString(Color.PINK) }), 
/* 32 */   SHADOW(new Object[] { mxConstants.STYLE_SHADOW, Boolean.valueOf(true) }), 
/* 33 */   DASHED(new Object[] { mxConstants.STYLE_DASHED, Boolean.valueOf(true) }), 
/* 34 */   ROUNDED(new Object[] { mxConstants.STYLE_ROUNDED, Boolean.valueOf(true) }), 
/* 35 */   HORIZONTAL(new Object[] { mxConstants.STYLE_HORIZONTAL, Boolean.valueOf(false) }), 
/* 36 */   FONTCOLOR(new Object[] { mxConstants.STYLE_FONTCOLOR, mxUtils.getHexColorString(Color.ORANGE) }), 
/* 37 */   FONTFAMILY(new Object[] { mxConstants.STYLE_FONTFAMILY, "Times New Roman" }), 
/* 38 */   FONTSIZE(new Object[] { mxConstants.STYLE_FONTSIZE, Integer.valueOf(15) }), 
/* 39 */   FONTSTYLE(new Object[] { mxConstants.STYLE_FONTSTYLE, Integer.valueOf(1) });
/*    */   
/*    */   public String mxStyle;
/*    */   
/* 43 */   private StylesEnum(Object... values) { this.mxStyle = "";
/* 44 */     for (int i = 0; i < values.length; i++) {
/* 45 */       if (i % 2 == 0) {
/* 46 */         this.mxStyle = (this.mxStyle + values[i] + "=");
/*    */       } else {
/* 48 */         this.mxStyle = (this.mxStyle + values[i] + ";");
/*    */       }
/*    */     }
/*    */   }
/*    */ }
