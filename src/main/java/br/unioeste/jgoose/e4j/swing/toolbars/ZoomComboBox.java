/*    */ package br.unioeste.jgoose.e4j.swing.toolbars;

/*    */
 /*    */ import br.unioeste.jgoose.e4j.swing.BasicGraphEditor;
/*    */ import com.mxgraph.swing.mxGraphComponent;
/*    */ import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
/*    */ import com.mxgraph.util.mxResources;
/*    */ import com.mxgraph.view.mxGraphView;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JOptionPane;

/*    */
 /*    */
 /*    */ public class ZoomComboBox
        /*    */ extends JComboBox /*    */ {

    /* 19 */ private boolean ignoreZoomChange = false;
    /*    */    private final mxGraphView view;

    /*    */
 /*    */ public ZoomComboBox(mxGraphView view, final BasicGraphEditor editor) {
        /* 23 */ super(new Object[]{"400%", "200%", "150%", "100%", "75%", "50%",
            /*    */
            /* 25 */ mxResources.get("page"),
            /* 26 */ mxResources.get("width"),
            /* 27 */ mxResources.get("actualSize")});
        /*    */
 /*    */
 /* 30 */ this.view = view;
        /*    */
 /* 32 */ setEditable(true);
        /* 33 */ setMinimumSize(new Dimension(75, 0));
        /* 34 */ setPreferredSize(new Dimension(75, 0));
        /* 35 */ setMaximumSize(new Dimension(75, 100));
        /* 36 */ setMaximumRowCount(9);
        /*    */
 /* 38 */ addActionListener(new ActionListener() /*    */ {
            /*    */ public void actionPerformed(ActionEvent e) {
                /* 41 */ mxGraphComponent graphComponent = editor.getGraphComponent();
                /*    */
 /*    */
 /*    */
 /* 45 */ if (!ZoomComboBox.this.ignoreZoomChange) {
                    /* 46 */ String zoom = ZoomComboBox.this.getSelectedItem().toString();
                    /*    */
 /* 48 */ if (zoom.equals(mxResources.get("page"))) {
                        /* 49 */ graphComponent.setPageVisible(true);
                        /* 50 */ graphComponent
                                /* 51 */.setZoomPolicy(1);
                        /* 52 */                    } else if (zoom.equals(mxResources.get("width"))) {
                        /* 53 */ graphComponent.setPageVisible(true);
                        /* 54 */ graphComponent
                                /* 55 */.setZoomPolicy(2);
                        /* 56 */                    } else if (zoom.equals(mxResources.get("actualSize"))) {
                        /* 57 */ graphComponent.zoomActual();
                        /*    */                    } else {
                        /*    */ try {
                            /* 60 */ zoom = zoom.replace("%", "");
                            /* 61 */ double scale = Math.min(16.0D, Math.max(0.01D,
                                    /* 62 */ Double.parseDouble(zoom) / 100.0D));
                            /* 63 */ graphComponent.zoomTo(scale, graphComponent
                                    /* 64 */.isCenterZoom());
                            /*    */                        } catch (Exception ex) {
                            /* 66 */ JOptionPane.showMessageDialog(editor, ex
                                    /* 67 */.getMessage());
                            /*    */                        }
                        /*    */                    }
                    /*    */                }
                /*    */            }
            /*    */        });
        /*    */    }

    /*    */
 /*    */ public mxEventSource.mxIEventListener getScaleTracker() {
        /* 76 */ return new mxEventSource.mxIEventListener() /*    */ {
            /*    */ public void invoke(Object sender, mxEventObject evt) {
                /* 79 */ ZoomComboBox.this.ignoreZoomChange = true;
                /*    */ try /*    */ {
                    /* 82 */ Object sel = (int) Math.round(100.0D * ZoomComboBox.this.view.getScale()) + "%";
                    /* 83 */ ZoomComboBox.this.setSelectedItem(sel);
                    /*    */                } finally {
                    /* 85 */ ZoomComboBox.this.ignoreZoomChange = false;
                    /*    */                }
                /*    */            }
            /*    */        };
        /*    */    }
    /*    */ }
