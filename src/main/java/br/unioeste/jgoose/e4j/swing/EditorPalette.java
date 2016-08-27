/*     */ package br.unioeste.jgoose.e4j.swing;
/*     */ 
/*     */ import com.mxgraph.model.mxCell;
/*     */ import com.mxgraph.model.mxGeometry;
/*     */ import com.mxgraph.swing.util.mxGraphTransferable;
/*     */ import com.mxgraph.swing.util.mxSwingConstants;
/*     */ import com.mxgraph.util.mxEventObject;
/*     */ import com.mxgraph.util.mxEventSource;
/*     */ import com.mxgraph.util.mxEventSource.mxIEventListener;
/*     */ import com.mxgraph.util.mxPoint;
/*     */ import com.mxgraph.util.mxRectangle;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.dnd.DragGestureEvent;
/*     */ import java.awt.dnd.DragGestureListener;
/*     */ import java.awt.dnd.DragSource;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.TransferHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditorPalette
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 7771113885935187066L;
/*  44 */   protected JLabel selectedEntry = null;
/*  45 */   protected mxEventSource eventSource = new mxEventSource(this);
/*  46 */   protected Color gradientColor = new Color(117, 195, 173);
/*     */   
/*     */   public EditorPalette()
/*     */   {
/*  50 */     setBackground(new Color(149, 230, 190));
/*  51 */     setLayout(new FlowLayout(3, 5, 5));
/*     */     
/*     */ 
/*  54 */     addMouseListener(new MouseListener()
/*     */     {
/*     */       public void mousePressed(MouseEvent e) {
/*  57 */         EditorPalette.this.clearSelection();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       public void mouseClicked(MouseEvent e) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       public void mouseEntered(MouseEvent e) {}
/*     */       
/*     */ 
/*     */ 
/*     */       public void mouseExited(MouseEvent e) {}
/*     */       
/*     */ 
/*     */ 
/*     */       public void mouseReleased(MouseEvent e) {}
/*  77 */     });
/*  78 */     setTransferHandler(new TransferHandler()
/*     */     {
/*     */       public boolean canImport(JComponent comp, DataFlavor[] flavors) {
/*  81 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void setGradientColor(Color c) {
/*  87 */     this.gradientColor = c;
/*     */   }
/*     */   
/*     */   public Color getGradientColor() {
/*  91 */     return this.gradientColor;
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g)
/*     */   {
/*  96 */     if (this.gradientColor == null) {
/*  97 */       super.paintComponent(g);
/*     */     } else {
/*  99 */       Rectangle rect = getVisibleRect();
/*     */       
/* 101 */       if (g.getClipBounds() != null) {
/* 102 */         rect = rect.intersection(g.getClipBounds());
/*     */       }
/*     */       
/* 105 */       Graphics2D g2 = (Graphics2D)g;
/*     */       
/* 107 */       g2.setPaint(new GradientPaint(0.0F, 0.0F, getBackground(), getWidth(), 0.0F, this.gradientColor));
/*     */       
/* 109 */       g2.fill(rect);
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearSelection() {
/* 114 */     setSelectionEntry(null, null);
/*     */   }
/*     */   
/*     */   public void setSelectionEntry(JLabel entry, mxGraphTransferable t) {
/* 118 */     JLabel previous = this.selectedEntry;
/* 119 */     this.selectedEntry = entry;
/*     */     
/* 121 */     if (previous != null) {
/* 122 */       previous.setBorder(null);
/* 123 */       previous.setOpaque(false);
/*     */     }
/*     */     
/* 126 */     if (this.selectedEntry != null) {
/* 127 */       this.selectedEntry.setBorder(ShadowBorder.getSharedInstance());
/* 128 */       this.selectedEntry.setOpaque(true);
/*     */     }
/*     */     
/* 131 */     this.eventSource.fireEvent(new mxEventObject("select", new Object[] { "entry", this.selectedEntry, "transferable", t, "previous", previous }));
/*     */   }
/*     */   
/*     */   public void setPreferredWidth(int width)
/*     */   {
/* 136 */     int cols = Math.max(1, width / 55);
/* 137 */     setPreferredSize(new Dimension(width, 
/* 138 */       getComponentCount() * 55 / cols + 30));
/* 139 */     revalidate();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addEdgeTemplate(String name, ImageIcon icon, String style, int width, int height, Object value)
/*     */   {
/* 153 */     mxGeometry geometry = new mxGeometry(0.0D, 0.0D, width, height);
/* 154 */     geometry.setTerminalPoint(new mxPoint(0.0D, height), true);
/* 155 */     geometry.setTerminalPoint(new mxPoint(width, 0.0D), false);
/* 156 */     geometry.setRelative(true);
/*     */     
/* 158 */     mxCell cell = new mxCell(value, geometry, style);
/* 159 */     cell.setEdge(true);
/*     */     
/* 161 */     addTemplate(name, icon, cell);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addTemplate(String name, ImageIcon icon, String style, int width, int height, Object value)
/*     */   {
/* 175 */     mxCell cell = new mxCell(value, new mxGeometry(0.0D, 0.0D, width, height), style);
/*     */     
/* 177 */     cell.setVertex(true);
/*     */     
/* 179 */     addTemplate(name, icon, cell);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addTemplate(String name, ImageIcon icon, mxCell cell)
/*     */   {
/* 189 */     mxRectangle bounds = (mxGeometry)cell.getGeometry().clone();
/* 190 */     final mxGraphTransferable t = new mxGraphTransferable(new Object[] { cell }, bounds);
/*     */     
/*     */ 
/*     */ 
/* 194 */     if ((icon != null) && (
/* 195 */       (icon.getIconWidth() > 32) || (icon.getIconHeight() > 32))) {
/* 196 */       icon = new ImageIcon(icon.getImage().getScaledInstance(32, 32, 0));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 201 */     final JLabel entry = new JLabel(icon);
/* 202 */     entry.setPreferredSize(new Dimension(50, 50));
/* 203 */     entry.setBackground(getBackground().brighter());
/* 204 */     entry.setFont(new Font(entry.getFont().getFamily(), 0, 10));
/*     */     
/* 206 */     entry.setVerticalTextPosition(3);
/* 207 */     entry.setHorizontalTextPosition(0);
/* 208 */     entry.setIconTextGap(0);
/*     */     
/* 210 */     entry.setToolTipText(name);
/* 211 */     entry.setText(name);
/*     */     
/* 213 */     entry.addMouseListener(new MouseListener()
/*     */     {
/*     */       public void mousePressed(MouseEvent e) {
/* 216 */         EditorPalette.this.setSelectionEntry(entry, t);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       public void mouseClicked(MouseEvent e) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       public void mouseEntered(MouseEvent e) {}
/*     */       
/*     */ 
/*     */ 
/*     */       public void mouseExited(MouseEvent e) {}
/*     */       
/*     */ 
/*     */ 
/*     */       public void mouseReleased(MouseEvent e) {}
/* 236 */     });
/* 237 */     DragGestureListener dragGestureListener = new DragGestureListener()
/*     */     {
/*     */       public void dragGestureRecognized(DragGestureEvent e) {
/* 240 */         e.startDrag(null, mxSwingConstants.EMPTY_IMAGE, new Point(), t, null);
/*     */       }
/*     */       
/*     */ 
/* 244 */     };
/* 245 */     DragSource dragSource = new DragSource();
/* 246 */     dragSource.createDefaultDragGestureRecognizer(entry, 1, dragGestureListener);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 251 */     if (name.toLowerCase().equals("depdendency")) {
/* 252 */       setSelectionEntry(entry, t);
/*     */     }
/* 254 */     add(entry);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addListener(String eventName, mxEventSource.mxIEventListener listener)
/*     */   {
/* 264 */     this.eventSource.addListener(eventName, listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEventsEnabled()
/*     */   {
/* 272 */     return this.eventSource.isEventsEnabled();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeListener(mxEventSource.mxIEventListener listener)
/*     */   {
/* 281 */     this.eventSource.removeListener(listener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeListener(mxEventSource.mxIEventListener listener, String eventName)
/*     */   {
/* 291 */     this.eventSource.removeListener(listener, eventName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEventsEnabled(boolean eventsEnabled)
/*     */   {
/* 299 */     this.eventSource.setEventsEnabled(eventsEnabled);
/*     */   }
/*     */ }
