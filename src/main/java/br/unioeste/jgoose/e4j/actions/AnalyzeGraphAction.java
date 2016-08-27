/*     */ package br.unioeste.jgoose.e4j.actions;
/*     */ 
/*     */ import br.unioeste.jgoose.e4j.swing.menubar.AnalyzeType;
/*     */ import com.mxgraph.analysis.StructuralException;
/*     */ import com.mxgraph.analysis.mxAnalysisGraph;
/*     */ import com.mxgraph.analysis.mxGraphGenerator;
/*     */ import com.mxgraph.analysis.mxGraphProperties;
/*     */ import com.mxgraph.analysis.mxGraphStructure;
/*     */ import com.mxgraph.analysis.mxTraversal;
/*     */ import com.mxgraph.costfunction.mxCostFunction;
/*     */ import com.mxgraph.model.mxIGraphModel;
/*     */ import com.mxgraph.swing.mxGraphComponent;
/*     */ import com.mxgraph.view.mxGraph;
/*     */ import com.mxgraph.view.mxGraphView;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.AbstractAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnalyzeGraphAction
/*     */   extends AbstractAction
/*     */ {
/*     */   private static final long serialVersionUID = 6926170745240507985L;
/*     */   mxAnalysisGraph aGraph;
/*     */   protected AnalyzeType analyzeType;
/*     */   
/*     */   public AnalyzeGraphAction(AnalyzeType analyzeType, mxAnalysisGraph aGraph)
/*     */   {
/*  36 */     this.analyzeType = analyzeType;
/*  37 */     this.aGraph = aGraph;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*  42 */     if ((e.getSource() instanceof mxGraphComponent)) {
/*  43 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/*  44 */       mxGraph graph = graphComponent.getGraph();
/*  45 */       if (this.analyzeType == AnalyzeType.IS_CONNECTED) {
/*  46 */         boolean isConnected = mxGraphStructure.isConnected(this.aGraph);
/*  47 */         if (isConnected) {
/*  48 */           System.out.println("The graph is connected");
/*     */         } else {
/*  50 */           System.out.println("The graph is not connected");
/*     */         }
/*  52 */       } else if (this.analyzeType == AnalyzeType.IS_SIMPLE) {
/*  53 */         boolean isSimple = mxGraphStructure.isSimple(this.aGraph);
/*  54 */         if (isSimple) {
/*  55 */           System.out.println("The graph is simple");
/*     */         } else {
/*  57 */           System.out.println("The graph is not simple");
/*     */         }
/*  59 */       } else if (this.analyzeType == AnalyzeType.IS_CYCLIC_DIRECTED) {
/*  60 */         boolean isCyclicDirected = mxGraphStructure.isCyclicDirected(this.aGraph);
/*  61 */         if (isCyclicDirected) {
/*  62 */           System.out.println("The graph is cyclic directed");
/*     */         } else {
/*  64 */           System.out.println("The graph is acyclic directed");
/*     */         }
/*  66 */       } else if (this.analyzeType == AnalyzeType.IS_CYCLIC_UNDIRECTED) {
/*  67 */         boolean isCyclicUndirected = mxGraphStructure.isCyclicUndirected(this.aGraph);
/*  68 */         if (isCyclicUndirected) {
/*  69 */           System.out.println("The graph is cyclic undirected");
/*     */         } else {
/*  71 */           System.out.println("The graph is acyclic undirected");
/*     */         }
/*  73 */       } else if (this.analyzeType == AnalyzeType.COMPLEMENTARY) {
/*  74 */         graph.getModel().beginUpdate();
/*  75 */         mxGraphStructure.complementaryGraph(this.aGraph);
/*  76 */         mxGraphStructure.setDefaultGraphStyle(this.aGraph, true);
/*  77 */         graph.getModel().endUpdate();
/*  78 */       } else if (this.analyzeType == AnalyzeType.REGULARITY) {
/*     */         try {
/*  80 */           int regularity = mxGraphStructure.regularity(this.aGraph);
/*  81 */           System.out.println("Graph regularity is: " + regularity);
/*     */         } catch (StructuralException e1) {
/*  83 */           System.out.println("The graph is irregular");
/*     */         }
/*  85 */       } else if (this.analyzeType == AnalyzeType.COMPONENTS) {
/*  86 */         Object[][] components = mxGraphStructure.getGraphComponents(this.aGraph);
/*  87 */         mxIGraphModel model = this.aGraph.getGraph().getModel();
/*  88 */         for (int i = 0; i < components.length; i++) {
/*  89 */           System.out.print("Component " + i + " :");
/*  90 */           for (int j = 0; j < components[i].length; j++) {
/*  91 */             System.out.print(" " + model.getValue(components[i][j]));
/*     */           }
/*  93 */           System.out.println(".");
/*     */         }
/*  95 */         System.out.println("Number of components: " + components.length);
/*  96 */       } else if (this.analyzeType == AnalyzeType.MAKE_CONNECTED) {
/*  97 */         graph.getModel().beginUpdate();
/*  98 */         if (!mxGraphStructure.isConnected(this.aGraph)) {
/*  99 */           mxGraphStructure.makeConnected(this.aGraph);
/* 100 */           mxGraphStructure.setDefaultGraphStyle(this.aGraph, false);
/*     */         }
/* 102 */         graph.getModel().endUpdate();
/* 103 */       } else if (this.analyzeType == AnalyzeType.MAKE_SIMPLE) {
/* 104 */         mxGraphStructure.makeSimple(this.aGraph);
/* 105 */       } else if (this.analyzeType == AnalyzeType.IS_TREE) {
/* 106 */         boolean isTree = mxGraphStructure.isTree(this.aGraph);
/* 107 */         if (isTree) {
/* 108 */           System.out.println("The graph is a tree");
/*     */         } else {
/* 110 */           System.out.println("The graph is not a tree");
/*     */         }
/* 112 */       } else if (this.analyzeType == AnalyzeType.ONE_SPANNING_TREE) {
/*     */         try {
/* 114 */           graph.getModel().beginUpdate();
/* 115 */           this.aGraph.getGenerator().oneSpanningTree(this.aGraph, true, true);
/* 116 */           mxGraphStructure.setDefaultGraphStyle(this.aGraph, false);
/* 117 */           graph.getModel().endUpdate();
/*     */         } catch (StructuralException e1) {
/* 119 */           System.out.println("The graph must be simple and connected");
/*     */         }
/* 121 */       } else if (this.analyzeType == AnalyzeType.IS_DIRECTED) {
/* 122 */         boolean isDirected = mxGraphProperties.isDirected(this.aGraph.getProperties(), mxGraphProperties.DEFAULT_DIRECTED);
/* 123 */         if (isDirected) {
/* 124 */           System.out.println("The graph is directed.");
/*     */         } else {
/* 126 */           System.out.println("The graph is undirected.");
/*     */         }
/* 128 */       } else if (this.analyzeType == AnalyzeType.GET_CUT_VERTEXES) {
/* 129 */         Object[] cutVertices = mxGraphStructure.getCutVertices(this.aGraph);
/* 130 */         System.out.print("Cut vertices of the graph are: [");
/* 131 */         mxIGraphModel model = this.aGraph.getGraph().getModel();
/* 132 */         for (int i = 0; i < cutVertices.length; i++) {
/* 133 */           System.out.print(" " + model.getValue(cutVertices[i]));
/*     */         }
/* 135 */         System.out.println(" ]");
/* 136 */       } else if (this.analyzeType == AnalyzeType.GET_CUT_EDGES) {
/* 137 */         Object[] cutEdges = mxGraphStructure.getCutEdges(this.aGraph);
/* 138 */         System.out.print("Cut edges of the graph are: [");
/* 139 */         mxIGraphModel model = this.aGraph.getGraph().getModel();
/* 140 */         for (int i = 0; i < cutEdges.length; i++) {
/* 141 */           System.out.print(" " + Integer.parseInt((String)model.getValue(this.aGraph.getTerminal(cutEdges[i], true))) + "-" + Integer.parseInt((String)model.getValue(this.aGraph.getTerminal(cutEdges[i], false))));
/*     */         }
/* 143 */         System.out.println(" ]");
/* 144 */       } else if (this.analyzeType == AnalyzeType.GET_SOURCES) {
/*     */         try {
/* 146 */           Object[] sourceVertices = mxGraphStructure.getSourceVertices(this.aGraph);
/* 147 */           System.out.print("Source vertices of the graph are: [");
/* 148 */           mxIGraphModel model = this.aGraph.getGraph().getModel();
/* 149 */           for (int i = 0; i < sourceVertices.length; i++) {
/* 150 */             System.out.print(" " + model.getValue(sourceVertices[i]));
/*     */           }
/* 152 */           System.out.println(" ]");
/*     */         } catch (StructuralException e1) {
/* 154 */           System.out.println(e1);
/*     */         }
/* 156 */       } else if (this.analyzeType == AnalyzeType.GET_SINKS) {
/*     */         try {
/* 158 */           Object[] sinkVertices = mxGraphStructure.getSinkVertices(this.aGraph);
/* 159 */           System.out.print("Sink vertices of the graph are: [");
/* 160 */           mxIGraphModel model = this.aGraph.getGraph().getModel();
/* 161 */           for (int i = 0; i < sinkVertices.length; i++) {
/* 162 */             System.out.print(" " + model.getValue(sinkVertices[i]));
/*     */           }
/* 164 */           System.out.println(" ]");
/*     */         } catch (StructuralException e1) {
/* 166 */           System.out.println(e1);
/*     */         }
/* 168 */       } else if (this.analyzeType != AnalyzeType.PLANARITY)
/*     */       {
/* 170 */         if (this.analyzeType == AnalyzeType.IS_BICONNECTED) {
/* 171 */           boolean isBiconnected = mxGraphStructure.isBiconnected(this.aGraph);
/* 172 */           if (isBiconnected) {
/* 173 */             System.out.println("The graph is biconnected.");
/*     */           } else {
/* 175 */             System.out.println("The graph is not biconnected.");
/*     */           }
/* 177 */         } else if (this.analyzeType != AnalyzeType.GET_BICONNECTED)
/*     */         {
/* 179 */           if (this.analyzeType != AnalyzeType.SPANNING_TREE)
/*     */           {
/* 181 */             if (this.analyzeType == AnalyzeType.FLOYD_ROY_WARSHALL) {
/* 182 */               ArrayList<Object[][]> FWIresult = new ArrayList();
/*     */               try
/*     */               {
/* 185 */                 FWIresult = mxTraversal.floydRoyWarshall(this.aGraph);
/* 186 */                 Object[][] dist = (Object[][])FWIresult.get(0);
/* 187 */                 Object[][] paths = (Object[][])FWIresult.get(1);
/* 188 */                 Object[] vertices = this.aGraph.getChildVertices(this.aGraph.getGraph().getDefaultParent());
/* 189 */                 int vertexNum = vertices.length;
/* 190 */                 System.out.println("Distances are:");
/* 191 */                 for (int i = 0; i < vertexNum; i++) {
/* 192 */                   System.out.print("[");
/* 193 */                   for (int j = 0; j < vertexNum; j++) {
/* 194 */                     System.out.print(" " + Math.round(((Double)dist[i][j]).doubleValue() * 100.0D) / 100.0D);
/*     */                   }
/* 196 */                   System.out.println("] ");
/*     */                 }
/* 198 */                 System.out.println("Path info:");
/* 199 */                 mxCostFunction costFunction = this.aGraph.getGenerator().getCostFunction();
/* 200 */                 mxGraphView view = this.aGraph.getGraph().getView();
/* 201 */                 for (int i = 0; i < vertexNum; i++) {
/* 202 */                   System.out.print("[");
/* 203 */                   for (int j = 0; j < vertexNum; j++) {
/* 204 */                     if (paths[i][j] != null) {
/* 205 */                       System.out.print(" " + costFunction.getCost(view.getState(paths[i][j])));
/*     */                     } else {
/* 207 */                       System.out.print(" -");
/*     */                     }
/*     */                   }
/* 210 */                   System.out.println(" ]");
/*     */                 }
/*     */                 try {
/* 213 */                   Object[] path = mxTraversal.getWFIPath(this.aGraph, FWIresult, vertices[0], vertices[(vertexNum - 1)]);
/* 214 */                   System.out.print("The path from " + costFunction.getCost(view.getState(vertices[0])) + " to " + costFunction.getCost(view.getState(vertices[(vertexNum - 1)])) + " is:");
/* 215 */                   for (int i = 0; i < path.length; i++) {
/* 216 */                     System.out.print(" " + costFunction.getCost(view.getState(path[i])));
/*     */                   }
/* 218 */                   System.out.println();
/*     */                 } catch (StructuralException e1) {
/* 220 */                   System.out.println(e1);
/*     */                 }
/*     */               } catch (StructuralException e2) {
/* 223 */                 System.out.println(e2);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }
