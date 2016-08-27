/*    */ package br.unioeste.jgoose.e4j.swing.menubar;
/*    */ 
/*    */ import br.unioeste.jgoose.e4j.actions.ScaleAction;
/*    */ import br.unioeste.jgoose.e4j.actions.TogglePropertyItem;
/*    */ import br.unioeste.jgoose.e4j.actions.ToggleRulersItem;
/*    */ import br.unioeste.jgoose.e4j.actions.ZoomPolicyAction;
/*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.swing.util.mxGraphActions;
/*    */ import com.mxgraph.util.mxPoint;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.view.mxGraph;
/*    */ import com.mxgraph.view.mxGraphView;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JMenu;
/*    */ import javax.swing.JMenuItem;
/*    */ 
/*    */ public class ViewJMenu extends JMenu
/*    */ {
/*    */   public ViewJMenu(final BasicGraphEditor editor)
/*    */   {
/* 23 */     super("View");
/* 24 */     final mxGraphComponent graphComponent = editor.getGraphComponent();
/*    */     
/* 26 */     JMenuItem item = add(new TogglePropertyItem(graphComponent, mxResources.get("pageLayout"), "PageVisible", true, new ActionListener()
/*    */     {
/*    */       public void actionPerformed(ActionEvent e)
/*    */       {
/* 30 */         if ((graphComponent.isPageVisible()) && (graphComponent.isCenterPage())) {
/* 31 */           graphComponent.zoomAndCenter();
/*    */         } else {
/* 33 */           graphComponent.getGraphControl().updatePreferredSize();
/*    */         }
/*    */         
/*    */       }
/*    */       
/* 38 */     }));
/* 39 */     item.addActionListener(new ActionListener()
/*    */     {
/*    */       public void actionPerformed(ActionEvent e) {
/* 42 */         if ((e.getSource() instanceof TogglePropertyItem)) {
/* 43 */           final mxGraphComponent graphComponent = editor.getGraphComponent();
/* 44 */           TogglePropertyItem toggleItem = (TogglePropertyItem)e.getSource();
/*    */           
/* 46 */           if (toggleItem.isSelected())
/*    */           {
/* 48 */             javax.swing.SwingUtilities.invokeLater(new Runnable()
/*    */             {
/*    */               public void run() {
/* 51 */                 graphComponent.scrollToCenter(true);
/* 52 */                 graphComponent.scrollToCenter(false);
/*    */               }
/*    */             });
/*    */           }
/*    */           else {
/* 57 */             mxPoint tr = graphComponent.getGraph().getView().getTranslate();
/*    */             
/* 59 */             if ((tr.getX() != 0.0D) || (tr.getY() != 0.0D)) {
/* 60 */               graphComponent.getGraph().getView().setTranslate(new mxPoint());
/*    */             }
/*    */             
/*    */           }
/*    */         }
/*    */       }
/* 66 */     });
/* 67 */     add(new TogglePropertyItem(graphComponent, mxResources.get("antialias"), "AntiAlias", true));
/*    */     
/* 69 */     addSeparator();
/* 70 */     add(new br.unioeste.jgoose.e4j.actions.ToggleGridItem(editor, mxResources.get("grid")));
/* 71 */     add(new ToggleRulersItem(editor, mxResources.get("rulers")));
/*    */     
/* 73 */     addSeparator();
/*    */     
/* 75 */     JMenu submenu = (JMenu)add(new JMenu(mxResources.get("zoom")));
/*    */     
/* 77 */     submenu.add(editor.bind("400%", new ScaleAction(4.0D)));
/* 78 */     submenu.add(editor.bind("200%", new ScaleAction(2.0D)));
/* 79 */     submenu.add(editor.bind("150%", new ScaleAction(1.5D)));
/* 80 */     submenu.add(editor.bind("100%", new ScaleAction(1.0D)));
/* 81 */     submenu.add(editor.bind("75%", new ScaleAction(0.75D)));
/* 82 */     submenu.add(editor.bind("50%", new ScaleAction(0.5D)));
/*    */     
/* 84 */     submenu.addSeparator();
/* 85 */     submenu.add(editor.bind(mxResources.get("custom"), new ScaleAction(0.0D)));
/*    */     
/* 87 */     addSeparator();
/* 88 */     add(editor.bind(mxResources.get("zoomIn"), mxGraphActions.getZoomInAction()));
/* 89 */     add(editor.bind(mxResources.get("zoomOut"), mxGraphActions.getZoomOutAction()));
/*    */     
/* 91 */     addSeparator();
/* 92 */     add(editor.bind(mxResources.get("page"), new ZoomPolicyAction(1)));
/* 93 */     add(editor.bind(mxResources.get("width"), new ZoomPolicyAction(2)));
/*    */     
/* 95 */     addSeparator();
/* 96 */     add(editor.bind(mxResources.get("actualSize"), mxGraphActions.getZoomActualAction()));
/*    */   }
/*    */ }

