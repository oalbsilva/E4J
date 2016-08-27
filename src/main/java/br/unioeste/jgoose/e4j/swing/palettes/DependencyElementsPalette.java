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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DependencyElementsPalette
/*    */   extends AbstractPalette
/*    */ {
/* 23 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */   public DependencyElementsPalette(JTabbedPane libraryPane) {
/* 26 */     super(mxResources.get("Elements", "Dependency Elements"), libraryPane);
/*    */     
/* 28 */     File shapesFolder = new File("resources/shapes/elements/");
/* 29 */     File[] files = shapesFolder.listFiles(ShapeFilenameFilter.instance);
/* 30 */     if ((files == null) || (files.length < 1)) {
/* 31 */       CONSOLE.info("no shape found. " + shapesFolder.getAbsolutePath());
/* 32 */       return;
/*    */     }
/* 34 */     for (File f : files) {
/*    */       try
/*    */       {
/* 37 */         String nodeXml = mxUtils.readFile(f.getAbsolutePath());
/* 38 */         if (!f.getName().matches("actor.shape|actor_agent.shape|actor_position.shape|actor_role.shape")) {
/* 39 */           ImporStencilAction.addStencilShape(this, nodeXml, f.getParent() + File.separator);
/*    */         }
/*    */       } catch (IOException ex) {
/* 42 */         CONSOLE.fatal(ex);
/*    */       }
/*    */     }
/*    */   }
/*    */ }
