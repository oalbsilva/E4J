/*    */ package br.unioeste.jgoose.e4j.swing.toolbars;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.util.List;
/*    */ import javax.swing.JComboBox;
/*    */ 
/*    */ public class FontComboBox extends JComboBox
/*    */ {
/*    */   public FontComboBox(final BasicGraphEditor editor, List<String> fonts)
/*    */   {
/* 15 */     super(fonts.toArray());
/* 16 */     setEditable(true);
/* 17 */     setMinimumSize(new Dimension(120, 0));
/* 18 */     setPreferredSize(new Dimension(120, 0));
/* 19 */     setMaximumSize(new Dimension(120, 100));
/*    */     
/* 21 */     addActionListener(new ActionListener()
/*    */     {
/*    */       public void actionPerformed(ActionEvent e) {
/* 24 */         String font = FontComboBox.this.getSelectedItem().toString();
/*    */         
/* 26 */         if ((font != null) && (!font.equals("-"))) {
/* 27 */           mxGraph graph = editor.getGraphComponent().getGraph();
/* 28 */           graph.setCellStyles(com.mxgraph.util.mxConstants.STYLE_FONTFAMILY, font);
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */ }

