/*     */ package br.unioeste.jgoose.e4j.swing;

/*     */
 /*     */ import com.mxgraph.swing.handler.mxCellHandler;
/*     */ import com.mxgraph.swing.handler.mxGraphTransferHandler;
/*     */ import com.mxgraph.swing.mxGraphComponent;
/*     */ import com.mxgraph.swing.util.mxGraphTransferable;
/*     */ import com.mxgraph.view.mxGraph;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Point;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.dnd.DropTarget;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableModel;

/*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public class JTableRenderer
        /*     */ extends JComponent /*     */ {

    /*     */ private static final long serialVersionUID = 2106746763664760745L;
    /*     */    public static final String IMAGE_PATH = "/com/mxgraph/examples/swing/images/";
    /*  64 */    protected static JTableRenderer dragSource = null;
    /*  65 */    protected static int sourceRow = 0;
    /*     */
 /*     */    protected Object cell;
    /*     */    protected mxGraphComponent graphContainer;
    /*     */    protected mxGraph graph;
    /*     */    public JTable table;

    /*     */
 /*     */ public JTableRenderer(final Object cell, final mxGraphComponent graphContainer) /*     */ {
        /*  74 */ this.cell = cell;
        /*  75 */ this.graphContainer = graphContainer;
        /*  76 */ this.graph = graphContainer.getGraph();
        /*  77 */ setLayout(new BorderLayout());
        /*  78 */ setBorder(BorderFactory.createCompoundBorder(
                /*  79 */ShadowBorder.getSharedInstance(),
                /*  80 */ BorderFactory.createBevelBorder(0)));
        /*     */
 /*  82 */ JPanel title = new JPanel();
        /*  83 */ title.setBackground(new Color(149, 173, 239));
        /*  84 */ title.setOpaque(true);
        /*  85 */ title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
        /*  86 */ title.setLayout(new BorderLayout());
        /*     */
 /*     */
 /*  89 */ JLabel icon = new JLabel(new ImageIcon(JTableRenderer.class.getResource("/com/mxgraph/examples/swing/images/preferences.gif")));
        /*  90 */ icon.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 1));
        /*  91 */ title.add(icon, "West");
        /*     */
 /*  93 */ JLabel label = new JLabel(String.valueOf(this.graph.getLabel(cell)));
        /*  94 */ label.setForeground(Color.WHITE);
        /*  95 */ label.setFont(title.getFont().deriveFont(1, 11.0F));
        /*  96 */ label.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
        /*  97 */ title.add(label, "Center");
        /*     */
 /*  99 */ JPanel toolBar2 = new JPanel();
        /* 100 */ toolBar2.setLayout(new FlowLayout(0, 1, 2));
        /* 101 */ toolBar2.setOpaque(false);
        /*     */
 /* 103 */ JButton button = new JButton(new AbstractAction("", new ImageIcon(JTableRenderer.class.getResource("/com/mxgraph/examples/swing/images/minimize.gif"))) /*     */ {
            /*     */ public void actionPerformed(ActionEvent e) {
                /* 105 */ JTableRenderer.this.graph.foldCells(!JTableRenderer.this.graph.isCellCollapsed(cell), false, new Object[]{cell});
                /*     */
 /* 107 */ ((JButton) e.getSource())
                        /* 108 */.setIcon(new ImageIcon(JTableRenderer.class
                                /*     */
                                /* 110 */.getResource("/com/mxgraph/examples/swing/images/" + (JTableRenderer.this.graph
                                        /* 111 */.isCellCollapsed(cell) ? "maximize.gif" : "minimize.gif"))));
                /*     */            }
            /*     */
 /* 114 */        });
        /* 115 */ button.setPreferredSize(new Dimension(16, 16));
        /* 116 */ button.setCursor(new Cursor(0));
        /* 117 */ button.setToolTipText("Collapse/Expand");
        /* 118 */ button.setOpaque(false);
        /* 119 */ toolBar2.add(button);
        /*     */
 /* 121 */ title.add(toolBar2, "East");
        /* 122 */ add(title, "North");
        /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /* 128 */ this.table = new MyTable();
        /* 129 */ JScrollPane scrollPane = new JScrollPane(this.table);
        /* 130 */ scrollPane.setCursor(new Cursor(0));
        /*     */
 /* 132 */ if (this.graph.getModel().getChildCount(cell) == 0) {
            /* 133 */ scrollPane.getViewport().setBackground(Color.WHITE);
            /* 134 */ setOpaque(true);
            /* 135 */ add(scrollPane, "Center");
            /*     */        }
        /*     */
 /* 138 */ scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() /*     */ {
            /*     */ public void adjustmentValueChanged(AdjustmentEvent e) {
                /* 141 */ graphContainer.refresh();
                /*     */            }
            /*     */
 /*     */
 /* 145 */        });
        /* 146 */ label = new JLabel(new ImageIcon(JTableRenderer.class.getResource("/com/mxgraph/examples/swing/images/resize.gif")));
        /* 147 */ label.setCursor(new Cursor(6));
        /*     */
 /* 149 */ JPanel panel = new JPanel();
        /* 150 */ panel.setLayout(new BorderLayout());
        /* 151 */ panel.add(label, "East");
        /*     */
 /* 153 */ add(panel, "South");
        /*     */
 /* 155 */ ResizeHandler resizeHandler = new ResizeHandler();
        /* 156 */ label.addMouseListener(resizeHandler);
        /* 157 */ label.addMouseMotionListener(resizeHandler);
        /*     */
 /* 159 */ setMinimumSize(new Dimension(20, 30));
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */ public class ResizeHandler
            /*     */ implements MouseListener, MouseMotionListener /*     */ {

        /*     */ protected int index;

        /*     */
 /*     */
 /*     */
 /*     */ public ResizeHandler() /*     */ {
            /* 173 */ this(7);
            /*     */        }

        /*     */
 /*     */ public ResizeHandler(int index) {
            /* 177 */ this.index = index;
            /*     */        }

        /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void mouseClicked(MouseEvent e) {
        }

        /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void mouseEntered(MouseEvent e) {
        }

        /*     */
 /*     */
 /*     */
 /*     */ public void mouseExited(MouseEvent e) {
        }

        /*     */
 /*     */
 /*     */
 /*     */ public void mousePressed(MouseEvent e) /*     */ {
            /* 198 */ if (!JTableRenderer.this.graph.isCellSelected(JTableRenderer.this.cell)) {
                /* 199 */ JTableRenderer.this.graphContainer.selectCellForEvent(JTableRenderer.this.cell, e);
                /*     */            }
            /*     */
 /*     */
 /* 203 */ mxCellHandler handler = JTableRenderer.this.graphContainer.getSelectionCellsHandler().getHandler(JTableRenderer.this.cell);
            /*     */
 /*     */
 /* 206 */ if (handler != null) /*     */ {
                /* 208 */ handler.start(SwingUtilities.convertMouseEvent(
                        /* 209 */(Component) e.getSource(), e, JTableRenderer.this.graphContainer.getGraphControl()), this.index);
                /*     */
 /* 211 */ e.consume();
                /*     */            }
            /*     */        }

        /*     */
 /*     */ public void mouseReleased(MouseEvent e) /*     */ {
            /* 217 */ JTableRenderer.this.graphContainer.getGraphControl().dispatchEvent(
                    /* 218 */SwingUtilities.convertMouseEvent((Component) e.getSource(), e, JTableRenderer.this.graphContainer
                            /* 219 */.getGraphControl()));
            /*     */        }

        /*     */
 /*     */ public void mouseDragged(MouseEvent e) /*     */ {
            /* 224 */ JTableRenderer.this.graphContainer.getGraphControl().dispatchEvent(
                    /* 225 */SwingUtilities.convertMouseEvent((Component) e.getSource(), e, JTableRenderer.this.graphContainer
                            /* 226 */.getGraphControl()));
            /*     */        }

        /*     */
 /*     */
 /*     */ public void mouseMoved(MouseEvent e) {
        }
        /*     */    }

    /*     */
 /*     */ public class MyTable
            /*     */ extends JTable
            /*     */ implements DropTargetListener /*     */ {

        /*     */ private static final long serialVersionUID = 5841175227984561071L;
        /*     */        Object[][] data;
        /* 239 */        String[] colNames = {"A", "B", "C", "D", "E"};

        /*     */
 /*     */ public MyTable() /*     */ {
            /* 243 */ this.data = new Object[30][5];
            /* 244 */ for (int i = 0; i < 30; i++) {
                /* 245 */ this.data[i][0] = new Boolean(false);
                /* 246 */ this.data[i][1] = ("Column " + i);
                /* 247 */ this.data[i][2] = (Math.random() > 0.5D ? new ImageIcon(JTableRenderer.class
                        /* 248 */.getResource("/com/mxgraph/examples/swing/images/preferences.gif")) : null);
                /*     */
 /* 250 */ this.data[i][3] = (Math.random() > 0.5D ? new ImageIcon(JTableRenderer.class
                        /* 251 */.getResource("/com/mxgraph/examples/swing/images/preferences.gif")) : null);
                /*     */
 /* 253 */ this.data[i][4] = (Math.random() > 0.5D ? new ImageIcon(JTableRenderer.class
                        /* 254 */.getResource("/com/mxgraph/examples/swing/images/preferences.gif")) : null);
                /*     */            }
            /*     */
 /* 257 */ setModel(createModel());
            /* 258 */ setTableHeader(null);
            /* 259 */ setAutoscrolls(true);
            /* 260 */ setGridColor(Color.WHITE);
            /* 261 */ TableColumn column = getColumnModel().getColumn(0);
            /* 262 */ column.setMaxWidth(20);
            /* 263 */ column = getColumnModel().getColumn(2);
            /* 264 */ column.setMaxWidth(12);
            /* 265 */ column = getColumnModel().getColumn(3);
            /* 266 */ column.setMaxWidth(12);
            /* 267 */ column = getColumnModel().getColumn(4);
            /* 268 */ column.setMaxWidth(12);
            /*     */
 /* 270 */ setTransferHandler(new TransferHandler() /*     */ {
                /*     */
 /*     */
 /*     */ public int getSourceActions(JComponent c) /*     */ {
                    /*     */
 /* 277 */ return 3;
                    /*     */                }

                /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ protected Transferable createTransferable(JComponent c) /*     */ {
                    /* 286 */ JTableRenderer.sourceRow = JTableRenderer.MyTable.this.getSelectedRow();
                    /* 287 */ JTableRenderer.dragSource = JTableRenderer.this;
                    /*     */
 /*     */
 /* 290 */ return new mxGraphTransferable(null, null, null);
                    /*     */                }
                /*     */
 /* 293 */            });
            /* 294 */ setDragEnabled(true);
            /* 295 */ setDropTarget(new DropTarget(this, 3, this));
            /*     */
 /*     */
 /* 298 */ setCursor(new Cursor(0));
            /*     */        }

        /*     */
 /*     */ public DropTarget getDropTarget() /*     */ {
            /* 303 */ if (!((mxGraphTransferHandler) JTableRenderer.this.graphContainer.getTransferHandler()).isLocalDrag()) {
                /* 304 */ return super.getDropTarget();
                /*     */            }
            /*     */
 /* 307 */ return null;
            /*     */        }

        /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void dragEnter(DropTargetDragEvent e) {
        }

        /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void dragOver(DropTargetDragEvent e) /*     */ {
            /* 325 */ if ((!((mxGraphTransferHandler) JTableRenderer.this.graphContainer.getTransferHandler()).isLocalDrag()) && (JTableRenderer.this != JTableRenderer.dragSource)) /*     */ {
                /* 327 */ Point p = e.getLocation();
                /* 328 */ int row = rowAtPoint(p);
                /* 329 */ getSelectionModel().setSelectionInterval(row, row);
                /*     */            }
            /*     */        }

        /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void dropActionChanged(DropTargetDragEvent dtde) {
        }

        /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void drop(DropTargetDropEvent e) /*     */ {
            /* 348 */ if (JTableRenderer.dragSource != null) {
                /* 349 */ e.acceptDrop(3);
                /* 350 */ Point p = e.getLocation();
                /* 351 */ int targetRow = rowAtPoint(p);
                /*     */
 /* 353 */ Object edge = JTableRenderer.this.graph.insertEdge(null, null, null, JTableRenderer.dragSource.cell, JTableRenderer.this.cell, "sourceRow=" + JTableRenderer.sourceRow + ";targetRow=" + targetRow);
                /*     */
 /*     */
 /* 356 */ JTableRenderer.this.graph.setSelectionCell(edge);
                /*     */
 /*     */
 /* 359 */ JTableRenderer.dragSource = null;
                /* 360 */ e.dropComplete(true);
                /*     */            } else {
                /* 362 */ e.rejectDrop();
                /*     */            }
            /*     */        }

        /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void dragExit(DropTargetEvent dte) {
        }

        /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public TableModel createModel() /*     */ {
            /* 380 */ return new AbstractTableModel() {
                /*     */ private static final long serialVersionUID = -3642207266816170738L;

                /*     */
 /*     */ public int getColumnCount() {
                    /* 384 */ return JTableRenderer.MyTable.this.colNames.length;
                    /*     */                }

                /*     */
 /*     */ public int getRowCount() {
                    /* 388 */ return JTableRenderer.MyTable.this.data.length;
                    /*     */                }

                /*     */
 /*     */ public String getColumnName(int col) {
                    /* 392 */ return JTableRenderer.MyTable.this.colNames[col];
                    /*     */                }

                /*     */
 /*     */ public Object getValueAt(int row, int col) {
                    /* 396 */ return JTableRenderer.MyTable.this.data[row][col];
                    /*     */                }

                /*     */
 /*     */ public Class<? extends Object> getColumnClass(int c) {
                    /* 400 */ Object value = getValueAt(0, c);
                    /* 401 */ return value != null ? value.getClass() : ImageIcon.class;
                    /*     */                }

                /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public boolean isCellEditable(int row, int col) /*     */ {
                    /* 409 */ return col == 0;
                    /*     */                }

                /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void setValueAt(Object value, int row, int col) /*     */ {
                    /* 417 */ JTableRenderer.MyTable.this.data[row][col] = value;
                    /* 418 */ fireTableCellUpdated(row, col);
                    /*     */                }
                /*     */            };
            /*     */        }
        /*     */    }

    /*     */
 /*     */ public static JTableRenderer getVertex(Component component) /*     */ {
        /* 426 */ while (component != null) {
            /* 427 */ if ((component instanceof JTableRenderer)) {
                /* 428 */ return (JTableRenderer) component;
                /*     */            }
            /* 430 */ component = component.getParent();
            /*     */        }
        /*     */
 /* 433 */ return null;
        /*     */    }
    /*     */ }
