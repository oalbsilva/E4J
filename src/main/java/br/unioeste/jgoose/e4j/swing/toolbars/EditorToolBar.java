/*     */ package br.unioeste.jgoose.e4j.swing.toolbars;

/*     */
 /*     */ import br.unioeste.jgoose.e4j.actions.AlignCellsAction;
/*     */ import br.unioeste.jgoose.e4j.actions.ColorAction;
/*     */ import br.unioeste.jgoose.e4j.actions.FontStyleAction;
/*     */ import br.unioeste.jgoose.e4j.actions.HistoryAction;
/*     */ import br.unioeste.jgoose.e4j.actions.KeyValueAction;
/*     */ import br.unioeste.jgoose.e4j.actions.NewAction;
/*     */ import br.unioeste.jgoose.e4j.actions.OpenAction;
/*     */ import br.unioeste.jgoose.e4j.actions.PrintAction;
/*     */ import br.unioeste.jgoose.e4j.actions.SaveAction;
/*     */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*     */ import com.mxgraph.swing.util.mxGraphActions;
/*     */ import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEventSource;
/*     */ import com.mxgraph.util.mxResources;
/*     */ import com.mxgraph.view.mxGraphView;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.TransferHandler;

/*     */
 /*     */
 /*     */
 /*     */
 /*     */ public class EditorToolBar
        /*     */ extends JToolBar /*     */ {

    /*     */ private static final long serialVersionUID = -8015443128436394471L;

    /*     */
 /*     */ public EditorToolBar(BasicGraphEditor editor, int orientation) /*     */ {
        /*  40 */ super(orientation);
        /*  41 */ setBorder(BorderFactory.createCompoundBorder(
                /*  42 */BorderFactory.createEmptyBorder(3, 3, 3, 3), getBorder()));
        /*  43 */ setFloatable(false);
        /*     */
 /*  45 */ add(editor.bind("New", new NewAction(), "/com/mxgraph/examples/swing/images/new.gif")).setToolTipText("New");
        /*  46 */ add(editor.bind("Open", new OpenAction(), "/com/mxgraph/examples/swing/images/open.gif")).setToolTipText("Open");
        /*  47 */ add(editor.bind("Save", new SaveAction(false), "/com/mxgraph/examples/swing/images/save.gif")).setToolTipText("Save");
        /*     */
 /*  49 */ addSeparator();
        /*  50 */ add(editor.bind("Print", new PrintAction(), "/com/mxgraph/examples/swing/images/print.gif")).setToolTipText("Print");
        /*     */
 /*  52 */ addSeparator();
        /*  53 */ add(editor.bind("Cut", TransferHandler.getCutAction(), "/com/mxgraph/examples/swing/images/cut.gif")).setToolTipText("Cut");
        /*  54 */ add(editor.bind("Copy", TransferHandler.getCopyAction(), "/com/mxgraph/examples/swing/images/copy.gif")).setToolTipText("Copy");
        /*  55 */ add(editor.bind("Paste", TransferHandler.getPasteAction(), "/com/mxgraph/examples/swing/images/paste.gif")).setToolTipText("Paste");
        /*     */
 /*  57 */ addSeparator();
        /*  58 */ add(editor.bind("Delete", mxGraphActions.getDeleteAction(), "/com/mxgraph/examples/swing/images/delete.gif")).setToolTipText("Delete");
        /*     */
 /*  60 */ addSeparator();
        /*  61 */ add(editor.bind("Undo", new HistoryAction(true), "/com/mxgraph/examples/swing/images/undo.gif")).setToolTipText("Undo");
        /*  62 */ add(editor.bind("Redo", new HistoryAction(false), "/com/mxgraph/examples/swing/images/redo.gif")).setToolTipText("Redo");
        /*     */
 /*  64 */ addSeparator();
        /*     */
 /*     */
 /*     */
 /*  68 */ GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        /*     */
 /*  70 */ List<String> fonts = new ArrayList();
        /*  71 */ fonts.addAll(Arrays.asList(new String[]{"Helvetica", "Verdana", "Times New Roman", "Garamond", "Courier New", "-"}));
        /*     */
 /*  73 */ fonts.addAll(Arrays.asList(env.getAvailableFontFamilyNames()));
        /*     */
 /*  75 */ JComboBox fontCombo = new FontComboBox(editor, fonts);
        /*  76 */ add(fontCombo);
        /*     */
 /*  78 */ JComboBox sizeCombo = new SizeComboBox(editor);
        /*  79 */ add(sizeCombo);
        /*     */
 /*  81 */ addSeparator();
        /*  82 */ add(editor.bind("Bold", new FontStyleAction(true), "/com/mxgraph/examples/swing/images/bold.gif")).setToolTipText("Bold");
        /*  83 */ add(editor.bind("Italic", new FontStyleAction(false), "/com/mxgraph/examples/swing/images/italic.gif")).setToolTipText("Italic");
        /*     */
 /*  85 */ addSeparator();
        /*  86 */ add(editor.bind("Left", new KeyValueAction(mxConstants.STYLE_ALIGN, "left"), "/com/mxgraph/examples/swing/images/left.gif")).setToolTipText("Left");
        /*  87 */ add(editor.bind("Center", new KeyValueAction(mxConstants.STYLE_ALIGN, "center"), "/com/mxgraph/examples/swing/images/center.gif")).setToolTipText("Center");
        /*  88 */ add(editor.bind("Right", new KeyValueAction(mxConstants.STYLE_ALIGN, "right"), "/com/mxgraph/examples/swing/images/right.gif")).setToolTipText("Right");
        /*     */
 /*  90 */ addSeparator();
        /*  91 */ add(editor.bind("Top", new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, "top"), "/com/mxgraph/examples/swing/images/top.gif")).setToolTipText("Top");
        /*  92 */ add(editor.bind("Middle", new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, "middle"), "/com/mxgraph/examples/swing/images/middle.gif")).setToolTipText("Middle");
        /*  93 */ add(editor.bind("Bottom", new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, "bottom"), "/com/mxgraph/examples/swing/images/bottom.gif")).setToolTipText("Bottom");
        /*     */
 /*  95 */ addSeparator();
        /*  96 */ add(editor.bind("Font", new ColorAction("Font", mxConstants.STYLE_FONTCOLOR), "/com/mxgraph/examples/swing/images/fontcolor.gif")).setToolTipText("Font");
        /*  97 */ add(editor.bind("Stroke", new ColorAction("Stroke", mxConstants.STYLE_STROKECOLOR), "/com/mxgraph/examples/swing/images/linecolor.gif")).setToolTipText("Stroke");
        /*  98 */ add(editor.bind("Fill", new ColorAction("Fill", mxConstants.STYLE_FILLCOLOR), "/com/mxgraph/examples/swing/images/fillcolor.gif")).setToolTipText("Fill");
        /*     */
 /* 100 */ addSeparator();
        /*     */
 /*     */
 /* 103 */ add(editor.bind(mxResources.get("left"), new AlignCellsAction("left"), "/com/mxgraph/examples/swing/images/alignleft.gif"))
                /* 104 */.setToolTipText("Align Left");
        /* 105 */ add(editor.bind(mxResources.get("center"), new AlignCellsAction("center"), "/com/mxgraph/examples/swing/images/aligncenter.gif"))
                /* 106 */.setToolTipText("Align Center");
        /* 107 */ add(editor.bind(mxResources.get("right"), new AlignCellsAction("right"), "/com/mxgraph/examples/swing/images/alignright.gif"))
                /* 108 */.setToolTipText("Align Right");
        /*     */
 /* 110 */ addSeparator();
        /*     */
 /* 112 */ add(editor.bind(mxResources.get("top"), new AlignCellsAction("top"), "/com/mxgraph/examples/swing/images/aligntop.gif"))
                /* 113 */.setToolTipText("Align Top");
        /* 114 */ add(editor.bind(mxResources.get("middle"), new AlignCellsAction("middle"), "/com/mxgraph/examples/swing/images/alignmiddle.gif"))
                /* 115 */.setToolTipText("Align Middle");
        /* 116 */ add(editor.bind(mxResources.get("bottom"), new AlignCellsAction("bottom"), "/com/mxgraph/examples/swing/images/alignbottom.gif"))
                /* 117 */.setToolTipText("Align Bottom");
        /*     */
 /* 119 */ addSeparator();
        /*     */
 /* 121 */ mxGraphView view = editor.getGraphComponent().getGraph().getView();
        /*     */
 /* 123 */ JComboBox zoomCombo = new ZoomComboBox(view, editor);
        /* 124 */ add(zoomCombo);
        /*     */
 /*     */
 /* 127 */ mxEventSource.mxIEventListener scaleTracker = ((ZoomComboBox) zoomCombo).getScaleTracker();
        /*     */
 /*     */
 /*     */
 /* 131 */ view.getGraph().getView().addListener("scale", scaleTracker);
        /* 132 */ view.getGraph().getView().addListener("scaleAndTranslate", scaleTracker);
        /*     */
 /*     */
 /* 135 */ scaleTracker.invoke(null, null);
        /*     */    }
    /*     */ }
