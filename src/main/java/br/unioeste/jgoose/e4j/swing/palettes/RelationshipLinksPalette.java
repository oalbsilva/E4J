/*    */ package br.unioeste.jgoose.e4j.swing.palettes;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.shape.DecompositionMarker;
/*    */ import br.unioeste.jgoose.e4j.shape.DependencyMarker;
/*    */ import br.unioeste.jgoose.util.IStarUtils;
/*    */ import com.mxgraph.shape.mxMarkerRegistry;
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
/*    */ public class RelationshipLinksPalette
/*    */   extends AbstractPalette
/*    */ {
/* 24 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */   public RelationshipLinksPalette(JTabbedPane libraryPane) {
/* 27 */     super(mxResources.get("Elements", "Relationship Links"), libraryPane);
/* 28 */     mxMarkerRegistry.registerMarker("dependency", new DependencyMarker());
/* 29 */     Element element = IStarUtils.createDepndency();
/* 30 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/dependency.png"), "straight;endArrow=dependency;noLabel=1;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/*    */ 
/* 33 */     element = IStarUtils.createMeansEnd();
/* 34 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/meansend.png"), "straight;noLabel=1;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/* 36 */     element = IStarUtils.createDecomposition();
/* 37 */     mxMarkerRegistry.registerMarker("decomposition", new DecompositionMarker());
/* 38 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/decomposition.png"), "straight;endArrow=decomposition;noLabel=1;", 80, 80, element);
/*    */   }
/*    */ }

