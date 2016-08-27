/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import java.awt.Component;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.swing.AbstractAction;
/*    */ 
/*    */ public class PromptPropertyAction extends AbstractAction
/*    */ {
/*    */   protected Object target;
/*    */   protected String fieldname;
/*    */   protected String message;
/*    */   
/*    */   public PromptPropertyAction(Object target, String message)
/*    */   {
/* 17 */     this(target, message, message);
/*    */   }
/*    */   
/*    */   public PromptPropertyAction(Object target, String message, String fieldname) {
/* 21 */     this.target = target;
/* 22 */     this.message = message;
/* 23 */     this.fieldname = fieldname;
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 27 */     if ((e.getSource() instanceof Component)) {
/*    */       try {
/* 29 */         Method getter = this.target.getClass().getMethod("get" + this.fieldname, new Class[0]);
/* 30 */         Object current = getter.invoke(this.target, new Object[0]);
/*    */         
/* 32 */         if ((current instanceof Integer)) {
/* 33 */           Method setter = this.target.getClass().getMethod("set" + this.fieldname, new Class[] { Integer.TYPE });
/* 34 */           String value = (String)javax.swing.JOptionPane.showInputDialog((Component)e.getSource(), "Value", this.message, -1, null, null, current);
/* 35 */           if (value != null) {
/* 36 */             setter.invoke(this.target, new Object[] { Integer.valueOf(Integer.parseInt(value)) });
/*    */           }
/*    */         }
/*    */       } catch (Exception ex) {
/* 40 */         ex.printStackTrace();
/*    */       }
/*    */     }
/*    */     
/* 44 */     if ((e.getSource() instanceof mxGraphComponent)) {
/* 45 */       mxGraphComponent graphComponent = (mxGraphComponent)e.getSource();
/* 46 */       graphComponent.repaint();
/*    */     }
/*    */   }
/*    */ }
