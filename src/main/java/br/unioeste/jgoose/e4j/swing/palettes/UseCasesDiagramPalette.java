/*    */ package br.unioeste.jgoose.e4j.swing.palettes;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.actions.ImporStencilAction;
/*    */ import br.unioeste.jgoose.e4j.filters.ShapeFilenameFilter;
/*    */ import br.unioeste.jgoose.util.IStarUtils;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.util.mxUtils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JTabbedPane;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UseCasesDiagramPalette
/*    */   extends AbstractPalette
/*    */ {
/* 21 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */   public UseCasesDiagramPalette(JTabbedPane libraryPane) {
/* 24 */     super(mxResources.get("Edges", "Use Cases Diagram"), libraryPane);
/*    */     
/*    */ 
/* 27 */     File shapesFolder = new File("resources/shapes/use cases diagram/");
/* 28 */     File[] files = shapesFolder.listFiles(ShapeFilenameFilter.instance);
/* 29 */     if ((files == null) || (files.length < 1)) {
/* 30 */       CONSOLE.info("no shape found. " + shapesFolder.getAbsolutePath());
/* 31 */       return;
/*    */     }
/*    */     
/* 34 */     for (File f : files) {
/*    */       try
/*    */       {
/* 37 */         String nodeXml = mxUtils.readFile(f.getAbsolutePath());
/* 38 */         ImporStencilAction.addStencilShape(this, nodeXml, f.getParent() + File.separator);
/*    */       } catch (IOException ex) {
/* 40 */         CONSOLE.fatal(ex);
/*    */       }
/*    */     }
/*    */     
/* 44 */     Element element = IStarUtils.createAssociation();
/* 45 */     addEdgeTemplate("Association", new ImageIcon("resources/shapes/use cases diagram/association.png"), "straight;endArrow=none;noLabel=1;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 46 */     element = IStarUtils.createGeneralization();
/* 47 */     addEdgeTemplate("Generalization", new ImageIcon("resources/shapes/use cases diagram/generalization.png"), "straight;noLabel=1;endArrow=block;endFill=0;endSize=14;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 48 */     element = IStarUtils.createInclude();
/* 49 */     addEdgeTemplate("Include", new ImageIcon("resources/shapes/use cases diagram/include.png"), "straight;dashed=1;endArrow=open;endSize=14;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 50 */     element = IStarUtils.createExtend();
/* 51 */     addEdgeTemplate("Extend", new ImageIcon("resources/shapes/use cases diagram/extend.png"), "straight;dashed=1;endArrow=open;endSize=14;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */   }
/*    */ }
