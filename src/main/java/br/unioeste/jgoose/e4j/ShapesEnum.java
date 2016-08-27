/*    */ package br.unioeste.jgoose.e4j;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ShapesEnum
/*    */ {
/* 12 */   RECTANGLE("rectangle"), 
/* 13 */   ELLIPSE("ellipse"), 
/* 14 */   DOUBLE_ELLIPSE("doubleEllipse"), 
/* 15 */   RHOMBUS("rhombus"), 
/* 16 */   LINE("line"), 
/* 17 */   IMAGE("image"), 
/* 18 */   ARROW("arrow"), 
/* 19 */   CURVE("curve"), 
/* 20 */   LABEL("label"), 
/* 21 */   CILINDER("cylinder"), 
/* 22 */   SWIMLANE("swimlane"), 
/* 23 */   CONNECTOR("connector"), 
/* 24 */   ACTOR("actor"), 
/* 25 */   CLOUD("cloud"), 
/* 26 */   TRIANGLE("triangle"), 
/* 27 */   HEXAGON("hexagon");
/*    */   
/*    */   public String mxShapeConstantValue;
/*    */   
/*    */   private ShapesEnum(String mxShapeConstantValue) {
/* 32 */     this.mxShapeConstantValue = mxShapeConstantValue;
/*    */   }
/*    */ }
