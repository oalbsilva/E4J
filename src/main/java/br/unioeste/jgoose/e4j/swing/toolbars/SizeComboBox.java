/*    */ package br.unioeste.jgoose.e4j.swing.toolbars;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JComboBox;
/*    */ 
/*    */ public class SizeComboBox extends JComboBox
/*    */ {
/*    */   public SizeComboBox(final BasicGraphEditor editor)
/*    */   {
/* 14 */     super(new Object[] { "6pt", "8pt", "9pt", "10pt", "12pt", "14pt", "18pt", "24pt", "30pt", "36pt", "48pt", "60pt" });
/*    */     
/*    */ 
/*    */ 
/* 18 */     setEditable(true);
/* 19 */     setMinimumSize(new Dimension(65, 0));
/* 20 */     setPreferredSize(new Dimension(65, 0));
/* 21 */     setMaximumSize(new Dimension(65, 100));
/*    */     
/* 23 */     addActionListener(new ActionListener()
/*    */     {
/*    */       public void actionPerformed(ActionEvent e) {
/* 26 */         mxGraph graph = editor.getGraphComponent().getGraph();
/* 27 */         graph.setCellStyles(com.mxgraph.util.mxConstants.STYLE_FONTSIZE, SizeComboBox.this
/* 28 */           .getSelectedItem().toString().replace("pt", ""));
/*    */       }
/*    */     });
/*    */   }
/*    */ }
