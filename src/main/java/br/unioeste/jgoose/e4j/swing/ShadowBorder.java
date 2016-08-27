/*    */ package br.unioeste.jgoose.e4j.swing;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Insets;
/*    */ import java.io.Serializable;
/*    */ import javax.swing.border.Border;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShadowBorder
/*    */   implements Border, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6854989457150641240L;
/*    */   private Insets insets;
/* 32 */   public static ShadowBorder sharedInstance = new ShadowBorder();
/*    */   
/*    */   private ShadowBorder() {
/* 35 */     this.insets = new Insets(0, 0, 2, 2);
/*    */   }
/*    */   
/*    */   public Insets getBorderInsets(Component c)
/*    */   {
/* 40 */     return this.insets;
/*    */   }
/*    */   
/*    */   public boolean isBorderOpaque()
/*    */   {
/* 45 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
/*    */   {
/* 51 */     Color bg = c.getBackground();
/*    */     
/* 53 */     if (c.getParent() != null) {
/* 54 */       bg = c.getParent().getBackground();
/*    */     }
/*    */     
/* 57 */     if (bg != null) {
/* 58 */       Color mid = bg.darker();
/* 59 */       Color edge = average(mid, bg);
/*    */       
/* 61 */       g.setColor(bg);
/* 62 */       g.drawLine(0, h - 2, w, h - 2);
/* 63 */       g.drawLine(0, h - 1, w, h - 1);
/* 64 */       g.drawLine(w - 2, 0, w - 2, h);
/* 65 */       g.drawLine(w - 1, 0, w - 1, h);
/*    */       
/*    */ 
/* 68 */       g.setColor(mid);
/* 69 */       g.drawLine(1, h - 2, w - 2, h - 2);
/* 70 */       g.drawLine(w - 2, 1, w - 2, h - 2);
/*    */       
/* 72 */       g.setColor(edge);
/* 73 */       g.drawLine(2, h - 1, w - 2, h - 1);
/* 74 */       g.drawLine(w - 1, 2, w - 1, h - 2);
/*    */     }
/*    */   }
/*    */   
/*    */   private static Color average(Color c1, Color c2) {
/* 79 */     int red = c1.getRed() + (c2.getRed() - c1.getRed()) / 2;
/* 80 */     int green = c1.getGreen() + (c2.getGreen() - c1.getGreen()) / 2;
/* 81 */     int blue = c1.getBlue() + (c2.getBlue() - c1.getBlue()) / 2;
/* 82 */     return new Color(red, green, blue);
/*    */   }
/*    */   
/*    */   public static ShadowBorder getSharedInstance() {
/* 86 */     return sharedInstance;
/*    */   }
/*    */ }
