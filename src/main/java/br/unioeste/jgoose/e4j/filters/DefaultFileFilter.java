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
/*    */ public class DefaultFileFilter
/*    */   extends FileFilter
/*    */ {
/*    */   protected String ext;
/*    */   protected String desc;
/*    */   
/*    */   public DefaultFileFilter(String extension, String description)
/*    */   {
/* 38 */     this.ext = extension.toLowerCase();
/* 39 */     this.desc = description;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean accept(File file)
/*    */   {
/* 51 */     return (file.isDirectory()) || (file.getName().toLowerCase().endsWith(this.ext));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getDescription()
/*    */   {
/* 61 */     return this.desc;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getExtension()
/*    */   {
/* 70 */     return this.ext;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setExtension(String extension)
/*    */   {
/* 79 */     this.ext = extension;
/*    */   }
/*    */ }

