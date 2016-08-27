/*    */ package br.unioeste.jgoose.e4j.swing.palettes;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.actions.ImporStencilAction;
/*    */ import br.unioeste.jgoose.e4j.filters.ShapeFilenameFilter;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.util.mxUtils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.swing.JTabbedPane;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ElementsPalette
/*    */   extends AbstractPalette
/*    */ {
/* 18 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */   public ElementsPalette(JTabbedPane libraryPane) {
/* 21 */     super(mxResources.get("Elements", "Elementos"), libraryPane);
/*    */     
/*    */ 
/* 24 */     File shapesFolder = new File("resources/shapes/elements/");
/* 25 */     File[] files = shapesFolder.listFiles(ShapeFilenameFilter.instance);
/* 26 */     if ((files == null) || (files.length < 1)) {
/* 27 */       CONSOLE.info("no shape found. " + shapesFolder.getAbsolutePath());
/* 28 */       return;
/*    */     }
/*    */     
/* 31 */     for (File f : files) {
/*    */       try
/*    */       {
/* 34 */         String nodeXml = mxUtils.readFile(f.getAbsolutePath());
/* 35 */         ImporStencilAction.addStencilShape(this, nodeXml, f.getParent() + File.separator);
/*    */       } catch (IOException ex) {
/* 37 */         CONSOLE.fatal(ex);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

