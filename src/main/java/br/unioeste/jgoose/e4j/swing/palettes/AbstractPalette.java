/*    */ package br.unioeste.jgoose.e4j.swing.palettes;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.EditorPalette;
/*    */ import java.awt.event.ComponentAdapter;
/*    */ import java.awt.event.ComponentEvent;
/*    */ import javax.swing.JScrollBar;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTabbedPane;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractPalette
/*    */   extends EditorPalette
/*    */ {
/*    */   protected final JScrollPane scrollPane;
/*    */   
/*    */   public AbstractPalette(String name, JTabbedPane libraryPane)
/*    */   {
/* 18 */     this.scrollPane = new JScrollPane(this);
/* 19 */     this.scrollPane.setVerticalScrollBarPolicy(22);
/* 20 */     this.scrollPane.setHorizontalScrollBarPolicy(31);
/*    */     
/* 22 */     libraryPane.add(name, this.scrollPane);
/*    */     
/*    */ 
/* 25 */     libraryPane.addComponentListener(new ComponentAdapter()
/*    */     {
/*    */       public void componentResized(ComponentEvent e) {
/* 28 */         int w = AbstractPalette.this.scrollPane.getWidth() - AbstractPalette.this.scrollPane.getVerticalScrollBar().getWidth();
/* 29 */         AbstractPalette.this.setPreferredWidth(w);
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   public JScrollPane getScrollPane()
/*    */   {
/* 36 */     return this.scrollPane;
/*    */   }
/*    */ }

