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
/*    */ public class ContributionLinksPalette
/*    */   extends AbstractPalette
/*    */ {
/* 21 */   private static final Logger CONSOLE = Logger.getLogger("console");
/*    */   
/*    */   public ContributionLinksPalette(JTabbedPane libraryPane) {
/* 24 */     super(mxResources.get("Elements", "Contribution Links"), libraryPane);
/*    */     
/*    */ 
/*    */ 
/* 28 */     Element element = IStarUtils.createContributionMake();
/* 29 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 30 */     element = IStarUtils.createContributionSomePlus();
/* 31 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 32 */     element = IStarUtils.createContributionHelp();
/* 33 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/* 35 */     element = IStarUtils.createContributionBreak();
/* 36 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 37 */     element = IStarUtils.createContributionSomeMinus();
/* 38 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 39 */     element = IStarUtils.createContributionHurt();
/* 40 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */     
/* 42 */     element = IStarUtils.createContributionUnknown();
/* 43 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 44 */     element = IStarUtils.createContributionAnd();
/* 45 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/* 46 */     element = IStarUtils.createContributionOr();
/* 47 */     addEdgeTemplate(element.getAttribute("label"), new ImageIcon("resources/shapes/elements/connector.png"), "straight;endArrow=open;shape=curvedEdge;edgeStyle=curvedEdgeStyle", 80, 80, element);
/*    */   }
/*    */ }
