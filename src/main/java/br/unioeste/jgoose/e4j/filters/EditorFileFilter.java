/*    */ package br.unioeste.jgoose.e4j.filters;
/*    */ 
/*    */ import java.io.File;
/*    */ import javax.swing.filechooser.FileFilter;
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
/*    */ public class EditorFileFilter
/*    */   extends FileFilter
/*    */ {
/*    */   protected String desc;
/*    */   
/*    */   public EditorFileFilter(String description)
/*    */   {
/* 24 */     this.desc = description;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean accept(File file)
/*    */   {
/* 34 */     if (file.isDirectory()) {
/* 35 */       return true;
/*    */     }
/* 37 */     String filename = file.getName().toLowerCase();
/* 38 */     return (filename.endsWith(".xml")) || (filename.endsWith(".xml.gz"));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getDescription()
/*    */   {
/* 47 */     return this.desc;
/*    */   }
/*    */ }

