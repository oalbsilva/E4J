/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetLabelPositionAction
/*    */   extends AbstractAction
/*    */ {
/*    */   protected String labelPosition;
/*    */   protected String alignment;
/*    */   
/*    */   public SetLabelPositionAction(String labelPosition, String alignment)
/*    */   {
/* 21 */     this.labelPosition = labelPosition;
/* 22 */     this.alignment = alignment;
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public void actionPerformed(java.awt.event.ActionEvent e)
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: invokestatic 4	com/mxgraph/swing/util/mxGraphActions:getGraph	(Ljava/awt/event/ActionEvent;)Lcom/mxgraph/view/mxGraph;
/*    */     //   4: astore_2
/*    */     //   5: aload_2
/*    */     //   6: ifnull +130 -> 136
/*    */     //   9: aload_2
/*    */     //   10: invokevirtual 5	com/mxgraph/view/mxGraph:isSelectionEmpty	()Z
/*    */     //   13: ifne +123 -> 136
/*    */     //   16: aload_2
/*    */     //   17: invokevirtual 6	com/mxgraph/view/mxGraph:getModel	()Lcom/mxgraph/model/mxIGraphModel;
/*    */     //   20: invokeinterface 7 1 0
/*    */     //   25: aload_0
/*    */     //   26: getfield 2	br/unioeste/jgoose/e4j/actions/SetLabelPositionAction:labelPosition	Ljava/lang/String;
/*    */     //   29: ldc 9
/*    */     //   31: invokevirtual 10	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   34: ifne +27 -> 61
/*    */     //   37: aload_0
/*    */     //   38: getfield 2	br/unioeste/jgoose/e4j/actions/SetLabelPositionAction:labelPosition	Ljava/lang/String;
/*    */     //   41: ldc 11
/*    */     //   43: invokevirtual 10	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   46: ifne +15 -> 61
/*    */     //   49: aload_0
/*    */     //   50: getfield 2	br/unioeste/jgoose/e4j/actions/SetLabelPositionAction:labelPosition	Ljava/lang/String;
/*    */     //   53: ldc 12
/*    */     //   55: invokevirtual 10	java/lang/String:equals	(Ljava/lang/Object;)Z
/*    */     //   58: ifeq +30 -> 88
/*    */     //   61: aload_2
/*    */     //   62: getstatic 13	com/mxgraph/util/mxConstants:STYLE_LABEL_POSITION	Ljava/lang/String;
/*    */     //   65: aload_0
/*    */     //   66: getfield 2	br/unioeste/jgoose/e4j/actions/SetLabelPositionAction:labelPosition	Ljava/lang/String;
/*    */     //   69: invokevirtual 14	com/mxgraph/view/mxGraph:setCellStyles	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/Object;
/*    */     //   72: pop
/*    */     //   73: aload_2
/*    */     //   74: getstatic 15	com/mxgraph/util/mxConstants:STYLE_ALIGN	Ljava/lang/String;
/*    */     //   77: aload_0
/*    */     //   78: getfield 3	br/unioeste/jgoose/e4j/actions/SetLabelPositionAction:alignment	Ljava/lang/String;
/*    */     //   81: invokevirtual 14	com/mxgraph/view/mxGraph:setCellStyles	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/Object;
/*    */     //   84: pop
/*    */     //   85: goto +27 -> 112
/*    */     //   88: aload_2
/*    */     //   89: getstatic 16	com/mxgraph/util/mxConstants:STYLE_VERTICAL_LABEL_POSITION	Ljava/lang/String;
/*    */     //   92: aload_0
/*    */     //   93: getfield 2	br/unioeste/jgoose/e4j/actions/SetLabelPositionAction:labelPosition	Ljava/lang/String;
/*    */     //   96: invokevirtual 14	com/mxgraph/view/mxGraph:setCellStyles	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/Object;
/*    */     //   99: pop
/*    */     //   100: aload_2
/*    */     //   101: getstatic 17	com/mxgraph/util/mxConstants:STYLE_VERTICAL_ALIGN	Ljava/lang/String;
/*    */     //   104: aload_0
/*    */     //   105: getfield 3	br/unioeste/jgoose/e4j/actions/SetLabelPositionAction:alignment	Ljava/lang/String;
/*    */     //   108: invokevirtual 14	com/mxgraph/view/mxGraph:setCellStyles	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/Object;
/*    */     //   111: pop
/*    */     //   112: aload_2
/*    */     //   113: invokevirtual 6	com/mxgraph/view/mxGraph:getModel	()Lcom/mxgraph/model/mxIGraphModel;
/*    */     //   116: invokeinterface 18 1 0
/*    */     //   121: goto +15 -> 136
/*    */     //   124: astore_3
/*    */     //   125: aload_2
/*    */     //   126: invokevirtual 6	com/mxgraph/view/mxGraph:getModel	()Lcom/mxgraph/model/mxIGraphModel;
/*    */     //   129: invokeinterface 18 1 0
/*    */     //   134: aload_3
/*    */     //   135: athrow
/*    */     //   136: return
/*    */     // Line number table:
/*    */     //   Java source line #27	-> byte code offset #0
/*    */     //   Java source line #28	-> byte code offset #5
/*    */     //   Java source line #29	-> byte code offset #16
/*    */     //   Java source line #32	-> byte code offset #25
/*    */     //   Java source line #33	-> byte code offset #61
/*    */     //   Java source line #34	-> byte code offset #73
/*    */     //   Java source line #36	-> byte code offset #88
/*    */     //   Java source line #37	-> byte code offset #100
/*    */     //   Java source line #40	-> byte code offset #112
/*    */     //   Java source line #41	-> byte code offset #121
/*    */     //   Java source line #40	-> byte code offset #124
/*    */     //   Java source line #43	-> byte code offset #136
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	137	0	this	SetLabelPositionAction
/*    */     //   0	137	1	e	java.awt.event.ActionEvent
/*    */     //   4	122	2	graph	com.mxgraph.view.mxGraph
/*    */     //   124	11	3	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   25	112	124	finally
/*    */   }
/*    */ }

