/*     */ package br.unioeste.jgoose.e4j.swing.menubar;
/*     */ 
/*     */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ 
/*     */ public class EditorMenuBar
/*     */   extends JMenuBar
/*     */ {
/*     */   private static final long serialVersionUID = 4060203894740766714L;
/*     */   private JMenu fileMenu;
/*     */   private JMenu editMenu;
/*     */   private JMenu viewMenu;
/*     */   private JMenu formatMenu;
/*     */   private JMenu shapeMenu;
/*     */   private JMenu diagramMenu;
/*     */   private JMenu optionsMenu;
/*     */   private JMenu helpMenu;
/*     */   
/*     */   public EditorMenuBar(BasicGraphEditor editor)
/*     */   {
/*  22 */     this.fileMenu = new FileJMenu(editor);
/*  23 */     add(this.fileMenu);
/*     */     
/*     */ 
/*  26 */     this.editMenu = new EditJMenu(editor);
/*  27 */     add(this.editMenu);
/*     */     
/*     */ 
/*  30 */     this.viewMenu = new ViewJMenu(editor);
/*  31 */     add(this.viewMenu);
/*     */     
/*     */ 
/*  34 */     this.formatMenu = new FormatJMenu(editor);
/*  35 */     add(this.formatMenu);
/*     */     
/*     */ 
/*  38 */     this.shapeMenu = new ShapeJMenu(editor);
/*  39 */     add(this.shapeMenu);
/*     */     
/*     */ 
/*  42 */     this.diagramMenu = new DiagramJMenu(editor);
/*  43 */     add(this.diagramMenu);
/*     */     
/*     */ 
/*  46 */     this.optionsMenu = new OptionsJMenu(editor);
/*  47 */     add(this.optionsMenu);
/*     */     
/*     */ 
/*  50 */     this.helpMenu = new HelpJMenu(editor);
/*  51 */     add(this.helpMenu);
/*     */   }
/*     */   
/*     */   public JMenu getFileMenu() {
/*  55 */     return this.fileMenu;
/*     */   }
/*     */   
/*     */   public void setFileMenu(JMenu fileMenu) {
/*  59 */     this.fileMenu = fileMenu;
/*     */   }
/*     */   
/*     */   public JMenu getEditMenu() {
/*  63 */     return this.editMenu;
/*     */   }
/*     */   
/*     */   public void setEditMenu(JMenu editMenu) {
/*  67 */     this.editMenu = editMenu;
/*     */   }
/*     */   
/*     */   public JMenu getViewMenu() {
/*  71 */     return this.viewMenu;
/*     */   }
/*     */   
/*     */   public void setViewMenu(JMenu viewMenu) {
/*  75 */     this.viewMenu = viewMenu;
/*     */   }
/*     */   
/*     */   public JMenu getFormatMenu() {
/*  79 */     return this.formatMenu;
/*     */   }
/*     */   
/*     */   public void setFormatMenu(JMenu formatMenu) {
/*  83 */     this.formatMenu = formatMenu;
/*     */   }
/*     */   
/*     */   public JMenu getShapeMenu() {
/*  87 */     return this.shapeMenu;
/*     */   }
/*     */   
/*     */   public void setShapeMenu(JMenu shapeMenu) {
/*  91 */     this.shapeMenu = shapeMenu;
/*     */   }
/*     */   
/*     */   public JMenu getDiagramMenu() {
/*  95 */     return this.diagramMenu;
/*     */   }
/*     */   
/*     */   public void setDiagramMenu(JMenu diagramMenu) {
/*  99 */     this.diagramMenu = diagramMenu;
/*     */   }
/*     */   
/*     */   public JMenu getOptionsMenu() {
/* 103 */     return this.optionsMenu;
/*     */   }
/*     */   
/*     */   public void setOptionsMenu(JMenu optionsMenu) {
/* 107 */     this.optionsMenu = optionsMenu;
/*     */   }
/*     */ }

