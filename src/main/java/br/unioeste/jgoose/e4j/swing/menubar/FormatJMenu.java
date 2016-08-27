/*     */ package br.unioeste.jgoose.e4j.swing.menubar;
/*     */ 
/*     */ import br.unioeste.jgoose.e4j.actions.ColorAction;
/*     */ import br.unioeste.jgoose.e4j.actions.KeyValueAction;
/*     */ import br.unioeste.jgoose.e4j.actions.PromptValueAction;
/*     */ import br.unioeste.jgoose.e4j.actions.SetLabelPositionAction;
/*     */ import br.unioeste.jgoose.e4j.actions.SetStyleAction;
/*     */ import br.unioeste.jgoose.e4j.actions.StyleAction;
/*     */ import br.unioeste.jgoose.e4j.actions.ToggleAction;
/*     */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*     */ import com.mxgraph.util.mxConstants;
/*     */ import com.mxgraph.util.mxResources;
/*     */ import javax.swing.JMenu;
/*     */ 
/*     */ public class FormatJMenu extends JMenu
/*     */ {
/*     */   public FormatJMenu(BasicGraphEditor editor)
/*     */   {
/*  19 */     super("Format");
/*     */     
/*  21 */     JMenu submenu = (JMenu)add(new JMenu(mxResources.get("background")));
/*     */     
/*  23 */     submenu.add(editor.bind(mxResources.get("fillcolor"), new ColorAction("Fillcolor", mxConstants.STYLE_FILLCOLOR), "/com/mxgraph/examples/swing/images/fillcolor.gif"));
/*     */     
/*  25 */     submenu.add(editor.bind(mxResources.get("gradient"), new ColorAction("Gradient", mxConstants.STYLE_GRADIENTCOLOR)));
/*     */     
/*  27 */     submenu.addSeparator();
/*     */     
/*  29 */     submenu.add(editor.bind(mxResources.get("image"), new PromptValueAction(mxConstants.STYLE_IMAGE, "Image")));
/*  30 */     submenu.add(editor.bind(mxResources.get("shadow"), new ToggleAction(mxConstants.STYLE_SHADOW)));
/*     */     
/*  32 */     submenu.addSeparator();
/*     */     
/*  34 */     submenu.add(editor.bind(mxResources.get("opacity"), new PromptValueAction(mxConstants.STYLE_OPACITY, "Opacity (0-100)")));
/*     */     
/*  36 */     submenu = (JMenu)add(new JMenu(mxResources.get("label")));
/*     */     
/*  38 */     submenu.add(editor.bind(mxResources.get("fontcolor"), new ColorAction("Fontcolor", mxConstants.STYLE_FONTCOLOR), "/com/mxgraph/examples/swing/images/fontcolor.gif"));
/*     */     
/*     */ 
/*  41 */     submenu.addSeparator();
/*     */     
/*  43 */     submenu.add(editor.bind(mxResources.get("labelFill"), new ColorAction("Label Fill", mxConstants.STYLE_LABEL_BACKGROUNDCOLOR)));
/*  44 */     submenu.add(editor.bind(mxResources.get("labelBorder"), new ColorAction("Label Border", mxConstants.STYLE_LABEL_BORDERCOLOR)));
/*     */     
/*  46 */     submenu.addSeparator();
/*     */     
/*  48 */     submenu.add(editor.bind(mxResources.get("rotateLabel"), new ToggleAction(mxConstants.STYLE_HORIZONTAL, true)));
/*     */     
/*  50 */     submenu.add(editor.bind(mxResources.get("textOpacity"), new PromptValueAction(mxConstants.STYLE_TEXT_OPACITY, "Opacity (0-100)")));
/*     */     
/*  52 */     submenu.addSeparator();
/*     */     
/*  54 */     JMenu subsubmenu = (JMenu)submenu.add(new JMenu(mxResources.get("position")));
/*     */     
/*  56 */     subsubmenu.add(editor.bind(mxResources.get("top"), new SetLabelPositionAction("top", "bottom")));
/*  57 */     subsubmenu.add(editor.bind(mxResources.get("middle"), new SetLabelPositionAction("middle", "middle")));
/*     */     
/*  59 */     subsubmenu.add(editor.bind(mxResources.get("bottom"), new SetLabelPositionAction("bottom", "top")));
/*     */     
/*  61 */     subsubmenu.addSeparator();
/*     */     
/*  63 */     subsubmenu.add(editor.bind(mxResources.get("left"), new SetLabelPositionAction("left", "right")));
/*  64 */     subsubmenu.add(editor.bind(mxResources.get("center"), new SetLabelPositionAction("center", "center")));
/*     */     
/*  66 */     subsubmenu.add(editor.bind(mxResources.get("right"), new SetLabelPositionAction("right", "left")));
/*     */     
/*  68 */     submenu.addSeparator();
/*     */     
/*  70 */     submenu.add(editor.bind(mxResources.get("wordWrap"), new KeyValueAction(mxConstants.STYLE_WHITE_SPACE, "wrap")));
/*  71 */     submenu.add(editor.bind(mxResources.get("noWordWrap"), new KeyValueAction(mxConstants.STYLE_WHITE_SPACE, null)));
/*     */     
/*  73 */     submenu.addSeparator();
/*     */     
/*  75 */     submenu.add(editor.bind(mxResources.get("hide"), new ToggleAction(mxConstants.STYLE_NOLABEL)));
/*     */     
/*  77 */     addSeparator();
/*     */     
/*  79 */     submenu = (JMenu)add(new JMenu(mxResources.get("line")));
/*     */     
/*  81 */     submenu.add(editor.bind(mxResources.get("linecolor"), new ColorAction("Linecolor", mxConstants.STYLE_STROKECOLOR), "/com/mxgraph/examples/swing/images/linecolor.gif"));
/*     */     
/*     */ 
/*  84 */     submenu.addSeparator();
/*     */     
/*  86 */     submenu.add(editor.bind(mxResources.get("orthogonal"), new ToggleAction(mxConstants.STYLE_ORTHOGONAL)));
/*  87 */     submenu.add(editor.bind(mxResources.get("dashed"), new ToggleAction(mxConstants.STYLE_DASHED)));
/*     */     
/*  89 */     submenu.addSeparator();
/*     */     
/*  91 */     submenu.add(editor.bind(mxResources.get("linewidth"), new PromptValueAction(mxConstants.STYLE_STROKEWIDTH, "Linewidth")));
/*     */     
/*  93 */     submenu = (JMenu)add(new JMenu(mxResources.get("connector")));
/*     */     
/*  95 */     submenu.add(editor.bind(mxResources.get("straight"), new SetStyleAction("straight"), "/com/mxgraph/examples/swing/images/straight.gif"));
/*     */     
/*     */ 
/*  98 */     submenu.add(editor.bind(mxResources.get("horizontal"), new SetStyleAction(""), "/com/mxgraph/examples/swing/images/connect.gif"));
/*  99 */     submenu.add(editor.bind(mxResources.get("vertical"), new SetStyleAction("vertical"), "/com/mxgraph/examples/swing/images/vertical.gif"));
/*     */     
/*     */ 
/* 102 */     submenu.addSeparator();
/*     */     
/* 104 */     submenu.add(editor.bind(mxResources.get("entityRelation"), new SetStyleAction("edgeStyle=mxEdgeStyle.EntityRelation"), "/com/mxgraph/examples/swing/images/entity.gif"));
/*     */     
/* 106 */     submenu.add(editor.bind(mxResources.get("arrow"), new SetStyleAction("arrow"), "/com/mxgraph/examples/swing/images/arrow.gif"));
/*     */     
/* 108 */     submenu.addSeparator();
/*     */     
/* 110 */     submenu.add(editor.bind(mxResources.get("plain"), new ToggleAction(mxConstants.STYLE_NOEDGESTYLE)));
/*     */     
/* 112 */     addSeparator();
/*     */     
/* 114 */     submenu = (JMenu)add(new JMenu(mxResources.get("linestart")));
/*     */     
/* 116 */     submenu.add(editor.bind(mxResources.get("open"), new KeyValueAction(mxConstants.STYLE_STARTARROW, "open"), "/com/mxgraph/examples/swing/images/open_start.gif"));
/*     */     
/* 118 */     submenu.add(editor.bind(mxResources.get("classic"), new KeyValueAction(mxConstants.STYLE_STARTARROW, "classic"), "/com/mxgraph/examples/swing/images/classic_start.gif"));
/*     */     
/* 120 */     submenu.add(editor.bind(mxResources.get("block"), new KeyValueAction(mxConstants.STYLE_STARTARROW, "block"), "/com/mxgraph/examples/swing/images/block_start.gif"));
/*     */     
/*     */ 
/* 123 */     submenu.addSeparator();
/*     */     
/* 125 */     submenu.add(editor.bind(mxResources.get("diamond"), new KeyValueAction(mxConstants.STYLE_STARTARROW, "diamond"), "/com/mxgraph/examples/swing/images/diamond_start.gif"));
/*     */     
/* 127 */     submenu.add(editor.bind(mxResources.get("oval"), new KeyValueAction(mxConstants.STYLE_STARTARROW, "oval"), "/com/mxgraph/examples/swing/images/oval_start.gif"));
/*     */     
/*     */ 
/* 130 */     submenu.addSeparator();
/*     */     
/* 132 */     submenu.add(editor.bind(mxResources.get("none"), new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.NONE)));
/* 133 */     submenu.add(editor.bind(mxResources.get("size"), new PromptValueAction(mxConstants.STYLE_STARTSIZE, "Linestart Size")));
/*     */     
/* 135 */     submenu = (JMenu)add(new JMenu(mxResources.get("lineend")));
/*     */     
/* 137 */     submenu.add(editor.bind(mxResources.get("open"), new KeyValueAction(mxConstants.STYLE_ENDARROW, "open"), "/com/mxgraph/examples/swing/images/open_end.gif"));
/*     */     
/* 139 */     submenu.add(editor.bind(mxResources.get("classic"), new KeyValueAction(mxConstants.STYLE_ENDARROW, "classic"), "/com/mxgraph/examples/swing/images/classic_end.gif"));
/*     */     
/* 141 */     submenu.add(editor.bind(mxResources.get("block"), new KeyValueAction(mxConstants.STYLE_ENDARROW, "block"), "/com/mxgraph/examples/swing/images/block_end.gif"));
/*     */     
/*     */ 
/* 144 */     submenu.addSeparator();
/*     */     
/* 146 */     submenu.add(editor.bind(mxResources.get("diamond"), new KeyValueAction(mxConstants.STYLE_ENDARROW, "diamond"), "/com/mxgraph/examples/swing/images/diamond_end.gif"));
/*     */     
/* 148 */     submenu.add(editor.bind(mxResources.get("oval"), new KeyValueAction(mxConstants.STYLE_ENDARROW, "oval"), "/com/mxgraph/examples/swing/images/oval_end.gif"));
/*     */     
/*     */ 
/* 151 */     submenu.addSeparator();
/*     */     
/* 153 */     submenu.add(editor.bind(mxResources.get("none"), new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.NONE)));
/* 154 */     submenu.add(editor.bind(mxResources.get("size"), new PromptValueAction(mxConstants.STYLE_ENDSIZE, "Lineend Size")));
/*     */     
/* 156 */     addSeparator();
/*     */     
/* 158 */     submenu = (JMenu)add(new JMenu(mxResources.get("alignment")));
/*     */     
/* 160 */     submenu.add(editor.bind(mxResources.get("left"), new KeyValueAction(mxConstants.STYLE_ALIGN, "left"), "/com/mxgraph/examples/swing/images/left.gif"));
/*     */     
/* 162 */     submenu.add(editor.bind(mxResources.get("center"), new KeyValueAction(mxConstants.STYLE_ALIGN, "center"), "/com/mxgraph/examples/swing/images/center.gif"));
/*     */     
/* 164 */     submenu.add(editor.bind(mxResources.get("right"), new KeyValueAction(mxConstants.STYLE_ALIGN, "right"), "/com/mxgraph/examples/swing/images/right.gif"));
/*     */     
/*     */ 
/* 167 */     submenu.addSeparator();
/*     */     
/* 169 */     submenu.add(editor.bind(mxResources.get("top"), new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, "top"), "/com/mxgraph/examples/swing/images/top.gif"));
/*     */     
/* 171 */     submenu.add(editor.bind(mxResources.get("middle"), new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, "middle"), "/com/mxgraph/examples/swing/images/middle.gif"));
/*     */     
/* 173 */     submenu.add(editor.bind(mxResources.get("bottom"), new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, "bottom"), "/com/mxgraph/examples/swing/images/bottom.gif"));
/*     */     
/*     */ 
/* 176 */     submenu = (JMenu)add(new JMenu(mxResources.get("spacing")));
/*     */     
/* 178 */     submenu.add(editor.bind(mxResources.get("top"), new PromptValueAction(mxConstants.STYLE_SPACING_TOP, "Top Spacing")));
/* 179 */     submenu.add(editor.bind(mxResources.get("right"), new PromptValueAction(mxConstants.STYLE_SPACING_RIGHT, "Right Spacing")));
/* 180 */     submenu.add(editor.bind(mxResources.get("bottom"), new PromptValueAction(mxConstants.STYLE_SPACING_BOTTOM, "Bottom Spacing")));
/* 181 */     submenu.add(editor.bind(mxResources.get("left"), new PromptValueAction(mxConstants.STYLE_SPACING_LEFT, "Left Spacing")));
/*     */     
/* 183 */     submenu.addSeparator();
/*     */     
/* 185 */     submenu.add(editor.bind(mxResources.get("global"), new PromptValueAction(mxConstants.STYLE_SPACING, "Spacing")));
/*     */     
/* 187 */     submenu.addSeparator();
/*     */     
/* 189 */     submenu.add(editor.bind(mxResources.get("sourceSpacing"), new PromptValueAction(mxConstants.STYLE_SOURCE_PERIMETER_SPACING, 
/* 190 */       mxResources.get("sourceSpacing"))));
/* 191 */     submenu.add(editor.bind(mxResources.get("targetSpacing"), new PromptValueAction(mxConstants.STYLE_TARGET_PERIMETER_SPACING, 
/* 192 */       mxResources.get("targetSpacing"))));
/*     */     
/* 194 */     submenu.addSeparator();
/*     */     
/* 196 */     submenu.add(editor.bind(mxResources.get("perimeter"), new PromptValueAction(mxConstants.STYLE_PERIMETER_SPACING, "Perimeter Spacing")));
/*     */     
/*     */ 
/* 199 */     submenu = (JMenu)add(new JMenu(mxResources.get("direction")));
/*     */     
/* 201 */     submenu.add(editor.bind(mxResources.get("north"), new KeyValueAction(mxConstants.STYLE_DIRECTION, "north")));
/* 202 */     submenu.add(editor.bind(mxResources.get("east"), new KeyValueAction(mxConstants.STYLE_DIRECTION, "east")));
/* 203 */     submenu.add(editor.bind(mxResources.get("south"), new KeyValueAction(mxConstants.STYLE_DIRECTION, "south")));
/* 204 */     submenu.add(editor.bind(mxResources.get("west"), new KeyValueAction(mxConstants.STYLE_DIRECTION, "west")));
/*     */     
/* 206 */     submenu.addSeparator();
/*     */     
/* 208 */     submenu.add(editor.bind(mxResources.get("rotation"), new PromptValueAction(mxConstants.STYLE_ROTATION, "Rotation (0-360)")));
/*     */     
/* 210 */     addSeparator();
/*     */     
/* 212 */     add(editor.bind(mxResources.get("rounded"), new ToggleAction(mxConstants.STYLE_ROUNDED)));
/*     */     
/* 214 */     add(editor.bind(mxResources.get("style"), new StyleAction()));
/*     */   }
/*     */ }
