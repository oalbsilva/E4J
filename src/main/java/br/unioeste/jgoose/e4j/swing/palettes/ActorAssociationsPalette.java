/*    */ package br.unioeste.jgoose.e4j.swing.palettes;
/*    */ 
/*    */ import br.unioeste.jgoose.util.IStarUtils;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JTabbedPane;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActorAssociationsPalette
/*    */   extends AbstractPalette
/*    */ {
/* 21 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */   public ActorAssociationsPalette(JTabbedPane libraryPane) {
/* 24 */     super(mxResources.get("Elements", "Actor Associations"), libraryPane);
/*    */     
/*    */ 
/* 27 */     Element element = IStarUtils.createIS_A();
/* 28 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/* 30 */     element = IStarUtils.createIS_PART_OF();
/* 31 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/* 33 */     element = IStarUtils.createPLAYS();
/* 34 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/* 36 */     element = IStarUtils.createCOVERS();
/* 37 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/* 39 */     element = IStarUtils.createOCCUPIES();
/* 40 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/* 42 */     element = IStarUtils.createINSTANCE_OF();
/* 43 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */   }
/*    */ }

