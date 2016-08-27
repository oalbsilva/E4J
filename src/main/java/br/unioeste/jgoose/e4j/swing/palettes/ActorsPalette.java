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
/*    */ 
/*    */ public class ActorsPalette
/*    */   extends AbstractPalette
/*    */ {
/* 24 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */   public ActorsPalette(JTabbedPane libraryPane) {
/* 27 */     super(mxResources.get("Elements", "Actors"), libraryPane);
/*    */     
/*    */ 
/* 30 */     File shapesFolder = new File("resources/shapes/elements/");
/* 31 */     File[] files = shapesFolder.listFiles(ShapeFilenameFilter.instance);
/* 32 */     if ((files == null) || (files.length < 1)) {
/* 33 */       CONSOLE.info("no shape found. " + shapesFolder.getAbsolutePath());
/* 34 */       return;
/*    */     }
/* 36 */     for (File f : files) {
/*    */       try
/*    */       {
/* 39 */         String nodeXml = mxUtils.readFile(f.getAbsolutePath());
/* 40 */         if (f.getName().matches("actor.shape|actor_agent.shape|actor_position.shape|actor_role.shape")) {
/* 41 */           ImporStencilAction.addStencilShape(this, nodeXml, f.getParent() + File.separator);
/*    */         }
/*    */       } catch (IOException ex) {
/* 44 */         CONSOLE.fatal(ex);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

