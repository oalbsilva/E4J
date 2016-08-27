/*    */ package br.unioeste.jgoose.e4j.actions;
/*    */ 
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.beans.PropertyChangeEvent;
/*    */ import java.beans.PropertyChangeListener;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.swing.JCheckBoxMenuItem;
/*    */ 
/*    */ public class TogglePropertyItem extends JCheckBoxMenuItem
/*    */ {
/*    */   public TogglePropertyItem(Object target, String name, String fieldname)
/*    */   {
/* 16 */     this(target, name, fieldname, false);
/*    */   }
/*    */   
/*    */   public TogglePropertyItem(Object target, String name, String fieldname, boolean refresh) {
/* 20 */     this(target, name, fieldname, refresh, null);
/*    */   }
/*    */   
/*    */   public TogglePropertyItem(final Object target, String name, final String fieldname, final boolean refresh, ActionListener listener) {
/* 24 */     super(name);
/*    */     
/*    */ 
/* 27 */     if (listener != null) {
/* 28 */       addActionListener(listener);
/*    */     }
/* 30 */     addActionListener(new ActionListener() {
/*    */       public void actionPerformed(ActionEvent e) {
/* 32 */         TogglePropertyItem.this.execute(target, fieldname, refresh);
/*    */       }
/* 34 */     });
/* 35 */     PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
/*    */     {
/*    */ 
/*    */       public void propertyChange(PropertyChangeEvent evt)
/*    */       {
/*    */ 
/* 41 */         if (evt.getPropertyName().equalsIgnoreCase(fieldname)) {
/* 42 */           TogglePropertyItem.this.update(target, fieldname);
/*    */         }
/*    */       }
/*    */     };
/* 46 */     if ((target instanceof mxGraphComponent)) {
/* 47 */       ((mxGraphComponent)target).addPropertyChangeListener(propertyChangeListener);
/* 48 */     } else if ((target instanceof mxGraph)) {
/* 49 */       ((mxGraph)target).addPropertyChangeListener(propertyChangeListener);
/*    */     }
/* 51 */     update(target, fieldname);
/*    */   }
/*    */   
/*    */   public void update(Object target, String fieldname) {
/* 55 */     if ((target != null) && (fieldname != null)) {
/*    */       try {
/* 57 */         Method getter = target.getClass().getMethod("is" + fieldname, new Class[0]);
/* 58 */         if (getter != null) {
/* 59 */           Object current = getter.invoke(target, new Object[0]);
/* 60 */           if ((current instanceof Boolean)) {
/* 61 */             setSelected(((Boolean)current).booleanValue());
/*    */           }
/*    */         }
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public void execute(Object target, String fieldname, boolean refresh)
/*    */   {
/* 71 */     if ((target != null) && (fieldname != null)) {
/*    */       try {
/* 73 */         Method getter = target.getClass().getMethod("is" + fieldname, new Class[0]);
/* 74 */         Method setter = target.getClass().getMethod("set" + fieldname, new Class[] { Boolean.TYPE });
/* 75 */         Object current = getter.invoke(target, new Object[0]);
/* 76 */         if ((current instanceof Boolean)) {
/* 77 */           boolean value = !((Boolean)current).booleanValue();
/* 78 */           setter.invoke(target, new Object[] { Boolean.valueOf(value) });
/* 79 */           setSelected(value);
/*    */         }
/* 81 */         if (refresh) {
/* 82 */           mxGraph graph = null;
/* 83 */           if ((target instanceof mxGraph)) {
/* 84 */             graph = (mxGraph)target;
/* 85 */           } else if ((target instanceof mxGraphComponent)) {
/* 86 */             graph = ((mxGraphComponent)target).getGraph();
/*    */           }
/* 88 */           graph.refresh();
/*    */         }
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/*    */   }
/*    */ }

