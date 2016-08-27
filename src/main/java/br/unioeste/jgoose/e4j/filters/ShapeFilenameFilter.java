/*    */ package br.unioeste.jgoose.e4j.filters;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FilenameFilter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShapeFilenameFilter
/*    */   implements FilenameFilter
/*    */ {
/* 12 */   public static ShapeFilenameFilter instance = new ShapeFilenameFilter();
/*    */   
/*    */   public boolean accept(File dir, String name)
/*    */   {
/* 16 */     return name.toLowerCase().endsWith(".shape");
/*    */   }
/*    */ }

