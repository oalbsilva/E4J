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
/*    */ public class LinksPalette
/*    */   extends AbstractPalette
/*    */ {
/* 20 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */   public LinksPalette(JTabbedPane libraryPane) {
/* 23 */     super(mxResources.get("Edges", "Conectores"), libraryPane);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 29 */     mxMarkerRegistry.registerMarker("dependency", new DependencyMarker());
/* 30 */     Element element = IStarUtils.createDepndency();
/* 31 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/dependency.png"), "straight;endArrow=dependency;noLabel=1", 80, 80, element);
/*    */     
/*    */ 
/*    */ 
/* 35 */     element = IStarUtils.createIS_A();
/* 36 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;", 80, 80, element);
/*    */     
/* 38 */     element = IStarUtils.createIS_PART_OF();
/* 39 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;", 80, 80, element);
/*    */     
/* 41 */     element = IStarUtils.createPLAYS();
/* 42 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;", 80, 80, element);
/*    */     
/* 44 */     element = IStarUtils.createCOVERS();
/* 45 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;", 80, 80, element);
/*    */     
/* 47 */     element = IStarUtils.createOCCUPIES();
/* 48 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;", 80, 80, element);
/*    */     
/* 50 */     element = IStarUtils.createINSTANCE_OF();
/* 51 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;", 80, 80, element);
/*    */     
/*    */ 
/* 54 */     element = IStarUtils.createMeansEnd();
/* 55 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/meansend.png"), "straight;", 80, 80, element);
/*    */     
/* 57 */     element = IStarUtils.createDecomposition();
/* 58 */     mxMarkerRegistry.registerMarker("decomposition", new DecompositionMarker());
/* 59 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/decomposition.png"), "straight;endArrow=decomposition;noLabel=1", 80, 80, element);
/*    */     
/*    */ 
/*    */ 
/* 63 */     element = IStarUtils.createContributionMake();
/* 64 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/* 65 */     element = IStarUtils.createContributionSomePlus();
/* 66 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/* 67 */     element = IStarUtils.createContributionHelp();
/* 68 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/*    */     
/* 70 */     element = IStarUtils.createContributionBreak();
/* 71 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/* 72 */     element = IStarUtils.createContributionSomeMinus();
/* 73 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/* 74 */     element = IStarUtils.createContributionHurt();
/* 75 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/*    */     
/* 77 */     element = IStarUtils.createContributionUnknown();
/* 78 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/* 79 */     element = IStarUtils.createContributionAnd();
/* 80 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/* 81 */     element = IStarUtils.createContributionOr();
/* 82 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open", 80, 80, element);
/*    */   }
/*    */ }

