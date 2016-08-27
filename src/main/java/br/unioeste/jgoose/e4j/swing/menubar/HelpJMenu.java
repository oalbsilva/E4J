/*    */ package br.unioeste.jgoose.e4j.swing.menubar;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JMenu;
/*    */ import javax.swing.JMenuItem;
/*    */ 
/*    */ public class HelpJMenu
/*    */   extends JMenu
/*    */ {
/*    */   public HelpJMenu(final BasicGraphEditor editor)
/*    */   {
/* 14 */     super("Help");
/*    */     
/* 16 */     JMenuItem item = add(new JMenuItem("About E4J"));
/* 17 */     item.addActionListener(new ActionListener()
/*    */     {
/*    */       public void actionPerformed(ActionEvent e) {
/* 20 */         editor.about();
/*    */       }
/*    */     });
/*    */   }
/*    */ }

